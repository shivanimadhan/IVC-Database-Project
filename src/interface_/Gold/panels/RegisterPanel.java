package interface_.Gold;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    public RegisterPanel(String perm) {
        setLayout(new BorderLayout());
        add(new JLabel("Register courses for student: " + perm, SwingConstants.CENTER), BorderLayout.CENTER);
    }
}