import StructurePackage.Structure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dmitriy on 26.01.2017.
 */
@SuppressWarnings("serial")
@WebServlet("/PrintStructure")
public class PrintStructure extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Structure.initStructure();

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        pw.println("<H1><b>Структура мэрии</b></H1>");
        pw.println("<body>");
        printStructure(pw);
        pw.println("</body>");
        pw.close();
    }

    private void printStructure(PrintWriter pw)
    {
        int parentId = 0;
        String indent = "";
        printElement(parentId, indent, pw);
    }

    private void printElement(int parentId, String indent, PrintWriter pw)
    {
        for (int i = 0; i < Structure.getStructure().size(); i++)
        {
            if (Structure.getStructure().get(i).getParent_id() == parentId)
            {
                pw.println(indent + Structure.getStructure().get(i).getName() + "<br>");
                printElement(Structure.getStructure().get(i).getId(), indent + "--", pw);
            }
        }
    }
}
