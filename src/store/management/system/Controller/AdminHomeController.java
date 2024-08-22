
package store.management.system.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

// Modules
import store.management.system.View.AdminHomeView;
//import store.management.system.DAO.AdminHomeDAO;
//import store.management.system.Model.AdminHomeModel;
import store.management.system.View.AdminStaffView;

public class AdminHomeController {
    private AdminHomeView adminHomeView;
//    private AdminStaffView adminStaffView;
    
    public AdminHomeController(AdminHomeView adminHomeView){
        this.adminHomeView = adminHomeView;
        this.adminHomeView.addHomeMenuButtonListner(new HomeMenuButtonListener());
        this.adminHomeView.addStaffMenuButtonListner(new StaffMenuButtonListener());
        this.adminHomeView.addInvoiceMenuButtonListner(new InvoiceMenuButtonListener());
        this.adminHomeView.addProductMenuButtonListner(new ProductMenuButtonListener());
    }
    
    class HomeMenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            adminHomeView.getCardLayout().show(adminHomeView.getMainPanel(), "Home");
        }
        
    }
    
    class StaffMenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            adminHomeView.getCardLayout().show(adminHomeView.getMainPanel(), "Staff");
        }
    }
    
    class InvoiceMenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            adminHomeView.getCardLayout().show(adminHomeView.getMainPanel(), "Invoice");
        }
    }
    
    class ProductMenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            adminHomeView.getCardLayout().show(adminHomeView.getMainPanel(), "Product");
        }
    }
    
    
   
}
