package fr.golderpotato.ac.cheats.packet;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 01/03/2017.
 */
public class TabComplete extends CheatListener{


    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.TAB_COMPLETE) {
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

                String message = (String) paramPacket.getPacketValue("a");
                String[] msg = message.split(" ");


                if(gplayer.tabComple == true)return paramPacket;

                if(msg[0].startsWith("#") || msg[0].startsWith("-") || msg[0].startsWith(".") || msg[0].startsWith("*")){
                    CheatType.POSSIBLECHEAT.alertMods(gplayer, "Chat tab complete: "+message);
                    gplayer.tabComple = true;
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            gplayer.tabComple = false;
                        }
                    }.runTaskLater(Main.getInstance(), 10L);
                }
                return paramPacket;
            }
        });
    }
}
