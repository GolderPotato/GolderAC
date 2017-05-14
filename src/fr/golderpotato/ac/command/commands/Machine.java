package fr.golderpotato.ac.command.commands;

import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.machine.MachineUtils;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 24/04/2017.
 */
public class Machine extends CommandAbstract{

    public Machine(){
        super("machine");
    }

    @Override
    public void handle(GACPlayer player, String[] args) {
        if(player.isDev()){
            if(args.length == 0){
                player.sendMessage(ChatUtils.getPrefix()+"You must enter a command :)");
            }
            try {
                StringBuilder command = new StringBuilder();
                for(int i = 0; i < args.length;i++){
                    command.append(args[i]+" ");
                }
                player.sendMessage(MachineUtils.executeCommandWithOutput(command.toString()));
            }catch (Exception e){
                player.sendMessage(e.getMessage());
            }
        }
    }
}
