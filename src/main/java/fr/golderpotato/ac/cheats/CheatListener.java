package fr.golderpotato.ac.cheats;

import fr.golderpotato.ac.cheats.movement.MovementCheck;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;
import org.bukkit.event.Listener;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class CheatListener implements Listener, MovementCheck{

    public void runSchedule(){};
    public void setupListener(){};


    @Override
    public void onMovementCheck(final GACPlayer player, final Location from, final Location to) {

    }
}
