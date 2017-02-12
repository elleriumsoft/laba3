package RequestsToDatabase;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public interface DatabaseRequest
{
    void sendRequest(Connection connection) throws SQLException;
}
