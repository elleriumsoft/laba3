package Connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 07.02.2017.
 */
public class AddEmployee implements GenerateBody
{
    private int element;
    private String empName;
    private int idOcc;
    private String empDate;

    public AddEmployee(String empName, String empDate, int idOcc, int element)
    {
        this.element = element;
        this.empName = empName;
        this.empDate = empDate;
        this.idOcc = idOcc;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        return null;
    }
}
