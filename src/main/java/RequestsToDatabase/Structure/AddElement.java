package RequestsToDatabase.Structure;

import Data.Structure;
import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 03.02.2017.
 */
public class AddElement implements DatabaseRequest
{
    private int element;
    private String deptName;

    public AddElement(String deptName, int element)
    {
        this.element = element;
        this.deptName = deptName;
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        //connection.createStatement().executeUpdate("INSERT INTO structure (dept, parent_id) VALUES ('" + deptName + "'," + String.valueOf(element) + ");\n");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO structure (dept, parent_id) VALUES (?,?)");
        statement.setString(1, deptName);
        statement.setInt(2, element);
        statement.executeUpdate();
        statement.close();
        Structure.getOpenElements().add(element);
    }
}
