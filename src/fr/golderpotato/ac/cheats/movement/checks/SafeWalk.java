package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.command.commands.Machine;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.PositionUtils;
import org.bukkit.Location;

/**
 * Created by Eliaz on 09/05/2017.
 */
public class SafeWalk extends CheatListener{

    @Override
    public void onMovementCheck(GACPlayer player, Location from, Location to) {
        if(player.isSneaking())return;
        if(!player.isSprinting())return;
        double toEdgeX = Math.abs(to.getX() - Math.round(to.getX()));
        double toEdgeZ = Math.abs(to.getZ() - Math.round(to.getZ()));
        double fromEdgeX = Math.abs(from.getX() - Math.round(from.getX()));
        double fromEdgeZ = Math.abs(from.getZ() - Math.round(from.getZ()));
        double yaw = Math.abs(PositionUtils.wrapAngleTo180_float(player.getYaw()));
        if(yaw >= 70 && yaw <= 110)return;
        if(yaw >= 150)return;
        if(yaw <= 40)return;
        if(((toEdgeX >= 0.29 && toEdgeX <= 0.31) || (toEdgeZ >= 0.69 && toEdgeX <= 0.71)) && ((fromEdgeX >= 0.29 && fromEdgeX <= 0.31) || (fromEdgeZ >= 0.69 && toEdgeZ <= 0.71))){
            player.safeWalk++;
        }

    }
}
