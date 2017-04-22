package fr.golderpotato.ac.command.commands;


import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.command.CommandAbstract;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.util.Vector;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class Velocity extends CommandAbstract {

    public Velocity(){
        super("vel");
    }

    @Override
    public void handle(GACPlayer player, String[] args) {
        if(player == null){
            return;
        }
        if(!player.isDev())return;

        player.setVelocity(new Vector( Double.valueOf(args[0]),Double.valueOf(args[1]), Double.valueOf(args[2])));

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("lol");
        out.writeUTF("mdr");
        player.sendPluginMessage(Main.getInstance(), "gac", out.toByteArray());

        return;
    }
}
