package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.*;

public class TranscriptDAO {
    private final Connection conn;

    public TranscriptDAO(Connection conn) {
        this.conn = conn;
    }

    public Quarter getCurrentQuarter(String perm) throws SQLException {
        String sql = "SELECT O.quarter, O.year " +
                    "FROM TAKES T " +
                    "JOIN OFFERINGS O ON T.enroll_code = O.enroll_code " +
                    "WHERE T.perm = ? " +
                    "FETCH FIRST 1 ROWS ONLY";

        String currentQuarter = null;
        int currentYear = -1;

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                currentQuarter = rs.getString("quarter");
                currentYear = rs.getInt("year");
                return new Quarter(currentQuarter, currentYear);
            }
        }

        return new Quarter("Unknown", -1);
    }

    public Quarter getPreviousQuarter(String perm) throws SQLException {
        Quarter curr = getCurrentQuarter(perm);
        Quarter prev = Quarter.previous(curr.getQuarterName(), curr.getYear());
        return prev;
    }

    public List<String> getPreviousQuarterGrades(String perm) throws SQLException {
        Quarter prev = getPreviousQuarter(perm);

        String sql = "SELECT C.course_no, C.title, T.grade " +
                     "FROM TOOK T " +
                     "JOIN OFFERINGS O ON T.enroll_code = O.enroll_code " +
                     "JOIN COURSES C ON O.course_no = C.course_no " +
                     "WHERE T.perm = ? AND O.quarter = ? AND O.year = ?";

        List<String> grades = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setString(2, prev.getQuarterName());
            stmt.setInt(3, prev.getYear());
            var rs = stmt.executeQuery();

            while (rs.next()) {
                String course = rs.getString("course_no");
                String title = rs.getString("title");
                String grade = rs.getString("grade");
                grades.add(course + " - " + title + ": " + grade);
            }
        }

        return grades;
    }

    public List<String> getAllTranscriptRecords(String perm) throws SQLException {
        String sql = "SELECT O.quarter, O.year, C.course_no, C.title, T.grade " +
                     "FROM TOOK T " +
                     "JOIN OFFERINGS O ON T.enroll_code = O.enroll_code " +
                     "JOIN COURSES C ON O.course_no = C.course_no " +
                     "WHERE T.perm = ? " +
                     "ORDER BY O.year, " +
                            "CASE " + 
                                    "WHEN O.quarter = 'Winter' THEN 1 " +
                                    "WHEN O.quarter = 'Spring' THEN 2 " +
                                    "WHEN O.quarter = 'Fall' THEN 3 " +
                                    "ELSE 4 " +
                            "END";

        List<String> transcript = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                String quarter = rs.getString("quarter");
                int year = rs.getInt("year");
                String courseNo = rs.getString("course_no");
                String title = rs.getString("title");
                String grade = rs.getString("grade");

                String record = quarter + " " + year + " - " + courseNo + " - " + title + ": " + grade;
                transcript.add(record);
            }
        }

        return transcript;
    }

    // public String generateTranscript(String perm) throws SQLException {
    //     // TODO: implement
    // }
}