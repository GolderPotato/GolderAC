package fr.golderpotato.ac.rabbit.packet;

import fr.golderpotato.ac.rabbit.RabbitMQ;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omg.CORBA.LongHolder;

/**
 * Created by Eliaz on 19/08/2017.
 */
public class PacketListener implements RabbitMQ.ISubscriber{

    private final PacketDestination destination;

    public PacketListener(final PacketDestination destination){
        this.destination = destination;
        RabbitMQ.getInstance().subscribe(this, "golderac");
    }

    public void getMessage(String route, String message){
        JSONParser parser = new JSONParser();

        JSONObject object = null;
        try{
            object = (JSONObject) parser.parse(message);
        }catch (Exception e){
            e.printStackTrace();
        }

        PacketDestination dest = PacketDestination.valueOf((String)object.get("destination"));
        if(!dest.equals(this.destination))return;
        Integer id = new Integer(((Long)object.get("id")).intValue());

        RabbitPacket packet = null;
        try{
            packet = PacketList.getPacketByID(id).getClass().newInstance();
            packet.receive(object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
