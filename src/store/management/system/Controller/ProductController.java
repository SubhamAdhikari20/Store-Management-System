package store.management.system.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;
import javax.swing.table.*;
import store.management.system.View.ProductView;
import store.management.system.DAO.ProductDAO;
import store.management.system.Model.ProductModel;

public class ProductController {
    private final ProductView ProductView;
    private final ProductDAO productDAO;

    public ProductController(ProductView adminProductView, ProductDAO productDAO) {
        this.ProductView = adminProductView;
        this.productDAO = productDAO;
        this.ProductView.addActionListener(new ProductActionListener());
    }

    // Inner class to handle button actions
    class ProductActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Add":
                    addProduct();
                    break;
                case "Update":
                    updateProduct();
                    break;
                case "Delete":
                    deleteProduct();
                    break;
                case "Clear":
                    ProductView.clearFields();
                    break;
            }
            
            /*
            if (command == "Add"){
                addProduct();
            }
            else if (command == "Update"){
                updateProduct();
            }
            else if (command == "Delete"){
                deleteProduct();
            }
            else if (command == "Clear"){
                ProductView.clearFields();
            }
            */
            
        }

        // Method to add a product
        private void addProduct() {
            String productId = ProductView.getProductIdField();
            String productName = ProductView.getProductNameField();
            String category = ProductView.getCategoryField();
            String price = ProductView.getPriceField();
            String quantity = ProductView.getQuantityField();

            if (productId.isEmpty() || productName.isEmpty() || category.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                JOptionPane.showMessageDialog(ProductView, "Please, fill all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
            }
            else{
                try {
                    BigDecimal productPrice = new BigDecimal(price);
                    int productQuantity = Integer.parseInt(quantity);

                    ProductModel productModel = new ProductModel(productId, productName, category, productPrice, productQuantity);
                    if (productDAO.executeAddQuery(productModel)) {
                        updateTable();
                        JOptionPane.showMessageDialog(ProductView, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ProductView, "Failed to add product!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ProductView, "Price and Quantity must be numbers!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }    
            }
            
        }

        // Method to update a product
        private void updateProduct() {
            ProductModel selectedProduct = ProductView.getSelectedProduct();

            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(ProductView, "Please, select a row from the table!", "No Selection", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String productId = ProductView.getProductIdField();
                String productName = ProductView.getProductNameField();
                String category = ProductView.getCategoryField();
                String price = ProductView.getPriceField();
                String quantity = ProductView.getQuantityField();

                if (productId.isEmpty() || productName.isEmpty() || category.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(ProductView, "Please, fill all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        BigDecimal productPrice = new BigDecimal(price); 
                        int productQuantity = Integer.parseInt(quantity);

                        ProductModel productModel = new ProductModel(productId, productName, category, productPrice, productQuantity);
                        if (productDAO.executeUpdateQuery(productModel)) {
                            updateTable();
                            JOptionPane.showMessageDialog(ProductView, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } 
                        else {
                            JOptionPane.showMessageDialog(ProductView, "Failed to update product!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } 
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ProductView, "Price and Quantity must be numbers!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }

                
            }
            
        }

        // Method to delete a product
        private void deleteProduct() {
            ProductModel selectedProduct = ProductView.getSelectedProduct();

            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(ProductView, "Please, select a row from the table!", "No Selection", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String productId = selectedProduct.getProductId();

                if (productDAO.executeDeleteQuery(productId)) {
                    updateTable();
                    ProductView.clearFields();
                    JOptionPane.showMessageDialog(ProductView, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } 
                else {
                    JOptionPane.showMessageDialog(ProductView, "Failed to delete product!", "Error", JOptionPane.ERROR_MESSAGE);
                }    
            }
            
        }

        // Method to update the table with the latest product data
        private void updateTable() {
            Object[][] data = productDAO.fetchProductInfo();
            String[] columnNames = {"Product ID", "Product Name", "Category", "Quantity", "Price"};
            ProductView.setTableModel(new DefaultTableModel(data, columnNames));
        }
    }
}
