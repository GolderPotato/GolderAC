package fr.golderpotato.ac.cheats.combat.focefield;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 25/02/2017.
 */
public class ForceFieldB extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.USE_ENTITY) {
            @Override
            public Packet Send(final Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(final Packet paramPacket) {
                final Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                final GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(gplayer == null)return paramPacket;
                if(!gplayer.needsCheck())return paramPacket;
                final int id = (int)paramPacket.getPacketValue("a");

                Entity ent = null;
                for(Entity entity : gplayer.getWorld().getEntities()){
                    if(entity.getEntityId() == id){
                        ent = entity;
                        break;
                    }
                }
                if(!(ent instanceof LivingEntity)){
                    return paramPacket;
                }

                if(!(gplayer.ffID == 0)){
                    final double time = System.currentTimeMillis() - gplayer.ffTIME;
                    if(id != gplayer.ffID) {
                        if(time < (int)CheatType.FORCEFIELD.getValue("entdelay")){
                            CheatType.FORCEFIELD.alertMods(gplayer, "2 entity / <100ms");
                            CheatType.FORCEFIELD.ban(gplayer);
                        }
                    }
                    gplayer.ffID = id;
                    gplayer.ffTIME = System.currentTimeMillis();
                }else{
                    gplayer.ffID = id;
                    gplayer.ffTIME = System.currentTimeMillis();
                }
                return paramPacket;
            }
        });
    }
}
