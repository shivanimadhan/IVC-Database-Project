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
    private final JLabel requiredMetLabel;
    private final JLabel requiredInProgressLabel;
    private final JLabel requiredRemainingLabel;
    private final JLabel electivesMetLabel;
    private final JLabel electivesInProgressLabel;
    private final JLabel electivesLabel;
    private final DefaultTableModel completedModel;
    private final DefaultTableModel remainingModel;
    private final DefaultTableModel electivesModel;
    private final DefaultTableModel currentModel;
    private final JTable completedTable;
    private final JTable remainingTable;
    private final JTable electivesTable;
    private final JTable currentTable;
    private final DefaultTableModel planModel;
    private final JTable planTable;
    private final JPanel planWrapper;

    private final Connection conn;
    private final StudiesDAO studiesDAO;
    private final TranscriptDAO transcriptDAO;
    private final CourseCategoryDAO categoryDAO;
    private final MajorDAO majorDAO;
    private final CourseDAO courseDAO;
    private final EnrollmentDAO enrollmentDAO;

    public ProgressPanel(String perm, Connection conn) {
        this.conn = conn;
        this.studiesDAO = new StudiesDAO(conn);
        this.transcriptDAO = new TranscriptDAO(conn);
        this.categoryDAO = new CourseCategoryDAO(conn);
        this.majorDAO = new MajorDAO(conn);
        this.courseDAO = new CourseDAO(conn);
        this.enrollmentDAO = new EnrollmentDAO(conn);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

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

        requiredMetLabel = new JLabel("Required Met: ");
        requiredInProgressLabel = new JLabel("Required in Progress: ");
        requiredRemainingLabel = new JLabel("Remaining Required: ");
        electivesMetLabel = new JLabel("Electives Met: ");
        electivesInProgressLabel = new JLabel("Electives in Progress: ");
        electivesLabel = new JLabel("Remaining Electives: ");

        JPanel summaryPanel = new JPanel(new GridLayout(2, 3, 15, 5));
        summaryPanel.setOpaque(false);
        for (JLabel label : List.of(requiredMetLabel, requiredInProgressLabel, requiredRemainingLabel,
                                     electivesMetLabel, electivesInProgressLabel, electivesLabel)) {
            label.setFont(new Font("Serif", Font.BOLD, 18));
            summaryPanel.add(label);
        }
        add(summaryPanel);

        JLabel completedLabel = new JLabel("Completed Major Classes");
        completedLabel.setFont(new Font("Serif", Font.BOLD, 25));
        GUIStyleHelper.styleNavHeader(completedLabel);
        completedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(completedLabel);

        completedModel = new DefaultTableModel(new String[]{"Course No", "Title", "Grade"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
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

        JLabel remainingLabel = new JLabel("Remaining Major Classes");
        remainingLabel.setFont(new Font("Serif", Font.BOLD, 25));
        GUIStyleHelper.styleNavHeader(remainingLabel);
        remainingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(remainingLabel);

        remainingModel = new DefaultTableModel(new String[]{"Course No", "Title"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
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

        // Electives Taken
        JLabel electivesLabelTable = new JLabel("Elective Classes Taken");
        electivesLabelTable.setFont(new Font("Serif", Font.BOLD, 25));
        GUIStyleHelper.styleNavHeader(electivesLabelTable);
        electivesLabelTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(electivesLabelTable);

        electivesModel = new DefaultTableModel(new String[]{"Course No", "Title", "Grade"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        electivesTable = new JTable(electivesModel);
        GUIStyleHelper.styleTable(electivesTable);
        centerTableText(electivesTable);
        JScrollPane electivesScroll = new JScrollPane(electivesTable);
        electivesTable.getTableHeader().setReorderingAllowed(false);
        JPanel electivesWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        electivesWrapper.setOpaque(false);
        electivesWrapper.add(electivesScroll);
        add(electivesWrapper);

        // Current Enrollments
        JLabel currentLabel = new JLabel("Current Enrollments (In Progress)");
        currentLabel.setFont(new Font("Serif", Font.BOLD, 25));
        GUIStyleHelper.styleNavHeader(currentLabel);
        currentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(currentLabel);

        currentModel = new DefaultTableModel(new String[]{"Course No", "Title", "Type"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        currentTable = new JTable(currentModel);
        GUIStyleHelper.styleTable(currentTable);
        centerTableText(currentTable);
        JScrollPane currentScroll = new JScrollPane(currentTable);
        currentTable.getTableHeader().setReorderingAllowed(false);
        JPanel currentWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        currentWrapper.setOpaque(false);
        currentWrapper.add(currentScroll);
        add(currentWrapper);

        JButton planButton = new JButton("Make Course Plan");
        GUIStyleHelper.stylePinButton(planButton);
        planButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(planButton);

        planModel = new DefaultTableModel(new String[]{"Course No", "Title", "Type", "Quarter"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
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

        planButton.addActionListener(e -> {
            loadPlan(perm);
            planWrapper.setVisible(true);
            planWrapper.revalidate();
            planWrapper.repaint();
        });

        loadProgress(perm);
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
            electivesModel.setRowCount(0);
            currentModel.setRowCount(0);

            int requiredMet = 0;
            int requiredInProgress = 0;
            int electivesMet = 0;
            int electivesInProgress = 0;

            List<String[]> transcript = transcriptDAO.getAllTranscriptRecords(perm);
            for (String[] row : transcript) {
                String courseNo = row[2];
                String title = row[3];
                String grade = row[4];

                if (categoryDAO.isMandatory(courseNo)) {
                    if ("IP".equalsIgnoreCase(grade)) {
                        requiredInProgress++;
                    } else {
                        requiredMet++;
                    }
                    completedModel.addRow(new String[]{courseNo, title, grade});
                } else if (categoryDAO.isElective(courseNo)) {
                    if ("IP".equalsIgnoreCase(grade)) {
                        electivesInProgress++;
                    } else {
                        electivesMet++;
                    }
                    electivesModel.addRow(new String[]{courseNo, title, grade});
                }
            }

            List<String[]> currentCourses = enrollmentDAO.getCurrentCourses(perm);
            for (String[] row : currentCourses) {
                String courseNo = row[0];
                String title = row[1];
                String type;
                if (categoryDAO.isMandatory(courseNo)) {
                    requiredInProgress++;
                    type = "Required";
                } else if (categoryDAO.isElective(courseNo)) {
                    electivesInProgress++;
                    type = "Elective";
                } else {
                    type = "Other";
                }
                currentModel.addRow(new String[]{courseNo, title, type});
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

            int electivesLeft = studiesDAO.getRemainingElectives(perm);

            requiredMetLabel.setText("Required Met: " + requiredMet);
            requiredInProgressLabel.setText("Required in Progress: " + requiredInProgress);
            requiredRemainingLabel.setText("Remaining Required: " + missing.size());
            electivesMetLabel.setText("Electives Met: " + electivesMet);
            electivesInProgressLabel.setText("Electives in Progress: " + electivesInProgress);
            electivesLabel.setText("Remaining Electives: " + electivesLeft);

            adjustTableHeight(completedTable);
            adjustTableHeight(remainingTable);
            adjustTableHeight(electivesTable);
            adjustTableHeight(currentTable);
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
            table.getParent().revalidate();
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
