package RequestsToDatabase.Finder;

import Data.Employee;
import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 08.02.2017.
 */
public class FindByDate extends DatabaseRequestForFinder implements DatabaseRequest
{
//    private String firstDate;
//    private String secondDate;
    //private Employee employee;
//
//    public FindByDate(String firstDate, String secondDate)
//    {
//        this.firstDate = firstDate;
//        this.secondDate = secondDate;
//    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
//        PreparedStatement statement = connection.prepareStatement(
//                "select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
//                     " from employee, occupations, structure" +
//                     " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.date BETWEEN ? AND ?" +
//                     " ORDER BY employee.date)");
        ResultSet emp = connection.createStatement().executeQuery("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
                " from employee, occupations, structure" +
                " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.date BETWEEN '" + getParameter1() + "' AND '" + getParameter2() + "'" +
                " ORDER BY employee.date;\n");
//        statement.setString(1, firstDate);
//        statement.setString(2, secondDate);
//        ResultSet emp = statement.executeQuery();

        ResultSet occ = connection.createStatement().executeQuery("select * from occupations");

        employee = new Employee();
        employee.initEmployee(emp, occ, true);
    }

//    public Employee getEmployee()
//    {
//        return employee;
//    }
}
