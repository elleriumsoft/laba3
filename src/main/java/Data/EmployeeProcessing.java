package Data;

import RequestsToDatabase.ConnectionToDb;
import RequestsToDatabase.Employee.GenerateElement;
import RequestsToDatabase.Finder.FindByDate;
import RequestsToDatabase.Finder.FindByName;
import RequestsToDatabase.Finder.FindByOccupation;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitriy on 19.02.2017.
 */
public class EmployeeProcessing
{
    public Employee loadEmployee(int idElement)
    {
        GenerateElement generateElement = new GenerateElement(idElement);
        new ConnectionToDb().connectToDb(generateElement);
        return generateElement.getEmployee();
    }

    public String findElements(HttpServletRequest req)
    {
        if (req.getParameter("FindName") != null ||
                req.getParameter("FindOcc") != null ||
                    (req.getParameter("FirstDate") != null && req.getParameter("SecondDate") != null))
        {

            if (req.getParameter("FindName") != null)
            {
                FindByName findByName = new FindByName(req.getParameter("FindName"));
                new ConnectionToDb().connectToDb(findByName);
                return findByName.getEmployee().printFindedEmployee();
            }

            if (req.getParameter("FindOcc") != null)
            {
                FindByOccupation findByOccupation = new FindByOccupation(req.getParameter("FindOcc"));
                new ConnectionToDb().connectToDb(findByOccupation);
                return findByOccupation.getEmployee().printFindedEmployee();
            }

            if (req.getParameter("FirstDate") != null && req.getParameter("SecondDate") != null)
            {
                FindByDate findByDate = new FindByDate(req.getParameter("FirstDate"), req.getParameter("SecondDate"));
                new ConnectionToDb().connectToDb(findByDate);
                return findByDate.getEmployee().printFindedEmployee();
            }

            return "-";
        }
        else
        {
            return "-";
        }
    }
}
