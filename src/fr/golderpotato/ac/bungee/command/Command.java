package fr.golderpotato.ac.bungee.command;

import com.google.common.io.ByteArrayDataInput;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Eliaz on 31/01/2017.
 */
public abstract class Command {

    public String name;

    public Command(String name){
        this.name = name;
    }

    public abstract void execute(ByteArrayDataInput in);

}
