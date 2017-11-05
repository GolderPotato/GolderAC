package fr.golderpotato.ac.utils;

import fr.golderpotato.ac.ChatUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Eliaz on 19/08/2017.
 */
public class BungeeUtils {

    public static void alert(UUID player, String cheattype, String args){
        for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()){
            if(players == null)continue;
            if(players.hasPermission("gac.alerts")){
                players.sendMessage(new TextComponent(ChatUtils.getPrefix()+"Player "+ProxyServer.getInstance().getPlayer(player).getName()+" might be using "+cheattype+"("+args+")"));
            }
        }
    }

    public static void kick(UUID uuid, String reason){
        ProxyServer.getInstance().getPlayer(uuid).disconnect(new TextComponent(reason));
    }

    public static void connect(UUID player_1, UUID player_2){
        ProxiedPlayer player1 = ProxyServer.getInstance().getPlayer(player_1);
        ProxiedPlayer player2 = ProxyServer.getInstance().getPlayer(player_2);
        if(player1 == null || player2 == null)return;
        player1.connect(player2.getServer().getInfo());
    }

    public static void broadcast(String player, String reason){
        ProxyServer.getInstance().broadcast(new TextComponent(ChatUtils.getPrefix()+" Player "+player+" was banned from our server for "+reason));
    }

}
