package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dmitriy on 04.02.2017.
 */
public class Employee
{
    private static ArrayList<EmployeeElement> employee;

    public static void initEmployee(ResultSet rs) throws SQLException
    {
        employee = new ArrayList<>();
        while (rs.next())
        {
            EmployeeElement el = new EmployeeElement();
            el.setId(rs.getInt("id"));
            el.setName(rs.getString("name"));
            el.setDate(rs.getString("date"));
            el.setOccupation(rs.getString("occ"));
            employee.add(el);
        }
    }

    public static String printEmployee(String command, int idElement)
    {
        StringBuilder result = new StringBuilder("<table border>");
        result.append("<th>Фамилия Имя Отчество</th><th>Дата рождения</th><th>Должность</th>");

        for (EmployeeElement element : employee)
        {
            result.append("<tr>");

            result.append("<td>");
            result.append(element.getName());
            result.append("</td>");

            result.append("<td>");
            result.append(element.getDate());
            result.append("</td>");

            result.append("<td>");
            result.append(element.getOccupation());
            result.append("</td>");

            if (!command.equals(""))
            {
                result.append("<td>");
                result.append("<a href=\"/laba3/Servlets.PrintElement?id=" + String.valueOf(idElement) + "&command=" + command + "&idemployee=" + element.getId() + "\"style=\"color:#FF0000\">" + getRussianCommand(command) + "</a>");

                result.append("</td>");
            }

            result.append("</tr>");
        }
        result.append("</table>");
        return result.toString();
    }

    private static String getRussianCommand(String command)
    {
        if (command.equals("add"))
        {
            return "Добавить";
        }
        else if (command.equals("edit"))
        {
            return "Редактировать";
        }
        else if (command.equals("delete"))
        {
            return "Удалить";
        }
        return "";
    }

    public static ArrayList<EmployeeElement> getEmployee()
    {
        return employee;
    }
}
