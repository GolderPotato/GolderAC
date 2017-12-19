package fr.golderpotato.ac.cheats.movement;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 27/02/2017.
 */
public class MovementCheckHandler extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.POSITION_LOOK) {
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
                gplayer.PositionLook++;

                double x = 0;
                double y = 0;
                double z = 0;
                float yaw = 0;
                float pitch = 0;


                try {
                    x = (double)paramPacket.getPacketValue("x");
                    y = (double)paramPacket.getPacketValue("y");
                    z = (double)paramPacket.getPacketValue("z");
                    yaw = (float)paramPacket.getPacketValue("yaw");
                    pitch = (float)paramPacket.getPacketValue("pitch");
                }catch (Exception e){
                    e.printStackTrace();
                }

                final Location location = new Location(player.getWorld(), x, y, z, yaw, pitch);

                MovementCheckManager.handle(gplayer, gplayer.last, location);

                gplayer.last = location;
                return paramPacket;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.POSITION) {
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
                gplayer.Position++;

                double x = 0;
                double y = 0;
                double z = 0;
                float yaw = 0;
                float pitch = 0;


                try {
                    x = (double)paramPacket.getPacketValue("x");
                    y = (double)paramPacket.getPacketValue("y");
                    z = (double)paramPacket.getPacketValue("z");
                    yaw = (float)paramPacket.getPacketValue("yaw");
                    pitch = (float)paramPacket.getPacketValue("pitch");
                }catch (Exception e){
                    e.printStackTrace();
                }

                final Location location = new Location(player.getWorld(), x, y, z, yaw, pitch);

                MovementCheckManager.handle(gplayer, gplayer.last, location);

                gplayer.last = location;
                return paramPacket;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.FLYING) {
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
                gplayer.Flying++;
                return paramPacket;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.LOOK) {
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
                gplayer.Look++;
                return paramPacket;
            }
        });

    }
}
