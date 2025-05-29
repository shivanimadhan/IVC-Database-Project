package interface_.Registrar.panels;

import dao.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.*;
import utils.*;

public class SchedulePanel extends JPanel {
    private final Connection conn;
    private JComboBox<String> departmentDropdown;
    private JPanel resultsPanel;

    public SchedulePanel(Connection conn) {
        this.conn = conn;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        buildSearchForm(); // default view
    }

    private void buildSearchForm() {
        removeAll();

        // --- LEFT-ALIGNED DROPDOWN ---
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setOpaque(false);

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
        departmentDropdown.setSelectedItem("CS");
        departmentDropdown.addActionListener(e -> showCourseOfferings((String) departmentDropdown.getSelectedItem()));

        inputPanel.add(Box.createRigidArea(new Dimension(120, 0)));
        inputPanel.add(deptLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(departmentDropdown);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(inputPanel);

        // --- CENTERED RESULTS PANEL ---
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setOpaque(false);

        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerWrapper.setOpaque(false);
        centerWrapper.add(resultsPanel);

        add(centerWrapper);
        add(Box.createVerticalGlue());

        showCourseOfferings("CS");

        revalidate();
        repaint();
    }

    private void showCourseOfferings(String dept) {
        resultsPanel.removeAll();

        try {
            CourseDAO courseDAO = new CourseDAO(conn);
            Quarter curr = new Quarter("S", 25); // temp value

            List<String[]> offeredCourses = courseDAO.getOfferedCoursesByDept(dept, curr.getYear(), curr.getQuarterCode());

            String[] columnNames = {"Course", "Add", "Drop", "Roster"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (String[] course : offeredCourses) {
                String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
                        "&emsp;Professor " + course[4] + "<br>" +
                        "&emsp;" + course[5] + "<br>" +
                        "&emsp;" + course[6] + "</html>";
                model.addRow(new Object[]{formatted, "Add", "Drop", "Roster"});
            }

            JTable table = new JTable(model) {
                public boolean isCellEditable(int row, int column) {
                    return column >= 1;
                }
            };

            GUIStyleHelper.styleTable(table);
            table.setTableHeader(null);

            table.getColumn("Add").setCellRenderer(new ButtonRenderer("Add"));
            table.getColumn("Drop").setCellRenderer(new ButtonRenderer("Drop"));
            table.getColumn("Roster").setCellRenderer(new ButtonRenderer("Roster"));

            table.getColumn("Add").setPreferredWidth(125);
            table.getColumn("Add").setMaxWidth(125);
            table.getColumn("Add").setMinWidth(125);
            table.getColumn("Drop").setPreferredWidth(125);
            table.getColumn("Drop").setMaxWidth(125);
            table.getColumn("Drop").setMinWidth(125);
            table.getColumn("Roster").setPreferredWidth(125);
            table.getColumn("Roster").setMaxWidth(125);
            table.getColumn("Roster").setMinWidth(125);

            table.getColumn("Add").setCellEditor(new AddButtonEditor(new JCheckBox(), offeredCourses, courseDAO, table, conn));
            table.getColumn("Drop").setCellEditor(new DropButtonEditor(new JCheckBox(), offeredCourses, table, conn));
            table.getColumn("Roster").setCellEditor(new RosterButtonEditor(new JCheckBox(), offeredCourses, courseDAO, table, conn));

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(1100, table.getRowHeight() * table.getRowCount()));
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            JLabel header = new JLabel("Offered Courses for " + dept);
            GUIStyleHelper.styleNavHeader(header);
            header.setAlignmentX(Component.CENTER_ALIGNMENT);

            resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            resultsPanel.add(header);
            resultsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            resultsPanel.add(scrollPane);

        } catch (Exception e) {
            resultsPanel.add(new JLabel("Failed to load courses."));
            e.printStackTrace();
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}