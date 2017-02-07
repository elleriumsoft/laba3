package Servlets;

import Connections.ConnectionToDb;
import Connections.Finder.FindByName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dmitriy on 08.02.2017.
 */
@SuppressWarnings("serial")
@WebServlet("/Servlets.Finder")
public class Finder extends HttpServlet
{
    private int selectFinder;
    private String resultFind;
    private PrintWriter pw;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        getParameters(req, resp);

        resp.setContentType("text/html;charset=utf-8");
        pw = resp.getWriter();

        pw.println("<head>");
        pw.println("<title>Поиск</title>");
        pw.println("</head>");

        pw.println("<h1 style=\"color:#191970\"");
        pw.println("<h1><b>Поиск сотрудника в мэрии</b></h1>");
        pw.println("<body>");

        pw.println("<br>");

        printButtons();

        if (selectFinder != -1)
        {
            printInputForFind();
        }

        if (!resultFind.equals("-"))
        {
            pw.println("<br>");
            if (!resultFind.equals(""))
            {
                pw.println(resultFind);
            } else
            {
                pw.println("<b>Не найдено ни одного сотрудника!</b>");
            }
        }

        pw.println("</body>");
        pw.close();
    }

    private void printInputForFind()
    {
        pw.println("<br><form name=\"find\" method=\"get\" action=\"/laba3/Servlets.Finder\">");
        switch (selectFinder)
        {
            case 1:
                pw.println("<b>Введите имя полнстью или его часть</b>");
                pw.println("<td><input type=\"text\" id=\"Editbox1\" name=\"FindName\" value=\"\"  maxlength=\"125\"></td>");
                break;
            case 2:
                pw.println("<td><input type=\"text\" id=\"Editbox1\" name=\"FindOcc\" value=\"\"  maxlength=\"2\"></td>");
            case 3:
                pw.println("<td><input type=\"text\" id=\"Editbox1\" name=\"FindDate\" value=\"\"  maxlength=\"10\"></td>");
        }
        pw.println("<td><input type=\"submit\" id=\"Button1\" name=\"\" value=\"Поиск\"></td>");
        pw.println("</form>");

    }

    private void printButtons()
    {
        pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/index.jsp';return false;\" name=\"\" value=\"Вернуться в меню\" style=\"position:absolute;left:460px;top:18px;width:184px;height:25px;\">");
        if (selectFinder == -1)
        {
            pw.println("<input type=\"submit\" id=\"Button2\" onclick=\"window.location.href='/laba3/Servlets.Finder?find=1';return false;\" name=\"\" value=\"По имени\" style=\"position:absolute;left:6px;top:70px;width:175px;height:25px;color:#FF0000;\">");
            pw.println("<input type=\"submit\" id=\"Button3\" onclick=\"window.location.href='/laba3/Servlets.Finder?find=2';return false;\" name=\"\" value=\"По должности\" style=\"position:absolute;left:186px;top:70px;width:175px;height:25px;color:#FF0000;\">");
            pw.println("<input type=\"submit\" id=\"Button4\" onclick=\"window.location.href='/laba3/Servlets.Finder?find=3';return false;\" name=\"\" value=\"По дате рождения\" style=\"position:absolute;left:366px;top:70px;width:175px;height:25px;color:#FF0000;\">");
        }
        else
        {
            pw.println("<input type=\"submit\" id=\"Button2\" onclick=\"window.location.href='/laba3/Servlets.Finder';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:6px;top:50px;width:95px;height:25px;\">");
        }
    }

    private void getParameters(HttpServletRequest req, HttpServletResponse resp)
    {
        selectFinder = -1;
        resultFind = "-";
        if (req.getParameterNames().hasMoreElements())
        {
            if (req.getParameter("find") != null)
            {
                selectFinder = Integer.valueOf(req.getParameter("find"));
            }

            if (req.getParameter("FindName") != null)
            {
                resultFind = new ConnectionToDb().writeBody(new FindByName(req.getParameter("FindName")));
            }
        }
    }
}
