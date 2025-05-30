package interface_.Registrar;

import interface_.Registrar.panels.*;
import java.awt.*;
import java.sql.Connection;
import java.util.*;
import javax.swing.*;
import utils.*;

public class RegistrarHomeGUI extends JFrame {
    private final Connection conn;
    private JPanel contentPanel;
    private final Map<String, JPanel> pages = new HashMap<>();

    public RegistrarHomeGUI(Connection conn) {
        this.conn = conn;
        setTitle("IVC Registrar Home");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildUI();
    }

    private void buildUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        GUIStyleHelper.styleLogoutPanel(headerPanel);

        JLabel titleLabel = new JLabel("IVC REGISTRAR");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 160, 10, 0));
        GUIStyleHelper.styleLogoutHeader(titleLabel);

        JButton logoutButton = new JButton("Logout");
        GUIStyleHelper.styleLogoutButton(logoutButton);

        JPanel logoutWrapper = new JPanel();
        logoutWrapper.setLayout(new BoxLayout(logoutWrapper, BoxLayout.X_AXIS));
        logoutWrapper.setOpaque(false);
        logoutWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 30)); 

        logoutWrapper.add(Box.createRigidArea(new Dimension(10, 0))); 
        logoutWrapper.add(logoutButton);

        logoutButton.addActionListener(e -> {
            dispose();
            new RegistrarLoginGUI(conn).setVisible(true);
        });

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(logoutWrapper, BorderLayout.EAST);

        // NAVIGATION
        JPanel navPanel = new JPanel(new GridLayout(1, 4));
        String[] tabNames = { "Modify Schedule", "Enter Grades", "Generate Transcript", "Grade Mailers" };
        String[] tabKeys  = { "schedule", "grades", "transcript", "email" };

        for (int i = 0; i < tabNames.length; i++) {
            String name = tabNames[i];
            String key = tabKeys[i];
            JButton tabButton = new JButton(name);
            tabButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            GUIStyleHelper.styleNavButton(tabButton);
            tabButton.addActionListener(e -> switchToPage(key));
            navPanel.add(tabButton);
        }

        // STACK HEADER + NAV
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.add(headerPanel);
        topContainer.add(navPanel);

        // CONTENT AREA
        contentPanel = new JPanel(new BorderLayout());
        pages.put("schedule", new SchedulePanel(conn));
        pages.put("grades", new GradesPanel(conn));
        pages.put("transcript", new TranscriptPanel(conn));
        pages.put("email", new EmailGeneratorPanel(conn));

        contentPanel.add(pages.get("schedule")); // default

        mainPanel.add(topContainer, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void switchToPage(String key) {
        contentPanel.removeAll();
        contentPanel.add(pages.get(key), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // placeholder panel
    static class JLabelPanel extends JPanel {
        public JLabelPanel(String text) {
            setLayout(new BorderLayout());
            JLabel label = new JLabel(text, JLabel.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 24));
            add(label, BorderLayout.CENTER);
        }
    }
}
