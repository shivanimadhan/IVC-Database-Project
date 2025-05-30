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
import utils.GUIStyleHelper;

public class TranscriptPanel extends JPanel {
    private final Connection conn;
    private final JTextField permField;
    private final DefaultTableModel tableModel;
    private final JTable transcriptTable;
    private final JScrollPane scrollPane;
    private final JPanel tableWrapper;

    public TranscriptPanel(Connection conn) {
        this.conn = conn;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        JLabel header = new JLabel("Generate Transcript");
        header.setFont(new Font("Serif", Font.BOLD, 26));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.setOpaque(false);

        JLabel permLabel = new JLabel("Enter Perm Number:");
        GUIStyleHelper.styleLabel(permLabel);
        inputPanel.add(permLabel);

        permField = new JTextField(10);
        inputPanel.add(permField);

        JButton loadButton = new JButton("Generate");
        GUIStyleHelper.stylePinButton(loadButton);
        inputPanel.add(loadButton);

        JButton exportButton = new JButton("Export PDF");
        GUIStyleHelper.stylePinButton(exportButton);
        inputPanel.add(exportButton);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(header);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(inputPanel);

        String[] columnNames = {"Quarter", "Year", "Course No", "Title", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        transcriptTable = new JTable(tableModel);
        GUIStyleHelper.styleGradesTable(transcriptTable);
        transcriptTable.getTableHeader().setReorderingAllowed(false);
        transcriptTable.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < transcriptTable.getColumnCount(); i++) {
            transcriptTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader headerComponent = transcriptTable.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) headerComponent.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        scrollPane = new JScrollPane(transcriptTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVisible(false); // start hidden

        tableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableWrapper.setOpaque(false);
        tableWrapper.add(scrollPane);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(tableWrapper);

        loadButton.addActionListener(e -> loadTranscript());
        exportButton.addActionListener(e -> exportToPDF());
    }

    private void loadTranscript() {
        tableModel.setRowCount(0);
        String perm = permField.getText().trim();
        if (perm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a perm number.");
            return;
        }

        try {
            TranscriptDAO dao = new TranscriptDAO(conn);
            List<String[]> transcriptEntries = dao.getAllTranscriptRecords(perm);

            for (String[] row : transcriptEntries) {
                tableModel.addRow(row);
            }

            if (transcriptEntries.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No transcript records found for perm " + perm);
            } else {
                int rowHeight = transcriptTable.getRowHeight();
                int rowCount = transcriptTable.getRowCount();
                int totalHeight = rowHeight * rowCount + transcriptTable.getTableHeader().getPreferredSize().height;
                transcriptTable.setPreferredScrollableViewportSize(new Dimension(1100, totalHeight));
                scrollPane.setVisible(true);
            }

            revalidate();
            repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading transcript: " + ex.getMessage());
        }
    }

    private void exportToPDF() {
        JOptionPane.showMessageDialog(this, "PDF export not implemented yet.");
    }
}
