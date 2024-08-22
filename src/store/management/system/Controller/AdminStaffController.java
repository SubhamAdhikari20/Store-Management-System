
package store.management.system.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.util.List;

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
        this.adminStaffView.addSearchButtonListener(new SearchButtonListener());
        this.adminStaffView.addRefereshButtonListener(new RefereshButtonListener());
        this.adminStaffView.addCRUDButtonListener(new CRUDButtonListener());
        this.adminStaffView.addGoBackButtonListener(new GoBackButtonListener());
        this.adminStaffView.addAddButtonListener(new AddButtonListener());
        this.adminStaffView.addUpdateButtonListener(new UpdateButtonListener());
        this.adminStaffView.addDeleteButtonListener(new DeleteButtonListener());
        this.adminStaffView.addClearButtonListener(new ClearButtonListener());
    }
    
    /*
    // Search Button
    class SearchButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String staffID = adminStaffView.getID();
            String staffName = adminStaffView.getName();
            
            if (staffID.equals("") && staffName.equals("")){
                JOptionPane.showMessageDialog(adminStaffView, "Please, fill feilds with correct staff details!", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                List<AdminStaffModel> searchResults = adminStaffDAO.searchStaff(staffID, staffName);
            
                DefaultTableModel model = (DefaultTableModel) adminStaffView.getStaffTableModel();
                model.setRowCount(0); // Clear the table

                for (AdminStaffModel staff : searchResults) {
                    model.addRow(new Object[]{
                        staff.getStaffID(),
                        staff.getStaffName(),
                        staff.getStaffGender(),
                        staff.getStaffContact()
                    });
                }

                if (searchResults.isEmpty()) {
                    JOptionPane.showMessageDialog(adminStaffView, "No matching records found.", "Search", JOptionPane.INFORMATION_MESSAGE);
    //                Object [][] data = adminStaffDAO.fetchStaffInfo();
    //                String [] column = adminStaffView.getTableHeader();
    //                DefaultTableModel tableModel = new DefaultTableModel(data, column);
    //                adminStaffView.setTableModel(tableModel);
                }
            }
            
        }
    }
    */
    
    
    // Search Button
    class SearchButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String staffID = adminStaffView.getID();
            String staffName = adminStaffView.getName();
            String staffGender = null;
            String staffContact = null;
            String setPassword = null;
            String confirmPassword = null;
            
            AdminStaffModel adminStaffModel = new AdminStaffModel(staffID, staffName, staffGender, staffContact, setPassword, confirmPassword);
            
            if (staffID.equals("") && staffName.equals("")) {
                JOptionPane.showMessageDialog(adminStaffView, "Please, fill feilds with correct staff details!", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                Object [][] data = adminStaffDAO.search(adminStaffModel);
                String [] column = adminStaffView.getTableHeader();
                DefaultTableModel tableModel = new DefaultTableModel(data, column);
                adminStaffView.setTableModel(tableModel);
            }
        }
    }
    
    
    // Referesh Button
    class RefereshButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object [][] data = adminStaffDAO.fetchStaffInfo();
            String [] column = adminStaffView.getTableHeader();
            DefaultTableModel tableModel = new DefaultTableModel(data, column);
            adminStaffView.setTableModel(tableModel);
            adminStaffView.setID();
            adminStaffView.setName();
        }
    }
    
    // CRUD Staff Button
    class CRUDButtonListener implements ActionListener{
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
                // Fetch data from database and populate them in JTable
                updateTable();
                JOptionPane.showMessageDialog(adminStaffView, "Records inserted sucessfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        // Method to update the table with the latest product data
        private void updateTable() {
            Object [][] data = adminStaffDAO.fetchStaffInfo();
            String [] column = adminStaffView.getTableHeader();
            DefaultTableModel tableModel = new DefaultTableModel(data, column);
            adminStaffView.setTableModel(tableModel);
        }
    }
    
    
    
    // Update Button
    class UpdateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            AdminStaffModel selectedProduct = adminStaffView.getSelectedStaff();
            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(adminStaffView, "Please, select a row from the table!", "No Selection", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String staffID = adminStaffView.getStaffID();
                String staffName = adminStaffView.getStaffName();
                String staffGender = adminStaffView.getStaffGender();
                String staffContact = adminStaffView.getStaffContact();
                String setPassword = adminStaffView.getStaffPassword();
                String confirmPassword = adminStaffView.getStaffConfirmPassword();

                AdminStaffModel adminStaffModel = new AdminStaffModel(staffID, staffName, staffGender, staffContact, setPassword, confirmPassword);

                if (staffID.equals("") || staffName.equals("") || staffGender.equals("") || staffContact.equals("")) {
                    JOptionPane.showMessageDialog(adminStaffView, "Please, fill feilds with correct staff details!", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
                }
                else if (adminStaffDAO.executeUpdateQuery(adminStaffModel)) {
                    // Fetch data from database and populate them in JTable
                    updateTable();
                    JOptionPane.showMessageDialog(adminStaffView, "Records inserted sucessfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(adminStaffView, "Failed to update product!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
        }
        // Method to update the table with the latest product data
        private void updateTable() {
            Object [][] data = adminStaffDAO.fetchStaffInfo();
            String [] column = adminStaffView.getTableHeader();
            DefaultTableModel tableModel = new DefaultTableModel(data, column);
            adminStaffView.setTableModel(tableModel);
        }
    }
    
    
    
    // Delete Button
    class DeleteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            AdminStaffModel selectedProduct = adminStaffView.getSelectedStaff();
            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(adminStaffView, "Please, select a row from the table!", "No Selection", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String staffID = selectedProduct.getStaffID();

                if (adminStaffDAO.executeDeleteQuery(staffID)) {
                    updateTable();
                    adminStaffView.clearFields();
                    JOptionPane.showMessageDialog(adminStaffView, "Staff Info deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } 
                else {
                    JOptionPane.showMessageDialog(adminStaffView, "Failed to delete staff detail!", "Error", JOptionPane.ERROR_MESSAGE);
                }    
            }
            
        }
        // Method to update the table with the latest product data
        private void updateTable() {
            Object [][] data = adminStaffDAO.fetchStaffInfo();
            String [] column = adminStaffView.getTableHeader();
            DefaultTableModel tableModel = new DefaultTableModel(data, column);
            adminStaffView.setTableModel(tableModel);
        }
    }
    
}

