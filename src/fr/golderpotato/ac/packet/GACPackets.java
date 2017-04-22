package fr.golderpotato.ac.packet;

import fr.golderpotato.ac.packet.packetlist.PacketSource;
import fr.golderpotato.ac.packet.packetlist.PacketType;
import fr.golderpotato.ac.utils.NMS;
import fr.golderpotato.ac.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to Faiden
 */
public class GACPackets {
    public static List<GACPacketHandler> handles = new ArrayList<>();
    private static GACPackets instance = new GACPackets();

    public static GACPackets getInstance()
    {
        return instance;
    }

    public void addPacketListener(GACPacketHandler handler)
    {
        handles.add(handler);
    }

    public Object onReceive(Packet packet)
    {
        for (GACPacketHandler h : handles) {
            if(!h.getPacketType().getSource().equals(PacketSource.IN))continue;
            if (h.getPacketType().equals(PacketType.ALL)) {
                h.Receive(packet);
            } else if (h.getPacketType().getName().equals(packet.getPacketName())) {
                h.Receive(packet);
            }
        }
        return packet.getPacket();
    }

    public Object onSend(Packet packet){
        for( GACPacketHandler h : handles) {
            if(!h.getPacketType().getSource().equals(PacketSource.OUT))continue;
            if(h.getPacketType().equals(PacketType.ALL)){
                h.Send(packet);
            } else if(h.getPacketType().getName().equals(packet.getPacketName())){
                h.Send(packet);
            }
        }
        return packet.getPacket();
    }
}
