package RequestsToDatabase.Structure;

import Data.Structure;
import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public class GenerateSturcture implements DatabaseRequest
{
    private Structure structure;

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        ResultSet rs = connection.prepareStatement("select * from structure").executeQuery();
        structure = new Structure();
        structure.initStructureFromDb(rs);
    }

    public Structure getStructure()
    {
        return structure;
    }
}
