package fr.golderpotato.ac.cheats.packet;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 14/02/2017.
 */
public class NoSlowDown extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.BLOCK_DIG) {
            @Override
            public Packet Send(Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(gplayer == null)return paramPacket;
                PacketPlayInBlockDig.EnumPlayerDigType digtype = (PacketPlayInBlockDig.EnumPlayerDigType) paramPacket.getPacketValue("c");
                if(digtype.equals(PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM)){
                    if(gplayer.isSprinting()){
                        gplayer.NoSlowDown++;
                    }
                }
                return paramPacket;
            }
        });
    }

    @EventHandler
    public void onEat(FoodLevelChangeEvent event){
        Player player = (Player) event.getEntity();
        if(player == null)return;
        GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        if(gplayer == null)return;
        if(gplayer.isSprinting()){
            if(event.getFoodLevel() > player.getFoodLevel()){
                CheatType.NOSLOWDOWN.alertMods(gplayer);
            }
        }
    }

    @EventHandler
    public void onBow(EntityShootBowEvent event){
        if(!(event.getEntity() instanceof Player))return;
        Player player = (Player) event.getEntity();
        if(player == null)return;
        GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        if(gplayer == null)return;
        if(gplayer.isSprinting()){
            CheatType.NOSLOWDOWN.alertMods(gplayer);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player == null)return;
        GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        if(gplayer == null)return;
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            org.bukkit.inventory.ItemStack item = event.getItem();
            if(item == null)return;
            Material mat = item.getType();
            if(mat == null)return;
            if(mat == Material.WOOD_SWORD || mat == Material.GOLD_SWORD || mat == Material.STONE_SWORD || mat == Material.IRON_SWORD || mat == Material.DIAMOND_SWORD){
                new BukkitRunnable(){
                    public void run(){
                        if(gplayer.isSprinting()){
                            CheatType.NOSLOWDOWN.alertMods(gplayer);
                        }
                    }
                }.runTaskLater(Main.getInstance(), 1);
            }
        }
    }
}
