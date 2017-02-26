package Data;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dmitriy on 26.01.2017.
 */
public class Structure
{
    private ArrayList<StructureElement> structure;
    private ArrayList<StructureElement> structureFromDb;
    private ArrayList<Integer> openElements = new ArrayList<>();

    /**
     * Инициализация структуры из БД в виде дерева
     *
     * @param rs - результат запроса данных о структуре из БД
     * @throws SQLException
     */
    public void initStructureFromDb(ResultSet rs) throws SQLException
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

    /**
     * Рекурсивное добавление элементов дерева в лист с указанием уровня
     * @param level - уровень погружения
     * @param parentId - предок
     */
    private void initElement(int level, int parentId)
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
    public String printStructure(String command)
    {
        if (command == null){command = "";}
        StringBuilder pw = new StringBuilder("");
        for (StructureElement el : structure)
        {
            if (isElementOpen(el.getParent_id()) || el.getLevel() == 0)
            {
                pw.append(addSpaces(el.getLevel()));
                pw.append(addImageForActionList(isElementOpen(el.getId()), el.getId()));
                pw.append("&nbsp<span><a href=\"/laba3/PrintElementJsp.jsp?id=" + String.valueOf(el.getId()) + "\">" + el.getNameDepartment() + "</a>&nbsp");//pw.append("&nbsp<span><a href=\"/laba3/Servlets.PrintElement?id=" + String.valueOf(el.getId()) + "\">" + el.getNameDepartment() + "</a>&nbsp");
                if (!command.equals("") && !(command.equals("delete") && el.getId() == 1))
                {
                    pw.append("<a href=\"/laba3/PrintStructure.jsp?command=" + command + "&element=" + el.getId() + "\"style=\"color:#FF0000\">[" + getStringCommand(command) + "]</a>");
                }
                pw.append("</span><br><br>");
            }
        }
        return pw.toString();
    }

    private boolean isElementOpen(int id)
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

    private String addSpaces(int level)
    {
        String spaces = "";
        for (int i = 0; i < level; i++)
        {
            spaces = spaces + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        }
        return spaces;
    }

    private String addImageForActionList(boolean isOpen, int id)
    {
        if (elementHasChild(id))
        {
            if (isOpen)
            {
                return "<a href=\"/laba3/PrintStructure.jsp?close=" + String.valueOf(id) + "\"><img src=\"images/minus.png\" width=\"14\" height=\"14\" align = \"bottom\" alt=\"Раскрыть список\"</a>";
            } else
            {
                return "<a href=\"/laba3/PrintStructure.jsp?open=" + String.valueOf(id) + "\"><img src=\"images/plus.png\" width=\"14\" height=\"14\" align = \"bottom\" alt=\"Раскрыть список\"</a>";
            }
        }
        else
        {
            return "<img src=\"images/blank.png\" width=\"14\" height=\"14\" align = \"bottom\" alt=\"Элемент\"";
        }
    }

    private boolean elementHasChild(int id)
    {
        for (StructureElement element : structure)
        {
            if (element.getParent_id() == id) { return true;}
        }
        return false;
    }

    public StructureElement newElement(int id, String name, int parent_id)
    {
        StructureElement element = new StructureElement();
        element.setId(id);
        element.setNameDepartment(name);
        element.setParent_id(parent_id);
        return element;
    }

    public ArrayList<StructureElement> getStructure()
    {
        return structure;
    }

    public ArrayList<Integer> getOpenElements()
    {
        return openElements;
    }

    public void setOpenElements(ArrayList<Integer> openElements)
    {
        this.openElements = openElements;
    }

    public String getDeptName(String stringId)
    {
        int id;
        try
        {
            id = Integer.valueOf(stringId);
        }
        catch (Exception ex)
        {
            return "";
        }
        for (StructureElement element: structure)
        {
            if (element.getId() == id)
            {
                return element.getNameDepartment();
            }
        }
        return "";
    }

    private String getStringCommand(String command)
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

    public void verifyForOpenList(HttpServletRequest req)
    {
        if (req.getParameter("open") != null)
        {
            if (req.getParameter("open").equals("all"))
            {
                getOpenElements().clear();
                for (StructureElement element : structure)
                {
                    if (elementHasChild(element.getId()))
                    {
                        getOpenElements().add(element.getId());
                    }
                }
            }
            else
            {
                getOpenElements().add((Integer.valueOf(req.getParameter("open"))));
            }
        }
        if (req.getParameter("close") != null)
        {
            if (req.getParameter("close").equals("all"))
            {
                removeFromOpen(1);
            }
            else
            {
                removeFromOpen(Integer.valueOf(req.getParameter("close")));
            }
        }
    }

    private void removeFromOpen(int id)
    {
        for (int i = 0; i< getOpenElements().size(); i++)
        {

            if (getOpenElements().get(i) == id)
            {
                getOpenElements().remove(i);

                for (int j = 0; j < getStructure().size(); j++)
                {
                    if (structure.get(j).getParent_id() == id)
                    {
                        removeFromOpen(structure.get(j).getId());
                    }
                }
                break;
            }
        }
    }


}
