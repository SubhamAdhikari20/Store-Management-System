
package store.management.system.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

// Modules
import store.management.system.View.*;
import store.management.system.Model.LoginModel;



public class LoginController{
    private final LoginView loginView;
//    String username;
//    String password;
//    LoginModel userModel;
    
    public LoginController(LoginView loginView) {
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
        }
    }
    
    class ShowPasswordListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loginView.toggleShowPassword();
        }
    }
}
    