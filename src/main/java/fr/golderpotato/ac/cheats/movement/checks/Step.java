package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;

/**
 * Created by Eliaz on 19/01/2017.
 */
public class Step extends CheatListener{

    @Override
    public void onMovementCheck(final GACPlayer player, final Location from, final Location to) {
        if(!player.needsCheck())return;
        if(!player.isOnGround())return;
        final double i = to.getY() - from.getY();
        if(i % (double)CheatType.STEP.getValue("height") == 0.0D && i != 0.0D){
            CheatType.STEP.alertMods(player);
        }
    }
}
