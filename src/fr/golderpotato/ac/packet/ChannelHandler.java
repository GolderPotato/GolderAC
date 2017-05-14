package fr.golderpotato.ac.packet;

import fr.golderpotato.ac.Main;
import fr.golderpotato.ac.player.GACPlayer;
import fr.golderpotato.ac.utils.NMS;
import fr.golderpotato.ac.utils.ReflectionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to InventiveTalent
 */

public class ChannelHandler
        extends ChannelDuplexHandler
{
    private Player player;
    private GACPlayer gplayer;

    public ChannelHandler(Player player)
    {

        this.player = player;
        this.gplayer = Main.getInstance().getGACPlayer(player);
    }

    private static Field getChannelField()
    {
        Field channelField = null;
        try
        {
            channelField = ReflectionUtils.getField(NMS.getNMSClass("NetworkManager"), "channel");
        }
        catch (Exception e)
        {
            System.out.print("Channel class not found");
        }
        if (channelField != null) {
            channelField.setAccessible(true);
        }
        return channelField;
    }

    public static void addChannel(final Player player)
    {
        try
        {
            Object handle = NMS.getNMSPlayer(player);
            Object connection = ReflectionUtils.getFieldObject(handle.getClass(), "playerConnection", handle);
            Field network = ReflectionUtils.getField(NMS.getNMSClass("PlayerConnection"), "networkManager");
            Channel channel = (Channel)getChannelField().get(network.get(connection));
            new Thread(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        channel.pipeline().addBefore("packet_handler", "packet_listener_player",
                                new ChannelHandler(player));
                    }
                    catch (Exception localException) {}
                }
            }, " channel adder (" + player.getName() + ")").start();
        }catch (Exception e) {

        }
    }

    public static void removeChannel(Player player)
    {
        try
        {
            Object handle = NMS.getNMSPlayer(player);
            Object connection = ReflectionUtils.getFieldObject(handle.getClass(), "playerConnection", handle);
            Field network = ReflectionUtils.getField(NMS.getNMSClass("PlayerConnection"), "networkManager");
            Channel channel = (Channel)getChannelField().get(network.get(connection));
            new Thread(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        channel.pipeline().remove("packet_listener_player");
                    }
                    catch (Exception localException) {}
                }
            }, " channel remover (" + player.getName() + ")").start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
            throws Exception
    {

        msg = GACPackets.getInstance().onSend(new Packet(msg, this.player));
        super.write(ctx, msg, promise);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception
    {
        try {
            msg = GACPackets.getInstance().onReceive(new Packet(msg, this.player));
            if(msg == null)return;
            gplayer.packethistory.put(Double.valueOf(System.currentTimeMillis()), new Packet(msg, this.player));
            super.channelRead(ctx, msg);
        }catch (Exception e){}
    }
}
