package fr.golderpotato.ac.command.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.rabbit.packet.*;
import fr.golderpotato.ac.rabbit.packet.PacketList;
import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketTP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 14/02/2017.
 */
public class BungeeTP extends CommandAbstract{

    public BungeeTP(){
        super("bungeetp");
    }

    @Override
    public void handle(final GACPlayer player, final String[] args) {
        if(!player.hasPermission("gac.bungeetp"))return;
        if(args.length == 0){
            player.sendMessage("Invalid command!");
        }
        if(args.length == 1){
            Player player1 = Bukkit.getPlayer(args[0]);

            if(player1 == null){
                player.sendMessage("Player not found!");
                return;
            }

            RabbitPacketTP tp = new RabbitPacketTP(player.getUniqueId(), player1.getUniqueId());
            PacketSender.sendPacket(tp, PacketList.TP);
            return;
        }
        if(args.length == 2){
            final Player player1 = Bukkit.getPlayer(args[0]);
            final Player player2 = Bukkit.getPlayer(args[1]);

            if(player1 == null){
                player.sendMessage("Player not found!");
                return;
            }

            if(player2 == null){
                player.sendMessage("Player not found!");
                return;
            }

            RabbitPacketTP tp = new RabbitPacketTP(player.getUniqueId(), player1.getUniqueId());
            PacketSender.sendPacket(tp, PacketList.TP);
            return;
        }
    }
}
