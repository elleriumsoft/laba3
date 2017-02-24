package view.finder;

import Data.Employee;
import Data.EmployeeProcessing;
import Data.OccupationElement;

import java.util.HashMap;
import java.util.Map;

import static view.finder.Constants.*;

/*
 Подготовка формы, в которой собираются параметры поиска по определённому критерию
 */
public class Form
{
    public String build(String action, String by)
    {
        Map<String, String> forms = new HashMap<String, String>()
        {
            {
                put(BY_NAME, byName(action));
                put(BY_OCCUPATION, byOccupation(action));
                put(BY_BIRTH, byBirth(action));
            }
        };

        if (forms.containsKey(by))
        {
            return forms.get(by);
        }
        return "";
    }

    private String byName(String action)
    {
        return
            "                    <form name=\"Find\" method=\"get\" action=" + action +
                "                        <b>Введите имя полностью или его часть</b><br>\n" +
                "                        <input type=\"text\" id=\"Editbox1\" name=\"" + NAME +"\" value=\"\"  maxlength=\"125\">\n" +
                "                        <input type=\"hidden\" name=\"find\" value=\"" + BY_NAME + "\"/>\n" +
                "                        <input type=\"submit\" id=\"Button10\" value=\"Начать поиск\">\n" +
                "                    </form>";
    }

    private String byOccupation(String action)
    {
        return
            "                <form name=\"Find\" method=\"get\" action=" + action +
                "                   <b>Выберите должность для поиска</b><br>\n" +
                "                   <td>\n" +
                "                   <select size=\"8\" required size = \"1\" name=\""+ OCCUPATION + "\">\n" +
                "                        <option disabled>Выберите должность</option>\n" +
                                         occupations() +
                "                   </select>\n" +
                "                   </td>\n" +
                "                   <br><br>\n" +
                "                   <input type=\"hidden\" name=\"find\" value=\"" + BY_OCCUPATION + "\"/>\n" +
                "                   <td><input type=\"submit\" id=\"Button10\" value=\"Начать поиск\"></td>\n" +
                "                </form>";
    }

    private String occupations()
    {
        StringBuilder builder = new StringBuilder();
        Employee employee = new EmployeeProcessing().loadEmployee(0);
        for (OccupationElement occElement : employee.getOcc())
        {
            builder.append("<option value=" + occElement.getId() + ">" + occElement.getName() + "</option>\n");
        }
        return builder.toString();
    }

    private String byBirth(String action)
    {
        return
            "                <form name=\"Find\" method=\"get\" action=" + action +
                "                        <b>Выберите интервал дат для поиска</b><br>\n" +
                "                        <td><input type=\"date\" id=\"Editbox2\" name=\""+ START_DATE + "\" value=\"\"  maxlength=\"10\"></td>\n" +
                "                        <td><input type=\"date\" id=\"Editbox3\" name=\"" + END_DATE + "\" value=\"\"  maxlength=\"10\"></td>\n" +
                "                        <br><br>\n" +
                "                        <input type=\"hidden\" name=\"find\" value=\"" + BY_BIRTH + "\"/>\n" +
                "                        <td><input type=\"submit\" id=\"Button10\" value=\"Начать поиск\"></td>\n" +
                "                </form>";
    }
}
