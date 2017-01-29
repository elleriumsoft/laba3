package Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public class TestConnection implements GenerateBody
{
    @Override
    public String doBody(Connection connection) throws SQLException
    {

            ResultSet rs = connection.createStatement().executeQuery("select * from employee;\n");

            StringBuilder sb = new StringBuilder();

            sb.append("<body><h2>Employee Details</h2>")
                    .append("<table border=\"1\" cellspacing=10 cellpadding=5>")
                    .append("<th>Employee ID</th>")
                    .append("<th>Employee Name</th>");

            while (rs.next())
            {
                sb.append("<tr>");



                sb.append("<td>" + rs.getInt("id") + "</td>");

                sb.append("<td>" + rs.getString("name") + "</td>");

                sb.append("</tr>");
            }
            sb.append("</table></body><br/>");

            sb.append("<h3>Database Details</h3>");
            sb.append("Database Product: " + connection.getMetaData().getDatabaseProductName() + "<br/>");
            sb.append("Database Driver: " + connection.getMetaData().getDriverName());
            return sb.toString();
        }

}
