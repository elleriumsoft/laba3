package Servlets; /**
 * Created by Dmitriy on 28.01.2017.
 */

import Connections.ConnectionToDb;
import Connections.TestConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlets.TestDb")
public class TestDb extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print(new ConnectionToDb().writeBody(new TestConnection()));
        out.print("</html>");
    }

}


