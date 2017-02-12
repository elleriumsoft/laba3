package RequestsToDatabase.Employee;

import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 08.02.2017.
 */
public class DeleteEmployee implements DatabaseRequest
{
    private int element;

    public DeleteEmployee(int element)
    {
        this.element = element;
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
//        connection.createStatement().executeUpdate("DELETE FROM employee WHERE id=" + String.valueOf(element) + ";\n");
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id=?");
        statement.setInt(1, element);
        statement.executeUpdate();
        statement.close();
    }
}
