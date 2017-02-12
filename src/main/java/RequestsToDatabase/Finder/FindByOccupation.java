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
public class FindByOccupation implements DatabaseRequest
{
    private int idOccupationForFind;

    public FindByOccupation(String idOccupationForFind)
    {
        this.idOccupationForFind = Integer.valueOf(idOccupationForFind);
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(
                    "select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
                        " from employee, occupations, structure" +
                        " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.id_occ = ?" +
                        " ORDER BY employee.name");
        statement.setInt(1, idOccupationForFind);
        ResultSet emp = statement.executeQuery();

        ResultSet occ = connection.createStatement().executeQuery("select * from occupations");

        Employee.initEmployee(emp, occ, true);
    }
}
