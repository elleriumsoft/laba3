package Connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public interface GenerateBody
{
    String doBody(Connection connection) throws SQLException;
}
