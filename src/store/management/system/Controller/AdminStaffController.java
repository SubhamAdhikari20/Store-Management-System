
package store.management.system.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

// Modules
import store.management.system.View.AdminStaffView;
import store.management.system.DAO.AdminStaffDAO;
import store.management.system.Model.AdminStaffModel;


public class AdminStaffController {
    private final AdminStaffView adminStaffView;
    private final AdminStaffDAO adminStaffDAO;
    
    
    public AdminStaffController(AdminStaffView adminStaffView, AdminStaffDAO adminStaffDAO){
        this.adminStaffView = adminStaffView;
        this.adminStaffDAO = adminStaffDAO;
        this.adminStaffView.addAddStaffButtonListener(new AddStaffButtonListener());
        this.adminStaffView.addGoBackButtonListener(new GoBackButtonListener());
        this.adminStaffView.addAddButtonListener(new AddButtonListener());
        this.adminStaffView.addClearButtonListener(new ClearButtonListener());
    }
    
    // Add Staff Button
    class AddStaffButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            adminStaffView.getCardLayout().show(adminStaffView.getMainPanel(), "Add Staff");
        }
    }
    
    // Go Back Button
    class GoBackButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            adminStaffView.getCardLayout().show(adminStaffView.getMainPanel(), "Staff");
        }
    }
    
    // Clear Button
    class ClearButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String text = "";
            adminStaffView.setStaffID(text);
            adminStaffView.setStaffName(text);
            adminStaffView.setStaffGender("Select");
            adminStaffView.setStaffContact(text);
            adminStaffView.setStaffPassword(text);
            adminStaffView.setStaffConfirmPassword(text);
        }
    }
    
    // --------------------- CRUD ---------------------
    // Add Button
    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String staffID = adminStaffView.getStaffID();
            String staffName = adminStaffView.getStaffName();
            String staffGender = adminStaffView.getStaffGender();
            String staffContact = adminStaffView.getStaffContact();
            String setPassword = adminStaffView.getStaffPassword();
            String confirmPassword = adminStaffView.getStaffConfirmPassword();
            
            AdminStaffModel adminStaffModel = new AdminStaffModel(staffID, staffName, staffGender, staffContact, setPassword, confirmPassword);
            if (staffID.equals("") || staffName.equals("") || staffGender.equals("") || staffContact.equals("") || setPassword.equals("") || confirmPassword.equals("")) {
                JOptionPane.showMessageDialog(adminStaffView, "Please, fill feilds with correct staff details!", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
            }
            
            else if (!setPassword.equals(confirmPassword)){
                JOptionPane.showMessageDialog(adminStaffView, "Password and confirm password must be same!!!", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            
            else if (adminStaffDAO.executeAddQuery(adminStaffModel)) {
//                adminStaffDAO.executeAddQuery(adminStaffModel);
                
                // Fetch data from database and populate them in JTable
                Object [][] data = adminStaffDAO.fetchStaffInfo();
                String [] column = adminStaffView.getTableHeader();
                DefaultTableModel tableModel = new DefaultTableModel(data, column);
                adminStaffView.setTableModel(tableModel);
                JOptionPane.showMessageDialog(adminStaffView, "Records inserted sucessfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
    }
    
}

