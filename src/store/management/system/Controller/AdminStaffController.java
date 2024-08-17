
package store.management.system.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

// Modules
import store.management.system.View.AdminStaffView;

public class AdminStaffController {
    private final AdminStaffView adminStaffView;
    
    public AdminStaffController(AdminStaffView adminStaffView){
        this.adminStaffView = adminStaffView;
        this.adminStaffView.addAddStaffButtonListener(new AddStaffButtonListener());
        this.adminStaffView.addGoBackButtonListener(new GoBackButtonListener());
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


