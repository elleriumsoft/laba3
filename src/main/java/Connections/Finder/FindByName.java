package Connections.Finder;

import Connections.GenerateBody;
import Data.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 08.02.2017.
 */
public class FindByName implements GenerateBody
{
    private String nameForFind;

    public FindByName(String nameForFind)
    {
        this.nameForFind = nameForFind.toLowerCase();
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        ResultSet emp = connection.createStatement().executeQuery("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ, structure.dept as dept, structure.id as iddept" +
                                                                       " from employee, occupations, structure" +
                                                                       " WHERE employee.id_occ = occupations.id  and employee.id_dept = structure.id and employee.name LIKE '%" + nameForFind + "%'" +
                                                                       " ORDER BY employee.name;\n");
        ResultSet occ = connection.createStatement().executeQuery("select * from occupations" + ";\n");
        Employee.initEmployee(emp, occ, true);
        String result = Employee.printFindedEmployee();
        return result;
    }
}