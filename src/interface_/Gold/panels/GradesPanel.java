package interface_.Gold;

import javax.swing.*;
import java.awt.*;

public class GradesPanel extends JPanel {
    public GradesPanel(String perm) {
        setLayout(new BorderLayout());
        add(new JLabel("Grades for student: " + perm, SwingConstants.CENTER), BorderLayout.CENTER);
    }
}