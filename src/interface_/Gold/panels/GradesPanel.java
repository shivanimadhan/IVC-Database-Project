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
    private final JScrollPane scrollPane;

    public GradesPanel(String perm, Connection conn) {
        this.transcriptDAO = new TranscriptDAO(conn);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        // === DROPDOWN (TRUE LEFT-ALIGNED) ===
        JPanel dropdownRow = new JPanel();
        dropdownRow.setLayout(new BoxLayout(dropdownRow, BoxLayout.X_AXIS));
        dropdownRow.setOpaque(false);
        dropdownRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel quarterLabel = new JLabel("Quarter:");
        GUIStyleHelper.styleLabel(quarterLabel);

        quarterDropdown = new JComboBox<>();
        quarterDropdown.setPreferredSize(new Dimension(150, 30));
        quarterDropdown.setMaximumSize(new Dimension(150, 30));

        dropdownRow.add(Box.createRigidArea(new Dimension(60, 0))); // left margin
        dropdownRow.add(quarterLabel);
        dropdownRow.add(Box.createRigidArea(new Dimension(10, 0))); // gap
        dropdownRow.add(quarterDropdown);

        // === TABLE ===
        String[] columnNames = {"Course No", "Course Title", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        gradesTable = new JTable(tableModel);
        GUIStyleHelper.styleGradesTable(gradesTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < gradesTable.getColumnCount(); i++) {
            gradesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = gradesTable.getTableHeader();
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        scrollPane = new JScrollPane(gradesTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT); // this one is centered and should be

        // === ADD COMPONENTS ===
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(dropdownRow); // LEFT aligned now
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scrollPane);

        // === LOAD DATA ===
        try {
            List<Quarter> quarters = transcriptDAO.getAllQuartersTaken(perm);
            for (Quarter q : quarters) quarterDropdown.addItem(q);

            if (!quarters.isEmpty()) {
                Quarter first = quarters.get(0);
                quarterDropdown.setSelectedItem(first);
                populateTable(perm, first.getQuarterCode(), first.getYear());
            }

            quarterDropdown.addActionListener(e -> {
                Quarter selected = (Quarter) quarterDropdown.getSelectedItem();
                if (selected != null) populateTable(perm, selected.getQuarterCode(), selected.getYear());
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load grades.");
        }
    }

    private void populateTable(String perm, String quarter, int year) {
        try {
            List<String[]> grades = transcriptDAO.getGradesForQuarter(perm, year, quarter);
            tableModel.setRowCount(0);
            for (String[] row : grades) tableModel.addRow(row);

            int rowHeight = gradesTable.getRowHeight();
            int rowCount = gradesTable.getRowCount();
            int headerHeight = gradesTable.getTableHeader().getPreferredSize().height;
            int totalHeight = rowHeight * rowCount + headerHeight;

            Dimension newSize = new Dimension(1000, totalHeight);
            gradesTable.setPreferredScrollableViewportSize(newSize);
            scrollPane.setPreferredSize(newSize);
            scrollPane.setMaximumSize(newSize);
            scrollPane.setMinimumSize(newSize);

            scrollPane.revalidate();
            scrollPane.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}