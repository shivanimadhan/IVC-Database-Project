package interface_.Gold;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.*;
import javax.swing.*;
import interface_.Gold.panels.*;
import utils.*;

public class GoldHomeGUI extends JFrame {
    private final String perm;
    private final Connection conn;
    private JPanel contentPanel;
    private final Map<String, JPanel> pages = new HashMap<>();

    public GoldHomeGUI(String perm, Connection conn) {
        this.conn = conn;
        this.perm = perm;
        setTitle("IVC GOLD Home");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildUI();
    }

    private void buildUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // HEADER BAR
        JPanel headerPanel = new JPanel(new BorderLayout());
        GUIStyleHelper.styleLogoutPanel(headerPanel);

        JLabel titleLabel = new JLabel("IVC GOLD");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));
        GUIStyleHelper.styleLogoutHeader(titleLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 100));
        GUIStyleHelper.styleLogoutButton(logoutButton);

        logoutButton.addActionListener(e -> {
            dispose();
            new GoldLoginGUI(conn).setVisible(true);
        });

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);

        // nav bar
        JPanel navPanel = new JPanel(new GridLayout(1, 5));
        String[] tabNames = {
            "Schedule", "Register",
            "Grades", "Progress", "Settings"
        };

        String[] tabKeys = { "schedule", "register", "grades", "progress", "settings" };

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

        // content area
        contentPanel = new JPanel(new BorderLayout());
        pages.put("schedule", new SchedulePanel(perm, conn));
        pages.put("register", new JLabelPanel("Registration Page"));
        pages.put("grades", new JLabelPanel("Grades Page"));
        pages.put("progress", new JLabelPanel("Progress Page"));
        pages.put("settings", new SettingsPanel(perm, conn));

        contentPanel.add(pages.get("schedule")); // default page

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

    // temporary filler panel class
    static class JLabelPanel extends JPanel {
        public JLabelPanel(String text) {
            setLayout(new BorderLayout());
            JLabel label = new JLabel(text, JLabel.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 24));
            add(label, BorderLayout.CENTER);
        }
    }
}