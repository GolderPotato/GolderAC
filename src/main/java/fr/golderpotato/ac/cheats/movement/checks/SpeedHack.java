package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * Created by Eliaz on 16/01/2017.
 */
public class SpeedHack extends CheatListener{

    @Override
    public void onMovementCheck(final GACPlayer player, final Location ffrom, final Location tto) {
        final Vector from = ffrom.toVector().setY(0);
        final Vector to = tto.toVector().setY(0);
        final double distance = from.distanceSquared(to);
        //if (!player.getOnGround()) return;
        if(player.hasPotionEffect(PotionEffectType.SPEED))return;
        if(player.hasPotionEffect(PotionEffectType.JUMP))return;
        if (player.getAllowFlight()) return;
        if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) return;
        if (player.isRiding()) return;
        if (from.getY() < to.getY()) return;
        if (player.isSneaking())return;
        if (player.getVelocity().getZ() != 0.0 || player.getVelocity().getX() != 0.0)return;
        if (distance > (double)CheatType.SPEEDHACK.getValue("basespeed") * (player.getWalkSpeed() * 10)) {
            player.speedhack++;
            CheatType.SPEEDHACK.alertMods(player);
        }
    }
}
