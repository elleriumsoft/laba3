package Connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 04.02.2017.
 */
public class EditElement implements GenerateBody
{
    private int element;
    private String deptName;

    public EditElement(String deptName, int element)
    {
        this.element = element;
        this.deptName = deptName;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        connection.createStatement().executeUpdate("UPDATE structure SET dept='" + deptName + "' WHERE id=" + String.valueOf(element) + ";\n");
        return null;
    }
}
