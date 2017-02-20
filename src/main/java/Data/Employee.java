package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dmitriy on 04.02.2017.
 */
public class Employee
{
    private ArrayList<EmployeeElement> employee = new ArrayList<>();
    private ArrayList<OccupationElement> occ;

    public void initEmployee(ResultSet employeeSet, ResultSet occupationSet, boolean withDept) throws SQLException
    {
        employee = new ArrayList<>();
        while (employeeSet.next())
        {
            EmployeeElement el = new EmployeeElement();
            el.setId(employeeSet.getInt("id"));
            el.setName(employeeSet.getString("name"));
            el.setDate(employeeSet.getString("date"));
            el.setOccupation(employeeSet.getString("occ"));
            if (withDept)
            {
                el.setDept(employeeSet.getString("dept"));
                el.setIdDept(Integer.valueOf(employeeSet.getString("iddept")));
            }
            employee.add(el);
        }

        occ = new ArrayList<>();
        while (occupationSet.next())
        {
            occ.add(new OccupationElement(occupationSet.getInt("id"), occupationSet.getString("occupation")));
        }
    }

    public String printEmployee(String command, int idElement, String idEmpString)
    {
        if (command == null){command = "";}
        if (idEmpString == null){idEmpString = "-1";}
        Integer idEmp = Integer.valueOf(idEmpString);

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
                    result.append("<td><a href=\"/laba3/PrintElementJsp.jsp?id=" + String.valueOf(idElement) + "&command=" + command + "&idemployee=" + element.getId() + "\"style=\"color:#FF0000\">" + getRussianCommand(command) + "</a></td>");
                }
            }

            result.append("</tr>");
        }
        result.append("</table>");
        return result.toString();
    }

    private StringBuilder addEditFields(EmployeeElement element, int idEmp)
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

    private String getRussianCommand(String command)
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

    public  ArrayList<EmployeeElement> getEmployee()
    {
        return employee;
    }

    public  ArrayList<OccupationElement> getOcc()
    {
        return occ;
    }

    public String printFindedEmployee()
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
            result.append("<a href=\"/laba3/PrintElementJsp.jsp?id=" + String.valueOf(element.getIdDept()) + "\">" + element.getDept() + "</a>");
            result.append("</td>");


            result.append("</tr>");
        }
        result.append("</table>");


        return result.toString();

    }
}
