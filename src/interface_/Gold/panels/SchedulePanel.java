package interface_.Gold.panels;

import utils.*;
import models.*;
import dao.TranscriptDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class SchedulePanel extends JPanel {
    public SchedulePanel(String perm, Connection conn) {
        GUIStyleHelper.stylePages(this);

        JLabel header;

        try {
            TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
            Quarter current = transcriptDAO.getCurrentQuarter(perm);
            header = new JLabel(current.getQuarterName() + " " + current.getYear());
        } catch (SQLException e) {
            header = new JLabel("Current Quarter: N/A");
            e.printStackTrace();
        }

        GUIStyleHelper.styleNavHeader(header);
        add(Box.createRigidArea(new Dimension(12, 30)));
        add(header);
    }
}