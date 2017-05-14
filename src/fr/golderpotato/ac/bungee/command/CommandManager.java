package fr.golderpotato.ac.bungee.command;

import com.google.common.io.ByteArrayDataInput;
import fr.golderpotato.ac.bungee.command.commands.AlertMods;
import fr.golderpotato.ac.bungee.command.commands.BroadCast;
import fr.golderpotato.ac.bungee.command.commands.BungeeTP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliaz on 31/01/2017.
 */
public class CommandManager {

    public List<Command> commands = new ArrayList<>();

    public void setup(){
        add(new AlertMods());
        add(new BroadCast());
        add(new BungeeTP());
    }

    public void add(Command command){
        commands.add(command);
    }

    public void run(String cmd, ByteArrayDataInput in){
        for(Command commmand : commands){
            if(commmand.name.equalsIgnoreCase(cmd)){
                commmand.execute(in);
                break;
            }
        }
    }
}
