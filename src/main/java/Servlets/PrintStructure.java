package Servlets;

import Connections.ConnectionToDb;
import Connections.GenerateSturcture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dmitriy on 29.01.2017.
 */
@SuppressWarnings("serial")
@WebServlet("/Servlets.PrintStructure")
public class PrintStructure extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.println("<H1><b>Структура мэрии</b></H1>");
        pw.println("<body>");
        pw.print(new ConnectionToDb().writeBody(new GenerateSturcture()));
        pw.println("</body>");
        pw.close();
    }
}
