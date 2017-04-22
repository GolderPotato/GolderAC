package fr.golderpotato.ac.cheats.combat;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatListener;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.packet.GACPacketHandler;
import fr.golderpotato.ac.packet.GACPackets;
import fr.golderpotato.ac.packet.Packet;
import fr.golderpotato.ac.packet.packetlist.PacketType;
import fr.golderpotato.ac.player.GACPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Eliaz on 17/01/2017.
 */
public class FastBow extends CheatListener{

    @EventHandler
    public void onShoot(EntityShootBowEvent event){
        if(!(event.getEntity() instanceof Player))return;
        Player player = (Player)event.getEntity();
        if(player == null)return;
        GACPlayer gplayer = Main.getInstance().getGACPlayer(player);
        if(gplayer == null)return;
        if(!(event.getForce() == 1)){
            return;
        }
        long time = System.currentTimeMillis() - gplayer.LastBowTime;

        gplayer.LastBowTime = System.currentTimeMillis();

        if(time < (int)CheatType.FASTBOW.getValue("bowdelay")){
            CheatType.FASTBOW.alertMods(gplayer);
        }
    }
}