package fr.golderpotato.ac.cheats.other;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Eliaz on 26/02/2017.
 */
public class MoreInventory extends CheatListener{

    @EventHandler
    public void onMove(final PlayerMoveEvent event){
        final Player player = event.getPlayer();
        if(player == null)return;
        final GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        if(gplayer == null)return;
        if(!gplayer.needsCheck())return;
        if(!gplayer.isOnGround())return;
        if(gplayer.getVelocity().getX() != 0.0 && gplayer.getVelocity().getZ() != 0.0)return;
        if(gplayer.getOpenInventory().getItem(1).getType() != Material.AIR || gplayer.getOpenInventory().getItem(2).getType() != Material.AIR || gplayer.getOpenInventory().getItem(3).getType() != Material.AIR  || gplayer.getOpenInventory().getItem(4).getType() != Material.AIR ){
            CheatType.INVENTORYPLUS.alertMods(gplayer);
            CheatType.INVENTORYPLUS.ban(gplayer);
        }
    }
}
