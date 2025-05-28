package models;

import dao.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DropButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final JPanel panel;
    private boolean clicked;
    private final List<String[]> courseDetails;
    private final JTable table;
    private final Connection conn;

    public DropButtonEditor(JCheckBox checkBox, List<String[]> courseDetails, JTable table, Connection conn) {
        super(checkBox);
        this.courseDetails = courseDetails;
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

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        clicked = true;
        return panel;
    }

    public Object getCellEditorValue() {
        if (clicked) {
            int selectedRow = table.getSelectedRow();
            String courseNo = courseDetails.get(selectedRow)[0];
            int year = Integer.parseInt(courseDetails.get(selectedRow)[2]);
            String quarter = courseDetails.get(selectedRow)[3];

            String perm = JOptionPane.showInputDialog(null, "Enter Student PERM to drop from " + courseNo + ":");

            if (perm != null && !perm.trim().isEmpty()) {
                perm = perm.trim();
                try {
                    EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);
                    StudentDAO studentDAO = new StudentDAO(conn);

                    if (!studentDAO.studentExists(perm)) {
                        JOptionPane.showMessageDialog(null,
                            "Invalid PERM number. No student found.",
                            "Drop Failed",
                            JOptionPane.WARNING_MESSAGE);
                    } else if (!enrollmentDAO.isEnrolled(perm, courseNo, year, quarter)) {
                        JOptionPane.showMessageDialog(null,
                            "This student is not enrolled in this course.",
                            "Drop Failed",
                            JOptionPane.WARNING_MESSAGE);
                    } else if (enrollmentDAO.getStudentCourseCount(perm, year, quarter) <= 1) {
                        JOptionPane.showMessageDialog(null,
                            "Student must be enrolled in at least one course. Cannot drop their last course.",
                            "Drop Failed",
                            JOptionPane.WARNING_MESSAGE);
                    } else {
                        int result = JOptionPane.showConfirmDialog(null,
                            "Confirm dropping PERM " + perm + " from " + courseNo + "?",
                            "Confirm Drop",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                        if (result == JOptionPane.YES_OPTION) {
                            enrollmentDAO.dropCourse(perm, courseNo, year, quarter);
                            JOptionPane.showMessageDialog(null, "Successfully dropped PERM " + perm + " from " + courseNo);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(button, "Failed to drop student.");
                } finally {
                    SwingUtilities.invokeLater(() -> table.clearSelection());
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