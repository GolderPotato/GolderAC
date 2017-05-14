package fr.golderpotato.ac.utils;

import fr.golderpotato.ac.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 23/04/2017.
 */
public class ServerUtil {

    public static double startTime = 0;

    public static int getOnlinePlayers(){
        return Bukkit.getOnlinePlayers().size();
    }

    public static void broadCast(String message){
        Bukkit.getServer().broadcastMessage(ChatUtils.getPrefix()+message);
    }

    public static void stop(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
    }

    public static void restart(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
    }

    public static void ban(Player player, String reason){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban "+player.getName()+" "+reason);
    }

    public static String getLocalAddress(){
        return Bukkit.getIp()+":"+Bukkit.getPort();
    }

}
