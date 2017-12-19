package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Created by Eliaz on 10/05/2017.
 */
public class Spider extends CheatListener{

    @Override
    public void onMovementCheck(final GACPlayer player, final Location from, final Location to) {
        if(!player.needsCheck())return;
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)return;
        if(player.getAllowFlight())return;
        for(BlockFace bf : BlockFace.values()){
            if(player.getLocation().getBlock().getRelative(bf).getType().equals(Material.LADDER))return;
        }
        final double y = to.getY() - from.getY();

        if(y <= 0.0D)return;

        if(player.lastSpiderY == y){
            player.spiderLevel++;
        }
        player.lastSpiderY = y;
    }
}
