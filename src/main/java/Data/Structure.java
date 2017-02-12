package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dmitriy on 26.01.2017.
 */
public class Structure
{
    private static ArrayList<StructureElement> structure;
    private static ArrayList<StructureElement> structureFromDb;
    private static ArrayList<Integer> openElements = new ArrayList<>();

    /**
     * Инициализация структуры из БД в виде дерева
     *
     * @param rs - результат запроса данных о структуре из БД
     * @throws SQLException
     */
    public static void initStructureFromDb(ResultSet rs) throws SQLException
    {
        structureFromDb = new ArrayList<>();
        while (rs.next())
        {
            structureFromDb.add(newElement(rs.getInt("id"), rs.getString("dept"), rs.getInt("parent_id")));
        }
        structure = new ArrayList<>();
        int level = 0;
        int parentId = 0;
        initElement(level, parentId);
    }

    private static void initElement(int level, int parentId)
    {
        StructureElement el;
        for (int i = 0; i < structureFromDb.size(); i++)
        {
            el = structureFromDb.get(i);

            if (el.getParent_id() == parentId)
            {
                StructureElement elementForAdd = newElement(el.getId(), el.getNameDepartment(), el.getParent_id());
                elementForAdd.setLevel(level);
                structure.add(elementForAdd);
                initElement(level+1, el.getId());
            }
        }
    }

    /**
     * Формирование HTML страницы структуры для вывода из уже проинциализированной структуры из БД
     *
     * @param command Добавленная ссылка на команду действия с элементом
     * @return Сформированная HTML страница
     */
    public static String printStructure(String command)
    {
        StringBuilder pw = new StringBuilder("");
        for (StructureElement el : structure)
        {
            if (isElementOpen(el.getParent_id()) || el.getLevel() == 0)
            {
                pw.append(addSpaces(el.getLevel()));
                pw.append(addImageForActionList(isElementOpen(el.getId()), el.getId()));
                pw.append("&nbsp<span><a href=\"/laba3/Servlets.PrintElement?id=" + String.valueOf(el.getId()) + "\">" + el.getNameDepartment() + "</a>&nbsp");
                if (!command.equals("") && !(command.equals("delete") && el.getId() == 1))
                {
                    pw.append("<a href=\"/laba3/Servlets.PrintStructure?command=" + command + "&element=" + el.getId() + "\"style=\"color:#FF0000\">[" + getStringCommand(command) + "]</a>");
                }
                pw.append("</span><br><br>");
            }
        }
        return pw.toString();
    }

    private static boolean isElementOpen(int id)
    {
        boolean isOpen = false;
        for (int i = 0; i < openElements.size(); i++)
        {
            if (openElements.get(i) == id)
            {
                return true;
            }
        }
        return isOpen;
    }

    private static String addSpaces(int level)
    {
        String spaces = "";
        for (int i = 0; i < level; i++)
        {
            spaces = spaces + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        }
        return spaces;
    }

    private static String addImageForActionList(boolean isOpen, int id)
    {
        if (elementHasChild(id))
        {
            if (isOpen)
            {
                return "<a href=\"/laba3/Servlets.PrintStructure?close=" + String.valueOf(id) + "\"><img src=\"images/minus.png\" width=\"14\" height=\"14\" align = \"bottom\" alt=\"Раскрыть список\"</a>";
            } else
            {
                return "<a href=\"/laba3/Servlets.PrintStructure?open=" + String.valueOf(id) + "\"><img src=\"images/plus.png\" width=\"14\" height=\"14\" align = \"bottom\" alt=\"Раскрыть список\"</a>";
            }
        }
        else
        {
            return "<img src=\"images/blank.png\" width=\"14\" height=\"14\" align = \"bottom\" alt=\"Элемент\"";
        }
    }

    private static boolean elementHasChild(int id)
    {
        for (StructureElement element : Structure.getStructure())
        {
            if (element.getParent_id() == id) { return true;}
        }
        return false;
    }

    public static StructureElement newElement(int id, String name, int parent_id)
    {
        StructureElement element = new StructureElement();
        element.setId(id);
        element.setNameDepartment(name);
        element.setParent_id(parent_id);
        return element;
    }

    public static ArrayList<StructureElement> getStructure()
    {
        return structure;
    }

    public static ArrayList<Integer> getOpenElements()
    {
        return openElements;
    }

    public static String getDeptName(int id)
    {
        for (StructureElement element: structure)
        {
            if (element.getId() == id)
            {
                return element.getNameDepartment();
            }
        }
        return "";
    }

//    public static String printStructure(String command)
//    {
//        int level = 1;
//        int parentId = 0;
//        StringBuilder outString = new StringBuilder("<div id=\"multi-derevo\"><ul>");
//        printElement(level, parentId, outString, command);
//        return outString.toString();
//    }
//
//    private static void printElement(int level, int parentId, StringBuilder pw, String command)
//    {
//        StructureElement el;
//        for (int i = 0; i < Structure.getStructure().size(); i++)
//        {
//            el = Structure.getStructure().get(i);
//            pw.append("<ul>");
//            if (el.getParent_id() == parentId)
//            {
//                pw.append("<li><span><a href=\"/laba3/Servlets.PrintElement?id=" + String.valueOf(el.getId()) + "\">" + el.getNameDepartment() + "</a>&nbsp");
//                if (!command.equals(""))
//                {
//                    pw.append("<a href=\"/laba3/Servlets.PrintStructure?command=" + command + "&element=" + el.getId() + "\"style=\"color:#FF0000\">["+ getStringCommand(command) + "]</a>");
//                }
//                pw.append( "</span></li>" + "<br>");
//                printElement(level+1, el.getId(), pw, command);
//            }
//            pw.append("</ul>");
//        }
//    }

    private static String getStringCommand(String command)
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
        else
        {
            return "";
        }
    }
}
