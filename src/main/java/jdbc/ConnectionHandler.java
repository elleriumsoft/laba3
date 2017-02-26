package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionHandler {
    Object onConnection(Connection connection) throws SQLException;
}