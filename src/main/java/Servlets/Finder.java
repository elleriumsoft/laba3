package Servlets;

import RequestsToDatabase.ConnectionToDb;
import RequestsToDatabase.Employee.GenerateElement;
import RequestsToDatabase.Finder.FindByDate;
import RequestsToDatabase.Finder.FindByName;
import RequestsToDatabase.Finder.FindByOccupation;
import Data.Employee;
import Data.OccupationElement;

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
        pw.println("<br><form name=\"Find\" method=\"get\" action=\"/laba3/Servlets.Finder\">");
        switch (selectFinder)
        {
            case 1:
                pw.println("<b>Введите имя полностью или его часть</b><br>");
                pw.println("<td><input type=\"text\" id=\"Editbox1\" name=\"FindName\" value=\"\"  maxlength=\"125\"></td>");
                break;
            case 2:
                pw.println("<b>Выберите должность для поиска</b><br>");
                new ConnectionToDb().connectToDb(new GenerateElement(0));
                pw.println(generateSelectOccupations());
                pw.println("<br><br>");
                break;
            case 3:
                pw.println("<b>Выберите интервал дат для поиска</b><br>");
                pw.println(generateSelectDates());
                pw.println("<br><br>");
        }
        pw.println("<td><input type=\"submit\" id=\"Button1\" name=\"\" value=\"Начать поиск\"></td>");
        pw.println("</form>");

    }

    private String generateSelectDates()
    {
        StringBuilder forSelect = new StringBuilder();
        forSelect.append("<td><input type=\"date\" id=\"Editbox2\" name=\"FirstDate\" value=\"\"  maxlength=\"10\"></td>");
        forSelect.append(" - ");
        forSelect.append("<td><input type=\"date\" id=\"Editbox2\" name=\"SecondDate\" value=\"\"  maxlength=\"10\"></td>");
        return forSelect.toString();
    }

    private String generateSelectOccupations()
    {
        StringBuilder forSelect = new StringBuilder();
        forSelect.append("<td>");
        forSelect.append("<select size=\"8\" required size = \"1\" name=\"FindOcc\">");
        forSelect.append("<option disabled>Выберите должность</option>");

        for (OccupationElement occElement : Employee.getOcc())
        {
            forSelect.append("<option value=\"" + occElement.getId() + "\">" + occElement.getName() + "</option>");
        }
        forSelect.append("</select>");
        forSelect.append("</td>");       
        return forSelect.toString();
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
                new ConnectionToDb().connectToDb(new FindByName(req.getParameter("FindName")));
                resultFind = Employee.printFindedEmployee();
            }

            if (req.getParameter("FindOcc") != null)
            {
                new ConnectionToDb().connectToDb(new FindByOccupation(req.getParameter("FindOcc")));
                resultFind = Employee.printFindedEmployee();
            }

            if (req.getParameter("FirstDate") != null && req.getParameter("SecondDate") != null)
            {
                new ConnectionToDb().connectToDb(new FindByDate(req.getParameter("FirstDate"), req.getParameter("SecondDate")));
                resultFind = Employee.printFindedEmployee();
            }
        }
    }
}
