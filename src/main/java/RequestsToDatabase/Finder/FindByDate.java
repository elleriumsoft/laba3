package RequestsToDatabase.Finder;

import RequestsToDatabase.DatabaseRequest;
import Data.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 08.02.2017.
 */
public class FindByDate implements DatabaseRequest
{
    private String firstDate;
    private String secondDate;

    public FindByDate(String firstDate, String secondDate)
    {
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(
                "select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
                     " from employee, occupations, structure" +
                     " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.date BETWEEN ? AND ?" +
                     " ORDER BY employee.date)");
//        ResultSet emp = connection.createStatement().executeQuery("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
//                " from employee, occupations, structure" +
//                " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.date BETWEEN '" + firstDate + "' AND '" + secondDate + "'" +
//                " ORDER BY employee.date;\n");
        statement.setString(1, firstDate);
        statement.setString(2, secondDate);
        ResultSet emp = statement.executeQuery();

        ResultSet occ = connection.createStatement().executeQuery("select * from occupations");

        Employee.initEmployee(emp, occ, true);
    }
}