package interface_.Gold;

import dao.StudentDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.*;
import utils.*;

public class GoldLoginGUI extends JFrame {
    private final Connection conn;
    private JTextField permField;
    private JPasswordField pinField;
    private JButton loginButton;

    public GoldLoginGUI(Connection conn) {
        this.conn = conn;

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
        pinRow.add(Box.createRigidArea(new Dimension(25, 0)));
        GUIStyleHelper.styleLabel(pinLabel);
        GUIStyleHelper.styleInputField(pinField);
        GUIStyleHelper.styleRow(pinRow, pinLabel, pinField);

        loginButton = new JButton("Login");
        GUIStyleHelper.styleLoginButton(loginButton);
        loginButton.addActionListener(this::handleLogin);

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

        System.out.println("Entered PERM: '" + perm + "'");
        System.out.println("Entered PIN: '" + pin + "'");
        System.out.println("Hashed: " + PinManager.hashPin(pin));

        try {
            StudentDAO studentDAO = new StudentDAO(conn);
            boolean success = studentDAO.verifyPin(perm, pin);

            if (success) {
                new GoldHomeGUI(perm, conn).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect PERM or PIN.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}