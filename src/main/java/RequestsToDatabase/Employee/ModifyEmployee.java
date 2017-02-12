package RequestsToDatabase.Employee;

import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 07.02.2017.
 */
public class ModifyEmployee implements DatabaseRequest
{
    private int element;
    private String empName;
    private int idOcc;
    private String empDate;
    private int idDept;

    public ModifyEmployee(String empName, String empDate, int idOcc, int element, int idDept)
    {
        this.element = element;
        this.empName = empName.toLowerCase();
        this.empDate = empDate;
        this.idOcc = idOcc;
        this.idDept = idDept;
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        PreparedStatement statement;
        if (element == -1)
        {
            statement = connection.prepareStatement("INSERT INTO employee (id_dept, id_occ, name, date)" +
                                                                           " VALUES (?, ?, ?, ?)");
            //connection.createStatement().executeUpdate("INSERT INTO employee (id_dept, id_occ, name, date) VALUES (" + String.valueOf(idDept) + "," + String.valueOf(idOcc) + ",'" + empName + "','" + empDate+ "');\n");
        }
        else
        {
            statement = connection.prepareStatement("UPDATE employee" +
                                                        " SET id_dept=?, id_occ=?, name=?, date=?" +
                                                        " WHERE id=?)");
            statement.setInt(5, element);
            //connection.createStatement().executeUpdate("UPDATE employee SET id_dept=" + String.valueOf(idDept) + ", id_occ=" + String.valueOf(idOcc) + ", name='" + empName + "', date='" + empDate + "' WHERE id=" + String.valueOf(element) + ";\n");
        }
        statement.setInt(1, idDept);
        statement.setInt(2, idOcc);
        statement.setString(3, empName);
        statement.setString(4, empDate);
        statement.executeUpdate();
        statement.close();
    }
}
