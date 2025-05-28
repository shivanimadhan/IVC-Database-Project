package models;

import dao.*;
import utils.*;
import interface_.Gold.GoldHomeGUI;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.GridBagLayout;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnrollButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final JPanel panel;
    private boolean clicked;
    private final List<String[]> courseDetails;
    private final EnrollmentDAO dao;
    private final String perm;
    private final JTable table;
    private final Connection conn;

    public EnrollButtonEditor(JCheckBox checkBox, List<String[]> courseDetails, EnrollmentDAO dao, String perm, JTable table, Connection conn) {
        super(checkBox);
        this.courseDetails = courseDetails;
        this.dao = dao;
        this.perm = perm;
        this.table = table;
        this.conn = conn;

        button = new JButton("Drop");
        GUIStyleHelper.styleSmallButton(button);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());

        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.add(button);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        clicked = true;
        return panel;
    }

    public Object getCellEditorValue() {
        if (clicked) {
            String courseNo = courseDetails.get(table.getSelectedRow())[0];
            int year = Integer.parseInt(courseDetails.get(table.getSelectedRow())[2]);
            String quarter = courseDetails.get(table.getSelectedRow())[3];

            if (courseDetails.size() == 1) {
                JOptionPane.showMessageDialog(
                    null,
                    "You must remain enrolled in at least one course.",
                    "Drop Not Allowed",
                    JOptionPane.WARNING_MESSAGE
                );
                SwingUtilities.invokeLater(() -> table.clearSelection());
                clicked = false;
                return "Drop";
            }


            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to drop " + courseNo + "?",
                    "Confirm Drop",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                try {
                    dao.dropCourse(perm, courseNo, year, quarter);
                    JOptionPane.showMessageDialog(null, "Dropped " + courseNo);

                    SwingUtilities.getWindowAncestor(button).dispose(); 
                    new GoldHomeGUI(perm, conn).setVisible(true); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(button, "Failed to drop course.");
                }
            } else {
                SwingUtilities.invokeLater(() -> table.clearSelection());
            }
        }
        clicked = false;
        return "Drop";
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}