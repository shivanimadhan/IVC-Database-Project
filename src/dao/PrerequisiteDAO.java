package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PrerequisiteDAO {
    private final Connection conn;

    public PrerequisiteDAO(Connection conn) {
        this.conn = conn;
    }

    public List<String> getPrerequisites(String courseNo) throws SQLException {
        // TODO: implement
    }

    public boolean hasCompletedPrerequisites(String perm, String courseNo) throws SQLException {
        // TODO: implement
    }
}