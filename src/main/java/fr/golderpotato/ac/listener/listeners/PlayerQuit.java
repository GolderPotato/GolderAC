package fr.golderpotato.ac.listener.listeners;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.packet.ChannelHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class PlayerQuit implements Listener{

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        final Player player = event.getPlayer();
        Main.getInstance().removeGACPlayer(player);
        try {
            ChannelHandler.removeChannel(player);
        }catch (Exception e){
            Main.getInstance().print("Cannot uninject channel for player "+player.getName()+"!");
            Main.getInstance().print("Error:");
            e.printStackTrace();
        }
    }

}
