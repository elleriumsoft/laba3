package Connections;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public class ConnectionToDb
{
    public String writeBody(GenerateBody generateBody)
    {
        Connection connection = null;
        String out = "";
        try
        {
            connection = getConnection();
            out = generateBody.doBody(connection);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeConnection(connection);
            return out;
        }
    }

    private Connection getConnection()
    {
        try
        {
            InitialContext ic = new InitialContext();
            DataSource dataSource = (DataSource) ic.lookup("java:jboss/laba3");
            return dataSource.getConnection();
        }
        catch (NamingException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
