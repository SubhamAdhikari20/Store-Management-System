import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Test extends JFrame {
    public Test() {
        setTitle("Custom JTable Example");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sample data and column names
        Object[][] data = {
            {"John", 23, "Engineer"},
            {"Anna", 30, "Designer"},
            {"Paul", 25, "Artist"},
            {"Mike", 35, "Manager"}
        };
        String[] columnNames = {"Name", "Age", "Occupation"};

        // Create table with custom model
        JTable table = new JTable(new DefaultTableModel(data, columnNames)) {
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

                return component;
            }
        };

        // Customize table header
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        // Customize table appearance
        table.setRowHeight(30);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set custom cell renderer for specific column if needed
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        // Set table properties for better look
        table.setFillsViewportHeight(true);
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);

        // Add hover effect
        table.addMouseMotionListener(new MouseAdapter() {
            int previousRow = -1;

            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != previousRow) {
                    previousRow = row;
                    table.repaint();
                }
            }
        });
    }

    private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                cell.setBackground(new Color(173, 216, 230));
            } else if (row % 2 == 0) {
                cell.setBackground(new Color(230, 230, 250));
            } else {
                cell.setBackground(Color.WHITE);
            }

            setHorizontalAlignment(SwingConstants.CENTER);

            // Set hover effect
            Point mousePoint = table.getMousePosition();
            if (mousePoint != null && table.rowAtPoint(mousePoint) == row) {
                cell.setBackground(new Color(220, 220, 255));
            }

            return cell;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test frame = new Test();
            frame.setVisible(true);
        });
    }
}
