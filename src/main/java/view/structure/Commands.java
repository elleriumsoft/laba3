package view.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitriy on 26.02.2017.
 */
public class Commands
{
    private final static String COMMAND_ADD = "add";
    private final static String COMMAND_EDIT = "edit";

    public String build(String element, String command)
    {
        if (element == null || command == null)
        {
            return "<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/PrintStructure.jsp';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:9px;top:51px;width:104px;height:25px;color:#FF0000;\">";
        }


        Map<String, String> commands = new HashMap<String, String>()
        {
            {
                put(COMMAND_ADD, commandAdd(element));
                put(COMMAND_EDIT, commandEdit(element));
            }
        };

        if (commands.containsKey(command))
        {
            return commands.get(command);
        }
        return "";
    }

    private String commandAdd(String element)
    {
        return "Добавьте элемент в " + element + ":<br>" +
               "<form name=\"add\" method=\"get\" action=\"/laba3/Servlets.EditBoxesForStructure\">" +
               "<input type=\"text\" id=\"Editbox1\" style=\"position:absolute;line-\" name=\"EditAdd\" value=\"\"  maxlength=\"200\">" +
               "<input type=\"submit\" id=\"Button1\" \" name=\"\" value=\"ОК\" style=\"position:absolute;left:240px;top:83px;width:104px;height:23px;z-index:0;\">" +
               "<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/PrintStructure.jsp';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:350px;top:83px;width:104px;height:23px;z-index:0;\">" +
               "</form>";
    }

    private String commandEdit(String element)
    {
        return "Новое название для " + element + ":<br>" +
                "<form name=\"add\" method=\"get\" action=\"/laba3/Servlets.EditBoxesForStructure\">" +
                "<input type=\"text\" id=\"Editbox1\" style=\"position:absolute;line-\" name=\"EditEdit\" maxlength=\"200\" value=\"" + element + "\" >" +
                "<input type=\"submit\" id=\"Button1\" \" name=\"\" value=\"ОК\" style=\"position:absolute;left:240px;top:83px;width:104px;height:23px;z-index:0;\">" +
                "<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/PrintStructure.jsp';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:350px;top:83px;width:104px;height:23px;z-index:0;\">" +
                "</form>";
    }
}
