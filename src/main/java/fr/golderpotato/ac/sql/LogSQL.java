package fr.golderpotato.ac.sql;

import fr.golderpotato.ac.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

/**
 * Created by Eliaz on 24/02/2017.
 */
public class LogSQL {

    public Connection connection = Main.getInstance().connection.getConnection();

    public void addPlayerLog(final UUID uuid, final String cheatname){
        if(connection == null)return;
        try{
            PreparedStatement sql = this.connection.prepareStatement("INSERT INTO `playerlog` (`uuid`, `cheatname`, `args`, `date`) VALUES (?,?,?,NOW())");
            sql.setString(1, uuid.toString());
            sql.setString(2, cheatname);
            sql.setString(3, "");
            sql.executeUpdate();
            sql.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addPlayerLog(final UUID uuid, final String cheatname, final String args){
        if(connection == null)return;
        try{
            PreparedStatement sql = this.connection.prepareStatement("INSERT INTO `playerlog` (`uuid`, `cheatname`, `args`, `date`) VALUES (?,?,?,NOW())");
            sql.setString(1, uuid.toString());
            sql.setString(2, cheatname);
            sql.setString(3, args);
            sql.executeUpdate();
            sql.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
