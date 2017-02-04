package Connections;

import Data.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 04.02.2017.
 */
public class GenerateElement implements GenerateBody
{
    private String command;
    private int id;

    public GenerateElement(String command, int id)
    {
        this.command = command;
        this.id = id;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        String result = "";
        ResultSet rs = connection.createStatement().executeQuery("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ from employee, occupations WHERE employee.id_occ = occupations.id  and employee.id_dept=" + String.valueOf(id) + ";\n");
            Employee.initEmployee(rs);
            result = Employee.printEmployee(command, id);
        return result;
    }
}
