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
    private static ArrayList<OccupationElement> occ;

    public static void initEmployee(ResultSet employee, ResultSet occ, boolean withDept) throws SQLException
    {
        Employee.employee = new ArrayList<>();
        while (employee.next())
        {
            EmployeeElement el = new EmployeeElement();
            el.setId(employee.getInt("id"));
            el.setName(employee.getString("name"));
            el.setDate(employee.getString("date"));
            el.setOccupation(employee.getString("occ"));
            if (withDept)
            {
                el.setDept(employee.getString("dept"));
                el.setIdDept(Integer.valueOf(employee.getString("iddept")));
            }
            Employee.employee.add(el);
        }

        Employee.occ = new ArrayList<>();
        while (occ.next())
        {
            Employee.occ.add(new OccupationElement(occ.getInt("id"), occ.getString("occupation")));
        }
    }

    public static String printEmployee(String command, int idElement, int idEmp)
    {
        StringBuilder result = new StringBuilder("<table border>");
        result.append("<th>Фамилия Имя Отчество</th><th>Дата рождения</th><th>Должность</th>");
        if (command.equals("add"))
        {
            result.append(addEditFields(null, idEmp));
        }
        for (EmployeeElement element : employee)
        {
            if (idEmp == element.getId())
            {
                result.append(addEditFields(element, idEmp));
            }
            else
            {
                result.append("<tr>");

                result.append("<td>");
                result.append(element.getBeautifulName());
                result.append("</td>");

                result.append("<td>");
                result.append(element.getBeautifulDate());
                result.append("</td>");

                result.append("<td>");
                result.append(element.getOccupation());
                result.append("</td>");

                if (!command.equals("") && !(command.equals("add")))
                {
                    result.append("<td><a href=\"/laba3/Servlets.PrintElement?id=" + String.valueOf(idElement) + "&command=" + command + "&idemployee=" + element.getId() + "\"style=\"color:#FF0000\">" + getRussianCommand(command) + "</a></td>");
                }
            }

            result.append("</tr>");
        }
        result.append("</table>");
        return result.toString();
    }

    private static StringBuilder addEditFields(EmployeeElement element, int idEmp)
    {
        StringBuilder editFields = new StringBuilder("<tr>");
        editFields.append("<form name=\"add\" method=\"get\" action=\"/laba3/Servlets.EditBoxesForElement\">");
        if (idEmp == -1)
        {
            editFields.append("<td><input type=\"text\" id=\"Editbox1\" name=\"AddNameLine\" value=\"\"  maxlength=\"125\"></td>");
            editFields.append("<td><input type=\"date\" id=\"Editbox2\" name=\"AddDateLine\" value=\"\"  maxlength=\"10\"></td>");
        } else
        {
            editFields.append("<td><input type=\"text\" id=\"Editbox1\" value=\"" + element.getBeautifulName() + "\" name=\"AddNameLine\" value=\"\"  maxlength=\"125\"></td>");
            editFields.append("<td><input type=\"date\" id=\"Editbox2\" value=\"" + element.getDate() + "\" name=\"AddDateLine\" value=\"\"  maxlength=\"10\"></td>");
        }

        editFields.append("<td>");
        editFields.append("<select size=\"2\" required size = \"1\" name=\"occ\">");
        editFields.append("<option disabled>Выберите должность</option>");

        if (idEmp == -1)
        {
            for (OccupationElement occElement : occ)
            {
                editFields.append("<option value=\"" + occElement.getId() + "\">" + occElement.getName() + "</option>");
            }
        }
        else
        {
            for (int i = 0; i < occ.size(); i++)
            {
                if (occ.get(i).getName().equals(element.getOccupation()))
                {
                    editFields.append("<option value=\"" + occ.get(i).getId() + "\" selected>" + occ.get(i).getName() + "</option>");
                }
                else
                {
                    editFields.append("<option value=\"" + occ.get(i).getId() + "\">" + occ.get(i).getName() + "</option>");
                }
            }
        }
        editFields.append("</select>");
        editFields.append("</td>");

        if (idEmp == -1)
        {
            editFields.append("<td><input type=\"submit\" id=\"Button1\" name=\"\" value=\"Добавить\"></td>");
        }
        else
        {
            editFields.append("<td><input type=\"submit\" id=\"Button1\" name=\"\" value=\"Отредактировать\"></td>");
        }
        editFields.append("</form></tr>");
        return editFields;
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

    public static ArrayList<OccupationElement> getOcc()
    {
        return occ;
    }

    public static String printFindedEmployee()
    {
        StringBuilder result = new StringBuilder("<table border>");
        result.append("<th>Фамилия Имя Отчество</th><th>Дата рождения</th><th>Должность</th><th>Элемент структуры</th>");
        for (EmployeeElement element : employee)
        {
            result.append("<tr>");

            result.append("<td>");
            result.append(element.getBeautifulName());
            result.append("</td>");

            result.append("<td>");
            result.append(element.getBeautifulDate());
            result.append("</td>");

            result.append("<td>");
            result.append(element.getOccupation());
            result.append("</td>");

            result.append("<td>");
            result.append("<a href=\"/laba3/Servlets.PrintElement?id=" + String.valueOf(element.getIdDept())+ "\">" + element.getDept() + "</a>");
            result.append("</td>");


            result.append("</tr>");
        }
        result.append("</table>");
        return result.toString();
    }
}
