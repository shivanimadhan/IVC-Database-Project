package interface_.Gold.panels;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import dao.StudentDAO;
import utils.GUIStyleHelper;

// public class SettingsPanel extends JPanel {
//     public SettingsPanel(String perm, Connection conn) {
//         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//         GUIStyleHelper.stylePages(this);

//         JLabel infoHeader = new JLabel("My Information");
//         GUIStyleHelper.styleNavHeader(infoHeader);
//         infoHeader.setFont(new Font("Serif", Font.BOLD, 28));

//         // declare labels OUTSIDE the try block
//         JLabel name = new JLabel();
//         JLabel permComp = new JLabel();
//         JLabel dept = new JLabel();
//         JLabel addr = new JLabel();

//         StudentDAO studentDAO = new StudentDAO(conn);

//         try {
//             name.setText("<html><b>Name: </b>" + studentDAO.getStudentName(perm) + "</html>");
//             permComp.setText("<html><b>Perm: </b>" + perm + "</html>");
//             dept.setText("<html><b>Department: </b>" + studentDAO.getStudentDept(perm) + "</html>");
//             addr.setText("<html><b>Address: </b>" + studentDAO.getStudentAddress(perm) + "</html>");
//         } catch (SQLException e) {
//             e.printStackTrace();
//             add(new JLabel("Failed to load student info."));
//         }

//         // style them
//         Font infoFont = new Font("Serif", Font.PLAIN, 22);
//         Color infoColor = new Color(20, 40, 80);
//         for (JLabel label : new JLabel[]{name, permComp, dept, addr}) {
//             label.setFont(infoFont);
//             label.setForeground(infoColor);
//             label.setAlignmentX(Component.LEFT_ALIGNMENT);
//         }

//         JLabel pin = new JLabel("<html><b>Pin: </b>****</html>");
//         pin.setFont(infoFont);
//         pin.setForeground(infoColor);
//         JButton changePinButton = new JButton("Change Pin");
//         changePinButton.setFont(infoFont);

//         JPanel pinPanel = new JPanel();
//         pinPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//         pinPanel.setOpaque(false);
//         pinPanel.add(pin);
//         pinPanel.add(changePinButton);

//         JPanel infoPanel = new JPanel();
//         infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
//         infoPanel.setOpaque(false);
//         infoPanel.add(infoHeader);
//         infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//         infoPanel.add(name);
//         infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
//         infoPanel.add(permComp);
//         infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
//         infoPanel.add(dept);
//         infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
//         infoPanel.add(addr);
//         infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
//         infoPanel.add(pinPanel);
//         infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

//         add(Box.createRigidArea(new Dimension(30, 20)));
//         add(infoPanel);
//     }
// }

public class SettingsPanel extends JPanel {
    public SettingsPanel(String perm, Connection conn) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GUIStyleHelper.stylePages(this);

        JLabel infoHeader = new JLabel("My Information");
        GUIStyleHelper.styleNavHeader(infoHeader);
        infoHeader.setFont(new Font("Serif", Font.BOLD, 28));

        JLabel name = new JLabel();
        JLabel permComp = new JLabel();
        JLabel dept = new JLabel();
        JLabel addr = new JLabel();

        StudentDAO studentDAO = new StudentDAO(conn);

        try {
            name.setText("Name: " + studentDAO.getStudentName(perm));
            permComp.setText("Perm: " + perm);
            dept.setText("Department: " + studentDAO.getStudentDept(perm));
            addr.setText("Address: " + studentDAO.getStudentAddress(perm));
        } catch (SQLException e) {
            e.printStackTrace();
            add(new JLabel("Failed to load student info."));
        }

        Font infoFont = new Font("Serif", Font.PLAIN, 22);
        Color infoColor = new Color(20, 40, 80);
        for (JLabel label : new JLabel[]{name, permComp, dept, addr}) {
            label.setFont(infoFont);
            label.setForeground(infoColor);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        JLabel pin = new JLabel("Pin: ****");
        pin.setFont(infoFont);
        pin.setForeground(infoColor);

        JButton changePinButton = new JButton("Change Pin");
        changePinButton.setFont(infoFont);
        GUIStyleHelper.stylePinButton(changePinButton);

        changePinButton.addActionListener(e -> {
            // create the fields
            JPasswordField oldPinField = new JPasswordField(10);
            JPasswordField newPinField = new JPasswordField(10);

            // label them
            JPanel pinInputPanel = new JPanel();
            pinInputPanel.setLayout(new BoxLayout(pinInputPanel, BoxLayout.Y_AXIS));
            pinInputPanel.add(new JLabel("Enter old PIN:"));
            pinInputPanel.add(oldPinField);
            pinInputPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            pinInputPanel.add(new JLabel("Enter new PIN:"));
            pinInputPanel.add(newPinField);

            int result = JOptionPane.showConfirmDialog(
                SettingsPanel.this,
                pinInputPanel,
                "Change PIN",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                String oldPin = new String(oldPinField.getPassword());
                String newPin = new String(newPinField.getPassword());

                try {
                    boolean success = studentDAO.setPin(perm, oldPin, newPin); // make sure this method returns a boolean
                    if (success) {
                        JOptionPane.showMessageDialog(SettingsPanel.this, "PIN changed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(SettingsPanel.this, "Failed to change PIN. Please check your old PIN.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SettingsPanel.this, "Database error occurred.");
                }
            }
        });

        JPanel pinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pinPanel.setOpaque(false);
        pinPanel.add(pin);
        pinPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        pinPanel.add(changePinButton);
        
        Box pinWrapper = Box.createHorizontalBox();
        pinWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        pinWrapper.add(Box.createRigidArea(new Dimension(-5, 0))); 
        pinWrapper.add(pinPanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(infoHeader);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        infoPanel.add(name);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(permComp);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(dept);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(addr);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(pinWrapper);

        add(Box.createRigidArea(new Dimension(30, 20)));
        add(infoPanel);
    }
}