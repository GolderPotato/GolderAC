package fr.golderpotato.ac.sql;

import fr.golderpotato.ac.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

/**
 * Created by Eliaz on 24/02/2017.
 */
public class BanSQL {

    public Connection connection = Main.getInstance().connection.getConnection();

    public void addPlayerLog(final UUID uuid, final String cheatname){
        if(connection == null)return;
        try{
            PreparedStatement sql = this.connection.prepareStatement("INSERT INTO `banlog` (`uuid`, `cheatname`, `date`) VALUES (?,?,NOW())");
            sql.setString(1, uuid.toString());
            sql.setString(2, cheatname);
            sql.executeUpdate();
            sql.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
