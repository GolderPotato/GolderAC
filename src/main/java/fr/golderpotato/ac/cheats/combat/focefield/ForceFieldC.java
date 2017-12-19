package fr.golderpotato.ac.cheats.combat.focefield;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.PositionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 25/02/2017.
 */
public class ForceFieldC extends CheatListener{

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
                final int eid = (int)paramPacket.getPacketValue("a");
                Entity ent = null;
                for(Entity enti : player.getWorld().getEntities()){
                    if(enti.getEntityId() == eid){
                        ent = enti;
                        break;
                    }
                }
                if(ent == null)return paramPacket;
                final double aimValue = Math.abs(PositionUtils.getDirection(player.getLocation(), ent.getLocation()));
                final double yawValue = Math.abs(PositionUtils.wrapAngleTo180_float(player.getLocation().getYaw()));
                final double difference = Math.abs(aimValue - yawValue);
                if(PositionUtils.getDistance(player.getLocation(), ent.getLocation()) > (double)CheatType.FORCEFIELD.getValue("minreach") && PositionUtils.getDistance(player.getLocation(), ent.getLocation()) < (double)CheatType.FORCEFIELD.getValue("maxreach")){
                    if(difference > (int)CheatType.FORCEFIELD.getValue("yawdifference")){
                        CheatType.FORCEFIELD.alertMods(gplayer, "not looking @ the ent("+difference+"/30)");
                    }
                }
                return paramPacket;
            }
        });
    }
}
