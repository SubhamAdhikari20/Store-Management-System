package store.management.system.DAO;

import store.management.system.Model.StaffAccountModel;
import store.management.system.Database.DatabaseConnection;

import java.sql.*;
import javax.swing.*;

public class StaffAccountDAO {
    private Connection connection;

    public StaffAccountDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Fetch staff details based on staffID
    public StaffAccountModel fetchStaffDetails(String staffID) {
        String query = "SELECT staff_id, staff_name, staff_contact, staff_password FROM user_details WHERE staff_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, staffID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new StaffAccountModel(
                    resultSet.getString("staff_id"),
                    resultSet.getString("staff_name"),
                    resultSet.getString("staff_contact"),  // Assuming staffEmail was intended to be staffContact
                    resultSet.getString("staff_password")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    // Change the password for a staff member
    public boolean changePassword(String staffID, String newPassword) {
        String query = "UPDATE user_details SET staff_password = ? WHERE staff_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setString(2, staffID);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
