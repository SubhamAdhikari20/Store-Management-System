package store.management.system.View;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import javax.swing.table.*;
import java.math.*;

import store.management.system.View.AdminHomeView;
import store.management.system.Controller.AdminStaffController;
import store.management.system.DAO.AdminStaffDAO;
import store.management.system.Model.AdminStaffModel;

public class AdminStaffView extends CustomPanel {

    CustomTextField staffIDTextField, staffNameTextField;
    CustomButton searchButton, crudButton, refereshButton;
    DefaultTableModel tableModel;
    CustomTable staffTable;
    String[] column;
    CRUDPanel crudPanel;
    AdminHomeView adminHomeView;

    public AdminStaffView(AdminHomeView adminHomeView) {
        this.adminHomeView = adminHomeView;

        super.setBackground(Color.white);

        // Add Staff Title Label
        JLabel staffTitleLabel = new JLabel("Staffs");
        staffTitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        staffTitleLabel.setBounds(30, 25, 75, 40);
        super.add(staffTitleLabel);

        // Staff ID Label
        JLabel staffIDLabel = new JLabel("Staff ID:");
        staffIDLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffIDLabel.setBounds(30, 70, 100, 40);
        super.add(staffIDLabel);

        // Staff ID Text Field 
        staffIDTextField = new CustomTextField();
        staffIDTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffIDTextField.setBounds(140, 74, 250, 35);
        super.add(staffIDTextField);

        // Staff Name Label
        JLabel staffNameLabel = new JLabel("Staff Name:");
        staffNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffNameLabel.setBounds(425, 70, 100, 40);
        super.add(staffNameLabel);

        // Staff Name Text Field 
        staffNameTextField = new CustomTextField();
        staffNameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffNameTextField.setBounds(550, 74, 250, 35);
        super.add(staffNameTextField);

        // Search Button
        searchButton = new CustomButton();
        searchButton.setText("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButton.setForeground(Color.black);
        searchButton.setBackground(new Color(150, 192, 101));
        searchButton.setHoverColor(new Color(130, 172, 81));
        searchButton.setPressColor(new Color(110, 152, 61));
        searchButton.setBounds(870, 70, 100, 47);
        super.add(searchButton);
        
        
        // Refresh Button
        refereshButton = new CustomButton();
        refereshButton.setText("Referesh");
        refereshButton.setFont(new Font("Arial", Font.BOLD, 18));
        refereshButton.setForeground(Color.black);
        refereshButton.setBackground(new Color(150, 192, 101));
        refereshButton.setHoverColor(new Color(130, 172, 81));
        refereshButton.setPressColor(new Color(110, 152, 61));
        refereshButton.setBounds(1020, 70, 120, 47);
        super.add(refereshButton);
       
        
        // Table Model
        AdminStaffDAO adminStaffDAO = new AdminStaffDAO();
        Object[][] rowData = adminStaffDAO.fetchStaffInfo();
        column = new String[]{"Staff ID", "Staff Name", "Gender", "Mobile"};
        tableModel = new DefaultTableModel(rowData, column);

        // Staff Table
        staffTable = new CustomTable(tableModel);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 14));
        staffTable.setRowHeight(25);
        staffTable.setAutoCreateRowSorter(true);

        // Scroll Pane for Table
        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.setBounds(30, 130, 1143, 400);
        super.add(scrollPane);
        
        // crud Button
        crudButton = new CustomButton();
        crudButton.setText("CRUD");
        crudButton.setFocusable(false);
        crudButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        crudButton.setFont(new Font("Arial", Font.BOLD, 18));
        crudButton.setForeground(Color.black);
        crudButton.setBackground(new Color(217, 74, 70));
        crudButton.setHoverColor(new Color(197, 54, 50));
        crudButton.setPressColor(new Color(177, 34, 30));
        crudButton.setBounds(30, 550, 110, 50);
        super.add(crudButton);
        
        

        // Add Staff Panel
        crudPanel = new CRUDPanel();
        this.adminHomeView.getMainPanel().add(crudPanel, "Add Staff");


        AdminStaffController staffController = new AdminStaffController(this, adminStaffDAO);
    }


    public CustomPanel getMainPanel() {
        return adminHomeView.getMainPanel();
    }

    public CardLayout getCardLayout() {
        return adminHomeView.getCardLayout();
    }
    
    // ---------- Search Button ---------------
    public void addSearchButtonListener(ActionListener addSearchButtonListener) {
        searchButton.addActionListener(addSearchButtonListener);
    }
    
    // ---------- Referesh Button ---------------
    public void addRefereshButtonListener(ActionListener addRefereshButtonListener) {
        refereshButton.addActionListener(addRefereshButtonListener);
    }
    
    // ------------CRUD Button----------
    public void addCRUDButtonListener(ActionListener addStaffButtonListener) {
        crudButton.addActionListener(addStaffButtonListener);
    }
    
    public String getID(){
        return staffIDTextField.getText();
    }
    
    public String getName(){
        return staffNameTextField.getText();
    }
    
    public void setID(){
        staffIDTextField.setText("");
    }
    
    public void setName(){
        staffNameTextField.setText("");
    }
    

    // -------------------------------------------- CRUD -------------------------------------------------
    // Text
    public String getStaffID() {
        return crudPanel.getStaffID();
    }

    public String getStaffName() {
        return crudPanel.getStaffName();
    }

    public String getStaffGender() {
        return crudPanel.getStaffGender();
    }

    public String getStaffContact() {
        return crudPanel.getStaffContact();
    }

    public String getStaffPassword() {
        return crudPanel.getStaffPassword();
    }

    public String getStaffConfirmPassword() {
        return crudPanel.getStaffConfirmPassword();
    }

    // Set
    public void setStaffID(String text) {
        crudPanel.setStaffID(text);
    }

    public void setStaffName(String text) {
        crudPanel.setStaffName(text);
    }

    public void setStaffGender(String text) {
        crudPanel.setStaffGender(text);
    }

    public void setStaffContact(String text) {
        crudPanel.setStaffContact(text);
    }

    public void setStaffPassword(String text) {
        crudPanel.setStaffPassword(text);
    }

    public void setStaffConfirmPassword(String text) {
        crudPanel.setStaffConfirmPassword(text);
    }

    public String[] getTableHeader() {
        return crudPanel.getTableHeader();
    }

    public void setTableModel(DefaultTableModel tableModel) {
        crudPanel.setTableModel(tableModel);
        staffTable.setModel(tableModel);
    }
    
    public TableModel getStaffTableModel(){
        return staffTable.getModel();
    }
    
    public AdminStaffModel getSelectedStaff(){
        return crudPanel.getSelectedStaff();
    }
    
    public void clearFields(){
        crudPanel.clearFields();
    }
    
    
    //----------------------------CRUD Panel----------------------------
    // ---------- Action Listeners ------------
    // ADD
    public void addAddButtonListener(ActionListener addButtonListener) {
        crudPanel.getAddButton().addActionListener(addButtonListener);
    }
    
    // Update
    public void addUpdateButtonListener(ActionListener updateButtonListener) {
        crudPanel.getUpdateButton().addActionListener(updateButtonListener);
    }
    
    // Delete
    public void addDeleteButtonListener(ActionListener deleteButtonListener) {
        crudPanel.getDeleteButton().addActionListener(deleteButtonListener);
    }
    
    // Go Back Button
    public void addGoBackButtonListener(ActionListener addStaffButtonListener) {
        crudPanel.getGoBackButton().addActionListener(addStaffButtonListener);
    }

    // Clear Button
    public void addClearButtonListener(ActionListener addClearButtonListener) {
        crudPanel.getClearButton().addActionListener(addClearButtonListener);
    }

}

class CRUDPanel extends CustomPanel {

    CustomTextField staffIDTextField, staffNameTextField, staffMobileNumberTextField;
    CustomPasswordField staffPasswordField, staffConfirmPasswordField;
    CustomComboBox staffGenderComboBox;
    CustomButton addButton, updateButton, deleteButton;
    JButton goBackButton, clearButton;
    DefaultTableModel tableModel;
    CustomTable staffTable;
    String[] column;
    // AdminStaffModel adminStaffModel;

    @SuppressWarnings("empty-statement")
    public CRUDPanel() {
        super.setBackground(Color.white);
        super.setLayout(null);

        // Go back Button
        goBackButton = new JButton();
        goBackButton.setBackground(Color.white);
        goBackButton.setFocusable(false);
        goBackButton.setBorder(BorderFactory.createEmptyBorder());
        goBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon goBackButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/go_back_arrow.png"));
        Image goBackButtonImage = goBackButtonIcon.getImage().getScaledInstance(25, 20, Image.SCALE_SMOOTH);
        goBackButtonIcon = new ImageIcon(goBackButtonImage);
        goBackButton.setIcon(goBackButtonIcon);
        goBackButton.setBounds(7, 7, 25, 25);
        super.add(goBackButton);

        // CRUD Staff Title Label
        JLabel crudStaffTitleLabel = new JLabel("CRUD Staff");
        crudStaffTitleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        crudStaffTitleLabel.setBounds(30, 25, 130, 40);
        super.add(crudStaffTitleLabel);

        // ---------------- Staff Information -------------------
        // Staff Name Label
        JLabel staffNameLabel = new JLabel("Staff Name:");
        staffNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffNameLabel.setBounds(30, 70, 100, 40);
        super.add(staffNameLabel);

        // Staff Name Text Field 
        staffNameTextField = new CustomTextField();
        staffNameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffNameTextField.setBounds(190, 73, 250, 35);
        super.add(staffNameTextField);

        // Staff Gender Label
        JLabel staffGenderLabel = new JLabel("Gender:");
        staffGenderLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffGenderLabel.setBounds(30, 120, 100, 40);
        super.add(staffGenderLabel);

        String[] gender = {"Select", "Male", "Female", "Others"};
        staffGenderComboBox = new CustomComboBox(gender);
        staffGenderComboBox.setFocusable(false);
        staffGenderComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        staffGenderComboBox.setBackground(Color.white);
        staffGenderComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        staffGenderComboBox.setSelectedItem("Select");
        staffGenderComboBox.setBounds(190, 123, 250, 35);
        super.add(staffGenderComboBox);

        // Staff Mobile Number Label
        JLabel staffMobileNumberLabel = new JLabel("Mobile Number:");
        staffMobileNumberLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffMobileNumberLabel.setBounds(30, 170, 130, 40);
        super.add(staffMobileNumberLabel);

        // Staff  Mobile Number Text Field 
        staffMobileNumberTextField = new CustomTextField();
        staffMobileNumberTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffMobileNumberTextField.setBounds(190, 173, 250, 35);
        super.add(staffMobileNumberTextField);

        // Staff ID Label
        JLabel staffIDLabel = new JLabel("StaffID:");
        staffIDLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffIDLabel.setBounds(500, 70, 100, 40);
        super.add(staffIDLabel);

        // Staff ID Text Field 
        staffIDTextField = new CustomTextField();
        staffIDTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffIDTextField.setBounds(700, 73, 250, 35);
        super.add(staffIDTextField);

        // Staff Password Label
        JLabel staffPasswordLabel = new JLabel("Password:");
        staffPasswordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffPasswordLabel.setBounds(500, 120, 100, 40);
        super.add(staffPasswordLabel);

        // Staff  Mobile Number Text Field 
        staffPasswordField = new CustomPasswordField();
        staffPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffPasswordField.setBounds(700, 123, 250, 35);
        super.add(staffPasswordField);

        // Staff Password Label
        JLabel staffConfirmPasswordLabel = new JLabel("Confirm Password:");
        staffConfirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffConfirmPasswordLabel.setBounds(500, 170, 170, 40);
        super.add(staffConfirmPasswordLabel);

        // Staff  Mobile Number Text Field 
        staffConfirmPasswordField = new CustomPasswordField();
        staffConfirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffConfirmPasswordField.setBounds(700, 173, 250, 35);
        super.add(staffConfirmPasswordField);

        // Clear Button
        clearButton = new JButton();
        clearButton.setBackground(Color.white);
        clearButton.setFocusable(false);
        clearButton.setBorder(BorderFactory.createEmptyBorder());
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon clearButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/clear.png"));
        Image clearButtonImage = clearButtonIcon.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        clearButtonIcon = new ImageIcon(clearButtonImage);
        clearButton.setIcon(clearButtonIcon);
        clearButton.setBounds(1040, 100, 60, 50);
        super.add(clearButton);

        // Add Button
        addButton = new CustomButton();
        addButton.setText("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(46, 109, 180));
        addButton.setHoverColor(new Color(26, 89, 160));
        addButton.setPressColor(new Color(6, 69, 140));
        addButton.setBounds(150, 235, 100, 47);
        super.add(addButton);
        
        
        // Update Button
        updateButton = new CustomButton();
        updateButton.setText("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 18));
        updateButton.setForeground(Color.white);
        updateButton.setBackground(new Color(46, 109, 180));
        updateButton.setHoverColor(new Color(26, 89, 160));
        updateButton.setPressColor(new Color(6, 69, 140));
        updateButton.setBounds(300, 235, 120, 47);
        super.add(updateButton);
        
        // Delete Button
        deleteButton = new CustomButton();
        deleteButton.setText("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setForeground(Color.white);
        deleteButton.setBackground(new Color(46, 109, 180));
        deleteButton.setHoverColor(new Color(26, 89, 160));
        deleteButton.setPressColor(new Color(6, 69, 140));
        deleteButton.setBounds(470, 235, 120, 47);
        super.add(deleteButton);
        


        // Table Model
        AdminStaffDAO adminStaffDAO = new AdminStaffDAO();
        Object[][] rowData = adminStaffDAO.fetchStaffInfo();
        column = new String[]{"Staff ID", "Staff Name", "Gender", "Mobile"};
        tableModel = new DefaultTableModel(rowData, column);

        // Staff Table
        staffTable = new CustomTable(tableModel);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 14));
        staffTable.setRowHeight(25);
        staffTable.setAutoCreateRowSorter(true);

        // Scroll Pane for Table
        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.setBounds(30, 300, 1143, 290);
        super.add(scrollPane);
        
        
        // Add table row selection listener
        staffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = staffTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    staffTable.setRowSelectionInterval(row, row);
                    populateFields(row);
                }
            }
        });

    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    // ------------------- CRUD----------------------
    public CustomButton getAddButton() {
        return addButton;
    }
    
    public CustomButton getUpdateButton() {
        return updateButton;
    }
    
    public CustomButton getDeleteButton() {
        return deleteButton;
    }

    public String getStaffID() {
        return staffIDTextField.getText();
    }

    public String getStaffName() {
        return staffNameTextField.getText();
    }

    public String getStaffGender() {
        String gender = staffGenderComboBox.getSelectedItem().toString();
        return gender;
    }

    public String getStaffContact() {
        return staffMobileNumberTextField.getText();
    }

    public String getStaffPassword() {
        return staffPasswordField.getText();
    }

    public String getStaffConfirmPassword() {
        return staffConfirmPasswordField.getText();
    }

    // Set Text
    public void setStaffID(String text) {
        staffIDTextField.setText(text);
    }

    public void setStaffName(String text) {
        staffNameTextField.setText(text);
    }

    public void setStaffGender(String text) {
        staffGenderComboBox.setSelectedItem(text);

    }

    public void setStaffContact(String text) {
        staffMobileNumberTextField.setText(text);
    }

    public void setStaffPassword(String text) {
        staffPasswordField.setText(text);
    }

    public void setStaffConfirmPassword(String text) {
        staffConfirmPasswordField.setText(text);
    }

    public String[] getTableHeader() {
        return column;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        staffTable.setModel(tableModel);
    }
    
    
    public void clearFields() {
        staffIDTextField.setText("");
        staffNameTextField.setText("");
        staffGenderComboBox.setSelectedItem("Select");
        staffMobileNumberTextField.setText("");
        staffPasswordField.setText("");
        staffConfirmPasswordField.setText("");
    }

    
    // Method to get selected row data
    public AdminStaffModel getSelectedStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow == -1) {
            return null; // No row selected
        }

        String staffId = (String) staffTable.getValueAt(selectedRow, 0);
        String staffName = (String) staffTable.getValueAt(selectedRow, 1);
        String staffGender = (String) staffTable.getValueAt(selectedRow, 2);
        String staffContact = (String) staffTable.getValueAt(selectedRow, 2);
        String staffPassword = null;
        String staffConfirmPassword = null;
        
        
        return new AdminStaffModel(staffId, staffName, staffGender, staffContact, staffPassword, staffConfirmPassword);
    }

    // Method to populate fields based on the selected row
    private void populateFields(int row) {
        staffIDTextField.setText((String) staffTable.getValueAt(row, 0));
        staffNameTextField.setText((String) staffTable.getValueAt(row, 1));
        staffGenderComboBox.setSelectedItem((String) staffTable.getValueAt(row, 2));
        staffMobileNumberTextField.setText((String) staffTable.getValueAt(row, 3));
    }
    
}

