package interface_;

import dao.StudentDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.*;
import utils.*;

public class GoldHomeGUI extends JFrame {
    private JTextField permField;
    private JPasswordField pinField;
    private JButton loginButton;

    public GoldHomeGUI() {
        setTitle("IVC GOLD Login");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildUI();
    }

    private void buildUI() {
        JPanel panel = new JPanel();
        GUIStyleHelper.styleMainPanel(panel);

        JLabel title = new JLabel("Welcome to IVC Gold!");
        GUIStyleHelper.styleTitle(title);

        JLabel permLabel = new JLabel("PERM:");
        permField = new JTextField();
        JPanel permRow = new JPanel();
        GUIStyleHelper.styleLabel(permLabel);
        GUIStyleHelper.styleInputField(permField);
        GUIStyleHelper.styleRow(permRow, permLabel, permField);

        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField();
        JPanel pinRow = new JPanel();
        GUIStyleHelper.styleLabel(pinLabel);
        GUIStyleHelper.styleInputField(pinField);
        pinRow.add(Box.createRigidArea(new Dimension(25, 0)));
        GUIStyleHelper.styleRow(pinRow, pinLabel, pinField);

        loginButton = new JButton("Login");
        GUIStyleHelper.styleLoginButton(loginButton);
        loginButton.addActionListener(this::handleLogin);

        // spacing + structure
        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(permRow);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(pinRow);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(loginButton);
        panel.add(Box.createVerticalGlue());

        add(panel);
    }

    private void handleLogin(ActionEvent e) {
        String perm = permField.getText().trim();
        String pin = new String(pinField.getPassword()).trim();

        try (Connection conn = DBConnection.getConnection()) {
            StudentDAO studentDAO = new StudentDAO(conn);
            boolean success = studentDAO.verifyPin(perm, pin);

            if (success) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
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

        SwingUtilities.invokeLater(() -> new GoldHomeGUI().setVisible(true));
    }
}