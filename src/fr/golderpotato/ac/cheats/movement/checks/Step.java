package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Eliaz on 19/01/2017.
 */
public class Step extends CheatListener{

    @Override
    public void onMovementCheck(GACPlayer player, Location from, Location to) {
        if(!player.needsCheck())return;
        if(!player.isOnGround())return;
        double i = to.getY() - from.getY();
        if(i % (double)CheatType.STEP.getValue("height") == 0.0D && i != 0.0D){
            CheatType.STEP.alertMods(player);
        }
    }
}
