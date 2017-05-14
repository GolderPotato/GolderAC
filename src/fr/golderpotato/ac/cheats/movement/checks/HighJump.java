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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.material.Stairs;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 28/02/2017.
 */
public class HighJump extends CheatListener{

    @Override
    public void onMovementCheck(GACPlayer player, Location from, Location to) {
        if(player.getVelocity().getY() != 0.41999998688697815)return;
        double distance = from.toVector().distance(to.toVector());
        double time = System.currentTimeMillis() - player.JumpTime;
        if(!player.needsCheck())return;
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN) instanceof Stairs){
            player.sendMessage("true!");
        }

        Material mat = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();

        if(player.hasMaterialAround(Material.ACACIA_STAIRS))return;
        if(player.hasMaterialAround(Material.BIRCH_WOOD_STAIRS))return;
        if(player.hasMaterialAround(Material.BRICK_STAIRS))return;
        if(player.hasMaterialAround(Material.SANDSTONE_STAIRS))return;
        if(player.hasMaterialAround(Material.COBBLESTONE_STAIRS))return;
        if(player.hasMaterialAround(Material.DARK_OAK_STAIRS))return;
        if(player.hasMaterialAround(Material.JUNGLE_WOOD_STAIRS))return;
        if(player.hasMaterialAround(Material.NETHER_BRICK_STAIRS))return;
        if(player.hasMaterialAround(Material.SMOOTH_STAIRS))return;
        if(player.hasMaterialAround(Material.SPRUCE_WOOD_STAIRS))return;
        if(player.hasMaterialAround(Material.WOOD_STAIRS))return;
        if(player.hasMaterialAround(Material.RED_SANDSTONE_STAIRS))return;
        if(player.hasMaterialAround(Material.QUARTZ_STAIRS))return;
        //if(player.getVelocity().getX() != 0.0D || player.getVelocity().getZ() !=0.0D)return;

        player.JumpTime = System.currentTimeMillis();

        if(distance > (double)CheatType.HIGHJUMP.getValue("jumpheight")){
            CheatType.HIGHJUMP.alertMods(player);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player))return;
        Player player = (Player) event.getEntity();
        if(player == null)return;
        GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        Main.getInstance().bypass.addByPassed(gplayer);
        new BukkitRunnable(){
            public void run(){
                Main.getInstance().getBypass().removeByPassed(gplayer);
            }
        }.runTaskLater(Main.getInstance(), 20L);
    }
}
