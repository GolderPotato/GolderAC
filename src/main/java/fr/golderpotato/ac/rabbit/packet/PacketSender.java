package fr.golderpotato.ac.rabbit.packet;

import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.rabbit.RabbitMQ;
import org.json.simple.JSONObject;

/**
 * Created by Eliaz on 19/08/2017.
 */
public class PacketSender {

    public static void sendPacket(RabbitPacket packet, PacketList packetList){
        JSONObject object = packet.serialize();
        object.put("id", (Integer)packetList.getID());
        RabbitMQ.getInstance().publish("golderac", "null", object.toJSONString());
    }

}
