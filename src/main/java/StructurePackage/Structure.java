package StructurePackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dmitriy on 26.01.2017.
 */
public class Structure
{
    private static ArrayList<StructureElement> structure;

    public static  void initStructure()
    {
        structure = new ArrayList<>();
        addElement(1, "Мэр", 0);
        addElement(2, "Первый заместитель мэра", 1);
        addElement(3, "Заместитель мэра по городскому хозяйству", 1);
        addElement(14, "Департамент финансов", 2);
        addElement(19, "Департамент дорожного хозяйства и транспорта", 3);
        addElement(40, "Административный отдел", 14);
    }

    public static void initStructureFromDb(ResultSet rs) throws SQLException
    {
        structure = new ArrayList<>();
        while (rs.next())
        {
            addElement(rs.getInt("id"), rs.getString("dept"), rs.getInt("parent_id"));
        }
    }

    public static void addElement(int id, String name, int parent_id)
    {
        StructureElement element = new StructureElement();
        element.setId(id);
        element.setName(name);
        element.setParent_id(parent_id);
        structure.add(element);
    }

    public static ArrayList<StructureElement> getStructure()
    {
        return structure;
    }

    public static String printStructure()
    {
        int parentId = 0;
        String indent = "";
        StringBuilder outString = new StringBuilder();
        printElement(parentId, indent, outString);
        return outString.toString();
    }

    private static void printElement(int parentId, String indent, StringBuilder pw)
    {
        for (int i = 0; i < Structure.getStructure().size(); i++)
        {
            if (Structure.getStructure().get(i).getParent_id() == parentId)
            {
                pw.append(indent + Structure.getStructure().get(i).getName() + "<br>");
                printElement(Structure.getStructure().get(i).getId(), indent + "--", pw);
            }
        }
    }
}
