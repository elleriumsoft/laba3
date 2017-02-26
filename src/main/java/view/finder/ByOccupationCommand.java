package view.finder;

import Data.Employee;
import RequestsToDatabase.ConnectionToDb;
import jdbc.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static view.finder.Constants.OCCUPATION;

public class ByOccupationCommand implements Command, ConnectionHandler
{
    private int occupationId;

    @Override
    public Employee execute(Map<String, String[]> parameters)
    {
        occupationId = Integer.parseInt(parameters.get(OCCUPATION)[0]);
        return (Employee) new ConnectionToDb().execute(this);
    }

    @Override
    public Object onConnection(Connection connection) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(
                    "select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
                        " from employee, occupations, structure" +
                        " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.id_occ = ?" +
                        " ORDER BY employee.name");
        statement.setInt(1, occupationId);
        ResultSet emp = statement.executeQuery();
        ResultSet occ = connection.createStatement().executeQuery("select * from occupations");

        Employee employee = new Employee();
        employee.initEmployee(emp, occ, true);
        return employee;
    }
}
