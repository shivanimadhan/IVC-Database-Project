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

public class CourseButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final JPanel panel;
    private boolean clicked;
    private final List<String[]> courseDetails;
    private final CourseDAO dao;
    private final String perm;
    private final JTable table;
    private final Connection conn;

    public CourseButtonEditor(JCheckBox checkBox, List<String[]> courseDetails, CourseDAO dao, String perm, JTable table, Connection conn) {
        super(checkBox);
        this.courseDetails = courseDetails;
        this.dao = dao;
        this.perm = perm;
        this.table = table;
        this.conn = conn;

        button = new JButton("Add");
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
            int selectedRow = table.getSelectedRow();
            String courseNo = courseDetails.get(selectedRow)[0];
            int year = Integer.parseInt(courseDetails.get(selectedRow)[2]);
            String quarter = courseDetails.get(selectedRow)[3];

            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to enroll in " + courseNo + "?",
                    "Confirm Enrollment",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                try {
                    PrerequisiteDAO prereqDAO = new PrerequisiteDAO(conn);

                    if (!prereqDAO.hasCompletedPrerequisites(perm, courseNo)) {
                        JOptionPane.showMessageDialog(null,
                                "You have not completed the prerequisites for this course.",
                                "Enrollment Failed",
                                JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());

                    } else if (dao.getCourseEnrollmentCount(courseNo, year, quarter) >= dao.getCourseCapacity(courseNo, year, quarter)) {
                        JOptionPane.showMessageDialog(null,
                                "This course is full and cannot accept more students.",
                                "Enrollment Failed",
                                JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());

                    } else if (dao.getStudentCourseCount(perm, year, quarter) >= 5) {
                        JOptionPane.showMessageDialog(null,
                                "You cannot enroll in more than 5 courses per quarter.",
                                "Enrollment Failed",
                                JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());

                    } else {
                        EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);
                        enrollmentDAO.addCourse(perm, courseNo, year, quarter);
                        JOptionPane.showMessageDialog(null, "Successfully enrolled in " + courseNo);
                        SwingUtilities.getWindowAncestor(button).dispose();
                        new GoldHomeGUI(perm, conn).setVisible(true);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(button, "Failed to enroll in course.");
                    SwingUtilities.invokeLater(() -> table.clearSelection());
                }
            } else {
                SwingUtilities.invokeLater(() -> table.clearSelection());
            }

            clicked = false;
        }
        return "Add";
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}