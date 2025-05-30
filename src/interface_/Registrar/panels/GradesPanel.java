package interface_.Registrar.panels;

import dao.CourseDAO;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import utils.GUIStyleHelper;

public class GradesPanel extends JPanel {
    private final Connection conn;
    private final JTextField enrollCodeField;
    private final JComboBox<String> yearDropdown;
    private final JComboBox<String> quarterDropdown;
    private final DefaultTableModel gradesModel;
    private final JTable gradesTable;

    public GradesPanel(Connection conn) {
        this.conn = conn;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Enter Grades by Enroll Code");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.setOpaque(false);
        formPanel.add(new JLabel("Enroll Code:"));
        enrollCodeField = new JTextField(8);
        formPanel.add(enrollCodeField);

        String[] years = {"24", "25"};
        String[] quarters = {"F", "W", "S"};
        yearDropdown = new JComboBox<>(years);
        quarterDropdown = new JComboBox<>(quarters);
        formPanel.add(new JLabel("Year:"));
        formPanel.add(yearDropdown);
        formPanel.add(new JLabel("Quarter:"));
        formPanel.add(quarterDropdown);

        add(Box.createVerticalStrut(10));
        add(formPanel);

        JLabel instructions = new JLabel("Click 'Load' to view enrolled students. Enter their grades below:");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(instructions);

        gradesModel = new DefaultTableModel(new String[]{"Perm", "Grade"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        gradesModel.addRow(new Object[]{"", ""});

        gradesTable = new JTable(gradesModel);
        GUIStyleHelper.styleGradesTable(gradesTable);
        gradesTable.setRowHeight(50);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < gradesTable.getColumnCount(); i++) {
            gradesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = gradesTable.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane tableScroll = new JScrollPane(gradesTable);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setOpaque(false);
        tableScroll.getViewport().setOpaque(false);

        JPanel tableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableWrapper.setOpaque(false);
        tableWrapper.add(tableScroll);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(tableWrapper);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        JButton loadButton = new JButton("Load");
        JButton submitButton = new JButton("Submit");
        GUIStyleHelper.stylePinButton(loadButton);
        GUIStyleHelper.stylePinButton(submitButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(submitButton);

        add(Box.createVerticalStrut(10));
        add(buttonPanel);

        loadButton.addActionListener(e -> loadStudentPerms());
        submitButton.addActionListener(e -> submitGrades());
    }

    private void loadStudentPerms() {
        try {
            int enrollCode = Integer.parseInt(enrollCodeField.getText().trim());
            int year = Integer.parseInt((String) yearDropdown.getSelectedItem());
            String quarter = ((String) quarterDropdown.getSelectedItem()).toUpperCase();

            CourseDAO courseDAO = new CourseDAO(conn);
            String[] courseInfo = courseDAO.getCourseByEnrollCode(enrollCode);
            if (courseInfo == null) {
                JOptionPane.showMessageDialog(this, "Course not found for enroll code.");
                return;
            }
            String courseNo = courseInfo[0];

            PreparedStatement stmt = conn.prepareStatement("""
                SELECT DISTINCT perm FROM (
                    SELECT perm FROM TAKES WHERE course_no = ? AND year = ? AND quarter = ?
                    UNION
                    SELECT perm FROM TOOK WHERE course_no = ? AND year = ? AND quarter = ?
                )
            """);
            stmt.setString(1, courseNo);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);
            stmt.setString(4, courseNo);
            stmt.setInt(5, year);
            stmt.setString(6, quarter);

            ResultSet rs = stmt.executeQuery();
            gradesModel.setRowCount(0);

            while (rs.next()) {
                String perm = rs.getString("perm");
                PreparedStatement gradeStmt = conn.prepareStatement("""
                    SELECT grade FROM TOOK
                    WHERE perm = ? AND course_no = ? AND year = ? AND quarter = ?
                """);
                gradeStmt.setString(1, perm);
                gradeStmt.setString(2, courseNo);
                gradeStmt.setInt(3, year);
                gradeStmt.setString(4, quarter);

                ResultSet gradeRs = gradeStmt.executeQuery();
                String grade = "";
                if (gradeRs.next()) {
                    grade = gradeRs.getString("grade");
                }

                gradesModel.addRow(new Object[]{perm, grade});
            }

            int rowHeight = gradesTable.getRowHeight();
            int rowCount = gradesTable.getRowCount();
            int totalHeight = rowHeight * rowCount + 26;
            gradesTable.setPreferredScrollableViewportSize(new Dimension(1200, Math.min(totalHeight, 600)));
            gradesTable.revalidate();
            gradesTable.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage());
        }
    }

    private void submitGrades() {
        try {
            if (gradesTable.isEditing()) {
                gradesTable.getCellEditor().stopCellEditing();
            }

            int enrollCode = Integer.parseInt(enrollCodeField.getText().trim());
            int year = Integer.parseInt((String) yearDropdown.getSelectedItem());
            String quarter = ((String) quarterDropdown.getSelectedItem()).toUpperCase();

            CourseDAO courseDAO = new CourseDAO(conn);
            String[] courseInfo = courseDAO.getCourseByEnrollCode(enrollCode);
            if (courseInfo == null) {
                JOptionPane.showMessageDialog(this, "Course not found for enroll code.");
                return;
            }

            String courseNo = courseInfo[0];

            PreparedStatement updateStmt = conn.prepareStatement("""
                UPDATE TOOK SET grade = ?
                WHERE perm = ? AND course_no = ? AND year = ? AND quarter = ?
            """);

            PreparedStatement insertStmt = conn.prepareStatement("""
                INSERT INTO TOOK (perm, course_no, year, quarter, grade)
                VALUES (?, ?, ?, ?, ?)
            """);

            for (int i = 0; i < gradesModel.getRowCount(); i++) {
                String perm = gradesModel.getValueAt(i, 0).toString().trim();
                String grade = gradesModel.getValueAt(i, 1).toString().trim();
                if (perm.isEmpty() || grade.isEmpty()) continue;

                updateStmt.setString(1, grade);
                updateStmt.setString(2, perm);
                updateStmt.setString(3, courseNo);
                updateStmt.setInt(4, year);
                updateStmt.setString(5, quarter);
                int rowsUpdated = updateStmt.executeUpdate();

                if (rowsUpdated == 0) {
                    insertStmt.setString(1, perm);
                    insertStmt.setString(2, courseNo);
                    insertStmt.setInt(3, year);
                    insertStmt.setString(4, quarter);
                    insertStmt.setString(5, grade);
                    insertStmt.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(this, "Grades submitted successfully for " + courseNo + " (" + quarter + " " + year + ")");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting grades: " + ex.getMessage());
        }
    }
}