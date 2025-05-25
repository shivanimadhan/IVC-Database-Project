package utils;

import java.awt.*;
import javax.swing.*;

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
        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
    }
}