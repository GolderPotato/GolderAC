package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;

/**
 * Created by Eliaz on 24/05/2017.
 */
public class FlightV2 extends CheatListener{

    @Override
    public void onMovementCheck(GACPlayer player, Location from, Location to) {
        if(player == null)return;
        if(player.isInsideVehicle())return;
        if(!player.needsCheck())return;
        if(player.getVelocity().getX() != 0.0D || player.getVelocity().getZ() != 0.0D){
            return;
        }
        if(player.getOnGround()){
            player.wentUP = false;
            player.wentDOWN = false;
            return;
        }
        if(from.getY() < to.getY()){
            player.wentUP = true;
        }
        if(player.wentUP){
            if(from.getY() > to.getY()){
                player.wentDOWN = true;
            }
            if(player.wentDOWN){
                if(from.getY() < to.getY()){
                    player.wentUP = false;
                    player.wentDOWN = false;
                    CheatType.FLY.alertMods(player,"Goes up and down!");
                }
            }
        }
    }
}
