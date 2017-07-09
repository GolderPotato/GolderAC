package fr.golderpotato.ac.cheats;

import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.BungeeCord;
import org.bukkit.Bukkit;

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


    CheatType(String name){
        this.cheatname = name;
    }

    public String cheatname;

    public void kick(GACPlayer player){
        if(!isEnabled(this))return;
        if(Main.getInstance().getBypass().gacPlayers.contains(player))return;
        player.kickPlayer(ChatUtils.getPrefix()+"Vous avez été kick pour "+cheatname);
    }

    public void forceKick(GACPlayer player){
        player.kickPlayer(ChatUtils.getPrefix()+"Vous avez été kick pour "+cheatname);
    }

    public void forceKick(GACPlayer player, String reason){
        player.kickPlayer(ChatUtils.getPrefix()+"Vous avez été kick pour "+cheatname+"("+reason+")");
    }

    public void ban(GACPlayer player){
        if(!isEnabled(this))return;
        if(!getAutoBan(this))return;
        if(Main.getInstance().getBypass().isByPassed(player))return;
        player.ban(this);
        Main.getInstance().twitter.tweetUnique(player.getName()+" got rekt :/");
        if(Main.getInstance().doSQL == true){
            Main.getInstance().banSQL.addPlayerLog(player.getUniqueId(), cheatname);
        }
    }

    public void forceBan(GACPlayer player){
        player.ban(this);
    }

    public void alertMods(GACPlayer player) {
        if(!isEnabled(this))return;
        if(Main.getInstance().getBypass().gacPlayers.contains(player)) return;
        BungeeCord.alert(player, cheatname);
        player.violations.put(this, "(no info)");
        if(Main.getInstance().doSQL == true) {
            Main.getInstance().logSQL.addPlayerLog(player.getUniqueId(), cheatname);
        }
    }

    public void alertMods(GACPlayer player, String arguments){
        if(!isEnabled(this))return;
        if (Main.getInstance().getBypass().isByPassed(player))return;
        BungeeCord.alert(player, cheatname+" ("+arguments+")");
        player.violations.put(this, "("+arguments+")");
        if(Main.getInstance().doSQL == true) {
            Main.getInstance().logSQL.addPlayerLog(player.getUniqueId(), cheatname, arguments);
        }
    }

    public boolean getAutoBan(CheatType cheattype){
        boolean toReturn = false;
        toReturn = Main.getInstance().getConfig().getBoolean(cheattype.toString().toLowerCase()+".autoban");
        return toReturn;
    }

    public boolean isEnabled(CheatType cheatType){
        boolean toReturn = false;
        toReturn = Main.getInstance().getConfig().getBoolean(cheatType.toString().toLowerCase()+".enabled");
        return toReturn;
    }

    public Object getValue(String value){
        return Main.getInstance().getConfig().get(this.toString().toLowerCase()+"."+value);
    }
}
