package fr.golderpotato.ac.command.commands;

import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.player.GACPlayer;

/**
 * Created by Eliaz on 28/02/2017.
 */
public class PacketList extends CommandAbstract{

    public PacketList(){
        super("packetlist");
    }

    @Override
    public void handle(GACPlayer player, String[] args) {
        if(!player.isDev())return;
        for(Packet packet : player.getPacketsInLastSecond()){
            player.sendMessage("> "+packet.getPacketName());
        }
    }
}
