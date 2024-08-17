
package store.management.system.DAO;

import javax.swing.*;
import java.sql.*;

import store.management.system.Model.LoginModel;
import store.management.system.Database.DatabaseConnection;

public class LoginDAO{
    private Connection connection;
    
    public LoginDAO(){
        this.connection = DatabaseConnection.getConnection();
        
    }
    
    public boolean executeLoginQuery(LoginModel userModel){
        boolean isValidate = false;
        String query = "select * from user_details where staff_id = ? and staff_password = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userModel.getUsername());
            statement.setString(2, userModel.getPassword());
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                isValidate = true;
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        return isValidate;
    }
    
    public boolean executeForgotPasswordQuery(LoginModel userModel){
        boolean isValidate = false;
        String query = "select * from user_details where username = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userModel.getUsername());
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    isValidate = true;
                }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        return isValidate;
    }
    
    
}