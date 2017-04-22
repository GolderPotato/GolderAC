package fr.golderpotato.ac.bungee;

import fr.golderpotato.ac.bungee.bungee.listener.MessageEvent;
import fr.golderpotato.ac.bungee.command.CommandManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Eliaz on 21/01/2017.
 */
public class BungeePlugin extends Plugin {

    public static BungeePlugin instance;
    public CommandManager commandManager;
    public String prefix;

    @Override
    public void onEnable() {
        print("Loading GAC plugin extention");
        instance = this;
        ProxyServer.getInstance().registerChannel("gac");
        ProxyServer.getInstance().registerChannel("BungeeCord");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new MessageEvent());
        commandManager = new CommandManager();
        commandManager.setup();
        prefix = "§8[§cANTI-CHEAT§8] §a";
    }



    public void print(String message){
        System.out.println(message);
    }

    public static BungeePlugin getInstance() {
        return instance;
    }
}
