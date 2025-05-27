package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrerequisiteDAO {
    private final Connection conn;

    public PrerequisiteDAO(Connection conn) {
        this.conn = conn;
    }

    public List<String> getPrerequisites(String courseNo) throws SQLException {
        String sql = "SELECT prereq_course FROM PREREQS WHERE course = ?";
        List<String> prereqCourses = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                prereqCourses.add(rs.getString("prereq_course"));
            }
        }
        return prereqCourses;
    }

    public boolean hasCompletedPrerequisites(String perm, String courseNo) throws SQLException {
        String sql = "SELECT prereq_course FROM PREREQS WHERE course = ?";
        List<String> prereqs = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                prereqs.add(rs.getString("prereq_course"));
            }
        }
        if (prereqs.isEmpty()) {
            return true; // No prerequisites, so automatically satisfied
        }
        String sqlCheck = """
            SELECT 1
            FROM TOOK K
            JOIN OFFERING O ON T.enroll_code = O.enroll_code
            WHERE T.perm = ? AND O.course_no = ? AND
                T.grade IN ('A+', 'A', 'B+', 'B', 'C+', 'C')
            """;
        try (var stmt = conn.prepareStatement(sqlCheck)) {
            for (String prereq : prereqs){
                stmt.setString(1, perm);
                stmt.setString(2, prereq);
                try (var rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}