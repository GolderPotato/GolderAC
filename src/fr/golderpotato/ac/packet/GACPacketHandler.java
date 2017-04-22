package fr.golderpotato.ac.packet;

import fr.golderpotato.ac.packet.packetlist.PacketBuild;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to Faiden
 */
public abstract class GACPacketHandler {

    public PacketBuild packet;

    public GACPacketHandler(PacketBuild packet)
    {
        this.packet = packet;
    }

    public PacketBuild getPacketType()
    {
        return this.packet;
    }

    public abstract void Send(Packet paramPacket);

    public abstract void Receive(Packet paramPacket);
}
