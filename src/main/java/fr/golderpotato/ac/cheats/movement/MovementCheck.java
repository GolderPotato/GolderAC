package fr.golderpotato.ac.cheats.movement;

import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;

/**
 * Created by Eliaz on 27/02/2017.
 */
public interface MovementCheck {

    void onMovementCheck(final GACPlayer player, final Location from, final Location to);

}
