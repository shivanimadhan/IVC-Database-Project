package dao;

import java.sql.Connection;
import java.sql.SQLException;

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

    // public List<String> getAllCourses() throws SQLException {
    //     // TODO: implement
    // }

    // public String getCourseByEnrollCode(int enrollCode) throws SQLException {
    //     // TODO: implement
    // }

    // public String getCourseInfo(String courseNo) throws SQLException {
    //     // TODO: implement
    // }

    // public List<String> getCourseOfferings(String courseNo) throws SQLException {
    //     // TODO: implement
    // }
}