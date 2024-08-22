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
    
    public boolean executeUpdateQuery(AdminStaffModel adminStaffModel){
        boolean isValidate = false;
        String query = "UPDATE user_details set staff_name = ?, staff_gender = ?, staff_contact = ? WHERE staff_id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, adminStaffModel.getStaffName());
            statement.setString(2, adminStaffModel.getStaffGender());
            statement.setString(3, adminStaffModel.getStaffContact());
            statement.setString(4, adminStaffModel.getStaffID());
            statement.executeUpdate();
            isValidate = true;
            
            
       } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        return isValidate;
    }
    
    public boolean executeDeleteQuery(String staffId){
        boolean isValidate = false;
        String query = "DELETE FROM user_details WHERE staff_id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, staffId);
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
    
    
    
    // -------------------- Search -------------------
    
    public Object[][] search(AdminStaffModel adminStaffModel) {
        ArrayList<Object[]> data = new ArrayList<>();
        String query = "SELECT * FROM user_details WHERE 1=1";

        if (!adminStaffModel.getStaffID().isEmpty()) {
            query += " AND staff_id = ?";
        }
        if (!adminStaffModel.getStaffName().isEmpty()) {
            query += " AND staff_name = ?";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            int parameterIndex = 1;
            if (!adminStaffModel.getStaffID().isEmpty()) {
                statement.setString(parameterIndex++, adminStaffModel.getStaffID());
            }
            if (!adminStaffModel.getStaffName().isEmpty()) {
                statement.setString(parameterIndex++, adminStaffModel.getStaffName());
            }

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                data.add(row);
            }

        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Invalid", JOptionPane.ERROR_MESSAGE);
        }

        return data.toArray(new Object[0][]);
    }
    
    
    /*
    public List<AdminStaffModel> searchStaff(String staffID, String staffName) {
        List<AdminStaffModel> staffList = new ArrayList<>();
        String query = "SELECT * FROM user_details WHERE 1=1";

        if (staffID != null && !staffID.isEmpty()) {
            query += " AND staff_id LIKE ?";
        }
        if (staffName != null && !staffName.isEmpty()) {
            query += " AND staff_name LIKE ?";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int parameterIndex = 1;

            if (staffID != null && !staffID.isEmpty()) {
                statement.setString(parameterIndex++, "%" + staffID + "%");
            }
            if (staffName != null && !staffName.isEmpty()) {
                statement.setString(parameterIndex++, "%" + staffName + "%");
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                AdminStaffModel staff = new AdminStaffModel(
                        resultSet.getString("staff_id"),
                        resultSet.getString("staff_name"),
                        resultSet.getString("staff_gender"),
                        resultSet.getString("staff_contact"),
                        null,
                        null 
                );
                staffList.add(staff);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }
    */
}
