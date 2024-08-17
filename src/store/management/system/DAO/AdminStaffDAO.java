package store.management.system.DAO;

import javax.swing.*;
import java.sql.*;
import java.util.*;

import store.management.system.Model.AdminStaffModel;
import store.management.system.Database.DatabaseConnection;

public class AdminStaffDAO {
    private Connection connection;

    public AdminStaffDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public boolean executeAddQuery(AdminStaffModel adminStaffModel){
        boolean isValidate = false;
        String query = "insert into user_details (staff_id, staff_name, staff_gender, staff_contact, staff_password) values (?, ?, ?, ?, ?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, adminStaffModel.getStaffID());
            statement.setString(2, adminStaffModel.getStaffName());
            statement.setString(3, adminStaffModel.getStaffGender());
            statement.setString(4, adminStaffModel.getStaffContact());
            statement.setString(5, adminStaffModel.getStaffPassword());
            statement.executeUpdate();
            isValidate = true;
            
            
       } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        return isValidate;
    }
    
    public Object[][] fetchStaffInfo(){
         ArrayList<Object[]> data = new ArrayList<>();
        String query = "select * from user_details ORDER BY created_at ASC;";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getString(i);
                }   
                data.add(row);
            
            }
            
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        
        Object[][] arrayData = new Object[data.size()][];
        return data.toArray(arrayData);
        
    }
    
    
    /*
    public Object[][] fetchStaffInfo(AdminStaffModel adminStaffModel){
        List<AdminStaffModel> staffList = new ArrayList<>();
        String query = "select * from user_details ORDER BY created_at ASC;";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                adminStaffModel.setStaffID(resultSet.getString("staff_id"));
                adminStaffModel.setStaffName(resultSet.getString("staff_name"));
                adminStaffModel.setStaffGender(resultSet.getString("staff_gender"));
                adminStaffModel.setStaffContact(resultSet.getString("staff_contact"));
                adminStaffModel.setStaffPassword(resultSet.getString("staff_password"));
                staffList.add(adminStaffModel);
            }
           
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        
        // Converting the list of models into a 2D Object array
        Object[][] dataArray = new Object[staffList.size()][5]; // Assuming 5 fields: ID, Name, Gender, Contact, Password
        for (int i = 0; i < staffList.size(); i++) {
            AdminStaffModel staff = staffList.get(i);
            dataArray[i][0] = staff.getStaffID();
            dataArray[i][1] = staff.getStaffName();
            dataArray[i][2] = staff.getStaffGender();
            dataArray[i][3] = staff.getStaffContact();
            dataArray[i][4] = staff.getStaffPassword();
        }
        
        return dataArray;
       
    }
    
    */
}
