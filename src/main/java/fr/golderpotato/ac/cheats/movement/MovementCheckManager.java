package fr.golderpotato.ac.cheats.movement;

import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliaz on 27/02/2017.
 */
public class MovementCheckManager {

    public static List<MovementCheck> movementChecks = new ArrayList<>();

    public static void add(MovementCheck movementCheck){
        movementChecks.add(movementCheck);
    }

    public static void handle(final GACPlayer player, final Location from, final Location to){
        if(!from.getWorld().equals(to.getWorld()))return;
        for(MovementCheck move : movementChecks){
            move.onMovementCheck(player, from, to);
        }
    }

}
