package fr.golderpotato.ac.command.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;
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
    public void handle(GACPlayer player, String[] args) {
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

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("bungeetp");
            out.writeUTF(player.getName());
            out.writeUTF(player1.getName());
            Bukkit.getServer().sendPluginMessage(Main.getInstance(), "gac", out.toByteArray());
            return;
        }
        if(args.length == 2){
            Player player1 = Bukkit.getPlayer(args[0]);
            Player player2 = Bukkit.getPlayer(args[1]);

            if(player1 == null){
                player.sendMessage("Player not found!");
                return;
            }

            if(player2 == null){
                player.sendMessage("Player not found!");
                return;
            }

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("bungeetp");
            out.writeUTF(player1.getName());
            out.writeUTF(player2.getName());
            Bukkit.getServer().sendPluginMessage(Main.getInstance(), "gac", out.toByteArray());
            return;
        }
    }
}
