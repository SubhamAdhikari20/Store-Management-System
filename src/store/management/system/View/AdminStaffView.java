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

import store.management.system.View.AdminHomeView;
import store.management.system.Controller.AdminStaffController;

public class AdminStaffView extends CustomPanel {

    CustomTextField staffIDTextField, staffNameTextField;
    CustomButton searchButton, addStaffButton;
    DefaultTableModel tableModel;
    JTable staffTable;
    AddStaffPanel addStaffPanel;
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
        searchButton.setBounds(900, 70, 100, 47);
        super.add(searchButton);

        // Staff Information Section
        // Staff ID Label
        JLabel idLabel = new JLabel("Staff ID");
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        idLabel.setBounds(150, 10, 100, 35);

        // Name Label
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setBounds(400, 10, 100, 35);

        // Gender Label
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        genderLabel.setBounds(630, 10, 100, 35);

        // Gender Label
        JLabel contactLabel = new JLabel("Contact");
        contactLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contactLabel.setBounds(850, 10, 100, 35);

        // Header Panel
        CustomPanel headerPanel = new CustomPanel();
        headerPanel.setBackground(new Color(216, 205, 205));
        headerPanel.setBounds(30, 130, 1143, 60);
        super.add(headerPanel);
        headerPanel.add(idLabel);
        headerPanel.add(nameLabel);
        headerPanel.add(genderLabel);
        headerPanel.add(contactLabel);

        // Scroll Pane for Table
        CustomScrollPane scrollPane = new CustomScrollPane();
        scrollPane.setBackground(new Color(241, 233, 233));
        scrollPane.setBounds(30, 187, 1143, 350);
        super.add(scrollPane);

         */
        // Add Staff Button
        addStaffButton = new CustomButton();
        addStaffButton.setText("Add Staff");
        addStaffButton.setFocusable(false);
        addStaffButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addStaffButton.setFont(new Font("Arial", Font.BOLD, 18));
        addStaffButton.setForeground(Color.black);
        addStaffButton.setBackground(new Color(217, 74, 70));
        addStaffButton.setHoverColor(new Color(197, 54, 50));
        addStaffButton.setPressColor(new Color(177, 34, 30));
        addStaffButton.setBounds(30, 550, 110, 50);
        super.add(addStaffButton);

        // Add Staff Panel
        addStaffPanel = new AddStaffPanel();
        this.adminHomeView.getMainPanel().add(addStaffPanel, "Add Staff");

        AdminStaffDAO adminStaffDAO = new AdminStaffDAO();
        AdminStaffController staffController = new AdminStaffController(this, adminStaffDAO);
    }

    class DynamicPanel extends JPanel {

        String id;
        String name;
        String gender;
        String contact;

        public DynamicPanel(String id, String name, String gender, String contact) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.contact = contact;
            setLayout(null);
            setBackground(Color.red);
//            setBackground(new Color(241, 233, 233));
            setBounds(30, 187, 1143, 100);

            // ID 
            JLabel idLabel = new JLabel(this.id);
            idLabel.setFont(new Font("Arial", Font.BOLD, 18));
            idLabel.setBounds(150, 10, 100, 35);
            add(idLabel);

            // Name
            JLabel nameLabel = new JLabel(this.name);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
            nameLabel.setBounds(400, 10, 100, 35);
            add(nameLabel);

            // Gender Label
            JLabel genderLabel = new JLabel(this.gender);
            genderLabel.setFont(new Font("Arial", Font.BOLD, 18));
            genderLabel.setBounds(630, 10, 100, 35);

            // Contact Label
            JLabel contactLabel = new JLabel(this.contact);
            contactLabel.setFont(new Font("Arial", Font.BOLD, 18));
            contactLabel.setBounds(850, 10, 100, 35);
        }

    }

    public CustomPanel getMainPanel() {
        return adminHomeView.getMainPanel();
    }

    public CardLayout getCardLayout() {
        return adminHomeView.getCardLayout();
    }

    // Add Staff Button
    public JButton getAddStaffButton() {
        return addStaffButton;
    }

    public void addAddStaffButtonListener(ActionListener addStaffButtonListener) {
        addStaffButton.addActionListener(addStaffButtonListener);
    }

    // Go Back Button
    public void addGoBackButtonListener(ActionListener addStaffButtonListener) {
        addStaffPanel.getGoBackButton().addActionListener(addStaffButtonListener);
    }

    // Clear Button
    public void addClearButtonListener(ActionListener addClearButtonListener) {
        addStaffPanel.getClearButton().addActionListener(addClearButtonListener);
    }

    // ------------------------------------- CRUD ----------------------------------------
    // Text
    public String getStaffID() {
        return addStaffPanel.getStaffID();
    }

    public String getStaffName() {
        return addStaffPanel.getStaffName();
    }

    public String getStaffGender() {
        return addStaffPanel.getStaffGender();
    }

    public String getStaffContact() {
        return addStaffPanel.getStaffContact();
    }

    public String getStaffPassword() {
        return addStaffPanel.getStaffPassword();
    }

    public String getStaffConfirmPassword() {
        return addStaffPanel.getStaffConfirmPassword();
    }

    // Set
    public void setStaffID(String text) {
        addStaffPanel.setStaffID(text);
    }

    public void setStaffName(String text) {
        addStaffPanel.setStaffName(text);
    }

    public void setStaffGender(String text) {
        addStaffPanel.setStaffGender(text);
    }

    public void setStaffContact(String text) {
        addStaffPanel.setStaffContact(text);
    }

    public void setStaffPassword(String text) {
        addStaffPanel.setStaffPassword(text);
    }

    public void setStaffConfirmPassword(String text) {
        addStaffPanel.setStaffConfirmPassword(text);
    }

    public String[] getTableHeader() {
        return addStaffPanel.getTableHeader();
    }

    public void setTableModel(DefaultTableModel tableModel) {
        addStaffPanel.setTableModel(tableModel);
    }

    // Action Listeners
    public void addAddButtonListener(ActionListener addButtonListener) {
        addStaffPanel.getAddButton().addActionListener(addButtonListener);
    }

}

class AddStaffPanel extends CustomPanel {

    CustomTextField staffIDTextField, staffNameTextField, staffMobileNumberTextField;
    CustomPasswordField staffPasswordField, staffConfirmPasswordField;
    CustomComboBox staffGenderComboBox;
    CustomButton addButton;
    JButton goBackButton, clearButton;
    DefaultTableModel tableModel;
    CustomTable staffTable;
    String[] column;
    // AdminStaffModel adminStaffModel;

    @SuppressWarnings("empty-statement")
    public AddStaffPanel() {
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

        // Add Staff Title Label
        JLabel addStaffTitleLabel = new JLabel("Add Staff");
        addStaffTitleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        addStaffTitleLabel.setBounds(30, 25, 100, 40);
        super.add(addStaffTitleLabel);

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
        clearButton.setBounds(1040, 65, 60, 50);
        super.add(clearButton);

        // Add Button
        addButton = new CustomButton();
        addButton.setText("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(46, 109, 180));
        addButton.setHoverColor(new Color(26, 89, 160));
        addButton.setPressColor(new Color(6, 69, 140));
        addButton.setBounds(1025, 165, 100, 47);
        super.add(addButton);

        // Table Model
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
        scrollPane.setBounds(30, 240, 1143, 350);
        super.add(scrollPane);

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
}
