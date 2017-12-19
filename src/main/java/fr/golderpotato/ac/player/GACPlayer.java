package fr.golderpotato.ac.player;

import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.Packet;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class GACPlayer extends CraftPlayer{

    public final Player player;

    public GACPlayer(final Player player){
        super((CraftServer) Bukkit.getServer(), ((CraftPlayer)player).getHandle());
        this.player = player;
    }

    public final HashMap<CheatType, String> violations = new HashMap<>();
    public final ArrayList<Integer> pings = new ArrayList<Integer>();
    public final HashMap<Double, Packet> packethistory = new HashMap<Double, Packet>();


    public boolean wentUP;
    public boolean wentDOWN;
    public boolean tabComple = false;

    public int AllPackets = 0;
    public int KeepAlive = 0;
    public int Look = 0;
    public int PositionLook = 0;
    public int Flying = 0;
    public int Position = 0;
    public int FlyLevel = 0;
    public int nofall_Look = 0;
    public int nofall_PositionLook = 0;
    public int nofall_Flying = 0;
    public int nofall_Position = 0;
    public int nofall = 0;
    public int CPS = 0;
    public int Glide = 0;
    public int Fly = 0;
    public int forceField;
    public int ffID;
    public int fastplace;
    public int speedhack;
    public int safeWalk = 0;
    public int LastFoodLevel = 0;
    public int NoSlowDown;
    public int spiderLevel = 0;
    public int Jesus = 0;

    public double nofall_height = 0.0D;
    public double GlideY = 0;
    public double JesusY = 0;
    public double ffTIME;
    public double JumpTime = 0;
    public double FastPlaceTime = 0;
    public double FastFoodTime = 0;
    public double lastSpiderY = 0;

    public long LastBowTime = 0;

    public Location last = new Location(getWorld(), 0, 0, 0);

    public final boolean getOnGround(){
        Block down = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if(player.getLocation().getBlock().getType() != Material.AIR)return true;
        if(down.getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.NORTH).getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.SOUTH).getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.EAST).getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.WEST).getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.NORTH_EAST).getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.NORTH_WEST).getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.SOUTH_EAST).getType() != Material.AIR)return true;
        if(down.getRelative(BlockFace.SOUTH_WEST).getType() != Material.AIR)return true;

        return false;
    }

    public final boolean hasMaterialAround(Material mat){
        Block down = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if(player.getLocation().getBlock().getType() == mat)return true;
        if(down.getType() == mat)return true;
        if(down.getRelative(BlockFace.NORTH).getType() == mat)return true;
        if(down.getRelative(BlockFace.SOUTH).getType() == mat)return true;
        if(down.getRelative(BlockFace.EAST).getType() == mat)return true;
        if(down.getRelative(BlockFace.WEST).getType() == mat)return true;
        if(down.getRelative(BlockFace.NORTH_EAST).getType() == mat)return true;
        if(down.getRelative(BlockFace.NORTH_WEST).getType() == mat)return true;
        if(down.getRelative(BlockFace.SOUTH_EAST).getType() == mat)return true;
        if(down.getRelative(BlockFace.SOUTH_WEST).getType() == mat)return true;

        return false;
    }

    public final boolean isRiding(){
        return player.getVehicle() != null;
    }

    public final Integer getPing(){
        return ((CraftPlayer)player).getHandle().ping;
    }

    public final Player getPlayer(){
        return player;
    }

    public final float getYaw(){
        return getLocation().getYaw();
    }

    public final float getPitch(){
        return getLocation().getPitch();
    }

    public void respawn(){
        spigot().respawn();
    }

    public void kill(){
        player.damage(Integer.MAX_VALUE);
    }


    public void setTakeNoDamage(final boolean noDamage){
        if(noDamage){
            player.setMaximumNoDamageTicks(Integer.MAX_VALUE);
        }else{
            player.setMaximumNoDamageTicks(20);
        }
    }

    public void ban(final CheatType cheatType){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban "+player.getName()+" YOU GOT BANNED FOR CHEAT ;'( ("+cheatType.cheatname+")");
    }

    public void ban(final String reason){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban "+player.getName()+" YOU GOT BANNED ("+reason+")");
    }

    public final List<Packet> getPacketsInLastSecond(){
        ArrayList<Packet> toReturn = new ArrayList<>();
        for(double i = System.currentTimeMillis() - 1000; i < System.currentTimeMillis(); i++){
            if(packethistory.containsKey(i)) {
                toReturn.add(packethistory.get(i));
            }
        }
        return toReturn;
    }

    public final List<Packet> getAllPacketsSince(final double time){
        ArrayList<Packet> toReturn = new ArrayList<>();
        for(double i = System.currentTimeMillis() - (System.currentTimeMillis() - time); i < System.currentTimeMillis(); i++){
            if(packethistory.containsKey(i)) {
                toReturn.add(packethistory.get(i));
            }
        }
        return toReturn;
    }

    public void reset(){
        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(false);
        ((CraftPlayer)player).getHandle().inventory.b(new NBTTagList());
        player.setSprinting(false);
        player.setFoodLevel(20);
        player.setSaturation(3.0f);
        player.setExhaustion(0.0f);
        player.setMaxHealth(20.0);
        player.setHealth((player).getMaxHealth());
        player.setFireTicks(0);
        player.setFallDistance(0.0f);
        player.setLevel(0);
        player.setExp(0.0f);
        player.setWalkSpeed(0.2f);
        player.setFlySpeed(0.1f);
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.updateInventory();
        for (final PotionEffect potion : player.getActivePotionEffects()) {
            player.removePotionEffect(potion.getType());
        }
    }

    public final double getLastPacketTime(String packetName){
        for(int i = packethistory.size(); i > 0;i++){
            if(packethistory.get(i).getPacketName() == packetName){
                return i;
            }
        }
        return -1;
    }

    public void clear(){
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.updateInventory();
    }

    public void sendACMessage(final String message){
        sendMessage(ChatUtils.getPrefix()+message);
    }

    public void broadcast(final String message){
        Bukkit.getServer().broadcastMessage("§e["+getName()+"] §f"+message);
    }

    public void removePotionEffects(){
        for(PotionEffect effects : getActivePotionEffects()){
            removePotionEffect(effects.getType());
        }
    }

    public boolean needsCheck(){
        if(Main.getInstance().bypass.isByPassed(this))return false;
        if(getGameMode().equals(GameMode.CREATIVE) || getGameMode().equals(GameMode.SPECTATOR))return false;
        if(!isOnline())return false;
        if(isBanned())return false;
        if(isDead())return false;
        return true;
    }

    public boolean isDev(){
        return hasPermission("gac.dev");
    }
}
