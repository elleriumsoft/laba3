package view.finder;

import Data.Employee;
import java.util.Map;
import java.util.TreeMap;

import static view.finder.Constants.*;

/**
 * Определяем по команде какой поисковик запустить
 */
public class Finder
{
    private Map<String, Command> commands;

    public Finder()
    {
        commands = new TreeMap<>();
        commands.put(BY_NAME, new ByNameCommand());
        commands.put(BY_BIRTH, new ByDateCommand());
        commands.put(BY_OCCUPATION, new ByOccupationCommand());
    }

    public String execute(String by, Map<String, String[]> parameters)
    {
        if (by != null && commands.containsKey(by))
        {
            Employee employee = commands.get(by).execute(parameters);
            return employee.printFindedEmployee();
        }
        return "";
    }
}