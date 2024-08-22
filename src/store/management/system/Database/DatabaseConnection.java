package store.management.system.Database;

import java.sql.*;
import javax.swing.*;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/store_ms"; 
    private static final String username = "root"; 
    private static final String password = "Root@123";
    private static Connection connection;
    private Statement statement;
    
    public static Connection getConnection() {
        if (connection == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connection;
    }
}
