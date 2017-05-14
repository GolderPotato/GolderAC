package fr.golderpotato.ac.cheats.movement.checks;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Eliaz on 19/01/2017.
 */
public class Glide extends CheatListener{

    @Override
    public void onMovementCheck(GACPlayer player, Location from, Location to) {
        if(player.getOnGround())return;
        if(!player.needsCheck())return;
        double y = 0;
        if(to.getY() < from.getY()){
            y = from.getY() - to.getY();
        }else{
            return;
        }
        if(to.getY() % 1 == 0){
            return;
        }
        if(y == 0.23052736891295922)return;
        if(y == player.GlideY){
            player.Glide++;
        }
        player.GlideY = y;
    }
}
