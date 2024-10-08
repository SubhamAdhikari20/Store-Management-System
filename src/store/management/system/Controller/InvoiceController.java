
package store.management.system.Controller;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import javax.swing.table.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JFileChooser;


import store.management.system.View.InvoiceView;
import store.management.system.Model.InvoiceModel;
import store.management.system.DAO.InvoiceDAO;

public class InvoiceController {
    private InvoiceView invoiceView;
    private InvoiceDAO invoiceDAO;
    public String invoiceId;
    
    
    

    public InvoiceController(InvoiceView invoiceView, InvoiceDAO invoiceDAO) {
        this.invoiceView = invoiceView;
        this.invoiceDAO = invoiceDAO;
        this.invoiceView.addSearchButtonListener(new SearchButtonListener());
        this.invoiceView.addAddButtonListener(new AddButtonListener());
        this.invoiceView.addCalculateButtonListener(new CalculateButtonListener());
        this.invoiceView.addPrintButtonListener(new PrintButtonListener());
        loadCategories();
    }
    
    public void loadCategories() {
        // Fetch categories from DAO
        List<String> categories = invoiceDAO.fetchCategory();

        // Update the view
        invoiceView.updateCategoryComboBox(categories);
    }
   
    
    
    // Search Button
    class SearchButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String productFieldInput = invoiceView.getProductField(); // Assuming this returns the content of the product field.
            String category = invoiceView.getCategoryField();

            Integer productID = null;
            String productName = null;

            try {
                // Try to parse the productFieldInput as an Integer for productID
                productID = Integer.parseInt(productFieldInput);
            } 
            catch (NumberFormatException ex) {
                // If parsing fails, treat it as a product name
                productName = productFieldInput;
            }

            int invoiceID = 0;  // Assuming this is handled elsewhere
            int quantity = 0;   // Will be determined in the search or set later
            BigDecimal price = null;
            BigDecimal total = null;  // Will be determined in the search or set later

            // Input validation
            if ((productID == null && (productName == null || productName.isEmpty())) && category.equals("Select")) {
                JOptionPane.showMessageDialog(invoiceView, "Please, fill fields with correct product details!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create an InvoiceModel instance with the available data
            InvoiceModel invoiceModel = new InvoiceModel(invoiceID, (productID != null ? productID : 0),(productName != null ? productName : ""), category, quantity, price, total);

            // Perform search
            Object[][] data = invoiceDAO.search(invoiceModel);
            String[] column = invoiceView.getTableHeader();
            DefaultTableModel tableModel1 = new DefaultTableModel(data, column);
            invoiceView.setTableModel1(tableModel1);
            
        }
    }
    

    // Add Button
    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            DefaultTableModel tableModel1 = (DefaultTableModel) invoiceView.getProductTableModel1();
            DefaultTableModel tableModel2 = (DefaultTableModel) invoiceView.getProductTableModel2();

            // Get selected rows from productTable1
            int[] selectedRows = invoiceView.getSelectedRowsProductTable1();
            
            
            for (int i : selectedRows) {
                // Create a new row with data from the selected row in table1
                Object[] rowData = new Object[tableModel1.getColumnCount()];
                for (int j = 0; j < tableModel1.getColumnCount(); j++) {
                    rowData[j] = tableModel1.getValueAt(i, j);
                }
                // Add the new row to table2
                tableModel2.addRow(rowData);
            }

            // Optionally clear selection in table1 after adding rows to table2
            invoiceView.tableClearSelection();
           
        }
        
        
        
        
        /*
        @Override
        public void actionPerformed(ActionEvent e){
            String productField = invoiceView.getProductField();
            String category = invoiceView.getCategoryField();
            int quantity = 0;
            BigDecimal total = null;

            
            InvoiceModel invoiceModel = new InvoiceModel(productField, category, quantity, total);
            
            if (productField.equals("") && category.equals("Select")) {
                JOptionPane.showMessageDialog(invoiceView, "Please, fill feilds with correct staff details!", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                Object [][] data = invoiceDAO.search(invoiceModel);
                String [] column = invoiceView.getTableHeader();
                DefaultTableModel tableModel2 = new DefaultTableModel(data, column);
                invoiceView.setTableModel2(tableModel2);
            }
        }
        */
        
        
    }
    
    
    

    class CalculateButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DefaultTableModel tableModel2 = (DefaultTableModel) invoiceView.getProductTableModel2();
            BigDecimal total = BigDecimal.ZERO;

            // Retrieve the existing total from the view
            String totalString = invoiceView.getTotalField();
            if (!totalString.isEmpty()) {
                try {
                    total = new BigDecimal(totalString);
                } catch (NumberFormatException ex) {
                    // Handle the case where the total is not a valid number
                    total = BigDecimal.ZERO;
                }
            }

            boolean stockAvailable = true;
            int rowCount = tableModel2.getRowCount();
            int specifiedQuantity = Integer.parseInt(invoiceView.getQuantityField()); // Get quantity from text box

            // First, check if all products are in stock and the specified quantity is valid
            for (int i = 0; i < rowCount; i++) {
                String productId = tableModel2.getValueAt(i, 0).toString();
                int tableQuantity = Integer.parseInt(tableModel2.getValueAt(i, 3).toString()); // Quantity from table

                // Check if the specified quantity exceeds the available quantity in the table
                if (specifiedQuantity > tableQuantity) {
                    JOptionPane.showMessageDialog(invoiceView, "Quantity specified exceeds quantity in table for product ID: " + productId, "Quantity Error", JOptionPane.WARNING_MESSAGE);
                    stockAvailable = false;
                    return; // Abort if the quantity exceeds
                }

                // Check stock availability
                int stock = invoiceDAO.checkStock(productId);
                if (stock < specifiedQuantity) {
                    JOptionPane.showMessageDialog(invoiceView, "Insufficient stock for product ID: " + productId, "Out of Stock", JOptionPane.WARNING_MESSAGE);
                    stockAvailable = false;
                    return; // Abort if out of stock
                }
            }

            if (stockAvailable) {
                // If stock is available, calculate the total
                for (int i = 0; i < rowCount; i++) {
                    String productId = tableModel2.getValueAt(i, 0).toString();
                    String productName = tableModel2.getValueAt(i, 1).toString();
                    String category = tableModel2.getValueAt(i, 2).toString();
                    int tableQuantity = Integer.parseInt(tableModel2.getValueAt(i, 3).toString()); // Quantity from table

                    // Calculate the reduced quantity
                    int reducedQuantity = tableQuantity - specifiedQuantity;

                    if (reducedQuantity >= 0) {
                        BigDecimal pricePerUnit = invoiceDAO.fetchProductPrice(productId);
                        InvoiceModel invoiceModel = new InvoiceModel(0, Integer.parseInt(productId), productName, category, specifiedQuantity, pricePerUnit, BigDecimal.ZERO);
                        invoiceModel.calculateTotal(pricePerUnit);

                        // Add to the total
                        total = total.add(invoiceModel.getTotalField());

                        // Update the table with the reduced quantity
                        tableModel2.setValueAt(reducedQuantity, i, 3); // Assuming the quantity column is at index 3
                    } else {
                        // Reset the total and break out if any product has an invalid reduced quantity
                        total = BigDecimal.ZERO;
                        JOptionPane.showMessageDialog(invoiceView, "Quantity specified exceeds quantity in table for product ID: " + productId, "Quantity Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                // Update inventory after processing all rows
                updateInventory();
                invoiceView.setTotalField(total.toString()); // Update the total field in the view
            }
        } 
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(invoiceView, "Please enter a valid quantity!", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(invoiceView, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateInventory() {
        DefaultTableModel tableModel2 = (DefaultTableModel) invoiceView.getProductTableModel2();
        int rowCount = tableModel2.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String productId = tableModel2.getValueAt(i, 0).toString();
            int reducedQuantity = Integer.parseInt(tableModel2.getValueAt(i, 3).toString()); // Get reduced quantity from table

            // Update database with the reduced quantity
            boolean success = invoiceDAO.updateProductQuantity(productId, reducedQuantity);
            if (!success) {
                JOptionPane.showMessageDialog(invoiceView, "Error updating quantity for product ID: " + productId, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}



    
    
    
    class PrintButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<InvoiceModel> invoiceDetails = invoiceDAO.fetchInvoiceDetails();
                if (invoiceDetails.isEmpty()) {
                    JOptionPane.showMessageDialog(invoiceView, "No invoice details found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                InvoiceModel latestInvoice = invoiceDetails.get(0); // Get the latest invoice details
                int invoiceID = latestInvoice.getInvoiceID();
                Document document = new Document();

                // Create the PDF file with invoice ID
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().endsWith(".pdf")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
                }

                try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                    PdfWriter.getInstance(document, fos);
                    document.open();

                    // Add content to the PDF
                    document.add(new Paragraph("Invoice ID: " + invoiceID));
                    document.add(new Paragraph("Date: " + new java.util.Date()));
                    document.add(new Paragraph(""));

                    DefaultTableModel tableModel2 = (DefaultTableModel) invoiceView.getProductTableModel2();
                    int rowCount = tableModel2.getRowCount();

                    // Add a table header
                    document.add(new Paragraph(String.format("%-15s %-30s %-20s %-10s %-10s", "Product ID", "Product Name", "Category", "Quantity", "Price")));
                    document.add(new Paragraph("--------------------------------------------------------------------------------------------"));

                    BigDecimal total = BigDecimal.ZERO;

                    for (int i = 0; i < rowCount; i++) {
                        String productId = tableModel2.getValueAt(i, 0).toString();
                        String productName = tableModel2.getValueAt(i, 1).toString();
                        String category = tableModel2.getValueAt(i, 2).toString();
                        int quantity = Integer.parseInt(tableModel2.getValueAt(i, 3).toString());
                        BigDecimal price = new BigDecimal(tableModel2.getValueAt(i, 4).toString());

                        BigDecimal lineTotal = price.multiply(new BigDecimal(quantity));
                        total = total.add(lineTotal);

                        document.add(new Paragraph(String.format("%-15s %-30s %-20s %-10d %-10s", productId, productName, category, quantity, price.toString())));

                        // Insert invoice details into the database
                        boolean inserted = invoiceDAO.insertInvoiceDetails(productId, productName, category, quantity, price, lineTotal);
                        if (!inserted) {
                            JOptionPane.showMessageDialog(invoiceView, "Error inserting invoice details for product ID: " + productId, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    document.add(new Paragraph(""));
                    document.add(new Paragraph("Total: " + total.toString()));
                    document.close();

                    JOptionPane.showMessageDialog(invoiceView, "Invoice has been printed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (DocumentException | IOException ex) {
                    JOptionPane.showMessageDialog(invoiceView, "Error creating PDF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(invoiceView, "Error fetching invoice details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}

    
    

