package fr.golderpotato.ac.rabbit.packet;

import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketAlert;
import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketBroadCast;
import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketKick;
import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketTP;
import org.bukkit.entity.Rabbit;

/**
 * Created by Eliaz on 19/08/2017.
 */
public enum PacketList {

    ALERT(1, new RabbitPacketAlert()),
    BAN(2, null),
    KICK(3, new RabbitPacketKick()),
    TP(4, new RabbitPacketTP()),
    BROADCAST(5, new RabbitPacketBroadCast());

    private final int ID;
    private final Object packet;

    PacketList(final int ID, final Object packet){
        this.ID = ID;
        this.packet = packet;
    }

    public int getID(){
        return this.ID;
    }

    public Object getPacket(){
        return this.packet;
    }

    public static RabbitPacket getPacketByID(int id){
        for(PacketList lists : values()){
            if(lists.getID() == id){
                return (RabbitPacket) lists.getPacket();
            }
        }
        return null;
    }

    public RabbitPacket newInstance(){
        try {
            return (RabbitPacket) getPacket().getClass().newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public RabbitPacket newInstance(Class<? extends Object> ... cargs){
        try {
            return (RabbitPacket) getPacket().getClass().getDeclaredConstructor(cargs).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
