package Servlets;

import StructurePackage.Structure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dmitriy on 04.02.2017.
 */
@SuppressWarnings("serial")
@WebServlet("/Servlets.PrintElement")
public class PrintElement extends HttpServlet
{
    private String nameElement = "";
    private int idElement = -1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameterNames().hasMoreElements())
        {
            idElement = Integer.valueOf(req.getParameter("id"));
        }
        else
        {
            resp.sendRedirect("/laba3/Servlets.PrintStructure");
        }
        nameElement = Structure.getDeptName(idElement);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.println("<head>");
        pw.println("<title>" + nameElement + "</title>");
        pw.println("</head>");

        pw.println("<h1 style=\"color:#191970\"");
        pw.println("<h1><b>" + nameElement + "</b></h1>");
        pw.println("<body>");
    }
}
