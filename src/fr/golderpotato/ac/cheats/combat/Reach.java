package fr.golderpotato.ac.cheats.combat;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.packetlist.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.PositionUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Eliaz on 22/01/2017.
 */
public class Reach extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.USE_ENTITY) {
            @Override
            public void Send(Packet paramPacket) {

            }

            @Override
            public void Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return;
                GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(gplayer == null)return;
                Entity ent = null;

                for(Entity ents : player.getWorld().getEntities()){
                    if(ents.getEntityId() == (int)paramPacket.getPacketValue("a")){
                        ent = ents;
                        break;
                    }
                }

                if(ent == null)return;

                LivingEntity damaged = (LivingEntity)ent;

                Location entityLoc = damaged.getLocation().add(0.0D, damaged.getEyeHeight(), 0.0D);
                Location playerLoc = player.getLocation().add(0.0D, player.getEyeHeight(), 0.0D);
                double distance = PositionUtils.getDistance(entityLoc, playerLoc);
                if(!(player.getLocation().getY() % 1 == 0))return;


                double reach = (double)CheatType.FORCEFIELD.getValue("maxreach");
                if(gplayer.getPing() > 100 && gplayer.getPing() < 200){
                    reach+=0.2;
                }
                if(gplayer.getPing() > 200 && gplayer.getPing() < 300){
                    reach++;
                }
                if(gplayer.getPing() > 300){
                    reach+=1.4;
                }

                if (distance / (gplayer.getPing() / 10000 + 1) > reach) {
                    CheatType.REACH.alertMods(gplayer, "Reach> "+distance+" (MAX: 4.5)");
                }
            }
        });
    }
}
