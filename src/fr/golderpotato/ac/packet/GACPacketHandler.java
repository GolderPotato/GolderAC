package fr.golderpotato.ac.packet;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to InventiveTalent
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

    public abstract Packet Send(Packet paramPacket);

    public abstract Packet Receive(Packet paramPacket);
}
