package fr.golderpotato.ac.rabbit.packet.packets;

import fr.golderpotato.ac.rabbit.packet.RabbitPacket;
import fr.golderpotato.ac.utils.BungeeUtils;
import org.json.simple.JSONObject;

import java.util.UUID;

/**
 * Created by Eliaz on 22/08/2017.
 */
public class RabbitPacketBroadCast implements RabbitPacket{

    private String player;
    private String reason;

    public RabbitPacketBroadCast(){

    }

    public RabbitPacketBroadCast(String player, String reason){
        this.player = player;
        this.reason = reason;
    }

    @Override
    public void receive(JSONObject object) {
        String player = (String)object.get("player");
        String reason = (String)object.get("reason");
        BungeeUtils.broadcast(player, reason);
    }

    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("destination", "BUNGEE");
        object.put("player", player);
        object.put("reason", reason);
        return object;
    }


}
