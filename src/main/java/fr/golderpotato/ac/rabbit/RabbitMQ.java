package fr.golderpotato.ac.rabbit;

import java.io.IOException;

import com.rabbitmq.client.*;

public class RabbitMQ {

    private static RabbitMQ instance;
    private Connection connection;

    public RabbitMQ() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("eliaz");
        factory.setPassword("eliaz");

        try {
            connection = factory.newConnection();
            instance = this;
        } catch (Exception e) {
            e.printStackTrace();
        }
        createTopic("golderac");
    }

    public Connection getConnection() {
        return this.connection;
    }

    public Channel getTopic(String name) {
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(name, "fanout");
            return channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Channel createTopic(String name) {
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(name, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, name, "");
            return channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void publish(String topic, String route, String bs) {
        Channel channel = getTopic(topic);
        if (channel != null) {
            try {
                channel.basicPublish(topic, route, null, bs.getBytes());
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void publish(String topic, String route, byte[] bs) {
        Channel channel = getTopic(topic);
        if (channel != null) {
            try {
                channel.basicPublish(topic, route, null, bs);
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void subscribe(final ISubscriber subscriber, String topic) {
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(topic, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, topic, "");
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    subscriber.getMessage(envelope.getRoutingKey(), new String(body, "UTF-8"));
                }
            };
            channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection.isOpen()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RabbitMQ getInstance() {
        return instance == null ? new RabbitMQ() : instance;
    }

    public interface ISubscriber {
        void getMessage(String route, String message);
    }
}