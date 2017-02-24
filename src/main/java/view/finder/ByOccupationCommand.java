package view.finder;

import Data.Employee;
import RequestsToDatabase.ConnectionToDb;
import jdbc.ConnectionHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static view.finder.Constants.OCCUPATION;

public class ByOccupationCommand implements Command, ConnectionHandler
{
    private String occupationId;

    @Override
    public Employee execute(Map<String, String[]> parameters)
    {
        occupationId = parameters.get(OCCUPATION)[0];
        return (Employee) new ConnectionToDb().execute(this);
    }

    @Override
    public Object onConnection(Connection connection) throws SQLException
    {
        ResultSet emp =  connection.createStatement().executeQuery("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
                                                                       " from employee, occupations, structure" +
                                                                       " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.id_occ =" + occupationId +
                                                                       " ORDER BY employee.name");
        ResultSet occ = connection.createStatement().executeQuery("select * from occupations");

        Employee employee = new Employee();
        employee.initEmployee(emp, occ, true);
        return employee;
    }
}
