
package store.management.system.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

// Modules
import store.management.system.View.AdminHomeView;

public class AdminHomeController {
    private AdminHomeView adminHomeView;
//    private AdminStaffView adminStaffView;
    
    public AdminHomeController(AdminHomeView adminHomeView){
        this.adminHomeView = adminHomeView;
        this.adminHomeView.addHomeMenuButtonListner(new HomeMenuButtonListener());
        this.adminHomeView.addStaffMenuButtonListner(new StaffMenuButtonListener());
        this.adminHomeView.addInvoiceMenuButtonListner(new InvoiceMenuButtonListener());
    }
    
    class HomeMenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        
    }
    
    class StaffMenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    
    class InvoiceMenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    
    
   
}
