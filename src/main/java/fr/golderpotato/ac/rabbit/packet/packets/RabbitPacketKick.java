package fr.golderpotato.ac.rabbit.packet.packets;

import fr.golderpotato.ac.rabbit.packet.RabbitPacket;
import fr.golderpotato.ac.utils.BungeeUtils;
import org.json.simple.JSONObject;

import java.util.UUID;

/**
 * Created by Eliaz on 22/08/2017.
 */
public class RabbitPacketKick implements RabbitPacket{

    private UUID player;
    private String reason;
    private String args;

    public RabbitPacketKick(){

    }

    public RabbitPacketKick(UUID player, String reason){
        this.player = player;
        this.reason = reason;
    }

    public RabbitPacketKick(UUID player, String reason, String args){
        this.player = player;
        this.reason = reason;
        this.args = args;
    }

    @Override
    public void receive(JSONObject object) {
        String player = (String)object.get("uuid");
        UUID uuid = UUID.fromString(player);
        String reason = (String)object.get("reason");
        if(!args.equals("")){
            reason+=" ("+args+")";
        }
        BungeeUtils.kick(uuid, reason);
    }

    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("destination", "BUNGEE");
        object.put("uuid", player);
        object.put("reason", reason);
        object.put("args", args);
        return object;
    }

}
