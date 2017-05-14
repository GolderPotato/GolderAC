package fr.golderpotato.ac.packet;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to InventiveTalent
 */
public class PacketBuild
{
    private int id;
    private String name;
    private PacketSource source;

    public PacketBuild(int id, String name, PacketSource source)
    {
        this.id = id;
        this.name = (source.prefix + name);
        this.source = source;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public PacketSource getSource() {
        return source;
    }

}
