package interface_.Gold.panels;

import utils.*;
import models.*;
import dao.TranscriptDAO;
import dao.EnrollmentDAO;
import interface_.Gold.GoldHomeGUI;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class SchedulePanel extends JPanel {
    public SchedulePanel(String perm, Connection conn) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        JLabel quarterHeader;

        try {
            TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
            Quarter current = transcriptDAO.getCurrentQuarter(perm);
            quarterHeader = new JLabel(current.getQuarterName() + " " + current.getYear());
        } catch (SQLException e) {
            quarterHeader = new JLabel("Current Quarter: N/A");
            e.printStackTrace();
        }

        GUIStyleHelper.styleNavHeader(quarterHeader);

        JPanel quarterHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quarterHeaderPanel.setOpaque(false); 
        quarterHeaderPanel.add(Box.createRigidArea(new Dimension(60, 0)));
        quarterHeaderPanel.add(quarterHeader);

        JLabel scheduleHeader = new JLabel("My Class Schedule");
        GUIStyleHelper.styleNavHeader(scheduleHeader);
        scheduleHeader.setFont(new Font("Serif", Font.BOLD, 26));
        
        JPanel scheduleHeaderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scheduleHeaderPanel.setOpaque(false);
        scheduleHeaderPanel.add(scheduleHeader);

        quarterHeaderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        scheduleHeaderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(quarterHeaderPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scheduleHeaderPanel);

        EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);

        try {
            String[] columnNames = {"Course", "Action"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<String[]> courseDetails = enrollmentDAO.getCurrentCourses(perm);
            for (String[] course : courseDetails) {
                String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
                                    "&emsp;Professor " + course[4] + "<br>" +
                                    "&emsp;" + course[5] + "<br>" +
                                    "&emsp;" + course[6] + "</html>";
                model.addRow(new Object[]{formatted, "Drop"});
            }

            JTable table = new JTable(model) {
                public boolean isCellEditable(int row, int column) {
                    return column == 1;
                }
            };
            GUIStyleHelper.styleTable(table);
            table.setTableHeader(null);

            table.getColumn("Action").setCellRenderer(new ButtonRenderer("Drop"));
            table.getColumn("Action").setCellEditor(
                new EnrollButtonEditor(new JCheckBox(), courseDetails, enrollmentDAO, perm, table, conn)
            );
            table.getColumn("Action").setMinWidth(200);
            table.getColumn("Action").setMaxWidth(200);
            table.getColumn("Action").setPreferredWidth(200);

            // wrap the table in a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            int rowHeight = table.getRowHeight();
            int rowCount = table.getRowCount();
            int totalHeight = rowHeight * rowCount;

            scrollPane.setMaximumSize(new Dimension(1100, totalHeight));

            add(Box.createRigidArea(new Dimension(0, 10)));
            add(scrollPane);

        } catch (Exception e) {
            add(new JLabel("Failed to load courses."));
            e.printStackTrace();
        }
    }
}