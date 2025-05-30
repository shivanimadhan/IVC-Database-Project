package interface_.Registrar.panels;

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

public class ViewGradesPanel extends JPanel {
    private final TranscriptDAO transcriptDAO;
    private final JComboBox<Quarter> quarterDropdown;
    private final JTable gradesTable;
    private final DefaultTableModel tableModel;
    private final JTextField permField;
    private final JButton loadButton;
    private final JScrollPane scrollPane;
    private final JPanel tableWrapper;

    public ViewGradesPanel(Connection conn) {
        this.transcriptDAO = new TranscriptDAO(conn);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setOpaque(false);

        JLabel permLabel = new JLabel("Perm Number:");
        GUIStyleHelper.styleLabel(permLabel);
        inputPanel.add(permLabel);

        permField = new JTextField(10);
        inputPanel.add(permField);

        JLabel quarterLabel = new JLabel("Quarter:");
        GUIStyleHelper.styleLabel(quarterLabel);
        inputPanel.add(quarterLabel);

        quarterDropdown = new JComboBox<>();
        quarterDropdown.setPreferredSize(new Dimension(150, 30));
        inputPanel.add(quarterDropdown);

        loadButton = new JButton("Load Grades");
        GUIStyleHelper.stylePinButton(loadButton);
        inputPanel.add(loadButton);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(inputPanel);

        // Table setup
        String[] columnNames = {"Course No", "Course Title", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        gradesTable = new JTable(tableModel);
        GUIStyleHelper.styleGradesTable(gradesTable);
        gradesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        gradesTable.getTableHeader().setReorderingAllowed(false);
        gradesTable.getTableHeader().setResizingAllowed(false);

        // Center align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < gradesTable.getColumnCount(); i++) {
            gradesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = gradesTable.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        gradesTable.getColumnModel().getColumn(0).setPreferredWidth(150); // Course No
        gradesTable.getColumnModel().getColumn(1).setPreferredWidth(700); // Title
        gradesTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Grade

        scrollPane = new JScrollPane(gradesTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableWrapper.setOpaque(false);
        tableWrapper.add(scrollPane);
        tableWrapper.setVisible(false); // Hide initially

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(tableWrapper);

        // Field events
        permField.addActionListener(e -> fetchAndPopulateQuarters(permField.getText().trim()));
        permField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                fetchAndPopulateQuarters(permField.getText().trim());
            }
        });

        loadButton.addActionListener(e -> {
            String perm = permField.getText().trim();
            Quarter selected = (Quarter) quarterDropdown.getSelectedItem();
            if (selected != null && !perm.isEmpty()) {
                populateTable(perm, selected.getQuarterCode(), selected.getYear());
            }
        });
    }

    private void fetchAndPopulateQuarters(String perm) {
        quarterDropdown.removeAllItems();
        if (perm.isEmpty()) return;

        try {
            List<Quarter> quarters = transcriptDAO.getAllQuartersTaken(perm);
            for (Quarter q : quarters) {
                quarterDropdown.addItem(q);
            }
            if (!quarters.isEmpty()) {
                quarterDropdown.setSelectedItem(quarters.get(0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load quarters for " + perm);
        }
    }

    private void populateTable(String perm, String quarter, int year) {
        try {
            List<String[]> grades = transcriptDAO.getGradesForQuarter(perm, year, quarter);
            tableModel.setRowCount(0);
            for (String[] row : grades) {
                tableModel.addRow(row);
            }

            int rowHeight = gradesTable.getRowHeight();
            int rowCount = gradesTable.getRowCount();
            int tableHeight = rowHeight * rowCount;
            int headerHeight = gradesTable.getTableHeader().getPreferredSize().height;
            int fullHeight = tableHeight + headerHeight;

            gradesTable.setPreferredScrollableViewportSize(new Dimension(1000, tableHeight));
            scrollPane.setPreferredSize(new Dimension(1000, fullHeight));

            tableWrapper.setVisible(true);
            tableWrapper.revalidate();
            tableWrapper.repaint();
            gradesTable.revalidate();
            gradesTable.repaint();
            scrollPane.revalidate();
            scrollPane.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
