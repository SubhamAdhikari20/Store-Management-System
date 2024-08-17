
package store.management.system.View;

import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;


// Modules
import store.management.system.Controller.AdminHomeController;
import store.management.system.Controller.AdminStaffController;
//import store.management.system.View.CustomSwing.*;

public class AdminHomeView extends JFrame{
    CustomMenuButton homeButton, staffButton, invoiceButton, reportButton;
    CustomPanel mainPanel;
    HomePanel homePanel;
    CardLayout cardLayout;
    AdminStaffView staffPanel;
    AdminStaffController staffController;
    AdminInvoiceView invoicePanel;
    AddStaffPanel addStaffPanel;
    
    
    
    public AdminHomeView(){
        this.setTitle("Store Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);    
        this.setLayout(null);    
        this.setSize(1470, 780);
        this.setLocation(0, 0);
        this.getContentPane().setBackground(new Color(241, 233, 233));
        
        // Title images
        ImageIcon titleImage1 = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/4.jpg"));
        Image titleImage2 = titleImage1.getImage();
        this.setIconImage(titleImage2);
        
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
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground( new Color(41, 127, 176));
        headerPanel.setBounds(0, 0, 1470, 75);
        headerPanel.setLayout(null); 
        this.add(headerPanel);
        headerPanel.add(titleMessage);
        headerPanel.add(logoLabel);
        headerPanel.add(titleMessage);
        headerPanel.add(logoLabel);
        headerPanel.add(rectangle1);
        headerPanel.add(rectangle2);
        headerPanel.add(rectangle3);
        headerPanel.add(rectangle4);
        
        
        // ------------- Menu ----------------
        // Home Button
        homeButton = new CustomMenuButton();
        homeButton.setText("Home");
        homeButton.setForeground(Color.black);
        homeButton.setBackground(new Color(241, 233, 233));
        homeButton.setContentAreaFilled(false);
        homeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        ImageIcon homeButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/home.png"));
        Image homeButtonImage = homeButtonIcon.getImage().getScaledInstance(25, 20, Image.SCALE_SMOOTH);
        homeButtonIcon = new ImageIcon(homeButtonImage);
        homeButton.setIcon(homeButtonIcon);
        homeButton.setIconTextGap(10);
        homeButton.setBounds(7, 25, 180, 45);
        
        
        // Staff Button
        staffButton = new CustomMenuButton();
        staffButton.setText("Staff");
        staffButton.setFont(new Font("Arial", Font.PLAIN, 18));
        staffButton.setForeground(Color.black);
        staffButton.setBackground(new Color(241, 233, 233));
        staffButton.setContentAreaFilled(false);
        ImageIcon staffButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/staff.png"));
        Image staffButtonImage = staffButtonIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        staffButtonIcon = new ImageIcon(staffButtonImage);
        staffButton.setIcon(staffButtonIcon);
        staffButton.setIconTextGap(10);
        staffButton.setBounds(7, 75, 180, 45);

        
        // Invoice Button
        invoiceButton = new CustomMenuButton();
        invoiceButton.setText("Invoice");
        invoiceButton.setFont(new Font("Arial", Font.PLAIN, 18));
        invoiceButton.setForeground(Color.black);
        invoiceButton.setBackground(new Color(241, 233, 233));
        invoiceButton.setContentAreaFilled(false);
        ImageIcon invoiceButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/staff.png"));
        Image invoiceButtonImage = invoiceButtonIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        invoiceButtonIcon = new ImageIcon(invoiceButtonImage);
        invoiceButton.setIcon(invoiceButtonIcon);
        invoiceButton.setIconTextGap(10);
        invoiceButton.setBounds(7, 125, 180, 45);

        
        
        // Side Menu Panel
        CustomPanel menuPanel = new CustomPanel();
        menuPanel.setBackground(Color.white);
        menuPanel.setBounds(0, 80, 200, 660);
        menuPanel.setLayout(null); 
        this.add(menuPanel);
        menuPanel.add(homeButton);
        menuPanel.add(staffButton);
        menuPanel.add(invoiceButton);
        

        
        // Main Panel with CardLayout -> lets us to stack Panel over another
        cardLayout = new CardLayout();
        mainPanel = new CustomPanel();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(225, 100, 1205, 620);
        this.add(mainPanel);
        
        // Panels to be added
        homePanel = new HomePanel();
        staffPanel = new AdminStaffView(this);      // Pass AdminHomeView instance to AdminStaffView
        invoicePanel = new AdminInvoiceView();

        // Adding the panel in CardLayout
        mainPanel.add(homePanel, "Home");
        mainPanel.add(staffPanel, "Staff");
        mainPanel.add(invoicePanel, "Invoice");

        
        // Set initial panel
        cardLayout.show(mainPanel, "Staff");
        
        
        this.add(mainPanel);
        setVisible(true);
        
        // Add ActionListeners to side buttons
        AdminHomeController adminHomeController = new AdminHomeController(this);
    }
    
    
    public CardLayout getCardLayout(){
        return cardLayout;
    }
    
    public CustomPanel getMainPanel(){
        return mainPanel;
    }
    
    public CustomPanel getHomePanel(){
        return homePanel;
    }
    
    public CustomPanel getStaffPanel(){
        return staffPanel;
    }
    
    public CustomPanel getInvoicePanel(){
        return invoicePanel;
    }
    
    public void addHomeMenuButtonListner(ActionListener addHomeMenuButtonListner){
        homeButton.addActionListener(addHomeMenuButtonListner);
    }
    
    public void addStaffMenuButtonListner(ActionListener addStaffMenuButtonListner){
        staffButton.addActionListener(addStaffMenuButtonListner);
    }
    
    public void addInvoiceMenuButtonListner(ActionListener addInvoiceMenuButtonListner){
        invoiceButton.addActionListener(addInvoiceMenuButtonListner);
    }
    

    
    
    public static void main(String[] args) {
        AdminHomeView homeView = new AdminHomeView();
    }
}

    

class HomePanel extends CustomPanel{
    public HomePanel(){
        setBackground(Color.white);
        setLayout(null); 
        
        
        // Add Staff Title Label
        JLabel welcomeMessageLabel = new JLabel("Welcome to Janta Mart");
        welcomeMessageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeMessageLabel.setBounds(525, 10, 250, 40);
        add(welcomeMessageLabel);
        
    }
}

