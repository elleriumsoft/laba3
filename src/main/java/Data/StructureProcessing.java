package Data;

import RequestsToDatabase.ConnectionToDb;
import RequestsToDatabase.Structure.GenerateSturcture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Dmitriy on 18.02.2017.
 */
public class StructureProcessing
{
    public static Structure loadStructure(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        if (request.getParameter("renew") != null)
        {
            Structure structure = (Structure) session.getAttribute("structure");
            ArrayList<Integer> open = structure.getOpenElements();

            GenerateSturcture generateSturcture = new GenerateSturcture();
            new ConnectionToDb().connectToDb(generateSturcture);
            Structure structure1 = generateSturcture.getStructure();

            structure1.setOpenElements(open);
            saveStructure(request, structure1);

            return structure1;
        }
        else
        {
            if (session.getAttribute("structure") == null)
            {
                return renewStructure(request);
            } else
            {
                return (Structure) session.getAttribute("structure");
            }
        }
    }

    public static void saveStructure(HttpServletRequest request, Structure structure)
    {
        HttpSession session = request.getSession();
        session.setAttribute("structure", structure);
    }

    public static Structure renewStructure(HttpServletRequest request)
    {
        GenerateSturcture generateSturcture = new GenerateSturcture();
        new ConnectionToDb().connectToDb(generateSturcture);
        Structure structure = generateSturcture.getStructure();
        saveStructure(request, structure);
        return structure;
    }
}
