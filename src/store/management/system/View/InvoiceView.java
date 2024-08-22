package store.management.system.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import store.management.system.Controller.InvoiceController;
import store.management.system.Model.InvoiceModel;
import store.management.system.DAO.InvoiceDAO;


public class InvoiceView extends CustomPanel {

    private CustomTextField productField, totalField, quantityField;
    CustomComboBox categoryComboBox;
    private CustomTable productTable1, productTable2;
    private DefaultTableModel tableModel1, tableModel2;
    private CustomButton calculateButton, clearButton, printButton, searchButton, addButton;
    String[] column;
    InvoiceController invoiceController;
    
    public InvoiceView() {
        setBackground(Color.white);
        setLayout(null);

        // Add Invoice Title Label
        JLabel invoiceTitleLabel = new JLabel("Invoice");
        invoiceTitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        invoiceTitleLabel.setBounds(30, 25, 200, 40);
        add(invoiceTitleLabel);

        // Product Label and Text Field
        JLabel productLabel = new JLabel("Product ID/Name:");
        productLabel.setFont(new Font("Arial", Font.BOLD, 17));
        productLabel.setBounds(30, 70, 200, 40);
        add(productLabel);

        productField = new CustomTextField();
        productField.setFont(new Font("Arial", Font.PLAIN, 16));
        productField.setBounds(190, 73, 250, 35);
        add(productField);
        
        // Price Label and Text Field
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 17));
        categoryLabel.setBounds(520, 70, 120, 40);
        add(categoryLabel);
        
//        String[] options = {"Select", "Clothes", "Shoes", "Mobile"};
        String[] options = {"Select"};
        categoryComboBox = new CustomComboBox(options);
        categoryComboBox.setFocusable(false);
        categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        categoryComboBox.setBackground(Color.white);
        categoryComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        categoryComboBox.setSelectedItem("Select");
        categoryComboBox.setBounds(650, 73, 250, 35);
        super.add(categoryComboBox);
        
        // Search Button
        searchButton = new CustomButton();
        searchButton.setText("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButton.setForeground(Color.black);
        searchButton.setBackground(new Color(150, 192, 101));
        searchButton.setHoverColor(new Color(130, 172, 81));
        searchButton.setPressColor(new Color(110, 152, 61));
        searchButton.setBounds(930, 70, 100, 47);
        super.add(searchButton);
        
        // Clear Button
        clearButton = new CustomButton();
        clearButton.setText("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setForeground(Color.white);
        clearButton.setBackground(new Color(46, 109, 180));
        clearButton.setHoverColor(new Color(26, 89, 160));
        clearButton.setPressColor(new Color(6, 69, 140));
        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                clear();
            }
        });
        clearButton.setBounds(1070, 70, 100, 47);
        super.add(clearButton);
        
        // Add Button
        addButton = new CustomButton();
        addButton.setText("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(46, 109, 180));
        addButton.setHoverColor(new Color(26, 89, 160));
        addButton.setPressColor(new Color(6, 69, 140));
        addButton.setBounds(1070, 190, 100, 47);
        super.add(addButton);
        
        
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        // --------------------Table 1--------------------
        // Table Model
        column = new String[]{"Product ID", "Product Name", "Category", "Quantity", "Price"};
        tableModel1 = new DefaultTableModel(column, 0);

        // Product Table - 1
        productTable1 = new CustomTable(tableModel1);
        productTable1.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable1.setRowHeight(25);
        productTable1.setAutoCreateRowSorter(true);

        // Scroll Pane for Table
        JScrollPane scrollPane1 = new JScrollPane(productTable1);
        scrollPane1.setBounds(30, 130, 1000, 185);
        add(scrollPane1);
        
        
        // --------------------Table 2--------------------
        // Table Model
        
        tableModel2 = new DefaultTableModel(column, 0);

        // Product Table - 1
        productTable2 = new CustomTable(tableModel2);
        productTable2.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable2.setRowHeight(25);
        productTable2.setAutoCreateRowSorter(true);

        // Scroll Pane for Table
        JScrollPane scrollPane2 = new JScrollPane(productTable2);
        scrollPane2.setBounds(30, 330, 1143, 185);
        add(scrollPane2);
        
        
        
        // Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 17));
        quantityLabel.setBounds(30, 540, 150, 40);
        add(quantityLabel);

        quantityField = new CustomTextField();
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField.setBounds(130, 545, 250, 35);
        add(quantityField);
        
        // Total Label and Text Field
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 17));
        totalLabel.setBounds(450, 540, 150, 40);
        add(totalLabel);

        totalField = new CustomTextField();
        totalField.setFont(new Font("Arial", Font.PLAIN, 16));
        totalField.setBounds(520, 545, 150, 35);
        totalField.setEditable(false);
        add(totalField);
        
        
        // Calculate Button
        calculateButton = new CustomButton();
        calculateButton.setText("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 18));
        calculateButton.setForeground(Color.white);
        calculateButton.setBackground(new Color(46, 109, 180));
        calculateButton.setHoverColor(new Color(26, 89, 160));
        calculateButton.setPressColor(new Color(6, 69, 140));
        calculateButton.setBounds(750, 545, 120, 47);
        add(calculateButton);
        
        
        // Print Button
        printButton = new CustomButton();
        printButton.setText("Print");
        printButton.setFont(new Font("Arial", Font.BOLD, 18));
        printButton.setForeground(Color.white);
        printButton.setBackground(new Color(46, 109, 180));
        printButton.setHoverColor(new Color(26, 89, 160));
        printButton.setPressColor(new Color(6, 69, 140));
        printButton.setBounds(900, 545, 100, 47);
        add(printButton);
        
        
        
        invoiceController = new InvoiceController(this, invoiceDAO);
        
        
    }
    
    // Method to update category combo box
    public void updateCategoryComboBox(List<String> categories) {
        categoryComboBox.removeAllItems(); // Clear existing items
        categoryComboBox.addItem("Select"); // Add default item

        for (String category : categories) {
            categoryComboBox.addItem(category);
        }
    }
    
    
    
    public String getProductField(){
        return productField.getText();
    }
    
    public String getCategoryField(){
        return categoryComboBox.getSelectedItem().toString();
    }
    
    public String getQuantityField(){
        return quantityField.getText();
    }
    
    public String getTotalField(){
        return totalField.getText();
    }
    
    public String[] getTableHeader() {
        return column;
    }
    
    public DefaultTableModel getTableModel1() {
        return tableModel1;
    }
    
    public DefaultTableModel getTableModel2() {
        return tableModel2;
    }
    
    public CustomTable getProductTable1(){
        return productTable1;
    }
    
    public CustomTable getProductTable2(){
        return productTable2;
    }
    
    public DefaultTableModel getProductTableModel2(){
        DefaultTableModel tableModel1 = (DefaultTableModel) productTable1.getModel();
        return tableModel1;
    }
    
    public DefaultTableModel getProductTableModel1(){
        DefaultTableModel tableModel2 = (DefaultTableModel) productTable2.getModel();
        return tableModel2;
    }
    
    public int[] getSelectedRowsProductTable1(){
        int[] selectedRows = productTable2.getSelectedRows();
        return selectedRows;
    }
    
    public void tableClearSelection(){
        productTable1.clearSelection();
    }
        
    
    public void setProductField(String text){
        productField.setText(text);
    }
    
    public void setCategoryField(String text){
        categoryComboBox.setSelectedItem(text);
    }
    
    public void setQuantityField(String text){
        quantityField.setText(text);
    }
    
    public void setTotalField(String text){
        totalField.setText(text);
    }
    
    
    
    public void setTableModel1(DefaultTableModel tableModel1) {
        productTable1.setModel(tableModel1);
    }
    
    public void setTableModel2(DefaultTableModel tableModel2) {
        productTable2.setModel(tableModel2);
    }
    
    
    // ---------- Search Button ---------------
    public void addSearchButtonListener(ActionListener addSearchButtonListener) {
        searchButton.addActionListener(addSearchButtonListener);
    }
    
    // ---------- Add Button ---------------
    public void addAddButtonListener(ActionListener addAddButtonListener) {
        addButton.addActionListener(addAddButtonListener);
    }
    
    // ---------- Calculate Button ---------------
    public void addCalculateButtonListener(ActionListener calculateButtonListener) {
        calculateButton.addActionListener(calculateButtonListener);
    }
    
    // ---------- Print Button ---------------
    public void addPrintButtonListener(ActionListener addPrintButtonListener) {
        printButton.addActionListener(addPrintButtonListener);
    }
    
    
    
    
//    // ---------- Referesh Button ---------------
//    public void addRefereshButtonListener(ActionListener addRefereshButtonListener) {
//        refereshButton.addActionListener(addRefereshButtonListener);
//    }
//    
//    // ------------CRUD Button----------
//    public void addCRUDButtonListener(ActionListener addStaffButtonListener) {
//        crudButton.addActionListener(addStaffButtonListener);
//    }
    
    
    // Clear Button
    public void clear() {
        productField.setText("");
        categoryComboBox.setSelectedItem("Select");
        quantityField.setText("");
        totalField.setText("");
        invoiceController.loadCategories();
        
        productTable1.clearSelection();
        productTable2.clearSelection();
    }

}
