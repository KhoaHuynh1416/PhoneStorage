/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADmin
 */
public class MyJDBCConnection {
    
    private static Connection connection = null;
    private static String result;
    
    private final String url = "jdbc:mysql://localhost:3306/phonestorage";
    private final String user = "root";
    //private final String password = "Rvpvnvp098765";
    private final String password = "";
    
    private MyJDBCConnection() {
        String className = "com.mysql.jdbc.Driver";
        try {
            Class.forName(className);
            connection = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() {
        if (connection == null) {
            new MyJDBCConnection();
        }
        if (connection == null) {
            result = "fail";
        }
        else 
            result = "success";
        return connection;
    }
    
    public static String getResult() {
        return result;
    }
    
}
