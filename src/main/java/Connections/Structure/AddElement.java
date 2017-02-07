package Connections.Structure;

import Connections.GenerateBody;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 03.02.2017.
 */
public class AddElement implements GenerateBody
{
    private int element;
    private String deptName;

    public AddElement(String deptName, int element)
    {
        this.element = element;
        this.deptName = deptName;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        connection.createStatement().executeUpdate("INSERT INTO structure (dept, parent_id) VALUES ('" + deptName + "'," + String.valueOf(element) + ");\n");
        return null;
    }
}
