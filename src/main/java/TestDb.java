/**
 * Created by Dmitriy on 28.01.2017.
 */

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/TestDb")
public class TestDb extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=ANSI-1251");
        PrintWriter out = response.getWriter();
        out.print("<html>");
        writeBody(out);
        out.print("</html>");
    }

    private void writeBody(PrintWriter out)
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            out.print(generateBody(connection));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
           // closeConnection(connection);
        }
    }

    private Connection getConnection()
    {
        try
        {
            InitialContext ic = new InitialContext();
            DataSource dataSource = (DataSource) ic.lookup("java:jboss/laba3");
            return dataSource.getConnection();
        }
        catch (NamingException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String generateBody(Connection connection) throws SQLException
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

    private void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}


