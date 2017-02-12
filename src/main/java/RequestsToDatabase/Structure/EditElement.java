package RequestsToDatabase.Structure;

import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 04.02.2017.
 */
public class EditElement implements DatabaseRequest
{
    private int element;
    private String deptName;

    public EditElement(String deptName, int element)
    {
        this.element = element;
        this.deptName = deptName;
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        //connection.createStatement().executeUpdate("UPDATE structure SET dept='" + deptName + "' WHERE id=" + String.valueOf(element) + ";\n");
        PreparedStatement statement = connection.prepareStatement("UPDATE structure SET dept=? WHERE id=?");
        statement.setString(1, deptName);
        statement.setInt(2, element);
        statement.executeUpdate();
        statement.close();
    }
}
