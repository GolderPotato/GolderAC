package fr.golderpotato.ac.listener.listeners;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 27/02/2017.
 */
public class PlayerTeleport implements Listener{

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event){
        final Player player = event.getPlayer();
        if(player == null)return;
        final GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        if(gplayer == null)return;
        Main.getInstance().bypass.addByPassed(gplayer);
        new BukkitRunnable(){
            public void run(){
                Main.getInstance().bypass.removeByPassed(gplayer);
            }
        }.runTaskLater(Main.getInstance(), 20L);
    }
}
