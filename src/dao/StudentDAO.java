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
                if (actualHashedPin != null) {
                    return actualHashedPin.equals(hashedInput);
                }
            }
        }
        return false;
    }

    public boolean setPin(String perm, String oldPin, String newPin) throws SQLException {
        if (!verifyPin(perm, oldPin)) {
            return false; 
        }

        String newHashedPin = PinManager.hashPin(newPin);
        String sql = "UPDATE STUDENTS SET pin = ? WHERE perm = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newHashedPin);
            stmt.setString(2, perm);
            return stmt.executeUpdate() == 1;
        }
    }

    public boolean studentExists(String perm) throws SQLException {
        String sql = "SELECT 1 FROM STUDENTS WHERE perm = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public String getStudentMajor(String perm) throws SQLException {
        String sql = "SELECT major_id FROM Studies WHERE perm = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("major_id");
            }
        }
        return null;
    }
}