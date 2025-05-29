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

    public List<String[]> getOfferedCoursesByDept(String dept, int year, String quarter) throws SQLException {
        String sql = "SELECT C.course_no, C.title, O.year, O.quarter, O.professor, O.time, O.location " +
                    "FROM OFFERINGS O " +
                    "JOIN COURSES C ON O.course_no = C.course_no " +
                    "WHERE C.dept = ? AND O.year = ? AND O.quarter = ?";

        List<String[]> courses = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dept);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);

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
    
    public int getCourseCapacity(String courseNo, int year, String quarter) throws SQLException {
        String sql = "SELECT capacity FROM OFFERINGS WHERE course_no = ? AND year = ? AND quarter = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("capacity");
            } else {
                throw new SQLException("Offering not found for " + courseNo + " (" + quarter + " " + year + ")");
            }
        }
    }

    public int getStudentCourseCount(String perm, int year, String quarter) throws SQLException {
        String sql = "SELECT COUNT(*) AS course_count FROM TAKES " +
                    "WHERE perm = ? AND year = ? AND quarter = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("course_count");
            } else {
                return 0;
            }
        }
    }

    public List<String> getEnrolledStudentNames(String courseNo, int year, String quarter) throws SQLException {
        List<String> studentNames = new ArrayList<>();

        String query = """
            SELECT S.name
            FROM TAKES T
            JOIN OFFERINGS O ON T.course_no = O.course_no AND T.year = O.year AND T.quarter = O.quarter
            JOIN STUDENTS S ON T.perm = S.perm
            WHERE O.course_no = ? AND O.year = ? AND O.quarter = ?
        """;

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseNo);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);

            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    studentNames.add(rs.getString("name"));
                }
            }
        }

        return studentNames;
    }

    public int getCourseEnrollmentCount(String courseNo, int year, String quarter) throws SQLException {
        String sql = "SELECT COUNT(*) AS enrollment_count FROM TAKES " +
                    "WHERE course_no = ? AND year = ? AND quarter = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseNo);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("enrollment_count");
            } else {
                return 0;
            }
        }
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
            ORDER BY year ASC,
                CASE quarter
                    WHEN 'F' THEN 1
                    WHEN 'W' THEN 2
                    WHEN 'S' THEN 3
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
    
    public String[] getOfferingInfoByEnrollCode(int enrollCode) throws SQLException {
        String sql = """
            SELECT C.course_no, O.year, O.quarter
            FROM COURSES C
            JOIN OFFERINGS O ON C.course_no = O.course_no
            WHERE C.enroll_code = ?
        """;
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollCode);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[] {
                    rs.getString("course_no"),
                    String.valueOf(rs.getInt("year")),
                    rs.getString("quarter")
                };
            }
        }
        return null; // if no match
    }
    
}