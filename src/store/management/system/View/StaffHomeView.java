package store.management.system.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Modules
import store.management.system.Controller.StaffHomeController;
//import store.management.system.Controller.StaffAccountController;

public class StaffHomeView extends JFrame {
    CustomMenuButton homeButton, accountButton, invoiceButton, productButton;
    CustomPanel mainPanel;
    HomePanel homePanel;
    StaffAccountView accountPanel;
    CardLayout cardLayout;
    InvoiceView invoicePanel;
    ProductView productPanel;
    

    public StaffHomeView(String staffID) {
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
        JLabel titleMessage = new JLabel("Janta Mart");
        titleMessage.setFont(new Font("Arial", Font.BOLD, 40));
        titleMessage.setForeground(Color.yellow);
        titleMessage.setBounds(635, 10, 200, 50);

        ImageIcon logoimage1 = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/4.jpg"));
        Image logoimage2 = logoimage1.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon logoimage3 = new ImageIcon(logoimage2);
        JLabel logoLabel = new JLabel(logoimage3);
        logoLabel.setBounds(10, 10, 55, 55);

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

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 127, 176));
        headerPanel.setBounds(0, 0, 1470, 75);
        headerPanel.setLayout(null);
        this.add(headerPanel);
        headerPanel.add(titleMessage);
        headerPanel.add(logoLabel);
        headerPanel.add(rectangle1);
        headerPanel.add(rectangle2);
        headerPanel.add(rectangle3);
        headerPanel.add(rectangle4);

        // ------------- Menu ----------------

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

        accountButton = new CustomMenuButton();
        accountButton.setText("Account");
        accountButton.setFont(new Font("Arial", Font.PLAIN, 18));
        accountButton.setForeground(Color.black);
        accountButton.setBackground(new Color(241, 233, 233));
        accountButton.setContentAreaFilled(false);
        ImageIcon accountButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/staff.png"));
        Image accountButtonImage = accountButtonIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        accountButtonIcon = new ImageIcon(accountButtonImage);
        accountButton.setIcon(accountButtonIcon);
        accountButton.setIconTextGap(10);
        accountButton.setBounds(7, 175, 180, 45);

        invoiceButton = new CustomMenuButton();
        invoiceButton.setText("Invoice");
        invoiceButton.setFont(new Font("Arial", Font.PLAIN, 18));
        invoiceButton.setForeground(Color.black);
        invoiceButton.setBackground(new Color(241, 233, 233));
        invoiceButton.setContentAreaFilled(false);
        ImageIcon invoiceButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/bill.png"));
        Image invoiceButtonImage = invoiceButtonIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        invoiceButtonIcon = new ImageIcon(invoiceButtonImage);
        invoiceButton.setIcon(invoiceButtonIcon);
        invoiceButton.setIconTextGap(10);
        invoiceButton.setBounds(7, 75, 180, 45);

        productButton = new CustomMenuButton();
        productButton.setText("Product");
        productButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productButton.setForeground(Color.black);
        productButton.setBackground(new Color(241, 233, 233));
        productButton.setContentAreaFilled(false);
        ImageIcon productButtonIcon = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/product_logo.png"));
        Image productButtonImage = productButtonIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        productButtonIcon = new ImageIcon(productButtonImage);
        productButton.setIcon(productButtonIcon);
        productButton.setIconTextGap(10);
        productButton.setBounds(7, 125, 180, 45);

        CustomPanel menuPanel = new CustomPanel();
        menuPanel.setBackground(Color.white);
        menuPanel.setBounds(0, 80, 200, 660);
        menuPanel.setLayout(null);
        this.add(menuPanel);
        menuPanel.add(homeButton);
        menuPanel.add(accountButton);
        menuPanel.add(invoiceButton);
        menuPanel.add(productButton);

        // Main Panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new CustomPanel();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(225, 100, 1205, 620);
        this.add(mainPanel);

        // Panels to be added
        homePanel = new HomePanel();
        accountPanel = new StaffAccountView(staffID);
        invoicePanel = new InvoiceView();
        productPanel = new ProductView();

        // Adding the panel in CardLayout
        mainPanel.add(homePanel, "Home");
        mainPanel.add(accountPanel, "Account");
        mainPanel.add(invoicePanel, "Invoice");
        mainPanel.add(productPanel, "Product");

        // Set initial panel
        cardLayout.show(mainPanel, "Home");

        // Setting Background Image
        ImageIcon loginimage1 = new ImageIcon(ClassLoader.getSystemResource("store/management/system/View/Images/5.jpg"));
        Image loginimage2 = loginimage1.getImage().getScaledInstance(1155, 540, Image.SCALE_SMOOTH);
        ImageIcon loginimage3 = new ImageIcon(loginimage2);
        JLabel imageLabel = new JLabel(loginimage3);
        imageLabel.setBounds(20, 50, 1155, 540);
        homePanel.add(imageLabel);

        this.add(mainPanel);
        setVisible(true);

        // Add ActionListeners to side buttons
        StaffHomeController staffHomeController = new StaffHomeController(this);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public CustomPanel getMainPanel() {
        return mainPanel;
    }

    public CustomPanel getHomePanel() {
        return homePanel;
    }

    public CustomPanel getAccountPanel() {
        return accountPanel;
    }

    public CustomPanel getInvoicePanel() {
        return invoicePanel;
    }

    public void addHomeMenuButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }

    public void addAccountMenuButtonListener(ActionListener listener) {
        accountButton.addActionListener(listener);
    }

    public void addInvoiceMenuButtonListener(ActionListener listener) {
        invoiceButton.addActionListener(listener);
    }

    public void addProductMenuButtonListener(ActionListener listener) {
        productButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        String id = "1";
        StaffHomeView staffHomeView = new StaffHomeView(id);
    }
}

class HomePanel extends CustomPanel {
    public HomePanel() {
        setBackground(Color.white);
        setLayout(null);

        // Add Staff Title Label
        JLabel welcomeMessageLabel = new JLabel("Welcome to Janta Mart");
        welcomeMessageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeMessageLabel.setForeground(Color.black);
        welcomeMessageLabel.setBounds(525, 10, 250, 40);
        add(welcomeMessageLabel);
    }
}
