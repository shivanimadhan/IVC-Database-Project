package interface_.Gold.panels;

import utils.*;
import models.*;
import dao.TranscriptDAO;
import dao.EnrollmentDAO;
import interface_.Gold.GoldHomeGUI;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.GridBagLayout;
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
            String[] columnNames = {"Course", "Action"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<String[]> courseDetails = enrollmentDAO.getCurrentCourses(perm);
            String[][] data = new String[courseDetails.size()][1];
            for (int i = 0; i < courseDetails.size(); i++) {
                String[] course = courseDetails.get(i);
                String formatted = "<html><b><span style='font-size:24pt'>&nbsp;&nbsp;&nbsp;" + course[0] + " — " + course[1] + "</span></b><br>" +
                                    "&emsp;Professor " + course[2] + "<br>" +
                                    "&emsp;" + course[3] + "<br>" +
                                    "&emsp;" + course[4] + "</html>";
                model.addRow(new Object[]{formatted, "Drop"});
            }

            JTable table = new JTable(model) {
                public boolean isCellEditable(int row, int column) {
                    return column == 1; // only "Drop" button is editable
                }
            };
            GUIStyleHelper.styleTable(table);

            table.getColumn("Action").setCellRenderer(new ButtonRenderer());
            table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), courseDetails, enrollmentDAO, perm, table, conn));
            table.getColumn("Action").setMinWidth(200);
            table.getColumn("Action").setMaxWidth(200);
            table.getColumn("Action").setPreferredWidth(200);

            JPanel tableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tableWrapper.setOpaque(false);
            tableWrapper.add(table);

            add(Box.createRigidArea(new Dimension(0, 10)));
            add(tableWrapper);
        } catch (Exception e) {
            add(new JLabel("Failed to load courses."));
            e.printStackTrace();
        }
    }
}

class ButtonRenderer extends JPanel implements TableCellRenderer {
    private final JButton button;

    public ButtonRenderer() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        button = new JButton("Drop");
        GUIStyleHelper.styleSmallButton(button); 
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        add(button);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final JPanel panel;
    private boolean clicked;
    private final List<String[]> courseDetails;
    private final EnrollmentDAO dao;
    private final String perm;
    private final JTable table;
    private final Connection conn;

    public ButtonEditor(JCheckBox checkBox, List<String[]> courseDetails, EnrollmentDAO dao, String perm, JTable table, Connection conn) {
        super(checkBox);
        this.courseDetails = courseDetails;
        this.dao = dao;
        this.perm = perm;
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

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        clicked = true;
        return panel;
    }

    public Object getCellEditorValue() {
        if (clicked) {
            String courseNo = courseDetails.get(table.getSelectedRow())[0];
            int enrollCode = Integer.parseInt(courseDetails.get(table.getSelectedRow())[5]);

            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to drop " + courseNo + "?",
                    "Confirm Drop",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                try {
                    dao.dropCourse(perm, enrollCode);
                    JOptionPane.showMessageDialog(null, "Dropped " + courseNo);

                    SwingUtilities.getWindowAncestor(button).dispose(); 
                    new GoldHomeGUI(perm, conn).setVisible(true); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(button, "Failed to drop course.");
                }
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