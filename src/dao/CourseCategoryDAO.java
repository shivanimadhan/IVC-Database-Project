package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CourseCategoryDAO {
    private final Connection conn;

    public CourseCategoryDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean isElective(String courseNo) throws SQLException {
        String sql = "SELECT 1 FROM ELECTIVES WHERE course_no = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            var rs = stmt.executeQuery();
            return rs.next();  
        }
    }

    public boolean isMandatory(String courseNo) throws SQLException {
        String sql = "SELECT 1 FROM MANDATORY WHERE course_no = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            var rs = stmt.executeQuery();
            return rs.next();  
        }
    }


    public String getCategory(String courseNo) throws SQLException {
        if (isElective(courseNo)) {
            return "Elective";
        } else if (isMandatory(courseNo)) {
            return "Mandatory";
        } else {
            return "Unknown";
        }
    }
}