package Connections.Structure;

import Connections.GenerateBody;
import Data.Structure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public class GenerateSturcture implements GenerateBody
{
    private String command;
    public GenerateSturcture(String command)
    {
        this.command = command;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        ResultSet rs = connection.createStatement().executeQuery("select * from structure;\n");
        Structure.initStructureFromDb(rs);
        return Structure.printStructure(command);
    }
}
