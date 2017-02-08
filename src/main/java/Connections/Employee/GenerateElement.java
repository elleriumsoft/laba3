package Connections.Employee;

import Connections.GenerateBody;
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
    private int idEmp;

    public GenerateElement(String command, int id, int idEmp)
    {
        this.command = command;
        this.id = id;
        this.idEmp = idEmp;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        ResultSet emp = connection.createStatement().executeQuery
                ("select employee.id as id, employee.name as name, employee.date as date, occupations.occupation as occ" +
                        " from employee, occupations" +
                        " WHERE employee.id_occ = occupations.id  and employee.id_dept=" + String.valueOf(id) +
                        " ORDER BY occupations.id;\n");
        ResultSet occ = connection.createStatement().executeQuery("select * from occupations" + ";\n");
            Employee.initEmployee(emp, occ, false);
            String result = Employee.printEmployee(command, id, idEmp);
        return result;
    }
}
