package models;

import dao.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final JPanel panel;
    private boolean clicked;
    private final List<String[]> courseDetails;
    private final CourseDAO courseDAO;
    private final JTable table;
    private final Connection conn;

    public AddButtonEditor(JCheckBox checkBox, List<String[]> courseDetails, CourseDAO courseDAO, JTable table, Connection conn) {
        super(checkBox);
        this.courseDetails = courseDetails;
        this.courseDAO = courseDAO;
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

            String perm = JOptionPane.showInputDialog(null, "Enter Student PERM to enroll in " + courseNo + ":");

            if (perm != null && !perm.trim().isEmpty()) {
                perm = perm.trim();
                try {
                    StudentDAO studentDAO = new StudentDAO(conn);
                    PrerequisiteDAO prereqDAO = new PrerequisiteDAO(conn);
                    TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
                    EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);

                    if (!studentDAO.studentExists(perm)) {
                        JOptionPane.showMessageDialog(null,
                            "Invalid PERM number. No student found.",
                            "Enrollment Failed",
                            JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());
                        clicked = false;
                        return "Add";
                    }

                    if (!prereqDAO.hasCompletedPrerequisites(perm, courseNo)) {
                        JOptionPane.showMessageDialog(null,
                            "This student has not completed the prerequisites for this course.",
                            "Enrollment Failed",
                            JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());
                        clicked = false;
                        return "Add";
                    }

                    List<String[]> transcriptRecords = transcriptDAO.getAllTranscriptRecords(perm);
                    for (String[] record : transcriptRecords) {
                        if (record[2].equals(courseNo)) {
                            JOptionPane.showMessageDialog(null,
                                "This student has already taken this course in a past quarter.",
                                "Enrollment Failed",
                                JOptionPane.WARNING_MESSAGE);
                            SwingUtilities.invokeLater(() -> table.clearSelection());
                            clicked = false;
                            return "Add";
                        }
                    }

                    if (!studentDAO.studentExists(perm)) {
                        JOptionPane.showMessageDialog(null,
                            "Invalid PERM number. No student found.",
                            "Enrollment Failed",
                            JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());
                        clicked = false;
                        return "Add";
                    } else if (courseDAO.getCourseEnrollmentCount(courseNo, year, quarter) >= courseDAO.getCourseCapacity(courseNo, year, quarter)) {
                        JOptionPane.showMessageDialog(null,
                            "This course is full and cannot accept more students.",
                            "Enrollment Failed",
                            JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());
                    } else if (courseDAO.getStudentCourseCount(perm, year, quarter) >= 5) {
                        JOptionPane.showMessageDialog(null,
                            "This student is already enrolled in 5 courses this quarter.",
                            "Enrollment Failed",
                            JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());
                    } else if (enrollmentDAO.isEnrolled(perm, courseNo, year, quarter)) {
                        JOptionPane.showMessageDialog(null,
                            "This student is already enrolled in this course.",
                            "Enrollment Failed",
                            JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(() -> table.clearSelection());
                    } else {
                        int result = JOptionPane.showConfirmDialog(null,
                            "Confirm enrollment of PERM " + perm + " in " + courseNo + "?",
                            "Confirm Enrollment",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                        if (result == JOptionPane.YES_OPTION) {
                            enrollmentDAO.addCourse(perm, courseNo, year, quarter);
                            JOptionPane.showMessageDialog(null, "Successfully enrolled PERM " + perm + " in " + courseNo);
                            SwingUtilities.invokeLater(() -> table.clearSelection());
                        } else {
                            SwingUtilities.invokeLater(() -> table.clearSelection());
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(button, "Failed to enroll student.");
                    SwingUtilities.invokeLater(() -> table.clearSelection());
                }
            } else {
                SwingUtilities.invokeLater(() -> table.clearSelection());
            }
        }

        clicked = false;
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