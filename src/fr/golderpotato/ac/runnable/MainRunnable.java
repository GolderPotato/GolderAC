package fr.golderpotato.ac.runnable;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.cheats.CheatType;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.TPS;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eliaz on 17/01/2017.
 */
public class MainRunnable extends BukkitRunnable{

    @Override
    public void run() {
        for(GACPlayer gplayers : Main.getInstance().getGACPlayers().values()){

            // FLY
            if(gplayers.FlyLevel > 6){
                CheatType.FLY.alertMods(gplayers, "packets");
            }

            // FLY
            if(gplayers.Fly > 5){
                CheatType.FLY.alertMods(gplayers, "movement");
            }

            // BLINK
            if(gplayers.AllPackets == 0 && gplayers.KeepAlive == 1){
                CheatType.BLINK.alertMods(gplayers);
            }

            // AUTOCLICK
            if(gplayers.CPS > (int)CheatType.FORCEFIELD.getValue("alertcps")){
                CheatType.AUTOCLICK.alertMods(gplayers, "CPS: "+gplayers.CPS+" PING: "+gplayers.getPing());
            }


            // GLIDE
            if(gplayers.Glide > 5){
                CheatType.GLIDE.alertMods(gplayers);
            }

            // JESUS
            if(gplayers.Jesus > 1){
                CheatType.JESUS.alertMods(gplayers);
                CheatType.JESUS.ban(gplayers);
            }


            // CNI
            if(gplayers.AllPackets > (int)CheatType.CNI.getValue("packetalert")){
                if (!isLagging(gplayers)){
                    CheatType.CNI.alertMods(gplayers, "PPS: "+gplayers.AllPackets+" PING: "+lag(gplayers));
                }
            }

            if(gplayers.AllPackets > (int)CheatType.CNI.getValue("packetban")){
                CheatType.CNI.ban(gplayers);
            }

            // NOSSLOWDOWN
            if(gplayers.NoSlowDown > 3){
                CheatType.NOSLOWDOWN.alertMods(gplayers);
            }

            // FASTPLACE
            if(gplayers.fastplace > 2){
                CheatType.FASTPLACE.alertMods(gplayers);
            }

            if(gplayers.fastplace > 4){
                CheatType.FASTPLACE.ban(gplayers);
            }

            int timer = (int)CheatType.TIMER.getValue("alert");

            if(gplayers.Flying > timer && gplayers.isOnGround()){
                CheatType.TIMER.alertMods(gplayers);
            }

            if(gplayers.Position > timer && gplayers.isOnGround()){
                CheatType.TIMER.alertMods(gplayers);
            }

            if(gplayers.PositionLook > timer && gplayers.isOnGround()){
                CheatType.TIMER.alertMods(gplayers);
            }

            if(gplayers.speedhack > 4){
                CheatType.SPEEDHACK.ban(gplayers);
            }


            // RESET VALUES
            gplayers.FlyLevel = 0;
            gplayers.AllPackets = 0;
            gplayers.KeepAlive = 0;
            gplayers.CPS = 0;
            gplayers.Flying = 0;
            gplayers.PositionLook = 0;
            gplayers.Position = 0;
            gplayers.Look = 0;
            gplayers.Glide = 0;
            gplayers.Jesus = 0;
            gplayers.Fly = 0;
            gplayers.NoSlowDown = 0;
            gplayers.pings.add(gplayers.getPing());
            gplayers.fastplace = 0;
            gplayers.speedhack = 0;
        }
    }

    public static boolean isLagging(GACPlayer player){
        if(TPS.getTPS() < Main.getInstance().getConfig().getInt("maxtps")){
            return false;
        }
        if(player.pings.size() < 10){
            return false;
        }
        int pingtotal = 0;
        for(int i = player.pings.size() - 10; i < player.pings.size();i++){
            pingtotal += player.pings.get(i);
        }
        if(pingtotal > 10*Main.getInstance().getConfig().getInt("maxping")){
            return true;
        }
        return false;
    }

    public static String lag(GACPlayer player){
        if(player.pings.size() < 10){
            return "INVALID";
        }
        int pingtotal = 0;
        for(int i = player.pings.size() - 10; i < player.pings.size();i++){
            pingtotal += player.pings.get(i);
        }
        return String.valueOf(pingtotal)+"/1200";
    }
}
