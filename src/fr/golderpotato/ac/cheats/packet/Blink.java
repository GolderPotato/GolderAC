package fr.golderpotato.ac.cheats.packet;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.packetlist.PacketType;
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
            public void Send(Packet paramPacket) {

            }

            @Override
            public void Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(paramPacket.getPacketName().equals("PacketPlayInKeepAlive"))return;
                if(gplayer.isDead())return;
                if(!gplayer.isOnline())return;
                gplayer.AllPackets++;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.KEEP_ALIVE) {
            @Override
            public void Send(Packet paramPacket) {

            }

            @Override
            public void Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return;
                GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(gplayer == null)return;
                if(gplayer.isDead())return;
                if(!gplayer.isOnline())return;
                gplayer.KeepAlive++;
            }
        });
    }
}
