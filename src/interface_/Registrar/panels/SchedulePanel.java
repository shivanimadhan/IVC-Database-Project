// // // // package interface_.Registrar.panels;

// // // // import utils.*;
// // // // import models.*;
// // // // import dao.*;
// // // // import interface_.Gold.GoldHomeGUI;

// // // // import javax.swing.*;
// // // // import java.awt.*;
// // // // import java.sql.Connection;
// // // // import java.sql.SQLException;
// // // // import java.util.List;
// // // // import javax.swing.table.DefaultTableModel;

// // // // public class SchedulePanel extends JPanel {
// // // //     private final Connection conn;
// // // //     private JComboBox<String> departmentDropdown;

// // // //     public SchedulePanel(Connection conn) {
// // // //         this.conn = conn;
// // // //         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
// // // //         GUIStyleHelper.stylePages(this);

// // // //         // === INPUT PANEL ===
// // // //         JPanel inputPanel = new JPanel();
// // // //         inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
// // // //         inputPanel.setOpaque(false);
// // // //         inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

// // // //         JLabel deptLabel = new JLabel("Department:");
// // // //         GUIStyleHelper.styleRegisterLabel(deptLabel);

// // // //         MajorDAO majorDao = new MajorDAO(conn);
// // // //         List<String> depts;
// // // //         try {
// // // //             depts = majorDao.getAllDepts();
// // // //         } catch (SQLException e) {
// // // //             e.printStackTrace();
// // // //             depts = new java.util.ArrayList<>();
// // // //         }

// // // //         departmentDropdown = new JComboBox<>(depts.toArray(new String[0]));
// // // //         departmentDropdown.setMaximumSize(new Dimension(150, 30));

        
// // // //         inputPanel.add(deptLabel);
// // // //         inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
// // // //         inputPanel.add(departmentDropdown);

// // // //         add(Box.createRigidArea(new Dimension(35, 30)));
// // // //         add(inputPanel);
// // // //     }
// // // // }

// // // package interface_.Registrar.panels;

// // // import utils.*;
// // // import dao.*;
// // // import models.*;

// // // import javax.swing.*;
// // // import javax.swing.table.DefaultTableModel;
// // // import java.awt.*;
// // // import java.sql.Connection;
// // // import java.sql.SQLException;
// // // import java.util.List;

// // // // public class SchedulePanel extends JPanel {
// // // //     private final Connection conn;
// // // //     private JComboBox<String> departmentDropdown;
// // // //     private JPanel resultsPanel;

// // // //     public SchedulePanel(Connection conn) {
// // // //         this.conn = conn;
// // // //         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
// // // //         GUIStyleHelper.stylePages(this);

// // // //         buildSearchForm(); // show initial view with dropdown + results
// // // //     }

// // // //     private void buildSearchForm() {
// // // //         removeAll();

// // // //         // === WRAPPER for left-aligned inputPanel ===
// // // //         JPanel inputWrapper = new JPanel();
// // // //         inputWrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
// // // //         inputWrapper.setOpaque(false);

// // // //         JPanel inputPanel = new JPanel();
// // // //         inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
// // // //         inputPanel.setOpaque(false);

// // // //         JLabel deptLabel = new JLabel("Department:");
// // // //         GUIStyleHelper.styleRegisterLabel(deptLabel);

// // // //         MajorDAO majorDao = new MajorDAO(conn);
// // // //         List<String> depts;
// // // //         try {
// // // //             depts = majorDao.getAllDepts();
// // // //         } catch (SQLException e) {
// // // //             e.printStackTrace();
// // // //             depts = new java.util.ArrayList<>();
// // // //         }

// // // //         departmentDropdown = new JComboBox<>(depts.toArray(new String[0]));
// // // //         departmentDropdown.setMaximumSize(new Dimension(150, 30));
// // // //         departmentDropdown.setSelectedItem("CS");

// // // //         departmentDropdown.addActionListener(e -> showCourseOfferings((String) departmentDropdown.getSelectedItem()));

// // // //         inputPanel.add(deptLabel);
// // // //         inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
// // // //         inputPanel.add(departmentDropdown);

// // // //         inputWrapper.add(Box.createRigidArea(new Dimension(35, 30)));
// // // //         inputWrapper.add(inputPanel);

// // // //         add(inputWrapper);

// // // //         // === CENTERED RESULTS PANEL ===
// // // //         resultsPanel = new JPanel();
// // // //         resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
// // // //         resultsPanel.setOpaque(false);

// // // //         JPanel centerWrapper = new JPanel();
// // // //         centerWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
// // // //         centerWrapper.setOpaque(false);
// // // //         centerWrapper.add(resultsPanel);

// // // //         add(Box.createRigidArea(new Dimension(0, 20)));
// // // //         add(centerWrapper);
// // // //         add(Box.createVerticalGlue());

// // // //         showCourseOfferings("CS");

// // // //         revalidate();
// // // //         repaint();
// // // //     }

// // // //     private void showCourseOfferings(String dept) {
// // // //         resultsPanel.removeAll();

// // // //         try {
// // // //             CourseDAO courseDAO = new CourseDAO(conn);
// // // //             TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
// // // //             Quarter curr = new Quarter("S", 25);

// // // //             List<String[]> offeredCourses = courseDAO.getOfferedCoursesByDept(dept, curr.getYear(), curr.getQuarterCode());

// // // //             String[] columnNames = {"Course"};
// // // //             DefaultTableModel model = new DefaultTableModel(columnNames, 0);

// // // //             for (String[] course : offeredCourses) {
// // // //                 String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
// // // //                                 "&emsp;Professor " + course[4] + "<br>" +
// // // //                                 "&emsp;" + course[5] + "<br>" +
// // // //                                 "&emsp;" + course[6] + "</html>";
// // // //                 model.addRow(new Object[]{formatted});
// // // //             }

// // // //             JTable table = new JTable(model) {
// // // //                 public boolean isCellEditable(int row, int column) {
// // // //                     return false;
// // // //                 }
// // // //             };

// // // //             GUIStyleHelper.styleTable(table);
// // // //             table.setTableHeader(null);

// // // //             JScrollPane scrollPane = new JScrollPane(table);
// // // //             scrollPane.setOpaque(false);
// // // //             scrollPane.getViewport().setOpaque(false);
// // // //             scrollPane.setBorder(BorderFactory.createEmptyBorder());

// // // //             // set fixed width, flexible height
// // // //             int preferredHeight = Math.min(table.getRowHeight() * table.getRowCount(), 400);
// // // //             scrollPane.setPreferredSize(new Dimension(800, preferredHeight));
// // // //             scrollPane.setMaximumSize(new Dimension(800, 500)); // don't let it grow vertically too much

// // // //             JLabel header = new JLabel("Offered Courses for " + dept);
// // // //             GUIStyleHelper.styleNavHeader(header);
// // // //             header.setAlignmentX(Component.CENTER_ALIGNMENT);

// // // //             JPanel scrollWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
// // // //             scrollWrapper.setOpaque(false);
// // // //             scrollWrapper.add(scrollPane);

// // // //             resultsPanel.add(header);
// // // //             resultsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
// // // //             resultsPanel.add(scrollWrapper);
// // // //         } catch (Exception e) {
// // // //             resultsPanel.add(new JLabel("Failed to load courses."));
// // // //             e.printStackTrace();
// // // //         }

// // // //         resultsPanel.revalidate();
// // // //         resultsPanel.repaint();
// // // //     }
// // // // }

// // // public class SchedulePanel extends JPanel {
// // //     public SchedulePanel(Connection conn) {
// // //         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
// // //         GUIStyleHelper.stylePages(this);

// // //         // JLabel quarterHeader;

// // //         // try {
// // //         //     TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
// // //         //     Quarter current = transcriptDAO.getCurrentQuarter(perm);
// // //         //     quarterHeader = new JLabel(current.getQuarterName() + " " + current.getYear());
// // //         // } catch (SQLException e) {
// // //         //     quarterHeader = new JLabel("Current Quarter: N/A");
// // //         //     e.printStackTrace();
// // //         // }

// // //         // GUIStyleHelper.styleNavHeader(quarterHeader);

// // //         // JPanel quarterHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
// // //         // quarterHeaderPanel.setOpaque(false); 
// // //         // quarterHeaderPanel.add(Box.createRigidArea(new Dimension(60, 0)));
// // //         // quarterHeaderPanel.add(quarterHeader);

// // //         JLabel scheduleHeader = new JLabel("My Class Schedule");
// // //         GUIStyleHelper.styleNavHeader(scheduleHeader);
// // //         scheduleHeader.setFont(new Font("Serif", Font.BOLD, 26));
        
// // //         JPanel scheduleHeaderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
// // //         scheduleHeaderPanel.setOpaque(false);
// // //         scheduleHeaderPanel.add(scheduleHeader);

// // //         // quarterHeaderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
// // //         scheduleHeaderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

// // //         // add(Box.createRigidArea(new Dimension(0, 20)));
// // //         // add(quarterHeaderPanel);
// // //         add(Box.createRigidArea(new Dimension(0, 10)));
// // //         add(scheduleHeaderPanel);

// // //         EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);

// // //         try {
// // //             String[] columnNames = {"Course", "Action"};
// // //             DefaultTableModel model = new DefaultTableModel(columnNames, 0);

// // //             List<String[]> courseDetails = enrollmentDAO.get(perm);
// // //             for (String[] course : courseDetails) {
// // //                 String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
// // //                                     "&emsp;Professor " + course[4] + "<br>" +
// // //                                     "&emsp;" + course[5] + "<br>" +
// // //                                     "&emsp;" + course[6] + "</html>";
// // //                 model.addRow(new Object[]{formatted, "Drop"});
// // //             }

// // //             JTable table = new JTable(model) {
// // //                 public boolean isCellEditable(int row, int column) {
// // //                     return column == 1;
// // //                 }
// // //             };
// // //             GUIStyleHelper.styleTable(table);
// // //             table.setTableHeader(null);

// // //             table.getColumn("Action").setCellRenderer(new ButtonRenderer("Drop"));
// // //             // table.getColumn("Action").setCellEditor(
// // //             //     new EnrollButtonEditor(new JCheckBox(), courseDetails, enrollmentDAO, perm, table, conn)
// // //             // );
// // //             table.getColumn("Action").setMinWidth(200);
// // //             table.getColumn("Action").setMaxWidth(200);
// // //             table.getColumn("Action").setPreferredWidth(200);

// // //             // wrap the table in a scroll pane
// // //             JScrollPane scrollPane = new JScrollPane(table);
// // //             scrollPane.setOpaque(false);
// // //             scrollPane.getViewport().setOpaque(false);
// // //             scrollPane.setBorder(BorderFactory.createEmptyBorder());

// // //             int rowHeight = table.getRowHeight();
// // //             int rowCount = table.getRowCount();
// // //             int totalHeight = rowHeight * rowCount;

// // //             scrollPane.setMaximumSize(new Dimension(1100, totalHeight));

// // //             add(Box.createRigidArea(new Dimension(0, 10)));
// // //             add(scrollPane);

// // //         } catch (Exception e) {
// // //             add(new JLabel("Failed to load courses."));
// // //             e.printStackTrace();
// // //         }
// // //     }
// // // }

// // package interface_.Registrar.panels;

// // import utils.*;
// // import dao.*;
// // import models.*;

// // import javax.swing.*;
// // import javax.swing.table.DefaultTableModel;
// // import java.awt.*;
// // import java.sql.Connection;
// // import java.sql.SQLException;
// // import java.util.List;

// // public class SchedulePanel extends JPanel {
// //     private final Connection conn;

// //     public SchedulePanel(Connection conn) {
// //         this.conn = conn;
// //         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
// //         GUIStyleHelper.stylePages(this);

// //         // title/header
// //         JLabel scheduleHeader = new JLabel("All Offered Courses");
// //         GUIStyleHelper.styleNavHeader(scheduleHeader);
// //         scheduleHeader.setFont(new Font("Serif", Font.BOLD, 26));

// //         JPanel scheduleHeaderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
// //         scheduleHeaderPanel.setOpaque(false);
// //         scheduleHeaderPanel.add(scheduleHeader);
// //         scheduleHeaderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

// //         add(Box.createRigidArea(new Dimension(0, 10)));
// //         add(scheduleHeaderPanel);

// //         try {
// //             // load all course offerings
// //             TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
// //             Quarter current = new Quarter("S", 25); 
// //             CourseDAO courseDAO = new CourseDAO(conn);
// //             String dept = "CS";
// //             List<String[]> courseDetails = courseDAO.getOfferedCoursesByDept(dept, current.getYear(), current.getQuarterCode());

// //             // table setup
// //             String[] columnNames = {"Course"};
// //             DefaultTableModel model = new DefaultTableModel(columnNames, 0);

// //             for (String[] course : courseDetails) {
// //                 String formatted = "<html><b><span style='font-size:24pt'>" + course[0] + " — " + course[1] + "</span></b><br>" +
// //                         "&emsp;Professor " + course[4] + "<br>" +
// //                         "&emsp;" + course[5] + "<br>" +
// //                         "&emsp;" + course[6] + "</html>";
// //                 model.addRow(new Object[]{formatted});
// //             }

// //             JTable table = new JTable(model) {
// //                 public boolean isCellEditable(int row, int column) {
// //                     return false;
// //                 }
// //             };

// //             GUIStyleHelper.styleTable(table);
// //             table.setTableHeader(null);

// //             JScrollPane scrollPane = new JScrollPane(table);
// //             scrollPane.setOpaque(false);
// //             scrollPane.getViewport().setOpaque(false);
// //             scrollPane.setBorder(BorderFactory.createEmptyBorder());

// //             int rowHeight = table.getRowHeight();
// //             int rowCount = table.getRowCount();
// //             int totalHeight = rowHeight * rowCount;
// //             scrollPane.setMaximumSize(new Dimension(1100, totalHeight));

// //             // center the scroll pane horizontally
// //             JPanel scrollWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
// //             scrollWrapper.setOpaque(false);
// //             scrollWrapper.add(scrollPane);

// //             add(Box.createRigidArea(new Dimension(0, 10)));
// //             add(scrollWrapper);

// //         } catch (Exception e) {
// //             add(new JLabel("Failed to load course offerings."));
// //             e.printStackTrace();
// //         }
// //     }
// // }

// package interface_.Registrar.panels;

// import utils.*;
// import dao.*;
// import models.*;

// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.List;

// public class SchedulePanel extends JPanel {
//     private final Connection conn;
//     private JComboBox<String> departmentDropdown;
//     private JPanel resultsPanel;

//     public SchedulePanel(Connection conn) {
//         this.conn = conn;
//         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//         GUIStyleHelper.stylePages(this);

//         buildSearchForm(); // default view
//     }

//     private void buildSearchForm() {
//         removeAll();

//         JPanel inputPanel = new JPanel();
//         inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
//         inputPanel.setOpaque(false);
//         inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

//         JLabel deptLabel = new JLabel("Department:");
//         GUIStyleHelper.styleRegisterLabel(deptLabel);

//         MajorDAO majorDao = new MajorDAO(conn);
//         List<String> depts;
//         try {
//             depts = majorDao.getAllDepts();
//         } catch (SQLException e) {
//             e.printStackTrace();
//             depts = new java.util.ArrayList<>();
//         }

//         departmentDropdown = new JComboBox<>(depts.toArray(new String[0]));
//         departmentDropdown.setMaximumSize(new Dimension(150, 30));
//         departmentDropdown.setSelectedItem("CS");
//         departmentDropdown.addActionListener(e -> showCourseOfferings((String) departmentDropdown.getSelectedItem()));

//         inputPanel.add(Box.createRigidArea(new Dimension(35, 30)));
//         inputPanel.add(deptLabel);
//         inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
//         inputPanel.add(departmentDropdown);

//         add(inputPanel);

//         resultsPanel = new JPanel();
//         resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
//         resultsPanel.setOpaque(false);
//         add(Box.createRigidArea(new Dimension(0, 20)));
//         add(resultsPanel);

//         showCourseOfferings("CS");

//         revalidate();
//         repaint();
//     }

//     private void showCourseOfferings(String dept) {
//         resultsPanel.removeAll();

//         try {
//             CourseDAO courseDAO = new CourseDAO(conn);
//             Quarter curr = new Quarter("S", 25); // temp value

//             List<String[]> offeredCourses = courseDAO.getOfferedCoursesByDept(dept, curr.getYear(), curr.getQuarterCode());

//             String[] columnNames = {"Course", "Add", "Drop", "Roster"};
//             DefaultTableModel model = new DefaultTableModel(columnNames, 0);

//             for (String[] course : offeredCourses) {
//                 String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
//                                    "&emsp;Professor " + course[4] + "<br>" +
//                                    "&emsp;" + course[5] + "<br>" +
//                                    "&emsp;" + course[6] + "</html>";
//                 model.addRow(new Object[]{formatted, "Add", "Drop", "Roster"});
//             }

//             JTable table = new JTable(model) {
//                 public boolean isCellEditable(int row, int column) {
//                     return column >= 1; // only action buttons are editable
//                 }
//             };

//             GUIStyleHelper.styleTable(table);
//             table.setTableHeader(null);

//             // Button renderers for each action
//             table.getColumn("Add").setCellRenderer(new ButtonRenderer("Add"));
//             table.getColumn("Drop").setCellRenderer(new ButtonRenderer("Drop"));
//             table.getColumn("Roster").setCellRenderer(new ButtonRenderer("Roster"));
            
//             table.getColumn("Add").setMinWidth(125);
//             table.getColumn("Add").setMaxWidth(125);
//             table.getColumn("Add").setPreferredWidth(125);
//             table.getColumn("Drop").setMinWidth(125);
//             table.getColumn("Drop").setMaxWidth(125);
//             table.getColumn("Drop").setPreferredWidth(125);
//             table.getColumn("Roster").setMinWidth(125);
//             table.getColumn("Roster").setMaxWidth(125);
//             table.getColumn("Roster").setPreferredWidth(125);

//             // TODO: You can set cell editors if you want buttons to be clickable
//             // table.getColumn("Add").setCellEditor(...);
//             // table.getColumn("Drop").setCellEditor(...);
//             // table.getColumn("Roster").setCellEditor(...);

//             JScrollPane scrollPane = new JScrollPane(table);
//             scrollPane.setOpaque(false);
//             scrollPane.getViewport().setOpaque(false);
//             scrollPane.setBorder(BorderFactory.createEmptyBorder());
//             scrollPane.setMaximumSize(new Dimension(1100, table.getRowHeight() * table.getRowCount()));

//             JLabel header = new JLabel("Offered Courses for " + dept);
//             GUIStyleHelper.styleNavHeader(header);
//             header.setAlignmentX(Component.CENTER_ALIGNMENT);

//             resultsPanel.add(header);
//             resultsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//             resultsPanel.add(scrollPane);
//         } catch (Exception e) {
//             resultsPanel.add(new JLabel("Failed to load courses."));
//             e.printStackTrace();
//         }

//         resultsPanel.revalidate();
//         resultsPanel.repaint();
//     }
// }

package interface_.Registrar.panels;

import utils.*;
import dao.*;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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