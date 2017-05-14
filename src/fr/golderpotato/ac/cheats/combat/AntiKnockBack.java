package fr.golderpotato.ac.cheats.combat;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 17/01/2017.
 */
public class AntiKnockBack extends CheatListener{

    @EventHandler
    public void onVelocityAntiKnockBack(PlayerVelocityEvent event) {

    }

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.ENTITY_VELOCITY) {
            @Override
            public Packet Send(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(gplayer == null)return paramPacket;
                if(!gplayer.needsCheck())return paramPacket;

                int eid = (int)paramPacket.getPacketValue("a");

                if(player.getEntityId() != eid)return paramPacket;

                if ((player.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.NORTH_WEST)
                                .getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.NORTH)
                                .getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.NORTH_EAST)
                                .getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.EAST)
                                .getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.SOUTH_WEST)
                                .getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.SOUTH)
                                .getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.SOUTH_EAST)
                                .getType() != Material.AIR) ||
                        (gplayer.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().getRelative(BlockFace.WEST)
                                .getType() != Material.AIR)) {
                    return paramPacket;
                }
                if (player.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER) || player.getLocation().getBlock().getType().equals(Material.STATIONARY_LAVA)) return paramPacket;
                double force =
                        Math.abs(player.getVelocity().getX() + player.getVelocity().getY() + player.getVelocity().getZ());
                if(force == 0.0784000015258789)return paramPacket;
                if(force < 0.2D)return paramPacket;
                if ((force == 0.0D) || (player.getVelocity().getX() == 0.0D) || (player.getVelocity().getZ() == 0.0D)) {
                    return paramPacket;
                }
                int y = (int)paramPacket.getPacketValue("c");
                if(y < 500)return paramPacket;
                if(!player.isOnGround())return paramPacket;

                new BukkitRunnable(){
                    int timer = 0;
                    double highestpoint = player.getLocation().getY();
                    double y = player.getLocation().getY();
                    @Override
                    public void run(){
                        timer++;
                        if(timer < 10){
                            if(player.getLocation().getY() > highestpoint){
                                highestpoint = player.getLocation().getY();
                            }
                        }else {
                            cancel();
                            if(y == highestpoint){
                                CheatType.ANTIKNOCKBACK.alertMods(gplayer);
                                if(gplayer.getPing() < 120){
                                    CheatType.FORCEFIELD.ban(gplayer);
                                }
                            }
                        }
                    }
                }.runTaskTimer(Main.getInstance(), 0, 1);
                return paramPacket;
            }

            @Override
            public Packet Receive(Packet paramPacket) {
                return paramPacket;
            }
        });
    }
}
