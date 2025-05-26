package utils;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.EmptyBorder;

public class GUIStyleHelper {

    public static void styleMainPanel(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(new Color(245, 250, 255));
    }

    public static void styleTitle(JLabel title) {
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setForeground(new Color(20, 40, 80));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static void styleNavHeader(JLabel header) {
        header.setFont(new Font("Serif", Font.BOLD, 20));
        header.setForeground(new Color(20, 40, 80));
    }

    public static void styleLogoutPanel(JPanel panel) {
        panel.setBackground(new Color(20, 40, 80));
    }

    public static void styleLogoutHeader(JLabel header) {
        header.setFont(new Font("Serif", Font.BOLD, 26));
        header.setBackground(new Color(20, 40, 80));
        header.setForeground(new Color(0xfcba03));
    }

    public static void styleLabel(JLabel label) {
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static void styleInputField(JTextField field) {
        field.setPreferredSize(new Dimension(300, 40));
        field.setMaximumSize(field.getPreferredSize());
        field.setFont(new Font("Serif", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
    }

    public static void styleRow(JPanel row, JLabel label, JTextField field) {
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setBackground(new Color(245, 250, 255));
        row.add(label);
        row.add(Box.createRigidArea(new Dimension(10, 0)));
        row.add(field);
        row.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static void styleLoginButton(JButton button) {
        button.setBackground(new Color(0xfcba03)); 
        button.setForeground(new Color(20, 40, 80));
        button.setFocusPainted(false);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(true);
        button.setOpaque(true); 
        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static void styleLogoutButton(JButton button) {
        button.setForeground(new Color(0xfcba03)); 
        button.setBackground(new Color(20, 40, 80));
        button.setFocusPainted(false);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(true);
        button.setOpaque(true); 
        button.setFont(new Font("Serif", Font.BOLD, 26));
        button.setPreferredSize(new Dimension(215, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static void styleNavButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(0xfcba03));
        button.setForeground(new Color(20, 40, 80));
        button.setFont(new Font("Serif", Font.BOLD, 26));
    }

    public static void stylePages(JPanel panel) {
        panel.setBackground(new Color(245, 250, 255)); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
    }

    public static void styleHomeFrame(JFrame frame) {
        frame.getContentPane().setBackground(new Color(245, 250, 255));
    }

    public static void styleTable(JTable table) {
        table.setFillsViewportHeight(true);
        table.setRowHeight(130);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.setForeground(new Color(20, 40, 80));
        table.setBackground(Color.WHITE);
        table.setGridColor(new Color(220, 220, 220)); // soft row separator
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(0, 20));
        table.setPreferredSize(new Dimension(1100, table.getRowHeight() * (table.getRowCount())));
        table.setBorder(BorderFactory.createLineBorder(new Color(20, 40, 80), 2));

        // header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0xfcba03));
        header.setForeground(new Color(20, 40, 80));
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setPreferredSize(new Dimension(100, 40));
    }
}