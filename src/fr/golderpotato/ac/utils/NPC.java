package fr.golderpotato.ac.utils;

import com.mojang.authlib.GameProfile;
import fr.golderpotato.ac.cheats.combat.focefield.ForceFieldA;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created by Eliaz on 19/01/2017.
 */
public class NPC {

    public String name;
    public Location location;
    public int entityid;
    public EntityPlayer eplayer;

    public NPC(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public void spawn(Player player){
        EntityPlayer NPC = new EntityPlayer(((CraftServer)Bukkit.getServer()).getServer(), ((CraftWorld)player.getWorld()).getHandle(), new GameProfile(UUID.randomUUID(), this.name), new PlayerInteractManager(((CraftWorld) player.getWorld()).getHandle()));
        NPC.setLocation(location.getX(), location.getY(), location.getZ(), 0.0F, 0.0F);
        this.entityid = NPC.getId();
        try {
            Field f = NPC.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("ticksLived");
            f.setAccessible(true);
            f.setInt(NPC, 6789);
        }catch (Exception e){
            e.printStackTrace();
        }
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, NPC));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(NPC));
        //connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, NPC));
        this.eplayer = NPC;
    }

    public void despawn(Player player)
    {
        this.entityid = 0;
        if(ForceFieldA.npcs.get(player) != null){
            ForceFieldA.npcs.remove(player);
            PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, this.eplayer));
            connection.sendPacket(new PacketPlayOutEntityDestroy(new int[] { this.eplayer.getId() }));
        }
    }
}
