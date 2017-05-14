package fr.golderpotato.ac.cheats.packet;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class Blink extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.ALL) {
            @Override
            public Packet Send(Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(paramPacket.getPacketName().equals("PacketPlayInKeepAlive"))return paramPacket;
                if(gplayer.isDead())return paramPacket;
                if(!gplayer.needsCheck())return paramPacket;
                if(!gplayer.isOnline())return paramPacket;
                gplayer.AllPackets++;
                return paramPacket;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.KEEP_ALIVE) {
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
                if(gplayer.isDead())return paramPacket;
                if(!gplayer.needsCheck())return paramPacket;
                if(!gplayer.isOnline())return paramPacket;
                gplayer.KeepAlive++;
                return paramPacket;
            }
        });
    }
}
