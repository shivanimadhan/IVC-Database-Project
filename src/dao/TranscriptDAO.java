package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TranscriptDAO {
    private final Connection conn;

    public TranscriptDAO(Connection conn) {
        this.conn = conn;
    }

    public List<String> getPreviousQuarterGrades(String perm) throws SQLException {
        // TODO: implement
    }

    public List<String> getAllTranscriptRecords(String perm) throws SQLException {
        // TODO: implement
    }

    public String generateTranscript(String perm) throws SQLException {
        // TODO: implement
    }
}