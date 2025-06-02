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
                    "JOIN OFFERINGS O ON T.course_no = O.course_no AND T.year = O.year AND T.quarter = O.quarter " +
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

        return new Quarter("S", 25);
    }

    public List<String[]> getGradesForQuarter(String perm, int year, String quarter) throws SQLException {
        String sql = """
            SELECT C.course_no, C.title, T.grade
            FROM TOOK T
            JOIN COURSES C ON T.course_no = C.course_no
            WHERE T.perm = ? AND T.year = ? AND T.quarter = ?
            """;

        List<String[]> grades = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            stmt.setInt(2, year);
            stmt.setString(3, quarter);
            var rs = stmt.executeQuery();

            while (rs.next()) {
                grades.add(new String[]{
                    rs.getString("course_no"),
                    rs.getString("title"),
                    rs.getString("grade")
                });
            }
        }
        return grades;
    }


    public List<Quarter> getAllQuartersTaken(String perm) throws SQLException {
        String sql = "SELECT DISTINCT quarter, year FROM TOOK WHERE perm = ? ORDER BY year DESC, " +
                    "CASE quarter WHEN 'Fall' THEN 1 WHEN 'Winter' THEN 2 WHEN 'Spring' THEN 3 ELSE 4 END";

        List<Quarter> quarters = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                quarters.add(new Quarter(rs.getString("quarter"), rs.getInt("year")));
            }
        }
        return quarters;
    }


    public List<String[]> getAllTranscriptRecords(String perm) throws SQLException {
        String sql = """
            SELECT T.quarter, T.year, C.course_no, C.title, T.grade
            FROM TOOK T
            JOIN COURSES C ON T.course_no = C.course_no
            WHERE T.perm = ?
            ORDER BY T.year,
                CASE T.quarter
                    WHEN 'Fall' THEN 1
                    WHEN 'Winter' THEN 2
                    WHEN 'Spring' THEN 3
                    ELSE 4
                END
            """;

        List<String[]> transcript = new ArrayList<>();
        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perm);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                String quarter = rs.getString("quarter");
                int year = rs.getInt("year");
                String courseNo = rs.getString("course_no");
                String title = rs.getString("title");
                String grade = rs.getString("grade");

                String[] course = new String[] {
                        quarter,
                        Integer.toString(year),
                        courseNo,
                        title,
                        grade
                    };

                transcript.add(course);
            }
        }

        return transcript;
    }

    // public String generateTranscript(String perm) throws SQLException {
    //     // TODO: implement
    // }
}