package fr.golderpotato.ac;

import fr.golderpotato.ac.runnable.MainRunnable;
import fr.golderpotato.ac.cheats.CheatListenerManager;
import fr.golderpotato.ac.command.CommandManager;
import fr.golderpotato.ac.listener.ListenerManager;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.runnable.NoFallRunnable;
import fr.golderpotato.ac.sql.BanSQL;
import fr.golderpotato.ac.sql.LogSQL;
import fr.golderpotato.ac.sql.SQLConnection;
import fr.golderpotato.ac.twitter.Twitter;
import fr.golderpotato.ac.utils.PluginMessage;
import fr.golderpotato.ac.utils.ServerUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class Main extends JavaPlugin{

    public static Main instance;
    public boolean disabled = false;
    public Map<Player, GACPlayer> GACPlayers;
    public ListenerManager listenermanager;
    public GACPackets GACpackets;
    public CheatListenerManager cheatlistenermanager;
    public CommandManager commandmanager;
    public Bypass bypass;
    public MainRunnable mainrunnable;
    public NoFallRunnable noFallRunnable;
    public CommandMap commandmap;
    public SQLConnection connection;
    public BanSQL banSQL;
    public LogSQL logSQL;
    public boolean doSQL;
    public Twitter twitter;

    @Override
    public void onEnable() {
        print("Saving config...");
        saveDefaultConfig();
        if(!getConfig().getBoolean("pluginEnabled") || !getConfig().getBoolean("eula")){
            print(ChatColor.GREEN+"FATAL! §2The plugin is not enabled in the config file or you did not agree to the eula! Plugin is disabled! Enable the plugin in the config file and agree to the eula.");
            disabled = true;
            return;
        }
        print("Loading plugin...");
        print("Instantiating variables...");
        instance = this;
        GACPlayers = new HashMap<>();
        listenermanager = new ListenerManager(this);
        listenermanager.registerListeners();
        GACpackets = new GACPackets();
        cheatlistenermanager = new CheatListenerManager();
        cheatlistenermanager.registerCheats();
        commandmanager = new CommandManager();
        bypass = new Bypass();
        mainrunnable = new MainRunnable();
        mainrunnable.runTaskTimer(instance, 0, 20);
        noFallRunnable = new NoFallRunnable();
        noFallRunnable.runTaskTimer(instance, 0, 5);
        print("Connecting to MySQL database...");
        if(getConfig().getBoolean("sql.enabled")){
            connection = new SQLConnection(getConfig().getString("host"), getConfig().getString("dbname"), getConfig().getString("username"), getConfig().getString("password"));
            banSQL = new BanSQL();
            logSQL = new LogSQL();
            doSQL = true;
        }else{
            print("SQL connection aborted! (disabled in config file)");
        }


        print("Initializing Plugin messager");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "gac", new PluginMessage());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "gac");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


        print("Kicking any player that is online...");
        for(Player players : Bukkit.getOnlinePlayers()){
            players.kickPlayer("Le serveur redémarre! Merci de ta patience =D");
        }

        try{
            if(Bukkit.getServer() instanceof CraftServer){
                final Field f = CraftServer.class.getDeclaredField("commandMap");
                f.setAccessible(true);
                this.commandmap = (CommandMap)f.get(Bukkit.getServer());
            }
        } catch (Exception e) {
            print("Error with CommandMap!");
            e.printStackTrace();
        }

        print("Setting prefix...");
        if(ChatUtils.getPrefix() == ""){
            ChatUtils.setPrefix(getConfig().getString("prefix"));
            if(ChatUtils.getPrefix() == ""){
                ChatUtils.setPrefix("[ERROR IN CONFIG!] ");
            }
        }
        print("Registering commands...");
        this.commandmanager.registerCommands();
        print("Commands registered!");

        print("Connecting to Twitter...");
        if(getConfig().getBoolean("twitter.enabled")) {
            try{
                twitter = new Twitter();
                twitter.connect();
            }catch (Exception e){
                print("Connection to twitter failed! ERROR:");
                e.printStackTrace();
            }
        }
        print("Setting start time...");
        ServerUtil.startTime = System.currentTimeMillis();
        print("Start time set!");
    }

    @Override
    public void onDisable() {
        if(!disabled){
            print("Unloading plugin...");
            if(getConfig().getBoolean("sql.enabled")){
                connection.closeConnection();
            }
            print("Updating twitter status...");
            print("Goodbye!");
        }
    }

    @Override
    public void onLoad() {

    }

    // BASIC MAIN FUNCTIONS
    public static Main getInstance() {
        return instance;
    }

    public void print(String message){
        System.out.println("["+getName()+"] "+message);
    }

    // PLAYER
    public GACPlayer getGACPlayer(Player player){
        return GACPlayers.get(player);
    }

    public void addGACPLayer(Player player){
        if(GACPlayers.get(player) == null) {
            GACPlayers.put(player, new GACPlayer(player));
        }
    }

    public void addGACPLayer(GACPlayer player){
      if(!GACPlayers.containsValue(player)){
          GACPlayers.put(player.getPlayer(), player);
      }
    }

    public void removeGACPlayer(Player player){
        if(GACPlayers.get(player) != null){
            GACPlayers.remove(player);
        }
    }

    // GETTERS
    public Bypass getBypass() {
        return bypass;
    }

    public Map<Player, GACPlayer> getGACPlayers() {
        return GACPlayers;
    }
}
