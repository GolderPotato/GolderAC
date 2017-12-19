package fr.golderpotato.ac.runnable;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 19/03/2017.
 */
public class NoFallRunnable extends BukkitRunnable{

    public void run(){
        for(GACPlayer gplayers : Main.getInstance().getGACPlayers().values()){
            int nofall = gplayers.nofall_Flying + gplayers.nofall_PositionLook + gplayers.nofall_Position + gplayers.nofall_Look;
            if(nofall > 14 && gplayers.isOnGround() && !gplayers.getOnGround()){
                CheatType.NOFALL.alertMods(gplayers);
            }

            gplayers.nofall_Look = 0;
            gplayers.nofall_Flying = 0;
            gplayers.nofall_Position = 0;
            gplayers.nofall_PositionLook = 0;
        }
    }
}
