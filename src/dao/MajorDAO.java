package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MajorDAO {
    private final Connection conn;

    public MajorDAO(Connection conn) {
        this.conn = conn;
    }

    public List<String> getAllMajors() throws SQLException {
        // TODO: implement
    }

    public List<String> getMustTakeCourses(int majorId) throws SQLException {
        // TODO: implement
    }

    public List<String> getElectiveOptions(int majorId) throws SQLException {
        // TODO: implement
    }
}