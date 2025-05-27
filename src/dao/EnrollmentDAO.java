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

    public boolean addCourse(String perm, int enrollCode) throws SQLException {
        String sql = "INSERT INTO TAKES (perm, enroll_code) VALUES (?, ?)";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setInt(2, enrollCode);
            return stmt.executeUpdate() == 1;
        }
    }

    public boolean dropCourse(String perm, int enrollCode) throws SQLException {
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

        String dropSql = "DELETE FROM TAKES WHERE perm = ? AND enroll_code = ?";
        try (var stmt = conn.prepareStatement(dropSql)) {
            stmt.setString(1, perm);
            stmt.setInt(2, enrollCode);
            return stmt.executeUpdate() == 1;
        }
    }

    public List<String[]> getCurrentCourses(String perm) throws SQLException {
        String sql = "SELECT C.course_no, C.title, O.professor, O.time, O.location, O.enroll_code " +
                    "FROM TAKES T " +
                    "JOIN OFFERINGS O ON T.enroll_code = O.enroll_code " +
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
                    rs.getString("professor"),
                    rs.getString("time"),
                    rs.getString("location"),
                    rs.getString("enroll_code")
                });
            }
        }
        return courses;
    }

    public boolean isEnrolled(String perm, int enrollCode) throws SQLException {
        String sql = "SELECT 1 FROM TAKES WHERE perm = ? AND enroll_code = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setInt(2, enrollCode);
            var rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public int getEnrolledCount(int enrollCode) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TAKES WHERE enroll_code = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollCode);
            var rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public List<String> getCoursesForStudent(String perm) throws SQLException {
        String sql = "SELECT C.course_no" +
                     "FROM TAKES T" +
                     "JOIN OFFERINGS O ON T.enroll_code = O.enroll_code" +
                     "JOIN COURSES C ON O.course_no = C.course_no" +
                     "WHERE T.perm = ?";

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
}