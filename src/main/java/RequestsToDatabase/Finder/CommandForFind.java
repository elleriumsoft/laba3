package RequestsToDatabase.Finder;

import RequestsToDatabase.ConnectionToDb;
import RequestsToDatabase.DatabaseRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * Определяем по команде какой поисковик запустить
 * В commands список команд
 * Если команда имеет 2 параметра, то они называются одинаково, но в конце отличаются цифрами 1 и 2,
 * в список команд заносится только первая
 */
public class CommandForFind
{
    private Map<String, DatabaseRequestForFinder> commands;

    public CommandForFind()
    {
        commands = new TreeMap<String, DatabaseRequestForFinder>();
        commands.put("FindName", new FindByName());
        commands.put("Date1", new FindByDate());
        commands.put("FindOcc", new FindByOccupation());
    }

    public String findElements(HttpServletRequest req)
    {

        Enumeration<String> parameters = req.getParameterNames();
        while (parameters.hasMoreElements())
        {
            String parameter = parameters.nextElement();
            DatabaseRequestForFinder finder = commands.get(parameter);
            if (finder != null)
            {
                if (parameter.substring(parameter.length()-1, parameter.length()).equals("1"))
                {
                    System.out.println(parameter.substring(0, parameter.length()-1)+"2");
                    finder.setParameters(req.getParameter(parameter), req.getParameter(parameter.substring(0, parameter.length()-1)+"2"));
                }
                else
                {
                    finder.setParameters(req.getParameter(parameter), req.getParameter(""));
                }
                new ConnectionToDb().connectToDb((DatabaseRequest)finder);
                return finder.getEmployee().printFindedEmployee();
            }
        }
        return "-";
//        if (req.getParameter("FindName") != null ||
//                req.getParameter("FindOcc") != null ||
//                (req.getParameter("FirstDate") != null && req.getParameter("SecondDate") != null))
//        {
//
//            if (req.getParameter("FindName") != null)
//            {
//                FindByName findByName = new FindByName(req.getParameter("FindName"));
//                new ConnectionToDb().connectToDb(findByName);
//                return findByName.getEmployee().printFindedEmployee();
//            }
//
//            if (req.getParameter("FindOcc") != null)
//            {
//                FindByOccupation findByOccupation = new FindByOccupation(req.getParameter("FindOcc"));
//                new ConnectionToDb().connectToDb(findByOccupation);
//                return findByOccupation.getEmployee().printFindedEmployee();
//            }
//
//            if (req.getParameter("FirstDate") != null && req.getParameter("SecondDate") != null)
//            {
//                FindByDate findByDate = new FindByDate(req.getParameter("FirstDate"), req.getParameter("SecondDate"));
//                new ConnectionToDb().connectToDb(findByDate);
//                return findByDate.getEmployee().printFindedEmployee();
//            }
//
//            return "-";
//        }
//        else
//        {
//            return "-";
//        }
    }
}
