package fr.golderpotato.ac.rabbit.packet.packets;

import fr.golderpotato.ac.rabbit.packet.RabbitPacket;
import fr.golderpotato.ac.utils.BungeeUtils;
import org.json.simple.JSONObject;

import java.util.UUID;

/**
 * Created by Eliaz on 19/08/2017.
 */
public class RabbitPacketAlert implements RabbitPacket{

    private UUID player;
    private String cheattype;
    private String args;

    public RabbitPacketAlert(){

    }

    public RabbitPacketAlert(final String uuid, final String cheattype){
        this.player = UUID.fromString(uuid);
        this.cheattype = cheattype;
    }

    public RabbitPacketAlert(final String uuid, final String cheattype, final String args){
        this.player = UUID.fromString(uuid);
        this.cheattype = cheattype;
        this.args = args;
    }

    @Override
    public void receive(JSONObject object) {
        String player = (String)object.get("uuid");
        UUID uuid = UUID.fromString(player);
        String cheattype = (String)object.get("cheattype");
        String args = (String)object.get("args");
        BungeeUtils.alert(uuid, cheattype, args);
    }

    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("destination", "BUNGEE");
        object.put("uuid", player.toString());
        object.put("cheattype", cheattype);
        object.put("args", args);
        return object;
    }
}
