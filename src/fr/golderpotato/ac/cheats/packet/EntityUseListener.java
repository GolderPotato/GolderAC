package fr.golderpotato.ac.cheats.packet;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 17/01/2017.
 */
public class EntityUseListener extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.USE_ENTITY) {
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
                if(!gplayer.needsCheck())return paramPacket;

                Integer eid = (Integer) paramPacket.getPacketValue("a");
                Object s = paramPacket.getPacketValue("action");

                if(s.toString().equals("ATTACK")){
                    gplayer.CPS++;
                }

                if(!gplayer.isOnGround() && !gplayer.getAllowFlight()){
                    if(gplayer.getLocation().getY() % 1.0D == 0.0D){
                        if(gplayer.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid()){
                            CheatType.CRITICALS.alertMods(gplayer);
                        }
                    }
                }
                return paramPacket;
            }
        });
    }
}
