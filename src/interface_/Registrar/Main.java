package interface_.Registrar;

import utils.DBConnection;
import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection(); 

            SwingUtilities.invokeLater(() -> {
                new RegistrarLoginGUI(conn).setVisible(true);
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not connect to the database:\n" + e.getMessage());
        }
    }
}