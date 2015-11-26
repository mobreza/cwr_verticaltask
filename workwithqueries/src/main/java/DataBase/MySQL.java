/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HSOTELO
 */
public class MySQL extends DBBase {
    @Override
    void setUrl() {
        this.setUrl("jdbc:mysql://" + this.getServer() + "/" + this.getBd());
    }

    @Override
    boolean setConnection()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            setConnection(DriverManager.getConnection(this.getUrl(), this.getLogin(), this.getPassword()));
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return getConnection()!=null;
    }
    
    /***
     * Method Construct 
     * @param server Server name or ip where database is locate
     * @param bd Name of database
     * @param login User login for database
     * @param password Password for database
     */
    public MySQL(String server,String bd,String login,String password)
    {
        super(server,bd,login,password);
    }
}
