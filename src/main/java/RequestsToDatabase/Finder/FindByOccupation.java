package RequestsToDatabase.Finder;

import Data.Employee;
import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 08.02.2017.
 */
public class FindByOccupation extends DatabaseRequestForFinder implements DatabaseRequest
{
//    private String  idOccupationForFind;
//    private Employee employee;
//
//    public FindByOccupation(String idOccupationForFind)
//    {
//        this.idOccupationForFind = idOccupationForFind;
//    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
//        PreparedStatement statement = connection.prepareStatement(
//                    "select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
//                        " from employee, occupations, structure" +
//                        " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.id_occ = ?" +
//                        " ORDER BY employee.name");
//        statement.setInt(1, idOccupationForFind);
//        ResultSet emp = statement.executeQuery();
        ResultSet emp =  connection.createStatement().executeQuery("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
                                                                    " from employee, occupations, structure" +
                                                                    " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.id_occ =" + getParameter1() +
                                                                    " ORDER BY employee.name");

        ResultSet occ = connection.createStatement().executeQuery("select * from occupations");

        employee = new Employee();
        employee.initEmployee(emp, occ, true);
    }

//    public Employee getEmployee()
//    {
//        return employee;
//    }
}
