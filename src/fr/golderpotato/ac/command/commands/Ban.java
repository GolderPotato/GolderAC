package fr.golderpotato.ac.command.commands;

import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 24/02/2017.
 */
public class Ban extends CommandAbstract{

    public Ban(){
        super("gban");
    }

    @Override
    public void handle(GACPlayer player, String[] args) {
        if(!player.hasPermission("gac.acban"))return;
        if(!(args.length != 2));
        Player target = Bukkit.getPlayer(String.valueOf(args[0]));
        if(target == null){
            player.sendMessage(ChatUtils.getPrefix()+"Wrong command!");
            return;
        }
        for(CheatType cheatType : CheatType.values()){
            if(cheatType.cheatname.equalsIgnoreCase(String.valueOf(args[1]))){
                cheatType.ban(Main.getInstance().getGACPlayer(target));
            }
        }

    }
}
