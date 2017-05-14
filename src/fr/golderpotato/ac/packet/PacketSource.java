package fr.golderpotato.ac.packet;

/**
 * Created by Eliaz on 27/02/2017.
 */
public enum PacketSource {

    IN("PacketPlayIn"),
    OUT("PacketPlayOut");

    PacketSource(String prefix){
        this.prefix = prefix;
    }

    public String prefix;

}
