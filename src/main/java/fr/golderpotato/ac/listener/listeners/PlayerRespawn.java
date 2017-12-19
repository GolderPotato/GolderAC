package fr.golderpotato.ac.listener.listeners;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 * Created by Eliaz on 16/01/2017.
 */
public class PlayerRespawn implements Listener{

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent event){
        final Player player = event.getPlayer();
        if(player == null)return;
        final GACPlayer gacPlayer = Main.getInstance().getGACPlayer(player);
        if(gacPlayer == null)return;
        Main.getInstance().getBypass().addByPassed(gacPlayer);
        new BukkitRunnable(){
            @Override
            public void run() {
                Main.getInstance().getBypass().removeByPassed(gacPlayer);
            }
        }.runTaskLater(Main.getInstance(), 20 * 10);
    }
}
