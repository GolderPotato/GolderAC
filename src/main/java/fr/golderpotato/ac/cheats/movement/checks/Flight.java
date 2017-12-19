package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class Flight extends CheatListener{

    @Override
    public void setupListener() {
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
                if(!gplayer.needsCheck())return paramPacket;
                if(player.getLocation().getY() < 0)return paramPacket;
                if(!(gplayer.isOnGround() && gplayer.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid()) && gplayer.getOnGround()){
                    gplayer.FlyLevel++;
                }
                return paramPacket;
            }
        });
    }


    @Override
    public void onMovementCheck(final GACPlayer player, final Location from, final Location to) {
        if(player.getAllowFlight())return;
        if(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))return;
        if(player.isInsideVehicle())return;
        if(!player.needsCheck())return;
        final double y = from.getY() - to.getY();
        if(y != 0)return;
        if(player.getLocation().getY() < 0)return;
        if(player.getOnGround())return;
        player.Fly++;
    }
}
