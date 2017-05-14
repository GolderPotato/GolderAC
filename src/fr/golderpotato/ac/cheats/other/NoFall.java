package fr.golderpotato.ac.cheats.other;

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
public class NoFall extends CheatListener{

    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.POSITION_LOOK) {
            @Override
            public Packet Send(Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                GACPlayer gplayers = Main.getInstance().getGACPlayer(player);
                if(gplayers == null)return paramPacket;
                gplayers.nofall_PositionLook++;
                gplayers.nofall = gplayers.nofall_Flying + gplayers.nofall_PositionLook + gplayers.nofall_Position + gplayers.nofall_Look;
                if(gplayers.nofall > 14 && gplayers.isOnGround() && !gplayers.getOnGround()){
                    paramPacket.dissalow();
                }
                return paramPacket;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.POSITION) {
            @Override
            public Packet Send(Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                GACPlayer gplayers = Main.getInstance().getGACPlayer(player);
                if(gplayers == null)return paramPacket;
                gplayers.nofall_Position++;
                gplayers.nofall = gplayers.nofall_Flying + gplayers.nofall_PositionLook + gplayers.nofall_Position + gplayers.nofall_Look;
                if(gplayers.nofall > 14 && gplayers.isOnGround() && !gplayers.getOnGround()){
                    paramPacket.dissalow();
                }
                return paramPacket;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.FLYING) {
            @Override
            public Packet Send(Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                GACPlayer gplayers = Main.getInstance().getGACPlayer(player);
                if(gplayers == null)return paramPacket;
                gplayers.nofall_Flying++;
                gplayers.nofall = gplayers.nofall_Flying + gplayers.nofall_PositionLook + gplayers.nofall_Position + gplayers.nofall_Look;
                if(gplayers.nofall > 14 && gplayers.isOnGround() && !gplayers.getOnGround()){
                    paramPacket.dissalow();
                }
                return paramPacket;
            }
        });

        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.LOOK) {
            @Override
            public Packet Send(Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(Packet paramPacket) {
                Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                GACPlayer gplayers = Main.getInstance().getGACPlayer(player);
                if(gplayers == null)return paramPacket;
                gplayers.nofall_Look++;
                gplayers.nofall = gplayers.nofall_Flying + gplayers.nofall_PositionLook + gplayers.nofall_Position + gplayers.nofall_Look;
                if(gplayers.nofall > 14 && gplayers.isOnGround() && !gplayers.getOnGround()){
                    paramPacket.dissalow();
                }
                return paramPacket;
            }
        });

    }
}
