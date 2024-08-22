package store.management.system.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import store.management.system.View.StaffHomeView;

public class StaffHomeController {
    private StaffHomeView staffHomeView;
    
    public StaffHomeController(StaffHomeView staffHomeView){
        this.staffHomeView = staffHomeView;
        this.staffHomeView.addHomeMenuButtonListener(new HomeMenuButtonListener());
        this.staffHomeView.addAccountMenuButtonListener(new AccountMenuButtonListener());
        this.staffHomeView.addInvoiceMenuButtonListener(new InvoiceMenuButtonListener());
        this.staffHomeView.addProductMenuButtonListener(new ProductMenuButtonListener());
    }
    
    class HomeMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            staffHomeView.getCardLayout().show(staffHomeView.getMainPanel(), "Home");
        }
    }
    
    class AccountMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            staffHomeView.getCardLayout().show(staffHomeView.getMainPanel(), "Account");
        }
    }
    
    class InvoiceMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            staffHomeView.getCardLayout().show(staffHomeView.getMainPanel(), "Invoice");
        }
    }
    
    class ProductMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            staffHomeView.getCardLayout().show(staffHomeView.getMainPanel(), "Product");
        }
    }
}
