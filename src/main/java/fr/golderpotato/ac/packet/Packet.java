package fr.golderpotato.ac.packet;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to InventiveTalent
 */
public class Packet {

    private final Object packet;
    private final Player player;
    private boolean allowed = true;

    public Packet(final Object packet, final Player player)
    {
        this.packet = packet;
        this.player = player;
    }

    public final String getPacketName()
    {
        return this.packet.getClass().getSimpleName();
    }

    public final Object getPacket()
    {
        return this.packet;
    }

    public final Player getPlayer()
    {
        return this.player;
    }

    public final List<String> getFields()
    {
        final List<String> list = new ArrayList<>();
        final Field[] arrayOfField;
        int j = (arrayOfField = isSuper() ? this.packet.getClass().getSuperclass().getDeclaredFields() : this.packet.getClass().getDeclaredFields()).length;
        for (int i = 0; i < j; i++)
        {
            Field f = arrayOfField[i];
            list.add(f.getType().getSimpleName() + " " + f.getName());
        }
        return list;
    }

    public final Object getPacketValue(final String name)
    {
        Object value = null;
        try
        {
            final Field f = isSuper() ? this.packet.getClass().getSuperclass().getDeclaredField(name) :
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

    public Object invoke(final String methodname, final Object[] args, final Class<?>... parameterTypes)
    {
        Object value = null;
        try
        {
            final Method m = isSuper() ? this.packet.getClass().getSuperclass().getDeclaredMethod(methodname, parameterTypes) :
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

    public void dissalow(){
        this.allowed = false;
    }

    public void allow(){
        this.allowed = true;
    }

    public boolean isAllowed(){
        return this.allowed;
    }
}
