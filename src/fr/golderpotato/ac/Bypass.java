package fr.golderpotato.ac;

import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.GameMode;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eliaz on 16/01/2017.
 */
public class Bypass {

    public ArrayList<GACPlayer> gacPlayers = new ArrayList<>();

    public boolean isByPassed(GACPlayer gplayer){
        if(Main.getInstance().getConfig().getBoolean("alertops") && gplayer.hasPermission("gac.bypass")){
            return true;
        }
        return gacPlayers.contains(gplayer);
    }

    public void addByPassed(GACPlayer player){
        gacPlayers.add(player);
    }

    public void removeByPassed(GACPlayer player){
        gacPlayers.remove(player);
    }
}
