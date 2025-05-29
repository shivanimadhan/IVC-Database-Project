package interface_.Gold.panels;

import dao.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.CoursePlanner;
import utils.GUIStyleHelper;

public class ProgressPanel extends JPanel {
    private final JComboBox<String> deptDropdown;
    private final JLabel electivesLabel;
    private final DefaultTableModel completedModel;
    private final DefaultTableModel remainingModel;
    private final JTable completedTable;
    private final JTable remainingTable;

    private final DefaultTableModel planModel;
    private final JTable planTable;
    private final JPanel planWrapper;
    
    private final Connection conn;

    private final StudiesDAO studiesDAO;
    private final TranscriptDAO transcriptDAO;
    private final CourseCategoryDAO categoryDAO;
    private final MajorDAO majorDAO;
    private final CourseDAO courseDAO;

    public ProgressPanel(String perm, Connection conn) {
        this.conn = conn;
        this.studiesDAO = new StudiesDAO(conn);
        this.transcriptDAO = new TranscriptDAO(conn);
        this.categoryDAO = new CourseCategoryDAO(conn);
        this.majorDAO = new MajorDAO(conn);
        this.courseDAO = new CourseDAO(conn);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        // Top: Requirement check
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(new JLabel("Requirement Check:"));

        deptDropdown = new JComboBox<>();
        try {
            for (String dept : majorDAO.getAllDepts()) {
                deptDropdown.addItem(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        topPanel.add(deptDropdown);

        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(e -> loadProgress(perm));
        topPanel.add(checkButton);

        add(topPanel);

        // Completed courses
        JLabel completedLabel = new JLabel("Completed Major Classes");
        completedLabel.setFont(new Font("Serif", Font.BOLD, 25));
        GUIStyleHelper.styleNavHeader(completedLabel);
        add(completedLabel);

        completedModel = new DefaultTableModel(new String[]{"Course No", "Title", "Grade"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
            @Override public int getRowCount() { return super.getRowCount() > 0 ? super.getRowCount() : 0; }
        };
        completedTable = new JTable(completedModel);
        GUIStyleHelper.styleTable(completedTable);
        centerTableText(completedTable);
        JScrollPane completedScroll = new JScrollPane(completedTable);
        completedTable.getTableHeader().setReorderingAllowed(false);

        JPanel completedWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        completedWrapper.setOpaque(false);
        completedWrapper.add(completedScroll);
        add(completedWrapper);


        // Remaining courses
        JLabel remainingLabel = new JLabel("Remaining Major Classes");
        remainingLabel.setFont(new Font("Serif", Font.BOLD, 25));
        GUIStyleHelper.styleNavHeader(remainingLabel);
        completedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        remainingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(remainingLabel);

        remainingModel = new DefaultTableModel(new String[]{"Course No", "Title"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
            @Override public int getRowCount() { return super.getRowCount() > 0 ? super.getRowCount() : 0; }
        };
        remainingTable = new JTable(remainingModel);
        remainingTable.getTableHeader().setReorderingAllowed(false);
        GUIStyleHelper.styleTable(remainingTable);
        centerTableText(remainingTable);
        JScrollPane remainingScroll = new JScrollPane(remainingTable);
    
        JPanel remainingWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        remainingWrapper.setOpaque(false);
        remainingWrapper.add(remainingScroll);
        add(remainingWrapper);

        // Electives
        electivesLabel = new JLabel("Electives Left: ");
        electivesLabel.setFont(new Font("Serif", Font.BOLD, 20));
        electivesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(electivesLabel);

        // Add Plan button
        JButton planButton = new JButton("Make Course Plan");
        GUIStyleHelper.stylePinButton(planButton);
        planButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(planButton);

        // Initialize plan table model and table
        planModel = new DefaultTableModel(new String[]{"Course No", "Title", "Type", "Quarter"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
            @Override public int getRowCount() { return super.getRowCount() > 0 ? super.getRowCount() : 0; }
        };

        planTable = new JTable(planModel);
        GUIStyleHelper.styleTable(planTable);
        centerTableText(planTable);
        JScrollPane planScroll = new JScrollPane(planTable);
        planTable.getTableHeader().setReorderingAllowed(false);

        planWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        planWrapper.setOpaque(false);
        planWrapper.add(planScroll);
        planWrapper.setVisible(false);
        add(planWrapper);

        // Action to load and show the plan
        planButton.addActionListener(e -> {
            loadPlan(perm);
            planWrapper.setVisible(true);
            planWrapper.revalidate();
            planWrapper.repaint();
        });


        loadProgress(perm); // Initial load
    }

    private void centerTableText(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader header = table.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void loadProgress(String perm) {
        try {
            completedModel.setRowCount(0);
            remainingModel.setRowCount(0);

            List<String[]> transcript = transcriptDAO.getAllTranscriptRecords(perm);
            for (String[] row : transcript) {
                String courseNo = row[2];
                if (categoryDAO.isMandatory(courseNo)) {
                    completedModel.addRow(new String[]{courseNo, row[3], row[4]});
                }
            }

            List<String> missing = studiesDAO.getMissingRequiredCourses(perm);
            for (String line : missing) {
                String[] split = line.split(" \\(");
                if (split.length == 2) {
                    String title = split[0];
                    String courseNo = split[1].replace(")", "");
                    remainingModel.addRow(new String[]{courseNo, title});
                }
            }

            int electives = studiesDAO.getRemainingElectives(perm);
            electivesLabel.setText("Electives Left: " + electives);

            adjustTableHeight(completedTable);
            adjustTableHeight(remainingTable);
            adjustTableHeight(planTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adjustTableHeight(JTable table) {
        int rowHeight = table.getRowHeight();
        int rowCount = table.getRowCount();
        int totalHeight = rowHeight * rowCount;

        table.setPreferredScrollableViewportSize(new Dimension(900, totalHeight));
        table.revalidate();
        table.repaint();
        if (table.getParent() != null) {
            table.getParent().revalidate();  // JScrollPane viewport
            table.getParent().repaint();
        }
    }
    
    private void loadPlan(String perm) {
        try {
            planModel.setRowCount(0);
            CoursePlanner planner = new CoursePlanner(conn);
            List<String[]> plan = planner.generatePlan(perm);

            for (String[] row : plan) {
                planModel.addRow(row);
            }

            adjustTableHeight(planTable);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to generate course plan.");
        }
    }
}