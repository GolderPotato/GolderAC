package fr.golderpotato.ac.listener.listeners;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.packet.ChannelHandler;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class PlayerJoin implements Listener{

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        final Player player = event.getPlayer();
        final GACPlayer gacPlayer = new GACPlayer(player);

        Main.getInstance().addGACPLayer(gacPlayer);
        try{
            ChannelHandler.addChannel(player);
            Main.getInstance().print("Successfully injected channel to player "+player.getName());
        }catch (Exception e){
            Main.getInstance().print("Failed injecting channel for player "+player.getName());
            Main.getInstance().print("ERROR:");
            e.printStackTrace();
        }

        Main.getInstance().getBypass().addByPassed(gacPlayer);
        new BukkitRunnable(){
            @Override
            public void run() {
                Main.getInstance().getBypass().removeByPassed(gacPlayer);
            }
        }.runTaskLater(Main.getInstance(), 20 * 15);
    }

}
