package fr.golderpotato.ac.command.commands;

import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;

/**
 * Created by Eliaz on 26/02/2017.
 */
public class ClearChat extends CommandAbstract{

    public ClearChat(){
        super("cc");
    }

    @Override
    public void handle(GACPlayer player, String[] args) {
        if(!player.hasPermission("gac.chatclear"))return;
        for(int i = 0; i < 20;i++){
            player.sendMessage(" ");
        }
    }
}
