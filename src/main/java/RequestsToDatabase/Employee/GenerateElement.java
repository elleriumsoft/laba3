package RequestsToDatabase.Employee;

import Data.Employee;
import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 04.02.2017.
 */
public class GenerateElement implements DatabaseRequest
{
    private int id;
    private Employee employee;

    public GenerateElement(int id)
    {
        this.id = id;
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement
                ("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ" +
                      " from employee, occupations" +
                      " WHERE employee.id_occ = occupations.id  and employee.id_dept=?" +
                      " ORDER BY occupations.id");
        statement.setInt(1, id);
        ResultSet emp = statement.executeQuery();

        ResultSet occ = connection.createStatement().executeQuery("select * from occupations;\n");

        employee = new Employee();
        employee.initEmployee(emp, occ, false);
    }

    public Employee getEmployee()
    {
        return employee;
    }
}
