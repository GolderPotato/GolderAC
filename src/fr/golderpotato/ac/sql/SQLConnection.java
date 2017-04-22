package fr.golderpotato.ac.sql;

import fr.golderpotato.ac.Main;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Eliaz on 25/02/2017.
 */
public class SQLConnection {

    private Connection connection;
    private String host;
    private String dbname;
    private String username;
    private String password;


    public SQLConnection(String host, String dbname, String username, String password){
        this.host = host;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        this.openConnection();
    }

    private void openConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.dbname+"", this.username, this.password);
            Main.getInstance().print("Connection to database successful!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        if(this.connection != null)
            try{this.connection.close();}catch(Exception e){e.printStackTrace();}
    }

    public Connection getConnection() {
        return connection;
    }
}
