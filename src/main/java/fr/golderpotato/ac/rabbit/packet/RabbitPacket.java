package fr.golderpotato.ac.rabbit.packet;

import org.json.simple.JSONObject;

/**
 * Created by Eliaz on 19/08/2017.
 */
public interface RabbitPacket {

    JSONObject serialize();

    void receive(JSONObject object);

}
