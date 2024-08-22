package store.management.system.DAO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import store.management.system.Model.ProductModel;
import store.management.system.Database.DatabaseConnection;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    // Method to execute add query
    public boolean executeAddQuery(ProductModel productModel) {
        boolean isSuccess = false;
        String query = "INSERT INTO product_details (product_id, product_name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productModel.getProductId());
            statement.setString(2, productModel.getProductName());
            statement.setString(3, productModel.getCategory());
            statement.setBigDecimal(4, productModel.getPrice());
            statement.setInt(5, productModel.getQuantity());
            statement.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }
    
    // Method to execute update query
    public boolean executeUpdateQuery(ProductModel productModel) {
        boolean isSuccess = false;
        String query = "UPDATE product_details SET product_name = ?, category = ?, price = ?, quantity = ? WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productModel.getProductName());
            statement.setString(2, productModel.getCategory());
            statement.setBigDecimal(3, productModel.getPrice());
            statement.setInt(4, productModel.getQuantity());
            statement.setString(5, productModel.getProductId());
            statement.executeUpdate();
            isSuccess = true;
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }
    
    // Method to execute delete query
    public boolean executeDeleteQuery(String productId) {
        boolean isSuccess = false;
        String query = "DELETE FROM product_details WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productId);
            statement.executeUpdate();
            isSuccess = true;
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }
    
    // Method to fetch product info
    public Object[][] fetchProductInfo() {
        ArrayList<Object[]> data = new ArrayList<>();
        String query = "SELECT * FROM product_details ORDER BY created_at ASC";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return data.toArray(new Object[0][]);
    }
}
