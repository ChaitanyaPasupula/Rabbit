
package com.mrcet.rabbit.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
public static Connection getConnection() throws FileNotFoundException, IOException
    {
        //Change Path in the given variable
        String path="C:\\Users\\hp\\Documents\\Rabbit\\src\\java\\com\\mrcet\\rabbit\\Util\\database-dev.properties"; // get workaround asap; use factory to load appropriate file
        try(FileInputStream fis = new FileInputStream(path)) {
            Properties p = new Properties();
            p.load(fis);
            String dc= p.getProperty("driver");
            String url=p.getProperty("connection-string");
            String username=p.getProperty("username");
            String password=p.getProperty("password");
            try{
                Class.forName(dc);
                Connection con = DriverManager.getConnection(url,username,password);
                return con;      
            }
            catch(ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
           }
        return null;
    }
    

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
}
