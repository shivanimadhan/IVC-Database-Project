package interface_.Registrar;

import dao.StudentDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.*;
import utils.*;

public class RegistrarLoginGUI extends JFrame {
    private final Connection conn;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public RegistrarLoginGUI(Connection conn) {
        this.conn = conn;

        setTitle("Registrar GOLD Login");
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

        JLabel title = new JLabel("Welcome to Registrar Portal!");
        GUIStyleHelper.styleTitle(title);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JPanel usernameRow = new JPanel();
        GUIStyleHelper.styleLabel(usernameLabel);
        GUIStyleHelper.styleInputField(usernameField);
        GUIStyleHelper.styleRow(usernameRow, usernameLabel, usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JPanel passwordRow = new JPanel();
        passwordRow.add(Box.createRigidArea(new Dimension(5, 0)));
        GUIStyleHelper.styleLabel(passwordLabel);
        GUIStyleHelper.styleInputField(passwordField);
        GUIStyleHelper.styleRow(passwordRow, passwordLabel, passwordField);

        loginButton = new JButton("Login");
        GUIStyleHelper.styleLoginButton(loginButton);
        loginButton.addActionListener(this::handleLogin);

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(usernameRow);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(passwordRow);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(loginButton);
        panel.add(Box.createVerticalGlue());

        add(panel);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        try {
            if ("ADMIN".equalsIgnoreCase(username) && "ADMIN".equalsIgnoreCase(password)) {
                new RegistrarHomeGUI(conn).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}