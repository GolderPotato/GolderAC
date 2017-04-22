package fr.golderpotato.ac.command.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 30/01/2017.
 */
public class PluginMessage extends CommandAbstract{

    public PluginMessage(){
        super("message");
    }

    @Override
    public void handle(GACPlayer player, String[] args) {
        if(!player.isDev())return;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("alertmods");
        out.writeUTF(String.valueOf(args[0]));
        out.writeUTF("CHEAT!");
        Bukkit.getServer().sendPluginMessage(Main.getInstance(), "gac", out.toByteArray());

    }
}
