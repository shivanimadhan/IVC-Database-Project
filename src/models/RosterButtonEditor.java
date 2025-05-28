package models;

import dao.CourseDAO;
import utils.GUIStyleHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RosterButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final JPanel panel;
    private final JTable table;
    private final List<String[]> courseDetails;
    private final CourseDAO courseDAO;
    private final Connection conn;
    private boolean clicked;

    public RosterButtonEditor(JCheckBox checkBox, List<String[]> courseDetails, CourseDAO courseDAO, JTable table, Connection conn) {
        super(checkBox);
        this.courseDetails = courseDetails;
        this.courseDAO = courseDAO;
        this.table = table;
        this.conn = conn;

        button = new JButton("Roster");
        GUIStyleHelper.styleSmallButton(button);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());

        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.add(button);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        clicked = true;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            int selectedRow = table.getSelectedRow();
            String courseNo = courseDetails.get(selectedRow)[0];
            int year = Integer.parseInt(courseDetails.get(selectedRow)[2]);
            String quarter = courseDetails.get(selectedRow)[3];

            try {
                List<String> students = courseDAO.getEnrolledStudentNames(courseNo, year, quarter);
                showRosterPopup(courseNo, year, quarter, students);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to fetch student roster.");
            }
        }

        clicked = false;
        return "Roster";
    }

    private void showRosterPopup(String courseNo, int year, String quarter, List<String> students) {
        JDialog dialog = new JDialog((Frame) null, "Student Roster", true);
        dialog.setLayout(new BorderLayout());
        dialog.setUndecorated(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Roster for " + courseNo + " (" + quarter + " " + year + ")");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setOpaque(false);
        textArea.setBorder(null);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        if (students.isEmpty()) {
            textArea.setText("No students enrolled.");
        } else {
            StringBuilder text = new StringBuilder();
            for (String name : students) {
                text.append("• ").append(name).append("\n");
            }
            textArea.setText(text.toString());
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JButton closeButton = new JButton("Close");
        GUIStyleHelper.stylePinButton(closeButton);
        closeButton.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> dialog.dispose());

        mainPanel.add(titleLabel);
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(closeButton);

        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}