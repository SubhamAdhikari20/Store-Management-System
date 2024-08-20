package store.management.system.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminProductView extends CustomPanel {

    private JTextField productIdField, productNameField, categoryField, priceField, quantityField;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private CustomButton addButton, updateButton, deleteButton, clearButton;

    public AdminProductView() {
        setBackground(Color.white);
        setLayout(null);

        // Add Product Title Label
        JLabel productTitleLabel = new JLabel("Manage Products");
        productTitleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        productTitleLabel.setBounds(500, 10, 200, 40);
        add(productTitleLabel);

        // Add Product ID Label and Text Field
        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setFont(new Font("Arial", Font.BOLD, 15));
        productIdLabel.setBounds(50, 60, 120, 30);
        add(productIdLabel);

        productIdField = new JTextField();
        productIdField.setBounds(180, 60, 200, 30);
        add(productIdField);

        // Add Product Name Label and Text Field
        JLabel productNameLabel = new JLabel("Product Name:");
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        productNameLabel.setBounds(50, 100, 120, 30);
        add(productNameLabel);

        productNameField = new JTextField();
        productNameField.setBounds(180, 100, 200, 30);
        add(productNameField);

        // Add Category Label and Text Field
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 15));
        categoryLabel.setBounds(50, 140, 120, 30);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(180, 140, 200, 30);
        add(categoryField);

        // Add Price Label and Text Field
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        priceLabel.setBounds(50, 180, 120, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(180, 180, 200, 30);
        add(priceField);

        // Add Quantity Label and Text Field
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 15));
        quantityLabel.setBounds(50, 220, 120, 30);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(180, 220, 200, 30);
        add(quantityField);

        // Add Button
        addButton = new CustomButton();
        addButton.setText("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBounds(400, 60, 100, 40);
        add(addButton);

        // Update Button
        updateButton = new CustomButton();
        updateButton.setText("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 18));
        updateButton.setBounds(400, 120, 100, 40);
        add(updateButton);

        // Delete Button
        deleteButton = new CustomButton();
        deleteButton.setText("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setBounds(400, 180, 100, 40);
        add(deleteButton);

        // Clear Button
        clearButton = new CustomButton();
        clearButton.setText("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setBounds(400, 240, 100, 40);
        add(clearButton);

        // Create table for products
        String[] columnNames = { "Product ID", "Product Name", "Category", "Price", "Quantity" };
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 300, 1100, 200);
        add(scrollPane);

        // Add Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Set row selection listener
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                setFieldsFromSelectedRow();
            }
        });
    }

    private void addProduct() {
        try {
            String productId = productIdField.getText();
            String productName = productNameField.getText();
            String category = categoryField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            tableModel.addRow(new Object[] { productId, productName, category, price, quantity });
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity.");
        }
    }

    private void updateProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.setValueAt(productIdField.getText(), selectedRow, 0);
            tableModel.setValueAt(productNameField.getText(), selectedRow, 1);
            tableModel.setValueAt(categoryField.getText(), selectedRow, 2);
            tableModel.setValueAt(Double.parseDouble(priceField.getText()), selectedRow, 3);
            tableModel.setValueAt(Integer.parseInt(quantityField.getText()), selectedRow, 4);
            clearFields();
        }
    }

    private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            clearFields();
        }
    }

    private void clearFields() {
        productIdField.setText("");
        productNameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    private void setFieldsFromSelectedRow() {
        int selectedRow = productTable.getSelectedRow();
        productIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
        productNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
        categoryField.setText(tableModel.getValueAt(selectedRow, 2).toString());
        priceField.setText(tableModel.getValueAt(selectedRow, 3).toString());
        quantityField.setText(tableModel.getValueAt(selectedRow, 4).toString());
    }
}
