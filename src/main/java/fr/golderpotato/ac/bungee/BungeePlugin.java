package fr.golderpotato.ac.bungee;

import fr.golderpotato.ac.ChatUtils;
import fr.golderpotato.ac.rabbit.RabbitMQ;
import fr.golderpotato.ac.rabbit.packet.PacketDestination;
import fr.golderpotato.ac.rabbit.packet.PacketListener;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Eliaz on 21/01/2017.
 */
public class BungeePlugin extends Plugin {

    public static BungeePlugin instance;
    public String prefix;

    @Override
    public void onEnable() {
        RabbitMQ.getInstance();
        print("Loading GAC plugin extention");
        instance = this;
        new PacketListener(PacketDestination.BUNGEE);
        prefix = "§8[§cANTI-CHEAT§8] §a";
        ChatUtils.setPrefix(prefix);
    }



    public void print(final String message){
        System.out.println(message);
    }

    public static BungeePlugin getInstance() {
        return instance;
    }
}
