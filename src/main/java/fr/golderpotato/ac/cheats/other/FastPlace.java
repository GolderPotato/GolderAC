package fr.golderpotato.ac.cheats.other;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import net.minecraft.server.v1_8_R3.ItemBlock;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by Eliaz on 01/03/2017.
 */
public class FastPlace extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.BLOCK_PLACE) {
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
                final Object type = paramPacket.getPacketValue("d");
                if(type == null)return paramPacket;
                try {
                    Field f = type.getClass().getDeclaredField("item");
                    f.setAccessible(true);
                    if(f.get(type) == null)return paramPacket;
                    if(f.get(type) instanceof ItemBlock){
                       final  double time = System.currentTimeMillis() - gplayer.FastPlaceTime;
                        if(time < 50){
                            gplayer.fastplace++;
                        }
                        gplayer.FastPlaceTime = System.currentTimeMillis();
                    }
                }catch (Exception e){
                }
                return paramPacket;
            }
        });
    }
}
