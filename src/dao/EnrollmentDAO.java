package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    private final Connection conn;

    public EnrollmentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addCourse(String perm, String courseNo, int year, String quarter) throws SQLException {
        String sql = "INSERT INTO TAKES (perm, course_no, year, quarter) VALUES (?, ?, ?, ?)";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setString(2, courseNo);
            stmt.setInt(3, year);
            stmt.setString(4, quarter);
            return stmt.executeUpdate() == 1;
        }
    }

    public boolean dropCourse(String perm, String courseNo, int year, String quarter) throws SQLException {
        String countSql = "SELECT COUNT(*) FROM TAKES WHERE perm = ?";
        try (var countStmt = conn.prepareStatement(countSql)) {
            countStmt.setString(1, perm);
            var rs = countStmt.executeQuery();
            if (rs.next()) {
                int enrolledCount = rs.getInt(1);
                if (enrolledCount <= 1) {
                    return false;
                }
            }
        }

        String dropSql = "DELETE FROM TAKES WHERE perm = ? AND course_no = ? AND year = ? AND quarter = ?";
        try (var stmt = conn.prepareStatement(dropSql)) {
            stmt.setString(1, perm);
            stmt.setString(2, courseNo);
            stmt.setInt(3, year);
            stmt.setString(4, quarter);
            return stmt.executeUpdate() == 1;
        }
    }

    public List<String[]> getCurrentCourses(String perm) throws SQLException {
        String sql = "SELECT C.course_no, C.title, O.year, O.quarter, O.professor, O.time, O.location " +
                    "FROM TAKES T " +
                    "JOIN OFFERINGS O ON T.course_no = O.course_no AND T.year = O.year AND T.quarter = O.quarter " +
                    "JOIN COURSES C ON O.course_no = C.course_no " +
                    "WHERE T.perm = ?";

        List<String[]> courses = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new String[] {
                    rs.getString("course_no"),
                    rs.getString("title"),
                    String.valueOf(rs.getInt("year")),
                    rs.getString("quarter"),
                    rs.getString("professor"),
                    rs.getString("time"),
                    rs.getString("location")
                });
            }
        }
        return courses;
    }

    public boolean isEnrolled(String perm, String courseNo, int year, String quarter) throws SQLException {
        String sql = "SELECT 1 FROM TAKES WHERE perm = ? AND course_no = ? AND year = ? AND quarter = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setString(2, courseNo);
            stmt.setInt(3, year);
            stmt.setString(4, quarter);
            var rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public int getEnrolledCount(String courseNo, int year, String quarter) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TAKES WHERE course_no = ? AND year = ? AND quarter = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);
            var rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public List<String> getCoursesForStudent(String perm) throws SQLException {
        String sql = "SELECT course_no FROM TAKES WHERE perm = ?";

        List<String> result = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("course_no"));
            }
        }

        return result;
    }

    public int getStudentCourseCount(String perm, int year, String quarter) throws SQLException {
        String sql = """
            SELECT COUNT(*) 
            FROM TAKES T
            JOIN OFFERINGS O ON T.course_no = O.course_no AND T.year = O.year AND T.quarter = O.quarter
            WHERE T.perm = ? AND O.year = ? AND O.quarter = ?
        """;
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
}