
package store.management.system.Controller;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import javax.swing.table.*;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


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
                DefaultTableModel tableModel1 = new DefaultTableModel(data, column);
                invoiceView.setTableModel1(tableModel1);
            }
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

                int rowCount = tableModel2.getRowCount();
                boolean stockAvailable = true;

                // First, check if all products are in stock
                for (int i = 0; i < rowCount; i++) {
                    String productId = tableModel2.getValueAt(i, 0).toString();
                    int quantity = Integer.parseInt(invoiceView.getQuantityField()); // Get quantity from text box

                    int stock = invoiceDAO.checkStock(productId);
                    if (stock < quantity) {
                        JOptionPane.showMessageDialog(invoiceView, "Insufficient stock for product ID: " + productId, "Out of Stock", JOptionPane.WARNING_MESSAGE);
                        stockAvailable = false;
                        break; // Abort stock checking if any product is out of stock
                    }
                }

                if (stockAvailable) {
                    // If stock is available, calculate the total
                    for (int i = 0; i < rowCount; i++) {
                        String productId = tableModel2.getValueAt(i, 0).toString();
                        int quantity = Integer.parseInt(invoiceView.getQuantityField()); // Get quantity from text box

                        BigDecimal pricePerUnit = invoiceDAO.fetchProductPrice(productId);

                        InvoiceModel invoiceModel = new InvoiceModel(productId, null, quantity, BigDecimal.ZERO);
                        invoiceModel.calculateTotal(pricePerUnit);

                        total = total.add(invoiceModel.getTotalField());
                    }

                    updateInventory();
                    invoiceView.setTotalField(total.toString());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(invoiceView, "Please enter a valid quantity!", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void updateInventory() {
            DefaultTableModel tableModel2 = (DefaultTableModel) invoiceView.getProductTableModel2();
            int rowCount = tableModel2.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                String productId = tableModel2.getValueAt(i, 0).toString();
                int quantity = Integer.parseInt(invoiceView.getQuantityField()); // Get quantity from text box

                boolean success = invoiceDAO.updateProductQuantity(productId, quantity);
                if (!success) {
                    JOptionPane.showMessageDialog(invoiceView, "Error updating quantity for product ID: " + productId, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    
    
    class PrintButtonListener implements ActionListener {
         // Add invoiceId to the class        
        @Override
        public void actionPerformed(ActionEvent e) {
            Document document = new Document();
            try {
                // Define the file path with invoice ID
                String filePath = invoiceId + ".pdf"; // PDF file named based on invoice ID
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Add content to the PDF
                document.add(new Paragraph("Invoice"));
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
                JOptionPane.showMessageDialog(invoiceView, "Error printing invoice: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
}
