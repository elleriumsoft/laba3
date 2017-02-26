package RequestsToDatabase;

import jdbc.ConnectionHandler;

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
    public Object execute(ConnectionHandler handler)
    {
        Object result = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            result = handler.onConnection(connection);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeConnection(connection);
        }
        return result;//null return is possible, it is bad, think something better
    }

    public void connectToDb(DatabaseRequest databaseRequest)
    {
        Connection connection = null;

        try
        {
            connection = getConnection();
            databaseRequest.sendRequest(connection);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeConnection(connection);
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
