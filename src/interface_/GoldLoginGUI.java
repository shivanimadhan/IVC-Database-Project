package interface_;

import dao.StudentDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.*;
import utils.DBConnection;

public class GoldLoginGUI extends JFrame {
    private JTextField permField;
    private JPasswordField pinField;
    private JButton loginButton;

    public GoldLoginGUI() {
        setTitle("IVC GOLD Login");
        setSize(750, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildUI();
    }

    private void buildUI() {
        // outer panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(new Color(245, 250, 255)); // soft light blue

        JLabel title = new JLabel("Welcome to Gold");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        title.setForeground(new Color(20, 40, 80));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel permLabel = new JLabel("PERM:");
        permField = new JTextField();
        permField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField();
        pinField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0xfcba03)); 
        loginButton.setForeground(new Color(20, 40, 80));
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Serif", Font.BOLD, 14));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(this::handleLogin);

        // spacing helpers
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(permLabel);
        panel.add(permField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);

        add(panel);
    }

    private void handleLogin(ActionEvent e) {
        String perm = permField.getText().trim();
        String pin = new String(pinField.getPassword()).trim();

        try (Connection conn = DBConnection.getConnection()) {
            StudentDAO studentDAO = new StudentDAO(conn);
            boolean success = studentDAO.verifyPin(perm, pin);

            if (success) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // TODO: launch dashboard
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect PERM or PIN.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.err.println("Database error: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new GoldLoginGUI().setVisible(true));
    }
}