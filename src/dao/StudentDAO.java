package dao;

import java.sql.Connection;
import java.sql.SQLException;
import utils.PinManager;

public class StudentDAO {
    private final Connection conn;

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean verifyPin(String perm, String inputPin) throws SQLException {
        String hashedInput = PinManager.hashPin(inputPin);
        String sql = "SELECT pin FROM STUDENTS WHERE perm = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String actualHashedPin = rs.getString("pin");
                return actualHashedPin.equals(hashedInput);
            }
        }
        return false;
    }
}

//     public boolean setPin(int oldPin, int newPin) throws SQLException {
//         String sql = "SELECT pin FROM STUDENTS WHERE perm = ?";
//         try (var stmt = conn.prepareStatement(sql)) {
//             stmt.setInt(1, perm);
//             var rs = stmt.executeQuery();
//             if (rs.next()) {
//                 if (verifyPin(perm, newPin)) {

//                 }
//             }
//         }
//     }

//     public String getStudentMajor(int perm) throws SQLException

//     public boolean studentExists(int perm) throws SQLException

//     public Student getStudentByPerm(int perm) throws SQLException
// }