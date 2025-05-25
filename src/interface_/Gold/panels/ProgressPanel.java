package interface_.Gold;

import javax.swing.*;
import java.awt.*;

public class ProgressPanel extends JPanel {
    public ProgressPanel(String perm) {
        setLayout(new BorderLayout());
        add(new JLabel("Progress for student: " + perm, SwingConstants.CENTER), BorderLayout.CENTER);
    }
}