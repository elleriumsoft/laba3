package Servlets;

import Connections.AddEmployee;
import Connections.ConnectionToDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy on 04.02.2017.
 */
@SuppressWarnings("serial")
@WebServlet("/Servlets.EditBoxesForElement")
public class EditBoxesForElement extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameterNames().hasMoreElements())
        {
            if (req.getParameter("AddNameLine") != null)
            {
                int idForAction  = Integer.valueOf((String)req.getSession().getAttribute("idforaction2"));

                new ConnectionToDb().writeBody(new AddEmployee(req.getParameter("AddNameLine"), req.getParameter("AddDateLine"), Integer.valueOf(req.getParameter("occ")), idForAction));
            }

            resp.sendRedirect("/laba3/Servlets.PrintElement");
        }
    }

}

