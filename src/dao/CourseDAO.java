package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private final Connection conn;

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    public String getCourseTitle(String courseNo) throws SQLException {
        String sql = "SELECT title FROM COURSES WHERE course_no = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("title");
            }
        }
        return "(Unknown)";
    }

    public List<String> getAllCourses() throws SQLException {
        String sql = "SELECT course_no, title FROM COURSES";
        List<String> courses = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                String course = rs.getString("course_no") + " - " + rs.getString("title");
                courses.add(course);
            }
        }
        return courses;
    }
    

    public String[] getCourseByEnrollCode(int enrollCode) throws SQLException {
        String sql = "SELECT course_no, title FROM COURSES WHERE enroll_code = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollCode);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[] {
                    rs.getString("course_no"),
                    rs.getString("title")
                };
            }
        }
        return null;
    }
    


    public String getCourseInfo(String courseNo) throws SQLException {
        String title = getCourseTitle(courseNo);
        List<String[]> offerings = getCourseOfferings(courseNo);
        StringBuilder sb = new StringBuilder("Course: " + courseNo + " - " + title + "\n");
        for (String[] offer : offerings) {
            sb.append("  ").append(String.join(" ", offer)).append("\n");
        }
        return sb.toString();
    }

    public List<String[]> getCourseOfferings(String courseNo) throws SQLException {
        String sql = """
            SELECT year, quarter, professor
            FROM OFFERINGS
            WHERE course_no = ?
            ORDER BY year DESC,
                CASE quarter
                    WHEN 'Fall' THEN 1
                    WHEN 'Winter' THEN 2
                    WHEN 'Spring' THEN 3
                    ELSE 4
                END
            """;
        List<String[]> offerings = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                offerings.add(new String[] {
                    Integer.toString(rs.getInt("year")),
                    rs.getString("quarter"),
                    rs.getString("professor")
                });
            }
        }
        return offerings;
    }
}