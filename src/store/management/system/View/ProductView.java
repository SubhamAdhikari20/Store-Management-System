package store.management.system.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

import store.management.system.Controller.ProductController;
import store.management.system.Model.ProductModel;
import store.management.system.DAO.ProductDAO;

public class ProductView extends CustomPanel {

    CustomTextField productIdField, productNameField, categoryField, priceField, quantityField;
    CustomTable productTable;
    DefaultTableModel tableModel;
    CustomButton addButton, updateButton, deleteButton, clearButton;

    public ProductView() {
        super.setBackground(Color.white);
        super.setLayout(null);

        // Add Product Title Label
        JLabel productTitleLabel = new JLabel("Manage Products");
        productTitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        productTitleLabel.setBounds(30, 25, 220, 40);
        super.add(productTitleLabel);

        // Product ID Label and Text Field
        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        productIdLabel.setBounds(30, 70, 120, 40);
        super.add(productIdLabel);

        productIdField = new CustomTextField();
        productIdField.setFont(new Font("Arial", Font.PLAIN, 16));
        productIdField.setBounds(190, 73, 250, 35);
        super.add(productIdField);

        // Product Name Label and Text Field
        JLabel productNameLabel = new JLabel("Product Name:");
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        productNameLabel.setBounds(30, 120, 150, 40);
        super.add(productNameLabel);

        productNameField = new CustomTextField();
        productNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        productNameField.setBounds(190, 123, 250, 35);
        super.add(productNameField);

        // Quantity Label and Text Field
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 17));
        quantityLabel.setBounds(30, 170, 150, 40);
        super.add(quantityLabel);

        quantityField = new CustomTextField();
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField.setBounds(190, 173, 250, 35);
        super.add(quantityField);

        // Category Label and Text Field
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 17));
        categoryLabel.setBounds(530, 120, 120, 40);
        super.add(categoryLabel);

        categoryField = new CustomTextField();
        categoryField.setFont(new Font("Arial", Font.PLAIN, 16));
        categoryField.setBounds(650, 123, 250, 35);
        super.add(categoryField);

        // Price Label and Text Field
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 17));
        priceLabel.setBounds(530, 70, 120, 40);
        super.add(priceLabel);

        priceField = new CustomTextField();
        priceField.setFont(new Font("Arial", Font.PLAIN, 16));
        priceField.setBounds(650, 73, 250, 35);
        super.add(priceField);

        // Add Button
        addButton = new CustomButton();
        addButton.setText("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(46, 109, 180));
        addButton.setHoverColor(new Color(26, 89, 160));
        addButton.setPressColor(new Color(6, 69, 140));
        addButton.setBounds(530, 200, 100, 47);
        super.add(addButton);

        // Update Button
        updateButton = new CustomButton();
        updateButton.setText("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 18));
        updateButton.setForeground(Color.white);
        updateButton.setBackground(new Color(46, 109, 180));
        updateButton.setHoverColor(new Color(26, 89, 160));
        updateButton.setPressColor(new Color(6, 69, 140));
        updateButton.setBounds(700, 200, 100, 47);
        super.add(updateButton);

        // Delete Button
        deleteButton = new CustomButton();
        deleteButton.setText("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setForeground(Color.white);
        deleteButton.setBackground(new Color(46, 109, 180));
        deleteButton.setHoverColor(new Color(26, 89, 160));
        deleteButton.setPressColor(new Color(6, 69, 140));
        deleteButton.setBounds(870, 200, 100, 47);
        super.add(deleteButton);

        // Clear Button
        clearButton = new CustomButton();
        clearButton.setText("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setForeground(Color.white);
        clearButton.setBackground(new Color(46, 109, 180));
        clearButton.setHoverColor(new Color(26, 89, 160));
        clearButton.setPressColor(new Color(6, 69, 140));
        clearButton.setBounds(1000, 70, 100, 47);
        super.add(clearButton);
        
        
        // Table Model
        ProductDAO productDAO = new ProductDAO();
        Object[][] rowData = productDAO.fetchProductInfo();
        String[] columnNames = {"Product ID", "Product Name", "Category", "Quantity", "Price"};
        tableModel = new DefaultTableModel(rowData, columnNames);

        // Product Table
        productTable = new CustomTable(tableModel);
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable.setRowHeight(25);
        productTable.setAutoCreateRowSorter(true);

        // Scroll Pane for Table
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(30, 270, 1143, 320);
        super.add(scrollPane);
        
        
        ProductController productController = new ProductController(this, productDAO);
        
        
        // Add table row selection listener
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = productTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    productTable.setRowSelectionInterval(row, row);
                    populateFields(row);
                }
            }
        });
        
        
    }

    // Getters for UI elements
    public String getProductIdField() {
        return productIdField.getText();
    }

    public String getProductNameField() {
        return productNameField.getText();
    }

    public String getCategoryField() {
        return categoryField.getText();
    }

    public String getPriceField() {
        return priceField.getText();
    }

    public String getQuantityField() {
        return quantityField.getText();
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    // Methods to set and clear fields
    public void clearFields() {
        productIdField.setText("");
        productNameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        productTable.setModel(tableModel);
    }

    // Method to add action listeners
    public void addActionListener(ActionListener listener) {
        addButton.addActionListener(listener);
        updateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        clearButton.addActionListener(listener);
    }
    
    // Method to get selected row data
    public ProductModel getSelectedProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            return null; // No row selected
        }

        String productId = (String) productTable.getValueAt(selectedRow, 0);
        String productName = (String) productTable.getValueAt(selectedRow, 1);
        String category = (String) productTable.getValueAt(selectedRow, 2);
        BigDecimal price = (BigDecimal) productTable.getValueAt(selectedRow, 4);
        int quantity = (int) productTable.getValueAt(selectedRow, 3);
        
        
        return new ProductModel(productId, productName, category, price, quantity);
    }

    // Method to populate fields based on the selected row
    private void populateFields(int row) {
        productIdField.setText((String) productTable.getValueAt(row, 0));
        productNameField.setText((String) productTable.getValueAt(row, 1));
        categoryField.setText((String) productTable.getValueAt(row, 2));
        priceField.setText(String.valueOf(productTable.getValueAt(row, 4)));
        quantityField.setText(String.valueOf(productTable.getValueAt(row, 3)));
    }
}
