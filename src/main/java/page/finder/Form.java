package page.finder;

import Data.Employee;
import Data.EmployeeProcessing;
import Data.OccupationElement;

import java.util.HashMap;
import java.util.Map;

public class Form
{
    private String action;

    public Form setAction(String action)
    {
        this.action = action;
        return this;
    }

    public String build(String form)
    {
        Map<String, String> forms = new HashMap<String, String>()
        {
            {
                put("byName", byName());
                put("byOccupation", byOccupation());
                put("byBirth", byBirth());
            }
        };

        if (forms.containsKey(form))
        {
            return forms.get(form);
        }
        return "";
    }

    private String byName()
    {
        String form =
            "                    <form name=\"Find\" method=\"get\" action=" + action +
                "                        <b>Введите имя полностью или его часть</b><br>\n" +
                "                        <input type=\"text\" id=\"Editbox1\" name=\"FindName\" value=\"\"  maxlength=\"125\">\n" +
                "                        <input type=\"submit\" id=\"Button10\" value=\"Начать поиск\">\n" +
                "                    </form>";
        return form;
    }

    private String byOccupation()
    {
        String form =
            "                <form name=\"Find\" method=\"get\" action=" + action +
                "                   <b>Выберите должность для поиска</b><br>\n" +
                "                   <td>\n" +
                "                   <select size=\"8\" required size = \"1\" name=\"FindOcc\">\n" +
                "                        <option disabled>Выберите должность</option>" +
                occupations() +
                "                   </select>\n" +
                "                   </td>\n" +
                "                   <br><br>\n" +
                "                   <td><input type=\"submit\" id=\"Button10\" value=\"Начать поиск\"></td>\n" +
                "                </form>";
        return form;
    }

    private String occupations()
    {
        StringBuilder builder = new StringBuilder();
        Employee employee = new EmployeeProcessing().loadEmployee(0);
        for (OccupationElement occElement : employee.getOcc())
        {
            builder.append("<option value=" + occElement.getId() + ">" + occElement.getName() + "</option>");
        }
        return builder.toString();
    }

    private String byBirth()
    {
        String form =
            "                <form name=\"Find\" method=\"get\" action=" + action +
                "                        <b>Выберите интервал дат для поиска</b><br>\n" +
                "                        <td><input type=\"date\" id=\"Editbox2\" name=\"Date1\" value=\"\"  maxlength=\"10\"></td>\n" +
                "                        <td><input type=\"date\" id=\"Editbox3\" name=\"Date2\" value=\"\"  maxlength=\"10\"></td>\n" +
                "                        <br><br>\n" +
                "                        <td><input type=\"submit\" id=\"Button10\" value=\"Начать поиск\"></td>\n" +
                "                </form>";
        return form;
    }
}
