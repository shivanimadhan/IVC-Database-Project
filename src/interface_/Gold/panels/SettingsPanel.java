package interface_.Gold;

import javax.swing.*;
import java.awt.*;
import utils.*;
import java.sql.Connection;

public class SettingsPanel extends JPanel {
    public SettingsPanel(String perm, Connection conn) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);
    }
}