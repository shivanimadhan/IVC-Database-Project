package interface_.Registrar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.*;
import javax.swing.*;
// import interface_.Registrar.panels.*;
import utils.*;

public class RegistrarHomeGUI extends JFrame {
    private final Connection conn;

    public RegistrarHomeGUI(Connection conn) {
        this.conn = conn;

        setTitle("Registrar Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        setLocationRelativeTo(null); // center the window

        JLabel welcomeLabel = new JLabel("Welcome, Registrar!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));

        add(welcomeLabel, BorderLayout.CENTER);
    }
}