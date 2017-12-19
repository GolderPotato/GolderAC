package fr.golderpotato.ac.packet;

/**
 * Created by Eliaz on 27/02/2017.
 */
public enum PacketSource {

    IN("PacketPlayIn"),
    OUT("PacketPlayOut"),
    LOGIN_IN("PacketLoginIn"),
    LOGIN_OUT("PacketLoginOut");

    PacketSource(final String prefix){
        this.prefix = prefix;
    }

    public final String prefix;

}
