package fr.golderpotato.ac.packet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to InventiveTalent
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
        Packet toReturn = packet;
        for (GACPacketHandler h : handles) {
            if(!h.getPacketType().getSource().equals(PacketSource.IN))continue;
            if (h.getPacketType().equals(PacketType.ALL)) {
                Packet packet1 = h.Receive(packet);
                if(!packet1.isAllowed()){
                    toReturn = null;
                }
            } else if (h.getPacketType().getName().equals(packet.getPacketName())) {
                Packet packet1 = h.Receive(packet);
                if(!packet1.isAllowed()){
                    toReturn = null;
                }
            }
        }
        return toReturn.getPacket();
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
