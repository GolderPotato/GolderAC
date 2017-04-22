package fr.golderpotato.ac.player;

import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.Packet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.DoubleChest;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.ELF;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.generator.InternalChunkGenerator;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 */
public class GACPlayer extends CraftPlayer{

    public Player player;

    public GACPlayer(Player player){
        super((CraftServer) Bukkit.getServer(), ((CraftPlayer)player).getHandle());
        this.player = player;
    }

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
    public int nofall_FlyLevel = 0;

    public int CPS = 0;
    public long LastBowTime = 0;
    public int Glide = 0;
    public double GlideY = 0;
    public int Jesus = 0;
    public double JesusY = 0;
    public int Fly = 0;
    public int forceField;
    public int ffID;
    public double ffTIME;
    public double JumpTime = 0;
    public double FastPlaceTime = 0;
    public double FastFoodTime = 0;
    public int LastFoodLevel = 0;
    public int NoSlowDown;
    public boolean tabComple = false;
    public int fastplace;
    public int speedhack;


    public List<Float> cpslog = new ArrayList<Float>();
    public ArrayList<Integer> pings = new ArrayList<Integer>();
    public ArrayList<Boolean> sprints = new ArrayList<>();

    public Location last = new Location(getWorld(), 0, 0, 0);

    public HashMap<Double, Packet> packethistory = new HashMap<Double, Packet>();

    public boolean getOnGround(){
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

    public boolean hasMaterialAround(Material mat){
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

    public boolean isRiding(){
        return player.getVehicle() != null;
    }

    public Integer getPing(){
        return ((CraftPlayer)player).getHandle().ping;
    }

    public Player getPlayer(){
        return player;
    }

    public float getYaw(){
        return getLocation().getYaw();
    }

    public float getPitch(){
        return getLocation().getPitch();
    }

    public void respawn(){
        spigot().respawn();
    }

    public void kill(){
        player.damage(Integer.MAX_VALUE);
    }


    public void setTakeNoDamage(boolean noDamage){
        if(noDamage){
            player.setMaximumNoDamageTicks(Integer.MAX_VALUE);
        }else{
            player.setMaximumNoDamageTicks(20);
        }
    }

    public void ban(CheatType cheatType){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban "+player.getName()+" YOU GOT BANNED FOR CHEAT ;'( ("+cheatType.cheatname+")");
    }

    public void ban(String reason){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban "+player.getName()+" YOU GOT BANNED ("+reason+")");
    }

    public List<Packet> getPacketsInLastSecond(){
        ArrayList<Packet> toReturn = new ArrayList<>();
        for(double i = System.currentTimeMillis() - 1000; i < System.currentTimeMillis(); i++){
            if(packethistory.containsKey(i)) {
                toReturn.add(packethistory.get(i));
            }
        }
        return toReturn;
    }

    public List<Packet> getAllPacketsSince(double time){
        ArrayList<Packet> toReturn = new ArrayList<>();
        for(double i = System.currentTimeMillis() - (System.currentTimeMillis() - time); i < System.currentTimeMillis(); i++){
            if(packethistory.containsKey(i)) {
                toReturn.add(packethistory.get(i));
            }
        }
        return toReturn;
    }

    public boolean isDev(){
        return hasPermission("gac.dev");
    }
}
