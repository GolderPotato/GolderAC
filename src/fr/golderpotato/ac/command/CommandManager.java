package fr.golderpotato.ac.command;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.command.commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class CommandManager {

    public List<CommandAbstract> commands = new ArrayList<>();

    public void registerCommands(){

        new Velocity();
        new PluginMessage();
        new BungeeTP();
        new Ban();
        new CheatList();
        new ClearChat();
        new PacketList();
        new Machine();

        for(CommandAbstract cmds : commands){
            Main.getInstance().commandmap.register(Main.getInstance().getName(), cmds);
        }
    }



}
