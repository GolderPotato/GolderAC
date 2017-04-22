package fr.golderpotato.ac.listener;

import fr.golderpotato.ac.listener.listeners.PlayerJoin;

import fr.golderpotato.ac.listener.listeners.PlayerQuit;
import fr.golderpotato.ac.listener.listeners.PlayerRespawn;
import fr.golderpotato.ac.listener.listeners.PlayerTeleport;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class ListenerManager {

    Plugin plugin;
    PluginManager pm;

    public ListenerManager(Plugin plugin){
        this.plugin = plugin;
        pm = Bukkit.getPluginManager();
    }

    public void registerListeners(){
        register(new PlayerJoin());
        register(new PlayerQuit());
        register(new PlayerRespawn());
        register(new PlayerTeleport());
    }

    public void register(Listener listener){
        pm.registerEvents(listener, this.plugin);
    }
}
