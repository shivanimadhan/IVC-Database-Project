package interface_.Gold;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel(String perm) {
        setLayout(new BorderLayout());
        add(new JLabel("Settings for student: " + perm, SwingConstants.CENTER), BorderLayout.CENTER);
    }
}