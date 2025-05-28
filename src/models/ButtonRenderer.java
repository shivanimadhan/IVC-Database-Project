package models;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.GridBagLayout;
import java.awt.*;
import utils.*;

public class ButtonRenderer extends JPanel implements TableCellRenderer {
    private final JButton button;

    public ButtonRenderer(String label) {
        setOpaque(false);
        setLayout(new GridBagLayout());

        button = new JButton(label);
        GUIStyleHelper.styleSmallButton(button); 
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        add(button);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}