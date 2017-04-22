package fr.golderpotato.ac.bungee.command.commands;

import com.google.common.io.ByteArrayDataInput;
import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.bungee.BungeePlugin;
import fr.golderpotato.ac.bungee.command.Command;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Eliaz on 31/01/2017.
 */
public class AlertMods extends Command{

    public AlertMods(){
        super("alertmods");
    }

    @Override
    public void execute(ByteArrayDataInput in) {
        String t = in.readUTF();
        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(t);
        if(pp == null)return;
        String reason = in.readUTF();
        System.out.println("The player "+pp.getName()+" might be using "+reason);
        for(ProxiedPlayer pps : ProxyServer.getInstance().getPlayers()){
            if(pps.hasPermission("gac.alerts")){
                TextComponent text = new TextComponent(BungeePlugin.getInstance().prefix +"The player "+pp.getName()+" might be using "+reason);
                text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bungeetp "+pp.getName()));
                text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("TP: "+pp.getName()).create()));
                pps.sendMessage(text);
            }
        }
    }
}
