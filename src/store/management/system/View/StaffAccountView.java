package store.management.system.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import store.management.system.Controller.StaffAccountController;
import store.management.system.Model.StaffAccountModel;
import store.management.system.DAO.StaffAccountDAO;

public class StaffAccountView extends CustomPanel {

    private CustomTextField staffIDTextField, staffNameTextField, staffEmailTextField;
    private CustomPasswordField currentPasswordField, newPasswordField, confirmPasswordField;
    private CustomButton changePasswordButton, logoutButton;
    private JLabel staffIDLabel, staffNameLabel, staffEmailLabel;
    private JLabel currentPasswordLabel, newPasswordLabel, confirmPasswordLabel;

    public StaffAccountView(String staffID) {
        setBackground(Color.white);
        setLayout(null);

        // Title Label
        JLabel accountTitleLabel = new JLabel("Account Settings");
        accountTitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        accountTitleLabel.setBounds(30, 25, 250, 40);
        add(accountTitleLabel);

        // Staff ID Label
        staffIDLabel = new JLabel("Staff ID:");
        staffIDLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffIDLabel.setBounds(30, 70, 100, 40);
        add(staffIDLabel);

        // Staff ID Text Field (read-only)
        staffIDTextField = new CustomTextField();
        staffIDTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffIDTextField.setBounds(140, 74, 250, 35);
        staffIDTextField.setEditable(false);
        add(staffIDTextField);

        // Staff Name Label
        staffNameLabel = new JLabel("Name:");
        staffNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffNameLabel.setBounds(425, 70, 100, 40);
        add(staffNameLabel);

        // Staff Name Text Field (read-only)
        staffNameTextField = new CustomTextField();
        staffNameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffNameTextField.setBounds(550, 74, 250, 35);
        staffNameTextField.setEditable(false);
        add(staffNameTextField);

        // Staff Email Label
        staffEmailLabel = new JLabel("Email:");
        staffEmailLabel.setFont(new Font("Arial", Font.BOLD, 17));
        staffEmailLabel.setBounds(30, 130, 100, 40);
        add(staffEmailLabel);

        // Staff Email Text Field (read-only)
        staffEmailTextField = new CustomTextField();
        staffEmailTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        staffEmailTextField.setBounds(140, 134, 250, 35);
        staffEmailTextField.setEditable(false);
        add(staffEmailTextField);

        // Current Password Label
        currentPasswordLabel = new JLabel("Current Password:");
        currentPasswordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        currentPasswordLabel.setBounds(30, 200, 200, 40);
        add(currentPasswordLabel);

        // Current Password Field
        currentPasswordField = new CustomPasswordField();
        currentPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        currentPasswordField.setBounds(200, 204, 250, 35);
        add(currentPasswordField);

        // New Password Label
        newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        newPasswordLabel.setBounds(30, 260, 200, 40);
        add(newPasswordLabel);

        // New Password Field
        newPasswordField = new CustomPasswordField();
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        newPasswordField.setBounds(200, 264, 250, 35);
        add(newPasswordField);

        // Confirm Password Label
        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        confirmPasswordLabel.setBounds(30, 320, 200, 40);
        add(confirmPasswordLabel);

        // Confirm Password Field
        confirmPasswordField = new CustomPasswordField();
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPasswordField.setBounds(200, 324, 250, 35);
        add(confirmPasswordField);

        // Change Password Button
        changePasswordButton = new CustomButton();
        changePasswordButton.setText("Change Password");
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 18));
        changePasswordButton.setForeground(Color.black);
        changePasswordButton.setBackground(new Color(150, 192, 101));
        changePasswordButton.setHoverColor(new Color(130, 172, 81));
        changePasswordButton.setPressColor(new Color(110, 152, 61));
        changePasswordButton.setBounds(200, 380, 200, 50);
        add(changePasswordButton);

        // Logout Button
        logoutButton = new CustomButton();
        logoutButton.setText("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 18));
        logoutButton.setForeground(Color.white);
        logoutButton.setBackground(new Color(217, 74, 70));
        logoutButton.setHoverColor(new Color(197, 54, 50));
        logoutButton.setPressColor(new Color(177, 34, 30));
        logoutButton.setBounds(420, 380, 150, 50);
        add(logoutButton);

        // Populate fields with staff details
        StaffAccountDAO staffDAO = new StaffAccountDAO();
        StaffAccountModel staff = staffDAO.fetchStaffDetails(staffID);
        if (staff != null) {
            setStaffID(staff.getStaffID());
            setStaffName(staff.getStaffName());
            setStaffEmail(staff.getStaffEmail());
        }

        StaffAccountController accountController = new StaffAccountController(this, staffDAO, staffID);
    }

    // Getters for fields and buttons
    public String getStaffID() {
        return staffIDTextField.getText();
    }

    public void setStaffID(String staffID) {
        staffIDTextField.setText(staffID);
    }

    public String getStaffName() {
        return staffNameTextField.getText();
    }

    public void setStaffName(String staffName) {
        staffNameTextField.setText(staffName);
    }

    public String getStaffEmail() {
        return staffEmailTextField.getText();
    }

    public void setStaffEmail(String email) {
        staffEmailTextField.setText(email);
    }

    public String getCurrentPassword() {
        return new String(currentPasswordField.getPassword());
    }

    public String getNewPassword() {
        return new String(newPasswordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public void addChangePasswordButtonListener(ActionListener listener) {
        changePasswordButton.addActionListener(listener);
    }

    public void addLogoutButtonListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
