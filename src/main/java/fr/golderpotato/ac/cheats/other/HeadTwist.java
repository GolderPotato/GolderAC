package fr.golderpotato.ac.cheats.other;

import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;

/**
 * Created by Eliaz on 02/06/2017.
 */
public class HeadTwist extends CheatListener{

    @Override
    public void onMovementCheck(final GACPlayer player, final Location from, final Location to) {
        if(player == null)return;
        if(Math.abs(to.getPitch()) > 90){
            CheatType.ILLEGALMOVEMENT.alertMods(player, "Head Twist");
        }
    }
}
