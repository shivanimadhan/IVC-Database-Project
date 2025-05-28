package interface_.Gold.panels;

import dao.TranscriptDAO;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.Quarter;
import utils.GUIStyleHelper;

public class GradesPanel extends JPanel {
    private final TranscriptDAO transcriptDAO;
    private final JComboBox<Quarter> quarterDropdown;
    private final JTable gradesTable;
    private final DefaultTableModel tableModel;

    public GradesPanel(String perm, Connection conn) {
        this.transcriptDAO = new TranscriptDAO(conn);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        // Dropdown Panel
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dropdownPanel.setOpaque(false);
        JLabel quarterLabel = new JLabel("Quarter:");
        GUIStyleHelper.styleLabel(quarterLabel);

        quarterDropdown = new JComboBox<>();
        quarterDropdown.setPreferredSize(new Dimension(150, 30));
        dropdownPanel.add(Box.createRigidArea(new Dimension(60, 0)));
        dropdownPanel.add(quarterLabel);
        dropdownPanel.add(quarterDropdown);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(dropdownPanel);

        // Table setup
        String[] columnNames = {"Course No", "Course Title", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        gradesTable = new JTable(tableModel);
        GUIStyleHelper.styleTable(gradesTable);
        gradesTable.getTableHeader().setReorderingAllowed(false);
        gradesTable.getTableHeader().setResizingAllowed(false);

        // Center align table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < gradesTable.getColumnCount(); i++) {
            gradesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = gradesTable.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(gradesTable);
        scrollPane.setPreferredSize(new Dimension(1000, 500));

        JPanel tableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableWrapper.setOpaque(false);
        tableWrapper.add(scrollPane);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(tableWrapper);

        // Populate dropdown and default table
        try {
            List<Quarter> quarters = transcriptDAO.getAllQuartersTaken(perm);
            for (Quarter q : quarters) {
                quarterDropdown.addItem(q);
            }

            if (!quarters.isEmpty()) {
                Quarter first = quarters.get(0);
                quarterDropdown.setSelectedItem(first);
                populateTable(perm, first.getQuarterCode(), first.getYear());
            }

            quarterDropdown.addActionListener(e -> {
                Quarter selected = (Quarter) quarterDropdown.getSelectedItem();
                if (selected != null) {
                    populateTable(perm, selected.getQuarterCode(), selected.getYear());
                }
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load grades.");
        }
    }

    private void populateTable(String perm, String quarter, int year) {
        try {
            List<String[]> grades = transcriptDAO.getGradesForQuarter(perm, year, quarter);
            tableModel.setRowCount(0); // Clear table
            for (String[] row : grades) {
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
