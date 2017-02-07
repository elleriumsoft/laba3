package Servlets;

import Connections.Structure.AddElement;
import Connections.ConnectionToDb;
import Connections.Structure.EditElement;

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
@WebServlet("/Servlets.EditBoxesForStructure")
public class EditBoxesForStructure extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameterNames().hasMoreElements())
        {
            if (req.getParameter("EditAdd") != null)
            {
                int idForAction  = Integer.valueOf((String)req.getSession().getAttribute("idforaction"));

                new ConnectionToDb().writeBody(new AddElement(req.getParameter("EditAdd"), idForAction));
            }
            if (req.getParameter("EditEdit") != null)
            {
                int idForAction  = Integer.valueOf((String)req.getSession().getAttribute("idforaction"));

                new ConnectionToDb().writeBody(new EditElement(req.getParameter("EditEdit"), idForAction));

            }

            resp.sendRedirect("/laba3/Servlets.PrintStructure");

        }
    }

}
