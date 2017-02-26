package Servlets;

import Data.Structure;
import Data.StructureProcessing;
import RequestsToDatabase.ConnectionToDb;
import RequestsToDatabase.Structure.AddElement;
import RequestsToDatabase.Structure.EditElement;

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

                new ConnectionToDb().connectToDb(new AddElement(req.getParameter("EditAdd"), idForAction));

                Structure structure = StructureProcessing.loadStructure(req);
                structure.getOpenElements().add(idForAction);
                StructureProcessing.saveStructure(req, structure);
            }
            if (req.getParameter("EditEdit") != null)
            {
                int idForAction  = Integer.valueOf((String)req.getSession().getAttribute("idforaction"));

                new ConnectionToDb().connectToDb(new EditElement(req.getParameter("EditEdit"), idForAction));

            }
            resp.sendRedirect("/laba3/PrintStructure.jsp?renew=1");
        }
    }

}
