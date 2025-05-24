package dao;

import java.sql.Connection;

public class MajorDAO {
    private final Connection conn;

    public MajorDAO(Connection conn) {
        this.conn = conn;
    }

    // public List<String> getAllMajors() throws SQLException {
    //     // TODO: implement
    // }

    // public List<String> getMustTakeCourses(int majorId) throws SQLException {
    //     // TODO: implement
    // }

    // public List<String> getElectiveOptions(int majorId) throws SQLException {
    //     // TODO: implement
    // }
}