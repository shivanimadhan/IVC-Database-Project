package interface_.Gold.panels;

import utils.*;
import models.*;
import dao.TranscriptDAO;
import dao.EnrollmentDAO;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
            List<String[]> courseDetails = enrollmentDAO.getCurrentCourses(perm);

            String[][] data = new String[courseDetails.size()][1];
            for (int i = 0; i < courseDetails.size(); i++) {
                String[] course = courseDetails.get(i);
                String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
                                    "&emsp;Professor " + course[2] + "<br>" +
                                    "&emsp;" + course[3] + "<br>" +
                                    "&emsp;" + course[4] + "</html>";
                data[i][0] = formatted;
            }

            String[] columnNames = {"Course"};

            JTable table = new JTable(new DefaultTableModel(data, columnNames));
            GUIStyleHelper.styleTable(table);

            JPanel tableWrapper = new JPanel();
            tableWrapper.setOpaque(false); 
            tableWrapper.setLayout(new FlowLayout(FlowLayout.CENTER)); // centers the table
            tableWrapper.add(table);
            
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(tableWrapper);
        } catch (Exception e) {
            add(new JLabel("Failed to load courses."));
            e.printStackTrace();
        }
    }
}