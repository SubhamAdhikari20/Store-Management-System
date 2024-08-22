
package store.management.system.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

// Modules
import store.management.system.View.*;
import store.management.system.DAO.LoginDAO;
import store.management.system.Model.LoginModel;



public class LoginController{
    private final LoginDAO loginDAO;
    private final LoginView loginView;
//    String username;
//    String password;
//    LoginModel userModel;
    
    public LoginController(LoginView loginView, LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
        this.loginView = loginView;
        
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addForgotPasswordListener(new ForgotPasswordListener());
    }
    
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {   
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            LoginModel userModel = new LoginModel(username, password);
            if (username.equals("") || password.equals("")){
            JOptionPane.showMessageDialog(loginView, "Please, fill feilds with correct username or password!", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
            }
            
            else if (loginDAO.executeLoginQuery(userModel)) {
                JOptionPane.showMessageDialog(loginView, "You have sucessfully logged in!", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                new AdminHomeView();
                loginView.dispose();
            } 
            else{
                JOptionPane.showMessageDialog(loginView, "Please, enter correct username or password!", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    class ForgotPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            LoginModel userModel = new LoginModel(username, password);
            if (loginDAO.executeForgotPasswordQuery(userModel)) {
                    
            } 
            else{
                JOptionPane.showMessageDialog(loginView, "Please, enter correct username!", "Invalid", JOptionPane.ERROR_MESSAGE);                
            }   
        }
    }
    
    class ShowPasswordListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loginView.toggleShowPassword();
        }
    }
}
    