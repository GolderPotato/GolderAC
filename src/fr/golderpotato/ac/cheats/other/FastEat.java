package fr.golderpotato.ac.cheats.other;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by Eliaz on 01/03/2017.
 */
public class FastEat extends CheatListener{

    @EventHandler
    public void onEat(FoodLevelChangeEvent event){
        Player player = (Player) event.getEntity();
        if(player == null)return;
        GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        if(gplayer == null)return;
        double time = System.currentTimeMillis() - gplayer.FastFoodTime;
        if(gplayer.LastFoodLevel > gplayer.getFoodLevel() || gplayer.getFoodLevel() == 0.0D){
            gplayer.LastFoodLevel = gplayer.getFoodLevel();
            return;
        }
        if(time < (int)CheatType.FASTEAT.getValue("eatdelay")){
            CheatType.FASTEAT.alertMods(gplayer);
        }
        gplayer.FastFoodTime = System.currentTimeMillis();
        gplayer.LastFoodLevel = gplayer.getFoodLevel();
    }

}
