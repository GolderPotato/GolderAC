package fr.golderpotato.ac.command;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 15/01/2017.
 */
public abstract class CommandAbstract extends Command{

    public final String name;

    public CommandAbstract(String name){
        super(name);
        this.name = name;
        Main.getInstance().commandmanager.commands.add(this);
    }


    public abstract void handle(final GACPlayer player, final String[] args);

    @Override
    public boolean execute(final CommandSender commandSender, final String s, final String[] strings){
        if(!(commandSender instanceof Player)){
            System.out.println("Cannot send this command through the console!");
        }
        final GACPlayer gplayer = Main.getInstance().getGACPlayer((Player)commandSender);
        handle(gplayer, strings);
        return false;
    }
}
