package interface_.Gold.panels;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import utils.*;
import models.*;
import dao.*;

public class RegisterPanel extends JPanel {
    private JComboBox<String> departmentDropdown;
    private JButton searchButton;
    private final String perm;
    private final Connection conn;

    public RegisterPanel(String perm, Connection conn) {
        this.perm = perm;
        this.conn = conn;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GUIStyleHelper.stylePages(this);

        buildSearchForm();
    }

    private void buildSearchForm() {
        removeAll();

        // === INPUT PANEL ===
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setOpaque(false);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel deptLabel = new JLabel("Department:");
        GUIStyleHelper.styleRegisterLabel(deptLabel);

        MajorDAO majorDao = new MajorDAO(conn);
        List<String> depts;
        try {
            depts = majorDao.getAllDepts();
        } catch (SQLException e) {
            e.printStackTrace();
            depts = new java.util.ArrayList<>();
        }

        departmentDropdown = new JComboBox<>(depts.toArray(new String[0]));
        departmentDropdown.setMaximumSize(new Dimension(150, 30));

        inputPanel.add(deptLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(departmentDropdown);

        // === BUTTON PANEL ===
        searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        GUIStyleHelper.stylePinButton(searchButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(searchButton);

        // === CONTENT WRAPPER ===
        JPanel contentWrapper = new JPanel();
        contentWrapper.setLayout(new BoxLayout(contentWrapper, BoxLayout.Y_AXIS));
        contentWrapper.setOpaque(false);
        contentWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentWrapper.add(inputPanel);
        contentWrapper.add(Box.createRigidArea(new Dimension(0, 30)));
        contentWrapper.add(buttonPanel);

        add(Box.createRigidArea(new Dimension(20, 30)));
        add(contentWrapper);
        add(Box.createVerticalGlue());

        searchButton.addActionListener(e -> {
            String selectedDept = (String) departmentDropdown.getSelectedItem();
            showSearchResults(selectedDept);
        });

        revalidate();
        repaint();
    }

    private void showSearchResults(String dept) {
        removeAll();
        
        try {
            CourseDAO courseDAO = new CourseDAO(conn);
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);
            TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
            Quarter curr = transcriptDAO.getCurrentQuarter(perm);

            List<String[]> offeredCourses = courseDAO.getOfferedCoursesByDept(dept, curr.getYear(), curr.getQuarterCode());
            List<String[]> currentCourses = enrollmentDAO.getCurrentCourses(perm);

            Set<String> enrolledKeys = new HashSet<>();
            for (String[] course : currentCourses) {
                String key = course[0] + "_" + course[2] + "_" + course[3];
                enrolledKeys.add(key);
            }

            List<String[]> filteredCourses = new ArrayList<>();
            for (String[] course : offeredCourses) {
                String key = course[0] + "_" + course[2] + "_" + course[3];
                if (!enrolledKeys.contains(key)) {
                    filteredCourses.add(course);
                }
            }

            String[] columnNames = {"Course", "Action"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (String[] course : filteredCourses) {
                String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
                                    "&emsp;Professor " + course[4] + "<br>" +
                                    "&emsp;" + course[5] + "<br>" +
                                    "&emsp;" + course[6] + "</html>";
                model.addRow(new Object[]{formatted, "Add"});
            }

            JTable table = new JTable(model) {
                public boolean isCellEditable(int row, int column) {
                    return column == 1;
                }
            };
        
            GUIStyleHelper.styleTable(table);
            table.setTableHeader(null);

            table.getColumn("Action").setCellRenderer(new ButtonRenderer("Add"));
            table.getColumn("Action").setCellEditor(
                new CourseButtonEditor(new JCheckBox(), filteredCourses, courseDAO, perm, table, conn)
            );
            table.getColumn("Action").setMinWidth(200);
            table.getColumn("Action").setMaxWidth(200);
            table.getColumn("Action").setPreferredWidth(200);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            int rowHeight = table.getRowHeight();
            int rowCount = table.getRowCount();
            int totalHeight = rowHeight * rowCount;

            scrollPane.setMaximumSize(new Dimension(1100, totalHeight));

            JLabel classes = new JLabel("Available Courses");
            GUIStyleHelper.styleNavHeader(classes);
            classes.setFont(new Font("Serif", Font.BOLD, 26));
            classes.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton backButton = new JButton("Back");
            GUIStyleHelper.stylePinButton(backButton);
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(20, 40, 80)),   // outer border
                BorderFactory.createEmptyBorder(10, 20, 10, 20)          // inner padding
            ));
            backButton.addActionListener(ev -> buildSearchForm());

            add(Box.createRigidArea(new Dimension(0, 20)));
            add(classes);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(scrollPane);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(backButton);

        } catch (Exception e) {
            add(new JLabel("Failed to load courses."));
            e.printStackTrace();
        }

        revalidate();
        repaint();
    }
}