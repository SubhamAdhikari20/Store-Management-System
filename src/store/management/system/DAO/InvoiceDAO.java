
package store.management.system.DAO;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.math.*;

import store.management.system.Model.InvoiceModel;
import store.management.system.Database.DatabaseConnection;

public class InvoiceDAO {
    private Connection connection;

    public InvoiceDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    // Method to fetch product info
    public List<String> fetchCategory() {
        List<String> categories = new ArrayList<>();
        String query = "SELECT DISTINCT category FROM product_details";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                categories.add(resultSet.getString("category"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return categories;
    }
    
    
    
    
    
    // -------------------- Search -------------------
    public Object[][] search(InvoiceModel invoiceModel) {
        ArrayList<Object[]> data = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT product_id, product_name, category, quantity, price FROM product_details WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        if (!invoiceModel.getProductField().isEmpty()) {
            query.append(" AND (product_id = ? OR product_name = ?)");
            parameters.add(invoiceModel.getProductField());
            parameters.add(invoiceModel.getProductField());
        }
        if (!invoiceModel.getCategoryField().equals("Select")) {  // Adjust this condition
            query.append(" AND category = ?");
            parameters.add(invoiceModel.getCategoryField());
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());

            // Set the parameters dynamically
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid", JOptionPane.ERROR_MESSAGE);
        }

        return data.toArray(new Object[0][]);
    }
    
    
    
    // Method to fetch product price
    public BigDecimal fetchProductPrice(String productId) {
        String query = "SELECT price FROM product_details WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBigDecimal("price");
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return BigDecimal.ZERO;
    }
    
    
    // Update Product Qunatity
    public boolean updateProductQuantity(String productId, int quantity) {
        String query = "UPDATE product_details SET quantity = quantity - ? WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, quantity);
            statement.setString(2, productId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public int checkStock(String productId) {
        String query = "SELECT quantity FROM product_details WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    
    
    // In InvoiceDAO.java
    public boolean insertInvoiceDetails(String productId, String productName, String category, int quantity, BigDecimal price, BigDecimal total) {
        String query = "INSERT INTO invoice_details (product_id, product_name, category, quantity, price, total) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productId);
            statement.setString(2, productName);
            statement.setString(3, category);
            statement.setInt(4, quantity);
            statement.setBigDecimal(5, price);
            statement.setBigDecimal(6, total);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    

}
