package store.management.system.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

import store.management.system.Controller.LoginController;
import store.management.system.DAO.LoginDAO;

public class LoginView extends JFrame{
    CustomButton loginButton;
    JButton forgotPasswordButton, showPasswordButton;
    JCheckBox showPassword;
    CustomTextField usernameField;
    CustomPasswordField passwordField;
    
    
    public LoginView() {
        // Setting main window
        super.setTitle("Store Management System");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // To stop the execution of code after the main window is closed.
        super.setResizable(false);    // prevent frame from being resized
        super.setLayout(null);    // Setting default border layout to null
        super.setSize(1470, 780);
        super.setLocation(0, 0);
        super.getContentPane().setBackground(new Color(241, 233, 233));
        
        // Title images
        ImageIcon titleImage1 = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/4.jpg"));
        Image titleImage2 = titleImage1.getImage();
        super.setIconImage(titleImage2);
        
        
        // -------------------- Header -------------------
        // setting title message
        JLabel titleMessage = new JLabel("Janta Mart");
        titleMessage.setFont(new Font("Arial", Font.BOLD, 40));
        titleMessage.setForeground(Color.yellow);
        titleMessage.setBounds(635, 10, 200, 50);
        
        // Setting Logo Image
        ImageIcon logoimage1 = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/4.jpg"));
        Image logoimage2 = logoimage1.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon logoimage3 = new ImageIcon(logoimage2);
        JLabel logoLabel = new JLabel(logoimage3);
        logoLabel.setBounds(10, 10, 55, 55);
        
                
        // Shapes
        CustomPanel rectangle1 = new CustomPanel();
        rectangle1.setBounds(115, 22, 200, 30);
        rectangle1.setBackground(new Color(217, 217, 217));
        
        CustomPanel rectangle2 = new CustomPanel();
        rectangle2.setBounds(380, 22, 200, 30);
        rectangle2.setBackground(new Color(217, 217, 217));
        
        CustomPanel rectangle3 = new CustomPanel();
        rectangle3.setBounds(905, 22, 200, 30);
        rectangle3.setBackground(new Color(217, 217, 217));
        
        CustomPanel rectangle4 = new CustomPanel();
        rectangle4.setBounds(1170, 22, 200, 30);
        rectangle4.setBackground(new Color(217, 217, 217));
        
        // Header Pannel
        CustomPanel headerPanel = new CustomPanel();
        headerPanel.setBackground( new Color(41, 127, 176));
        headerPanel.setBounds(0, 0, 1470, 75);
        headerPanel.setLayout(null); 
        super.add(headerPanel);
        headerPanel.add(titleMessage);
        headerPanel.add(logoLabel);
        headerPanel.add(rectangle1);
        headerPanel.add(rectangle2);
        headerPanel.add(rectangle3);
        headerPanel.add(rectangle4);
        
        
        // --------------- Body -------------------
        // Setting Background Image
        ImageIcon loginimage1 = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/1.jpg"));
        Image loginimage2 = loginimage1.getImage().getScaledInstance(550, 350, Image.SCALE_SMOOTH);
        ImageIcon loginimage3 = new ImageIcon(loginimage2);
        JLabel imageLabel = new JLabel(loginimage3);
        imageLabel.setBounds(20, 35, 550, 350);
        
        // Login Label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        loginLabel.setForeground(Color.black);
        loginLabel.setBounds(720, 60, 100, 35);
        
        // Username Logo
        ImageIcon usernameLogo1 = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/9.png"));
        Image usernameLogo2 = usernameLogo1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon usernameLogo = new ImageIcon(usernameLogo2);
        JLabel usernameLogoLabel = new JLabel(usernameLogo);
        usernameLogoLabel.setBounds(2, 1, 25, 25);

        
        // Username/Email Text Field
        usernameField = new CustomTextField();        
        usernameField.setText("Username");
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setForeground(Color.gray);
        usernameField.setBackground(new Color(241, 233, 233));
        usernameField.setBorder(new EmptyBorder(5, 5, 5, 5));
        ImageIcon usernameIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/9.png"));
        Image usernameImage = usernameIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        usernameIcon = new ImageIcon(usernameImage);
        usernameField.setUsernameIcon(usernameIcon);
        usernameField.setBounds(575, 125, 380, 45);
        
        
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.black);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(Color.gray);
                    usernameField.setText("Username");
                }
            }
        });
       
        // Password Text Field
        passwordField = new CustomPasswordField();
        passwordField.setText("Password");
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setForeground(Color.gray);
        passwordField.setBackground(new Color(241, 233, 233));
        passwordField.setBorder(new EmptyBorder(5, 5, 5, 5));
        ImageIcon prefixPasswordIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/password_icon.png"));
        Image prefixPasswordImage = prefixPasswordIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        prefixPasswordIcon = new ImageIcon(prefixPasswordImage);
        passwordField.setPrefixPasswordIcon(prefixPasswordIcon);

        passwordField.setBounds(575, 190, 380, 45);        
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getText()).equals("Password")) {
                    passwordField.setText("");
//                    JPasswordField password = (JPasswordField) passwordField;
//                    passwordField.setEchoChar('•');
                    passwordField.setForeground(Color.black);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getText()).isEmpty()) {
                    passwordField.setForeground(Color.gray);
                    passwordField.setText("Password");
//                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        
        /*
        // CheckBx - show passowrd
        showPassword = new JCheckBox();
        showPassword.setBackground(Color.white);
        showPassword.setCursor(new Cursor(HAND_CURSOR));
        showPassword.setBounds(580, 245, 20, 20);

        showPasswordButton = new JButton("show password");
        showPasswordButton.setFont(new Font("Arial", Font.BOLD, 12));
        showPasswordButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        showPasswordButton.setForeground(Color.black);
        showPasswordButton.setBackground(Color.white);
        showPasswordButton.setFocusable(false);
        showPasswordButton.setContentAreaFilled(false);
        showPasswordButton.setCursor(new Cursor(HAND_CURSOR));
        showPasswordButton.setBounds(600, 243, 100, 25);
        */
        
        // Forgot password label
        forgotPasswordButton = new JButton("Forgot password?");
        forgotPasswordButton.setFont(new Font("Arial", Font.BOLD, 12));
        forgotPasswordButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        forgotPasswordButton.setForeground(Color.black);
        forgotPasswordButton.setBackground(Color.white);
        forgotPasswordButton.setFocusable(false);
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.setCursor(new Cursor(HAND_CURSOR));
        forgotPasswordButton.setBounds(825, 243, 140, 25);

        
 
        // Login Button
        loginButton = new CustomButton();
        loginButton.setText("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setForeground(Color.white);
        loginButton.setBackground(new Color(41, 127, 176));
        loginButton.setContentAreaFilled(false);
        loginButton.setHoverColor(new Color(31, 100, 180));
        loginButton.setPressColor(new Color(10, 80, 160));
        loginButton.setDropShadowColor(new Color(0, 0, 0, 70));
        loginButton.setBounds(582, 300, 363, 45);

        
        
        //----- Login Pannel ------
        CustomPanel loginPanel = new CustomPanel();
        loginPanel.setBackground( new Color(255, 255, 255));
        loginPanel.setBounds(225, 200, 1005, 425);
        loginPanel.setLayout(null); 
        super.add(loginPanel);
        loginPanel.add(loginLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordField);
//        loginPanel.add(showPassword);
//        loginPanel.add(showPasswordButton);
        loginPanel.add(forgotPasswordButton);
        loginPanel.add(loginButton);
        loginPanel.add(imageLabel);
        
        
        super.setVisible(true);
        
        LoginDAO loginDAO = new LoginDAO();
        LoginController loginController = new LoginController(this, loginDAO);
        
    }
  
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }
    
    public void addLoginListener(ActionListener loginListener) {
        loginButton.addActionListener(loginListener);
    }

    public void addForgotPasswordListener(ActionListener forgotPasswordListener) {
        forgotPasswordButton.addActionListener(forgotPasswordListener);
    }

    public void addShowPasswordListener(ActionListener showPasswordListener) {
        showPasswordButton.addActionListener(showPasswordListener);
    }
    
    public void toggleShowPassword() {
        if (!showPassword.isSelected()){
            showPassword.setSelected(true);
        }
        else{
            showPassword.setSelected(false);
        }
    } 
}
