package fr.golderpotato.ac.cheats;

import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.rabbit.packet.PacketList;
import fr.golderpotato.ac.rabbit.packet.PacketSender;
import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketAlert;
import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketBroadCast;
import fr.golderpotato.ac.rabbit.packet.packets.RabbitPacketKick;
import fr.golderpotato.ac.utils.BungeeCord;

/**
 * Created by Eliaz on 15/01/2017.
 */
public enum CheatType {

    BLINK("Blink"),
    FLY("Fly"),
    REACH("Reach"),
    SPEEDHACK("SpeedHack"),
    AUTOCLICK("Autoclick"),
    CRITICALS("Criticals"),
    FASTBOW("FastBow"),
    NOSLOWDOWN("NoSlowDown"),
    ANTIKNOCKBACK("AntiKnockBack"),
    NOFALL("NoFall"),
    STEP("Step"),
    GLIDE("Glide"),
    JESUS("Jesus"),
    CNI("CNI"),
    FORCEFIELD("ForceField"),
    INVENTORYPLUS("InventoryPlus"),
    HIGHJUMP("HighJump"),
    TIMER("Timer"),
    FASTPLACE("FastPlace"),
    FASTEAT("FastEat"),
    POSSIBLECHEAT("PossibleCheat"),
    AIMBOT("Aimbot"),
    ANTILAG("AntiLag"),
    SAFEWALK("SafeWalk"),
    SPIDER("Spider"),
    ILLEGALMOVEMENT("IllegalMovement");


    public final String cheatname;

    CheatType(final String name){
        this.cheatname = name;
    }

    public void kick(final GACPlayer player){
        if(!isEnabled(this))return;
        if(Main.getInstance().getBypass().gacPlayers.contains(player))return;
        RabbitPacketKick kick = new RabbitPacketKick(player.getUniqueId(), "Vous avez été kick pour "+cheatname);
        PacketSender.sendPacket(kick, PacketList.KICK);
    }

    public void forceKick(final GACPlayer player){
        RabbitPacketKick kick = new RabbitPacketKick(player.getUniqueId(), "Vous avez été kick pour "+cheatname);
        PacketSender.sendPacket(kick, PacketList.KICK);
    }

    public void forceKick(final GACPlayer player, final String reason){
        RabbitPacketKick kick = new RabbitPacketKick(player.getUniqueId(), "Vous avez été kick pour "+cheatname);
        PacketSender.sendPacket(kick, PacketList.KICK);
    }

    public void ban(final GACPlayer player){
        if(!isEnabled(this))return;
        if(!getAutoBan(this))return;
        if(Main.getInstance().getBypass().isByPassed(player))return;

        player.ban(this);

        RabbitPacketBroadCast broadCast = new RabbitPacketBroadCast(player.getName(), cheatname);
        PacketSender.sendPacket(broadCast, PacketList.BROADCAST);

        Main.getInstance().twitter.tweetUnique(Main.getInstance().getConfig().getString("twitter.ban.message").replaceAll("player", player.getName()));
        if(Main.getInstance().doSQL == true){
            Main.getInstance().banSQL.addPlayerLog(player.getUniqueId(), cheatname);
        }
    }

    public void forceBan(final GACPlayer player){
        player.ban(this);
    }

    public void alertMods(final GACPlayer player) {
        if(!isEnabled(this))return;
        if(Main.getInstance().getBypass().gacPlayers.contains(player)) return;
        //BungeeCord.alert(player, cheatname);
        RabbitPacketAlert alert = new RabbitPacketAlert(player.getUniqueId().toString(), cheatname);
        PacketSender.sendPacket(alert, PacketList.ALERT);
        player.violations.put(this, "(no info)");
        if(Main.getInstance().doSQL == true) {
            Main.getInstance().logSQL.addPlayerLog(player.getUniqueId(), cheatname);
        }
    }

    public void alertMods(final GACPlayer player, final String arguments){
        if(!isEnabled(this))return;
        if (Main.getInstance().getBypass().isByPassed(player))return;
        //BungeeCord.alert(player, cheatname+" ("+arguments+")");
        RabbitPacketAlert alert = new RabbitPacketAlert(player.getUniqueId().toString(), cheatname, arguments);
        PacketSender.sendPacket(alert, PacketList.ALERT);
        player.violations.put(this, "("+arguments+")");
        if(Main.getInstance().doSQL == true) {
            Main.getInstance().logSQL.addPlayerLog(player.getUniqueId(), cheatname, arguments);
        }
    }

    public boolean getAutoBan(final CheatType cheattype){
        boolean toReturn = false;
        toReturn = Main.getInstance().getConfig().getBoolean(cheattype.toString().toLowerCase()+".autoban");
        return toReturn;
    }

    public boolean isEnabled(final CheatType cheatType){
        boolean toReturn = false;
        toReturn = Main.getInstance().getConfig().getBoolean(cheatType.toString().toLowerCase()+".enabled");
        return toReturn;
    }

    public Object getValue(final String value){
        return Main.getInstance().getConfig().get(this.toString().toLowerCase()+"."+value);
    }
}
