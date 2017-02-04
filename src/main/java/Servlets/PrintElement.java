package Servlets;

import Connections.ConnectionToDb;
import Connections.GenerateElement;
import Data.Structure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dmitriy on 04.02.2017.
 */
@SuppressWarnings("serial")
@WebServlet("/Servlets.PrintElement")
public class PrintElement extends HttpServlet
{
    private String nameElement = "";
    private int idElement = -1;
    private String command = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        getParameters(req, resp);
        nameElement = Structure.getDeptName(idElement);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.println("<head>");
        pw.println("<title>" + nameElement + "</title>");
        pw.println("</head>");

        pw.println("<body>");
        pw.println("<h1 style=\"color:#191970\"");
        pw.println("<h1><b>" + nameElement + "</b></h1>");
        pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure';return false;\" name=\"\" value=\"Вернуться к структуре\" style=\"position:absolute;left:8px;top:50px;width:184px;height:25px;z-index:0;\">");
//        pw.println("<a href=\"/laba3/Servlets.PrintStructure\"style=\"color:#808080\">[вернуться к структуре]</a>");
        pw.println("<br>");
        pw.println("<h2 style=\"color:#191970\"");
        pw.println("<h2><b>Сотрудники</b></h2>");
        printButtons(pw);
        pw.println("<br>");

        pw.print(new ConnectionToDb().writeBody(new GenerateElement(command, idElement)));
        pw.println("</body>");
    }

    private String getLink(String command)
    {
        return "'/laba3/Servlets.PrintElement?id=" + String.valueOf(idElement) + "&command=" + command + "'";
    }

    private void getParameters(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        if (req.getParameterNames().hasMoreElements())
        {
            idElement = Integer.valueOf(req.getParameter("id"));
            if (req.getParameter("command") != null)
            {
                command = req.getParameter("command");
            }
            else
            {
                command = "";
            }
        } else
        {
            resp.sendRedirect("/laba3/Servlets.PrintStructure");
        }
    }

    private void printButtons(PrintWriter pw)
    {
        if (command.equals(""))
        {
            pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href=" + getLink("add") + ";return false;\" name=\"\" value=\"Добавить\" style=\"position:absolute;left:8px;top:136px;width:104px;height:25px;color:#FF0000;\">");
            pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href=" + getLink("edit") + ";return false;\" name=\"\" value=\"Редактировать\" style=\"position:absolute;left:120px;top:136px;width:104px;height:25px;color:#FF0000;\">");
            pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href=" + getLink("delete") + ";return false;\" name=\"\" value=\"Удалить\" style=\"position:absolute;left:234px;top:136px;width:104px;height:25px;color:#FF0000;\">");
            //pw.println("<a href=" + getLink("add") + ">Добавить</a>&nbsp<a href=" + getLink("edit") + ">Редактировать</a>&nbsp<a href=" + getLink("delete") + ">Удалить</a>&nbsp");
        }
        else
        {
            pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintElement?id=" + String.valueOf(idElement) + "';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:8px;top:136px;width:104px;height:25px;color:#FF0000;\">");
            //pw.println("<a href=\"/laba3/Servlets.PrintElement?id=" + String.valueOf(idElement) + "\">Отмена</a>");
        }
    }
}
