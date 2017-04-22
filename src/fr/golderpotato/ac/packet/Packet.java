package fr.golderpotato.ac.packet;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to Faiden
 */
public class Packet {

    private Object packet;
    private Player player;

    public Packet(Object packet, Player player)
    {
        this.packet = packet;
        this.player = player;
    }

    public String getPacketName()
    {
        return this.packet.getClass().getSimpleName();
    }

    public Object getPacket()
    {
        return this.packet;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public List<String> getFields()
    {
        List<String> list = new ArrayList<>();
        Field[] arrayOfField;
        int j = (arrayOfField = isSuper() ? this.packet.getClass().getSuperclass().getDeclaredFields() : this.packet.getClass().getDeclaredFields()).length;
        for (int i = 0; i < j; i++)
        {
            Field f = arrayOfField[i];
            list.add(f.getType().getSimpleName() + " " + f.getName());
        }
        return list;
    }

    public Object getPacketValue(String name)
    {
        Object value = null;
        try
        {
            Field f = isSuper() ? this.packet.getClass().getSuperclass().getDeclaredField(name) :
                    this.packet.getClass().getDeclaredField(name);
            f.setAccessible(true);
            value = f.get(this.packet);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    public Object invoke(String methodname, Object[] args, Class<?>... parameterTypes)
    {
        Object value = null;
        try
        {
            Method m = isSuper() ? this.packet.getClass().getSuperclass().getDeclaredMethod(methodname, parameterTypes) :
                    this.packet.getClass().getDeclaredMethod(methodname, parameterTypes);
            m.setAccessible(true);
            value = m.invoke(this.packet, args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    private boolean isSuper()
    {
        return Modifier.isStatic(this.packet.getClass().getModifiers());
    }
}
