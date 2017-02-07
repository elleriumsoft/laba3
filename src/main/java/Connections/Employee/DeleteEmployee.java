package Connections.Employee;

import Connections.GenerateBody;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 08.02.2017.
 */
public class DeleteEmployee implements GenerateBody
{
    private int element;

    public DeleteEmployee(int element)
    {
        this.element = element;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        connection.createStatement().executeUpdate("DELETE FROM employee WHERE id=" + String.valueOf(element) + ";\n");
        return null;
    }
}
