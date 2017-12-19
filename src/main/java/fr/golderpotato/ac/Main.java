package fr.golderpotato.ac;

import fr.golderpotato.ac.rabbit.RabbitMQ;
import fr.golderpotato.ac.rabbit.packet.PacketDestination;
import fr.golderpotato.ac.rabbit.packet.PacketListener;
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
import fr.golderpotato.ac.utils.machine.ConsoleColours;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Console;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class Main extends JavaPlugin{

    public static Main instance;

    public boolean disabled = false;
    public boolean doSQL;

    public Map<Player, GACPlayer> GACPlayers;
    public CommandMap commandmap;

    public ListenerManager listenermanager;
    public GACPackets GACpackets;
    public CheatListenerManager cheatlistenermanager;
    public CommandManager commandmanager;
    public Bypass bypass;
    public MainRunnable mainrunnable;
    public NoFallRunnable noFallRunnable;
    public SQLConnection connection;
    public BanSQL banSQL;
    public LogSQL logSQL;
    public Twitter twitter;

    @Override
    public void onEnable() {
        displaySplash();

        print(ConsoleColours.GREEN+"Saving config..."+ConsoleColours.RESET);
        saveDefaultConfig();

        if(!getConfig().getBoolean("pluginEnabled") || !getConfig().getBoolean("eula")){
            print(ConsoleColours.RED+"FATAL! §2The plugin is not enabled in the config file or you did not agree to the eula! Plugin is disabled! Enable the plugin in the config file and agree to the eula."+ConsoleColours.RESET);
            disabled = true;
            return;
        }

        print(ConsoleColours.GREEN+"Loading plugin..."+ConsoleColours.RESET);
        instance = this;

        print(ConsoleColours.GREEN+"Instantiating variables..."+ConsoleColours.RESET);
        this.GACPlayers = new HashMap<>();
        this.GACpackets = new GACPackets();
        this.listenermanager = new ListenerManager(this);
        this.listenermanager.registerListeners();
        this.cheatlistenermanager = new CheatListenerManager();
        this.cheatlistenermanager.registerCheats();
        this.commandmanager = new CommandManager();
        this.bypass = new Bypass();

        this.mainrunnable = new MainRunnable();
        mainrunnable.runTaskTimer(instance, 0, 20);

        this.noFallRunnable = new NoFallRunnable();
        noFallRunnable.runTaskTimer(instance, 0, 5);

        print(ConsoleColours.BLUE+"Connecting to MySQL database..."+ConsoleColours.RESET);
        if(getConfig().getBoolean("sql.enabled")){
            this.connection = new SQLConnection(getConfig().getString("host"), getConfig().getString("dbname"), getConfig().getString("username"), getConfig().getString("password"));
            this.banSQL = new BanSQL();
            this.logSQL = new LogSQL();
            doSQL = true;
        }else{
            print(ConsoleColours.RED+"SQL connection aborted! (disabled in config file)"+ConsoleColours.RESET);
        }

        print(ConsoleColours.BLUE+"Connecting to RabbitMQ"+ConsoleColours.RESET);
        try{
            RabbitMQ.getInstance();
            print(ConsoleColours.GREEN+"RabbitMap successfully connected!"+ConsoleColours.RESET);
        }catch (Exception e){
            print(ConsoleColours.RED+"Error connecting to RabbitMQ!"+ConsoleColours.RESET);
        }

        new PacketListener(PacketDestination.SPIGOT);

        print(ConsoleColours.GREEN+"Kicking any player that is online..."+ConsoleColours.RESET);
        for(Player players : Bukkit.getOnlinePlayers()){
            players.kickPlayer("Le serveur redémarre! Merci de ta patience =D");
        }

        print(ConsoleColours.BLUE+"Setting up commandMap..."+ConsoleColours.RESET);
        try{
            if(Bukkit.getServer() instanceof CraftServer){
                final Field f = CraftServer.class.getDeclaredField("commandMap");
                f.setAccessible(true);
                this.commandmap = (CommandMap)f.get(Bukkit.getServer());
                print(ConsoleColours.GREEN+"CommandMap sucessfuly initialized!"+ConsoleColours.RESET);
            }
        } catch (Exception e) {
            print(ConsoleColours.RED+"Error with CommandMap!"+ConsoleColours.RESET);
            e.printStackTrace();
        }

        print(ConsoleColours.BLUE+"Setting prefix..."+ConsoleColours.RESET);
        if(ChatUtils.getPrefix() == ""){
            ChatUtils.setPrefix(getConfig().getString("prefix"));
            if(ChatUtils.getPrefix() == ""){
                ChatUtils.setPrefix(ConsoleColours.RED+"[ERROR IN CONFIG!] prefix not found"+ConsoleColours.RESET);
            }
        }

        print(ConsoleColours.BLUE+"Registering commands..."+ConsoleColours.RESET);
        this.commandmanager.registerCommands();
        print(ConsoleColours.GREEN+"Commands registered!"+ConsoleColours.RESET);

        print(ConsoleColours.BLUE+"Connecting to Twitter..."+ConsoleColours.RESET);
        if(getConfig().getBoolean("twitter.enabled")) {
            try{
                twitter = new Twitter();
                twitter.connect();
            }catch (Exception e){
                print(ConsoleColours.RED+"Connection to twitter failed! ERROR:"+ConsoleColours.RESET);
                e.printStackTrace();
            }
        }else{
            print(ConsoleColours.RED+"Twitter connection aborted! (Disabled in config)"+ConsoleColours.RESET);
        }

        print(ConsoleColours.BLUE+"Setting start time..."+ConsoleColours.RESET);
        ServerUtil.startTime = System.currentTimeMillis();
        print(ConsoleColours.GREEN+"Start time set!"+ConsoleColours.RESET);
    }

    @Override
    public void onDisable() {
        if(!disabled){
            print("Unloading plugin...");
            if(getConfig().getBoolean("sql.enabled")){
                connection.closeConnection();
            }
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

    public void print(final String message){
        System.out.println("["+getName()+"] "+message);
    }

    // PLAYER
    public GACPlayer getGACPlayer(Player player){
        return GACPlayers.get(player);
    }

    public void addGACPLayer(final Player player){
        if(GACPlayers.get(player) == null) {
            GACPlayers.put(player, new GACPlayer(player));
        }
    }

    public void addGACPLayer(final GACPlayer player){
      if(!GACPlayers.containsValue(player)){
          GACPlayers.put(player.getPlayer(), player);
      }
    }

    public void removeGACPlayer(final Player player){
        if(GACPlayers.get(player) != null){
            GACPlayers.remove(player);
        }
    }

    public void displaySplash(){
        System.out.println(
                                "\n"+
                                ConsoleColours.CYAN+"  ____       _     _            "+ConsoleColours.RED+"_    ____ \n"+
                                ConsoleColours.CYAN+" / ___| ___ | | __| | ___ _ __ "+ConsoleColours.RED+"/ \\  / ___|\n" +
                                ConsoleColours.CYAN+"| |  _ / _ \\| |/ _` |/ _ \\ '__"+ConsoleColours.RED+"/ _ \\| |    \n" +
                                ConsoleColours.CYAN+"| |_| | (_) | | (_| |  __/ | "+ConsoleColours.RED+"/ ___ \\ |___ \n" +
                                ConsoleColours.CYAN+" \\____|\\___/|_|\\__,_|\\___|_|"+ConsoleColours.RED+"/_/   \\_\\____|\n\n" +
                                ConsoleColours.RESET);
    }



    // GETTERS
    public final Bypass getBypass() {
        return bypass;
    }

    public final Map<Player, GACPlayer> getGACPlayers() {
        return GACPlayers;
    }
}
