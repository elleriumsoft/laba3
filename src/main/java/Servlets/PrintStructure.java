package Servlets;

import Connections.ConnectionToDb;
import Connections.GenerateSturcture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dmitriy on 29.01.2017.
 */
@SuppressWarnings("serial")
@WebServlet("/Servlets.PrintStructure")
public class PrintStructure extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String command = "";
        if (req.getParameterNames().hasMoreElements())
        {
            command = req.getParameter("command");
        }

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

       pw.println("<head>");
       pw.println("<title>Структура мэрии</title>");
       pw.println("</head>");

        pw.println("<H1><b>Структура мэрии</b></H1>");
        pw.println("<body>");

        if (command.equals(""))
        {
            printButtons(pw);
        }
        else
        {
            printButtonsForCommand(pw, command);
        }
        pw.println("<br>");

        pw.print(new ConnectionToDb().writeBody(new GenerateSturcture(command)));
        pw.println("</body>");
        pw.close();
    }

    private void printButtons(PrintWriter pw)
    {
        pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?command=add';return false;\" name=\"\" value=\"Добавить\" style=\"position:absolute;left:9px;top:51px;width:104px;height:25px;z-index:0;\">");
        pw.println("<input type=\"submit\" id=\"Button2\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?command=edit';return false;\" name=\"\" value=\"Редактировать\" style=\"position:absolute;left:121px;top:51px;width:104px;height:25px;z-index:1;\">");
        pw.println("<input type=\"submit\" id=\"Button3\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?command=delete';return false;\" name=\"\" value=\"Удалить\" style=\"position:absolute;left:235px;top:51px;width:104px;height:25px;z-index:2;\">");
    }

    private void printButtonsForCommand(PrintWriter pw, String command)
    {
        pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:9px;top:51px;width:104px;height:25px;z-index:0;\">");
    }
}
