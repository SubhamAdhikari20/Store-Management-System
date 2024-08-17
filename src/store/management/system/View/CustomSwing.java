package store.management.system.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.geom.*;
import javax.swing.table.*;

public class CustomSwing extends JPanel {
    /*Custom Swing*/
}

class CustomPanel extends JPanel{
    private int shadowOffset = 3;
    private int shadowAlpha = 30; // Alpha value for shadow transparency
    private Color shadowColor = new Color(0, 0, 0, shadowAlpha);
    private int arcWidth = 15;
    private int arcHeight = 10;

    public CustomPanel() {
        super.setOpaque(false); // Make panel non-opaque to allow custom painting
        super.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable antialiasing for smoother shadow
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw drop shadow
        g2d.setColor(shadowColor);
        g2d.fillRoundRect(shadowOffset, shadowOffset, getWidth() - shadowOffset, getHeight() - shadowOffset, arcWidth, arcHeight);

        // Draw panel background
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - shadowOffset, getHeight() - shadowOffset, arcWidth, arcHeight);

        g2d.dispose();
        super.paintComponent(g);
    }
}

class CustomScrollPane extends JScrollPane{
    private int shadowOffset = 3;
    private int shadowAlpha = 30; // Alpha value for shadow transparency
    private Color shadowColor = new Color(0, 0, 0, shadowAlpha);
    private int arcWidth = 15;
    private int arcHeight = 10;

    public CustomScrollPane() {
        setOpaque(false); // Make scroll pane non-opaque to allow custom painting
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getViewport().setOpaque(false); // Make viewport non-opaque
        setLayout(null);
    }
    public CustomScrollPane(Component view) {
        super(view);
        setOpaque(false); // Make scroll pane non-opaque to allow custom painting
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getViewport().setOpaque(false); // Make viewport non-opaque
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable antialiasing for smoother shadow
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw drop shadow
        g2d.setColor(shadowColor);
        g2d.fillRoundRect(shadowOffset, shadowOffset, width - shadowOffset, height - shadowOffset, arcWidth, arcHeight);

        // Draw scroll pane background
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - shadowOffset, height - shadowOffset, arcWidth, arcHeight);

        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing to remove default border painting
    }
}


class CustomTextField extends JTextField{
    private Icon usernameIcon;
    private Icon prefixPasswordIcon;   
    private Icon suffixPasswordIcon;   
    
    // Drop Shadow
    private int x = 5;
    private int y = 5;
    private Color borderColor = new Color(0, 0, 0, 50);
    private int width = 5;
    private int height = 5;     // Alpha value for shadow transparency
    private int radius = 15;

   
    public Icon getUsernameIcon() {
        return usernameIcon;
    }

    public void setUsernameIcon(Icon usernameIcon) {
        this.usernameIcon = usernameIcon;
        initBorder();
    }

    public Icon getPrefixPasswordIcon() {
        return prefixPasswordIcon;
    }
    
    public Icon getSuffixPasswordIcon() {
        return suffixPasswordIcon;
    }
    
    public void setPrefixPasswordIcon(Icon prefixPasswordIcon) {
        this.prefixPasswordIcon = prefixPasswordIcon;
        initBorder();
    }
    
    public void setSuffixPasswordIcon(Icon suffixPasswordIcon) {
        this.suffixPasswordIcon = suffixPasswordIcon;
        initBorder();
    }
   
       
    public CustomTextField(){
        super.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    
    @Override
    protected void paintComponent(Graphics g){      
        super.paintComponent(g);
        // Icon
        paintIcon(g);
        //  paint border
        paintBorderIfFocused(g);
    }
    
    private void paintIcon(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if (this.usernameIcon != null){
            Image username =  ((ImageIcon) this.usernameIcon).getImage();
            int y = (getHeight() - this.usernameIcon.getIconHeight())/2;
            g2.drawImage(username, 7, y, this);
        }
        if (this.prefixPasswordIcon != null){
            // Prefix Image
            Image prefixPasswordIcon =  ((ImageIcon) this.prefixPasswordIcon).getImage();
            int y1 = (getHeight() - this.prefixPasswordIcon.getIconHeight())/2;
            g2.drawImage(prefixPasswordIcon, 7, y1, this);
        }
        
    }
    
    @Override
    public String getText() {
        return super.getText();
    }
    
    private void initBorder(){
        int leftUsername = 5;
        int rightUsername = 10;
        int leftPassword = 5;
        int rightPassword = 10;  
        // Default -> 5

        if (this.usernameIcon != null){
            leftUsername = this.usernameIcon.getIconWidth() + 17;  
            setBorder(BorderFactory.createEmptyBorder(5, leftUsername, 5, rightUsername));
        }
        
        if (this.prefixPasswordIcon != null) {
            leftPassword = this.prefixPasswordIcon.getIconWidth() + 17; 
            setBorder(BorderFactory.createEmptyBorder(5, leftPassword, 5, rightPassword));
        }
    }
    
    private void paintBorderIfFocused(Graphics g) {
        if (isFocusOwner()) {
            g.setColor(new Color(4, 88, 167));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        } else {
            g.setColor(new Color(142, 142, 142));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        }
    }
} 



class CustomPasswordField extends JPasswordField{
    private Icon prefixPasswordIcon;   
    private Icon suffixPasswordIcon;   
    
    // Drop Shadow
    private int x = 5;
    private int y = 5;
    private Color borderColor = new Color(0, 0, 0, 50);
    private int width = 5;
    private int height = 5;     // Alpha value for shadow transparency
    private int radius = 15;



    public Icon getPrefixPasswordIcon() {
        return prefixPasswordIcon;
    }
    
    public Icon getSuffixPasswordIcon() {
        return suffixPasswordIcon;
    }
    
    public void setPrefixPasswordIcon(Icon prefixPasswordIcon) {
        this.prefixPasswordIcon = prefixPasswordIcon;
        initBorder();
    }
    
    public void setSuffixPasswordIcon(Icon suffixPasswordIcon) {
        this.suffixPasswordIcon = suffixPasswordIcon;
        initBorder();
    }
   
       
    public CustomPasswordField(){
        super.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        setOpaque(false); // To allow custom painting
//        setBorder(new CustomRoundedBorder(15)); // Set the rounded border
    }
    
    @Override
    protected void paintComponent(Graphics g){      
        super.paintComponent(g);
        // Icon
        paintIcon(g);
        //  paint border
        paintBorderIfFocused(g);
    }
    
    private void paintIcon(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        if (this.prefixPasswordIcon != null){
            // Prefix Image
            Image prefixPasswordIcon =  ((ImageIcon) this.prefixPasswordIcon).getImage();
            int y1 = (getHeight() - this.prefixPasswordIcon.getIconHeight())/2;
            g2.drawImage(prefixPasswordIcon, 7, y1, this);
        }
        
    }
    
    @Override
    public String getText() {
        return super.getText();
    }
    
    private void initBorder(){
        int leftUsername = 5;
        int rightUsername = 10;
        int leftPassword = 5;
        int rightPassword = 10;  
        // Default -> 5

        
        if (this.prefixPasswordIcon != null) {
            leftPassword = this.prefixPasswordIcon.getIconWidth() + 17; 
            setBorder(BorderFactory.createEmptyBorder(5, leftPassword, 5, rightPassword));
        }
    }
    
    private void paintBorderIfFocused(Graphics g) {
        if (isFocusOwner()) {
            g.setColor(new Color(4, 88, 167));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        } else {
            g.setColor(new Color(142, 142, 142));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        }
    }
}



class CustomMenuButton extends JButton {
    private static final List<CustomMenuButton> buttonList = new ArrayList<>();
    private int borderRadius = 10;
    public Color dropShadowColor = new Color(0, 0, 0, 50); // Semi-transparent shadow
    public final Insets shadowSize = new Insets(2, 5, 8, 5);
    private boolean hovered = false;
    private boolean clicked = false;
    public Color hoverColor = new Color(220, 220, 220); // Color when hovered
    public Color clickColor = new Color(200, 200, 200); // Color when clicked

    public CustomMenuButton() {
        super.setFocusable(false);
        super.setCursor(new Cursor(Cursor.HAND_CURSOR));
        super.setBorder(new EmptyBorder(shadowSize.top, shadowSize.left, shadowSize.bottom, shadowSize.right));

        // Add this button to the list of buttons
        buttonList.add(this);

        // Add mouse listeners to handle hover and click states
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!clicked) {
                    hovered = true;
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!clicked) {
                    hovered = false;
                    repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Reset the clicked state of all other buttons
                for (CustomMenuButton button : buttonList) {
                    if (button != CustomMenuButton.this) {
                        button.setClicked(false);
                        button.setHovered(false);
                    }
                }
                // Toggle the clicked state of this button
                clicked = !clicked;
                if (clicked) {
                    hovered = true;
                } else {
                    hovered = false;
                }
                repaint();
            }
        });
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
        super.repaint();
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        super.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        int x = shadowSize.left;
        int y = shadowSize.top;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw drop shadow
        g2.setColor(dropShadowColor);
        g2.fillRoundRect(x + 3, y + 3, width, height, borderRadius, borderRadius);

        // Determine the background color based on state
        if (clicked) {
            g2.setColor(clickColor);
        } else if (hovered) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(getBackground());
        }

        // Draw button background
        g2.fillRoundRect(x, y, width, height, borderRadius, borderRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Insets getInsets() {
        return new Insets(borderRadius, borderRadius, borderRadius, borderRadius);
    }
}



class CustomButton extends JButton {
    private int borderRadius = 10;
    public Color dropShadowColor = new Color(0, 0, 0, 50); // Semi-transparent shadow
    public final Insets shadowSize = new Insets(2, 5, 8, 5);
    private boolean hovered = false;
    private boolean pressed = false;
    public Color hoverColor = new Color(255, 255, 255); // Color when hovered
    public Color pressColor = new Color(255, 255, 255); // Color when pressed

    public CustomButton() {
        super.setFocusable(false);
        super.setContentAreaFilled(false);
        super.setCursor(new Cursor(Cursor.HAND_CURSOR));
        super.setBorder(new EmptyBorder(shadowSize.top, shadowSize.left, shadowSize.bottom, shadowSize.right));

        // Add mouse listeners to handle hover and press states
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }
    
    public Color setHoverColor(Color hoverColor){
        this.hoverColor = hoverColor;
        return this.hoverColor;
    }
    
    public Color setPressColor(Color pressColor){
        this.pressColor = pressColor;
        return this.pressColor;
    }
    
    public Color setDropShadowColor(Color dropShadowColor){
        this.dropShadowColor = dropShadowColor;
        return this.dropShadowColor;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        int x = shadowSize.left;
        int y = shadowSize.top;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw drop shadow
        g2.setColor(dropShadowColor);
        g2.fillRoundRect(x + 3, y + 3, width, height, borderRadius, borderRadius);

        // Determine the background color based on hover and press states
        if (pressed) {
            g2.setColor(pressColor);
        } else if (hovered) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(getBackground());
        }

        // Draw button background
        g2.fillRoundRect(x, y, width, height, borderRadius, borderRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Insets getInsets() {
        return new Insets(borderRadius, borderRadius, borderRadius, borderRadius);
    }
}




class CustomComboBox extends JComboBox<String> {
    private int radius = 0;

    public CustomComboBox(String[] items) {
        super(items);
//        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setOpaque(false);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBorderIfFocused(g);
    }

    private void initBorder() {
        int left = 5;
        setBorder(BorderFactory.createEmptyBorder(5, left, 5, 5));
    }

    private void paintBorderIfFocused(Graphics g) {
        if (isFocusOwner()) {
            g.setColor(new Color(4, 88, 167));
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, radius, radius);
        } else {
            g.setColor(new Color(142, 142, 142));
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, radius, radius);
        }
    }

}


class CustomTable extends JTable{
    private int previousRow = -1;
    
    public CustomTable(TableModel model){
        super(model);
        // Customize table header
        JTableHeader header = getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Customize table appearance
        setRowHeight(30);
        setShowVerticalLines(false);
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setDefaultRenderer(Object.class, new CenterTableCellRenderer());
        
        // Add hover effect
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != previousRow) {
                    previousRow = row;
                    repaint();
                }
            }
        });
    }
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);

        if (!isRowSelected(row)) {
            component.setBackground(row % 2 == 0 ? new Color(230, 230, 250) : Color.WHITE);
        } else {
            component.setBackground(new Color(173, 216, 230));
        }

        if (component instanceof JComponent) {
            ((JComponent) component).setOpaque(true);
        }

        // Set hover effect
        Point mousePoint = getMousePosition();
        if (mousePoint != null && rowAtPoint(mousePoint) == row && !isRowSelected(row)) {
            component.setBackground(new Color(220, 220, 255));
        }

        return component;
    }
    
    private static class CenterTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            return this;
        }
    }
    
    
}



/*
class CustomButton extends JButton {
    private static final List<CustomButton> buttonList = new ArrayList<>();
    private int borderRadius = 10;
    private Color dropShadowColor = new Color(0, 0, 0, 50); // Semi-transparent shadow
    private final Insets shadowSize = new Insets(2, 5, 8, 5);
    private boolean hovered = false;
    private boolean clicked = false;
    public Color hoverColor = new Color(220, 220, 220); // Color when hovered
    public Color clickColor = new Color(200, 200, 200); // Color when clicked

    public CustomButton() {
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.black);
        setBackground(new Color(241, 233, 233));
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(shadowSize.top, shadowSize.left, shadowSize.bottom, shadowSize.right));
        
        // Add mouse listeners to handle hover and click states
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!clicked) {
                    hovered = true;
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!clicked) {
                    hovered = false;
                    repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Reset the clicked state of all other buttons
                for (CustomButton button : buttonList) {
                    if (button != CustomButton.this) {
                        button.setClicked(false);
                        button.setHovered(false);
                    }
                }
                // Toggle the clicked state of this button
                clicked = !clicked;
                if (clicked) {
                    hovered = true;
                } else {
                    hovered = false;
                }
                repaint();
            }
        });
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
        repaint();
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        repaint();    
    }
    
    

    
    
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        int x = shadowSize.left;
        int y = shadowSize.top;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw drop shadow
        g2.setColor(dropShadowColor);
        g2.fillRoundRect(x + 3, y + 3, width, height, borderRadius, borderRadius);

        // Draw button background
        g2.setColor(getBackground());
        g2.fillRoundRect(x, y, width, height, borderRadius, borderRadius);

        g2.dispose();
        super.paintComponent(g);
    }
    
    @Override
    public Insets getInsets() {
        return new Insets(borderRadius, borderRadius, borderRadius, borderRadius);
    }
    
    
    @Override
    protected void paintBorder(Graphics g) {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        int x = shadowSize.left;
        int y = shadowSize.top;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw button border
        g2.setColor(getForeground());
        g2.drawRoundRect(x, y, width, height, borderRadius, borderRadius);

        g2.dispose();
    }
    
}
*/



/*
class CustomButton extends JButton{    
    private int borderRadius;
    private Color dropShadowColor = new Color(170, 170, 170);
//    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(2, 5, 8, 5);

    
    public CustomButton(){
//        setBorder(new EmptyBorder(10, 12, 15, 12));
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.black);
        setBackground(new Color(241, 233, 233));
        setIconTextGap(10);
        setBorder(new CustomRoundedBorder(15));
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        //  Create Shadow Image
//        g2.drawImage(imageShadow, 0, 0, null);
        //  Create Background Color
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, borderRadius, borderRadius));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g);
 
    }
  
}




class CustomRoundedBorder implements Border{
    private int radius;

    CustomRoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded border
        g2d.setColor(c.getForeground());
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

        g2d.dispose();
    }

}
*/