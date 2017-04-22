package fr.golderpotato.ac.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 01/02/2017.
 */
public class BungeeCord {

    public static void alert(GACPlayer player, String reason){
        try {
            if(Main.getInstance().getConfig().getBoolean("bungeecord")){
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("alertmods");
                out.writeUTF(player.getName());
                out.writeUTF(reason);
                Bukkit.getServer().sendPluginMessage(Main.getInstance(), "gac", out.toByteArray());
            }else{
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(players.hasPermission("gac.alerts")){
                        players.sendMessage(ChatUtils.getPrefix()+"The player "+player.getName()+" might be using "+reason);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void broadCast(GACPlayer gplayer, CheatType cheatType){
        if(Main.getInstance().getConfig().getBoolean("bungeecord")){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("broadcast");
            out.writeUTF(gplayer.getName());
            out.writeUTF(cheatType.name());
            Bukkit.getServer().sendPluginMessage(Main.getInstance(), "gac", out.toByteArray());
        }else{
            for(Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage(ChatUtils.getPrefix()+"Player "+gplayer.getName()+" was banned for "+cheatType.cheatname);
            }
        }
    }
}
