package store.management.system.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import store.management.system.Model.StaffAccountModel;
import store.management.system.DAO.StaffAccountDAO;
import store.management.system.View.StaffAccountView;

public class StaffAccountController {
    private StaffAccountView staffView;
    private StaffAccountDAO staffDAO;
    private StaffAccountModel staffModel;

    public StaffAccountController(StaffAccountView staffView, StaffAccountDAO staffDAO, String staffID) {
        this.staffView = staffView;
        this.staffDAO = staffDAO;
        loadStaffDetails(staffID);

        this.staffView.addChangePasswordButtonListener(new ChangePasswordButtonListener());
        this.staffView.addLogoutButtonListener(new LogoutButtonListener());
    }

    private void loadStaffDetails(String staffID) {
        try {
            staffModel = staffDAO.fetchStaffDetails(staffID);
            if (staffModel != null) {
                staffView.setStaffID(staffModel.getStaffID());
                staffView.setStaffName(staffModel.getStaffName());
                staffView.setStaffEmail(staffModel.getStaffEmail());
            } else {
                JOptionPane.showMessageDialog(staffView, "Staff details not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(staffView, "An error occurred while loading staff details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    class ChangePasswordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String currentPassword = staffView.getCurrentPassword();
            String newPassword = staffView.getNewPassword();
            String confirmPassword = staffView.getConfirmPassword();

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(staffView, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (staffModel.getPassword().equals(currentPassword)) {
                try {
                    boolean success = staffDAO.changePassword(staffModel.getStaffID(), newPassword);
                    if (success) {
                        JOptionPane.showMessageDialog(staffView, "Password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(staffView, "Failed to change password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(staffView, "An error occurred while changing password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(staffView, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class LogoutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(staffView, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Exit the program or navigate to the login screen
                System.exit(0); // For exiting the program
            }
        }
    }
}
