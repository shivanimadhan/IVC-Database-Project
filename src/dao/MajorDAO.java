package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDAO {
    private final Connection conn;

    public MajorDAO(Connection conn) {
        this.conn = conn;
    }

    public List<String> getAllMajors() throws SQLException {
        String sql = "SELECT major_id, name FROM MAJORS";
        List<String> majors = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                String major = rs.getInt("major_id") + " - " + rs.getString("name");
                majors.add(major);
            }
        }
        return majors;
    }

    public List<String> getAllDepts() throws SQLException {
        String sql = "SELECT dept FROM MAJORS";
        List<String> depts = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                String dept = rs.getString("dept");
                depts.add(dept);
            }
        }
        return depts;
    }

    public List<String> getMustTakeCourses(int majorId) throws SQLException {
        String sql = "SELECT course_no FROM MUST_TAKE WHERE major_id = ?";
        List<String> mustTakeCourses = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, majorId);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                mustTakeCourses.add(rs.getString("course_no"));
            }
        }
        return mustTakeCourses;
    }

    public List<String> getElectiveOptions(int majorId) throws SQLException {
        String sql = "SELECT course_no FROM CAN_TAKE WHERE major_id = ?";
        List<String> electiveCourses = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, majorId);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                electiveCourses.add(rs.getString("course_no"));
            }
        }
        return electiveCourses;
    }
}