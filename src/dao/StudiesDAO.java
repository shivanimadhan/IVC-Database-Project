package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudiesDAO {
    private final Connection conn;

    public StudiesDAO(Connection conn) {
        this.conn = conn;
    }

    public int getNumElectivesTaken(String perm) throws SQLException {
        String sql = "SELECT course_no FROM TOOK WHERE perm = ?";
        int count = 0;

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            CourseCategoryDAO categoryDAO = new CourseCategoryDAO(conn);

            while (rs.next()) {
                String courseNo = rs.getString("course_no");
                
                if (categoryDAO.isElective(courseNo)) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getStudentMajorId(String perm) throws SQLException {
        String sql = "SELECT major_id FROM STUDIES WHERE perm = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("major_id");
            } else {
                throw new SQLException("No major found for student " + perm);
            }
        }
    }

    private List<String> getMajorCourses(int majorId) throws SQLException {
        String sql = "SELECT course_no FROM MUST_TAKE WHERE major_id = ?";
        List<String> result = new ArrayList<>();

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, majorId);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("course_no"));
            }
        }

        return result;
    }

    public int getStudentAddress(String perm) throws SQLException {
        String sql = "SELECT address FROM STUDENTS WHERE perm = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("address");
            } else {
                throw new SQLException("No address found for student " + perm);
            }
        }
    }

    private List<String> getCompletedCourses(String perm) throws SQLException {
        String sql = "SELECT course_no FROM TOOK WHERE perm = ?";

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

    public List<String> getMissingRequiredCourses(String perm) throws SQLException {
        int majorId = getStudentMajorId(perm);
        List<String> requiredCourses = getMajorCourses(majorId);
        List<String> completedCourses = getCompletedCourses(perm);

        List<String> missing = new ArrayList<>();
        for (String courseNo : requiredCourses) {
            if (!completedCourses.contains(courseNo)) {
                missing.add(courseNo);
            }
        }

        CourseDAO courseDAO = new CourseDAO(conn);
        List<String> missingTitles = new ArrayList<>();
        for (String courseNo : missing) {
            String title = courseDAO.getCourseTitle(courseNo);
            missingTitles.add(title + " (" + courseNo + ")");
        }

        return missingTitles;
    }

    public int getRemainingElectives(String perm) throws SQLException {
        int numElectivesTaken = getNumElectivesTaken(perm);

        String sql = "SELECT num_electives FROM STUDIES WHERE perm = ?";
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                int numRequired = rs.getInt("num_electives");
                int numRemainingElectives = Math.max(0, numRequired - numElectivesTaken);
                return numRemainingElectives;
            } else {
                throw new SQLException("No record found in STUDIES for perm: " + perm);
            }
        }
    }
}