package fr.golderpotato.ac.cheats;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.combat.*;
import fr.golderpotato.ac.cheats.combat.focefield.*;
import fr.golderpotato.ac.cheats.movement.MovementCheckHandler;
import fr.golderpotato.ac.cheats.movement.MovementCheckManager;
import fr.golderpotato.ac.cheats.movement.checks.*;
import fr.golderpotato.ac.cheats.other.FastEat;
import fr.golderpotato.ac.cheats.other.FastPlace;
import fr.golderpotato.ac.cheats.other.MoreInventory;
import fr.golderpotato.ac.cheats.other.NoFall;
import fr.golderpotato.ac.cheats.packet.*;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class CheatListenerManager {

    public List<CheatListener> cheats;

    public void registerCheats(){
        cheats = new ArrayList<>();

        //COMBAT
        add(new EntityUseListener());
        add(new FastBow());
        add(new AntiKnockBack());

        add(new ForceFieldA());
        add(new ForceFieldB());
        add(new ForceFieldC());
        add(new Reach());

        //MOVEMENT
        add(new Flight());
        add(new SpeedHack());
        //add(new NoSlowDown());
        add(new Step());
        add(new Glide());
        add(new Jesus());
        add(new HighJump());
        //add(new SafeWalk());
        add(new Spider());

        //OTHER
        add(new Blink());
        add(new MoreInventory());
        add(new FastPlace());
        add(new FastEat());
        add(new NoFall());

        add(new TabComplete());

        // MOVEMENTMANAGER
        add(new MovementCheckHandler());

        for(CheatListener cl : cheats){
            cl.runSchedule();
            cl.setupListener();
            Bukkit.getPluginManager().registerEvents(cl, Main.getInstance());
            MovementCheckManager.add(cl);
        }
    }

    public void add(CheatListener cheatListener){
        cheats.add(cheatListener);
    }
}
