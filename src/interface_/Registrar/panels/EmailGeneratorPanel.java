package interface_.Registrar.panels;

import dao.TranscriptDAO;
import dao.StudentDAO;
import models.Quarter;
import utils.GUIStyleHelper;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmailGeneratorPanel extends JPanel {
    private final TranscriptDAO transcriptDAO;
    private final StudentDAO studentDAO;
    private final JComboBox<Quarter> quarterDropdown;
    private final JTextField permField;
    private final JTextArea emailArea;
    private final JButton generateButton;
    private final JButton emailButton;

    public EmailGeneratorPanel(Connection conn) {
        this.transcriptDAO = new TranscriptDAO(conn);
        this.studentDAO = new StudentDAO(conn);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

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

        generateButton = new JButton("Generate");
        GUIStyleHelper.stylePinButton(generateButton);
        inputPanel.add(generateButton);

        emailButton = new JButton("Send");
        GUIStyleHelper.stylePinButton(emailButton);
        emailButton.setEnabled(false);
        inputPanel.add(emailButton);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(inputPanel);

        emailArea = new JTextArea(12, 100);
        emailArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(emailArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        emailPanel.setOpaque(false);
        emailPanel.add(scrollPane);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(emailPanel);

        generateButton.addActionListener(e -> generateEmail());

        try {
            List<Quarter> quarters = transcriptDAO.getAllQuartersTaken("12345");
            for (Quarter q : quarters) {
                quarterDropdown.addItem(q);
            }
        } catch (SQLException ex) {
            emailArea.setText("Error loading quarters: " + ex.getMessage());
        }
    }

    private void generateEmail() {
        String perm = permField.getText().trim();
        Quarter selected = (Quarter) quarterDropdown.getSelectedItem();
        if (selected == null) {
            emailArea.setText("Please select a valid quarter.");
            return;
        }

        String quarter = selected.getQuarterCode();
        int year = selected.getYear();

        try {
            String name = studentDAO.getStudentName(perm);
            List<String[]> grades = transcriptDAO.getGradesForQuarter(perm, year, quarter);

            if (grades.isEmpty()) {
                emailArea.setText("No courses found for " + name + " in " + quarter + " " + year);
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(name).append(", your grades for ").append(quarter).append(" ").append(year).append(" are:\n\n");
            for (String[] row : grades) {
                sb.append(row[0]).append(" (" + row[1] + "): ").append(row[2]).append("\n");
            }

            emailArea.setText(sb.toString());
            emailButton.setEnabled(true);
        } catch (SQLException ex) {
            emailArea.setText("Error generating email: " + ex.getMessage());
            emailButton.setEnabled(false);
        }
    }
}
