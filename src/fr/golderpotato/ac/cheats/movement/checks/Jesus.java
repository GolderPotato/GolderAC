package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Eliaz on 19/01/2017.
 */
public class Jesus extends CheatListener{

    @Override
    public void onMovementCheck(GACPlayer player, Location from, Location to) {
        if(!player.needsCheck())return;
        double y = Math.abs(from.getY() - to.getY());
        double yDiffer = Math.abs(y - player.JesusY);
        player.JesusY = y;
        if(player.getAllowFlight())return;
        if(yDiffer != 0)return;
        if(player.getLocation().getBlock().getType().equals(Material.AIR)){
            Block down = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
            if(down.getType() == Material.STATIONARY_WATER){
                if(down.getRelative(BlockFace.NORTH).getType() != Material.STATIONARY_WATER)return;
                if(down.getRelative(BlockFace.SOUTH).getType() != Material.STATIONARY_WATER)return;
                if(down.getRelative(BlockFace.EAST).getType() != Material.STATIONARY_WATER)return;
                if(down.getRelative(BlockFace.WEST).getType() != Material.STATIONARY_WATER)return;
                if(down.getRelative(BlockFace.NORTH_EAST).getType() != Material.STATIONARY_WATER)return;
                if(down.getRelative(BlockFace.NORTH_WEST).getType() != Material.STATIONARY_WATER)return;
                if(down.getRelative(BlockFace.SOUTH).getType() != Material.STATIONARY_WATER)return;
                if(down.getRelative(BlockFace.SOUTH_WEST).getType() != Material.STATIONARY_WATER)return;
                Block lilypad = player.getLocation().getBlock();
                if(lilypad.getRelative(BlockFace.EAST).getType() == Material.WATER_LILY)return;
                if(lilypad.getRelative(BlockFace.NORTH).getType() == Material.WATER_LILY)return;
                if(lilypad.getRelative(BlockFace.SOUTH).getType() == Material.WATER_LILY)return;
                if(lilypad.getRelative(BlockFace.WEST).getType() == Material.WATER_LILY)return;
                if(lilypad.getRelative(BlockFace.NORTH_WEST).getType() == Material.WATER_LILY)return;
                if(lilypad.getRelative(BlockFace.NORTH_EAST).getType() == Material.WATER_LILY)return;
                if(lilypad.getRelative(BlockFace.SOUTH_WEST).getType() == Material.WATER_LILY)return;
                if(lilypad.getRelative(BlockFace.SOUTH_EAST).getType() == Material.WATER_LILY)return;
                player.Jesus++;
            }
        }
    }
}
