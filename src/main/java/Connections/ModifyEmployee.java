package Connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 07.02.2017.
 */
public class ModifyEmployee implements GenerateBody
{
    private int element;
    private String empName;
    private int idOcc;
    private String empDate;
    private int idDept;

    public ModifyEmployee(String empName, String empDate, int idOcc, int element, int idDept)
    {
        this.element = element;
        this.empName = empName;
        this.empDate = empDate;
        this.idOcc = idOcc;
        this.idDept = idDept;
    }

    @Override
    public String doBody(Connection connection) throws SQLException
    {
        if (element == -1)
        {
            connection.createStatement().executeUpdate("INSERT INTO employee (id_dept, id_occ, name, date) VALUES (" + String.valueOf(idDept) + "," + String.valueOf(idOcc) + ",'" + empName + "','" + empDate+ "');\n");
        }
        else
        {
            connection.createStatement().executeUpdate("UPDATE employee SET id_dept=" + String.valueOf(idDept) + ", id_occ=" + String.valueOf(idOcc) + ", name='" + empName + "', date='" + empDate + "' WHERE id=" + String.valueOf(element) + ";\n");
        }
        return null;
    }
}
