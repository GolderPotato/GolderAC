package fr.golderpotato.ac.bungee.command.commands;

import com.google.common.io.ByteArrayDataInput;
import fr.golderpotato.ac.bungee.BungeePlugin;
import fr.golderpotato.ac.bungee.command.Command;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Eliaz on 14/02/2017.
 */
public class BungeeTP extends Command{

    public BungeeTP(){
        super("bungeetp");
    }

    @Override
    public void execute(ByteArrayDataInput in) {
        String player1name = in.readUTF();
        ProxiedPlayer player1 = BungeePlugin.getInstance().getProxy().getPlayer(player1name);
        if(player1 == null)return;
        
        String player2name = in.readUTF();
        ProxiedPlayer player2 = BungeePlugin.getInstance().getProxy().getPlayer(player2name);
        if(player2 == null)return;

        player1.connect(player2.getServer().getInfo());
    }
}
