package interface_.Registrar.panels;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class GradesPanel extends JPanel {
    private final Connection conn;

    public GradesPanel(Connection conn) {
        this.conn = conn;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // padding

        // placeholder title
        JLabel header = new JLabel("Enter Grades");
        header.setFont(new Font("Serif", Font.BOLD, 26));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(header);

        // you can add buttons, tables, etc. here later
        JLabel placeholder = new JLabel("(schedule editing UI goes here)");
        placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(placeholder);
    }
}