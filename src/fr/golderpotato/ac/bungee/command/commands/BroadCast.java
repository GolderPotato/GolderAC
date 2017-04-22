package fr.golderpotato.ac.bungee.command.commands;

import com.google.common.collect.BinaryTreeTraverser;
import com.google.common.io.ByteArrayDataInput;
import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.bungee.BungeePlugin;
import fr.golderpotato.ac.bungee.command.Command;
import fr.golderpotato.ac.player.GACPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Eliaz on 14/02/2017.
 */
public class BroadCast extends Command{

    public BroadCast(){
        super("broadcast");
    }

    @Override
    public void execute(ByteArrayDataInput in) {
        String player = in.readUTF();
        ProxiedPlayer gplayer = BungeePlugin.getInstance().getProxy().getPlayer(player);
        if(gplayer == null){
            return;
        }
        String reason = in.readUTF();
        BungeePlugin.getInstance().getProxy().broadcast(new TextComponent(BungeePlugin.getInstance().prefix+"Player "+player+" was banned for "+reason));
    }
}
