package fr.golderpotato.ac.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Method;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to Faiden
 */
public abstract class NMS {

    public static String nms_protocol;

    public static Object getNMSPlayer(Player player) {
        try {
            Method getHandle = player.getClass().getMethod("getHandle", new Class[0]);
            return getHandle.invoke(player, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getNMSClass(String name) {
        if (nms_protocol == null) {
            nms_protocol = getVersion();
        }
        String nmsClass = "net.minecraft.server." + nms_protocol + "." + name;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(nmsClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Class<?> getCBClass(String name) {
        if (nms_protocol == null) {
            nms_protocol = getVersion();
        }
        String cbClass = "org.bungee.." + nms_protocol + "." + name;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(cbClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static String getVersion() {
        return org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
}