package fr.golderpotato.ac.rabbit.packet.packets;

import fr.golderpotato.ac.rabbit.packet.RabbitPacket;
import fr.golderpotato.ac.utils.BungeeUtils;
import org.json.simple.JSONObject;

import java.util.UUID;

/**
 * Created by Eliaz on 22/08/2017.
 */
public class RabbitPacketTP implements RabbitPacket{

    private UUID player1;
    private UUID player2;

    public RabbitPacketTP(){

    }

    public RabbitPacketTP(UUID player1, UUID player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void receive(JSONObject object) {
        String player_1 = (String)object.get("player1");
        UUID player1 = UUID.fromString(player_1);
        String player_2 = (String)object.get("player2");
        UUID player2 = UUID.fromString(player_2);
        BungeeUtils.connect(player1, player2);
    }

    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("destination", "BUNGEE");
        object.put("player1", player1.toString());
        object.put("player2", player2.toString());
        return object;
    }


}
