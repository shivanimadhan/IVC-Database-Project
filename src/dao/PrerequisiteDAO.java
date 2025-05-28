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
        // Step 1: Get all prerequisite course codes
        String sql = "SELECT prereq_course FROM PREREQS WHERE course = ?";
        List<String> prereqs = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                prereqs.add(rs.getString("prereq_course"));
            }
        }

        // No prerequisites → auto-pass
        if (prereqs.isEmpty()) {
            return true;
        }

        // Step 2: For each prerequisite, check if the student passed it
        String sqlCheck = """
            SELECT 1
            FROM TOOK T
            JOIN OFFERINGS O ON T.course_no = O.course_no AND T.year = O.year AND T.quarter = O.quarter
            WHERE T.perm = ? AND O.course_no = ? AND
                T.grade IN ('A+', 'A', 'B+', 'B', 'C+', 'C')
            """;

        for (String prereq : prereqs) {
            try (var stmt = conn.prepareStatement(sqlCheck)) {
                stmt.setString(1, perm);
                stmt.setString(2, prereq);
                try (var rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        return false; // prerequisite not completed
                    }
                }
            }
        }

        return true; // all prerequisites satisfied
    }
}