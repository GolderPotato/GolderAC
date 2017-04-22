package fr.golderpotato.ac.bungee.bungee.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.golderpotato.ac.bungee.BungeePlugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;

/**
 * Created by Eliaz on 21/01/2017.
 */
public class MessageEvent implements Listener{


    @EventHandler
    public void onMessage(PluginMessageEvent event){
        System.out.println("Received plugin message...");
        if(!event.getTag().equals("gac"))return;
        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String command = in.readUTF();
        BungeePlugin.getInstance().commandManager.run(command, in);
    }



}
