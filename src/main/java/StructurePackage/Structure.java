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
        int level = 1;
        int parentId = 0;
        StringBuilder outString = new StringBuilder("<div id=\"multi-derevo\"><ul>");
        printElement(level, parentId, outString);
        return outString.toString();
    }

    private static void printElement(int level, int parentId, StringBuilder pw)
    {
        for (int i = 0; i < Structure.getStructure().size(); i++)
        {
            pw.append("<ul>");
            if (Structure.getStructure().get(i).getParent_id() == parentId)
            {
                pw.append("<li><span>"  + Structure.getStructure().get(i).getName() + "</span></li><br>");
                printElement(level+1, Structure.getStructure().get(i).getId(), pw);
            }
            pw.append("</ul>");
        }
    }
}
