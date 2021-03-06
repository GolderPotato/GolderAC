package fr.golderpotato.ac.cheats.combat.focefield;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.security.SecureRandom;
import java.util.*;

/**
 * Created by Eliaz on 19/01/2017.
 */
public class ForceFieldA extends CheatListener{

    public static final Map<Player, NPC> npcs = new HashMap<Player, NPC>();

    @Override
    public void runSchedule() {
        new BukkitRunnable(){
            public void run() {
                if(Bukkit.getOnlinePlayers().size() == 0){
                    return;
                }
                final int random = new Random().nextInt(Bukkit.getServer().getOnlinePlayers().size());
                final Player player = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];
                spawn(player);
                remove(player);
            }
        }.runTaskTimer(Main.getInstance(), 0, 10*20);
    }


    @Override
    public void setupListener() {
        GACPackets.getInstance().addPacketListener(new GACPacketHandler(PacketType.USE_ENTITY) {
            @Override
            public Packet Send(final Packet paramPacket) {
                return paramPacket;
            }

            @Override
            public Packet Receive(final Packet paramPacket) {
                final Player player = paramPacket.getPlayer();
                if(player == null)return paramPacket;
                final GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
                if(gplayer == null)return paramPacket;
                if(!gplayer.needsCheck())return paramPacket;
                final Object entityID = paramPacket.getPacketValue("a");
                final String eid = String.valueOf(entityID);
                final Integer id = Integer.valueOf(eid);
                if(npcs.get(player) == null)return paramPacket;
                if(id == npcs.get(player).entityid){
                    if(npcs.get(player) != null){
                        npcs.get(player).despawn(player);
                    }
                    CheatType.FORCEFIELD.alertMods(gplayer);
                    CheatType.FORCEFIELD.ban(gplayer);
                    gplayer.forceField++;
                }
                return paramPacket;
            }
        });
    }

    public void spawn(final Player player)
    {
        try
        {
            final GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
            if(gplayer == null)return;
            if(!gplayer.isOnline())return;
            final Location location = gplayer.getLocation();
            location.setPitch(0.0F);
            Vector inverseDirectionVec = location.getDirection().normalize().multiply(-2);
            final Location behindLocation = player.getLocation().add(inverseDirectionVec);
            behindLocation.setY(player.getLocation().getY()+0.5);
            final NPC npc = new NPC(getRandomString(16), behindLocation);
            npc.spawn(player);
            npcs.put(player, npc);

        }
        catch (Exception e)
        {
            Main.getInstance().print("Error with NPC with player "+player.getName());
        }
    }

    public void remove(final Player player)
    {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable()
        {
            public void run() {
                if(npcs.get(player) != null){
                    npcs.get(player).despawn(player);
                }
            }
        }, 3L);
    }

    public String getRandomString(final int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
