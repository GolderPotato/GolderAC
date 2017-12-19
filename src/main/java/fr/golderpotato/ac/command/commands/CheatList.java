package fr.golderpotato.ac.command.commands;

import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;

/**
 * Created by Eliaz on 26/02/2017.
 */
public class CheatList extends CommandAbstract{

    public CheatList(){
        super("cl");
    }

    @Override
    public void handle(final GACPlayer player, final String[] args) {
        player.sendMessage("§e==================");
        player.sendMessage("§eListe des cheats:");
        for(CheatType ct : CheatType.values()){
            player.sendMessage("§e"+ct.cheatname);
        }
        player.sendMessage("§e==================");
    }
}
