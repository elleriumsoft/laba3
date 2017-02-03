package Connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 03.02.2017.
 */
public class DeleteElement implements GenerateBody
{
    private int element;

    public DeleteElement(int element)
    {
        this.element = element;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        connection.createStatement().executeUpdate("DELETE FROM structure WHERE id=" + String.valueOf(element) + ";\n");
        return null;
    }
}
