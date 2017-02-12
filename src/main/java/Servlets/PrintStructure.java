package Servlets;

import Data.Structure;
import Data.StructureElement;
import RequestsToDatabase.ConnectionToDb;
import RequestsToDatabase.Structure.DeleteElement;
import RequestsToDatabase.Structure.GenerateSturcture;

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
    private String command;
    private int idForAction;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        verifyForOpenList(req);

        fillVarFromRequest(req);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.println("<head>");
        pw.println("<title>>Структура мэрии</title>");
        pw.println("</head>");

        pw.println("<h1 style=\"color:#191970\"");
        pw.println("<h1><b>Структура мэрии</b></h1>");
        pw.println("<body>");

        if (command.equals(""))
        {
            printButtons(pw);
        } else
        {
            printButtonsForCommand(pw, command, req, resp);
        }
        pw.println("<br>");
        pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/index.jsp';return false;\" name=\"\" value=\"Вернуться в меню\" style=\"position:absolute;left:310px;top:18px;width:184px;height:25px;\">");

        new ConnectionToDb().connectToDb(new GenerateSturcture());

        pw.print(Structure.printStructure(command));

        pw.println("</body>");
        pw.close();
    }

    private void verifyForOpenList(HttpServletRequest req)
    {
        if (req.getParameter("open") != null)
        {
            if (req.getParameter("open").equals("all"))
            {
                Structure.getOpenElements().clear();
                for (StructureElement element : Structure.getStructure())
                {
                    if (elementHasChild(element.getId()))
                    {
                        Structure.getOpenElements().add(element.getId());
                    }
                }
            }
            else
            {
                Structure.getOpenElements().add((Integer.valueOf(req.getParameter("open"))));
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
        for (int i = 0; i< Structure.getOpenElements().size(); i++)
        {

            if (Structure.getOpenElements().get(i) == id)
            {
                Structure.getOpenElements().remove(i);

                for (int j = 0; j < Structure.getStructure().size(); j++)
                {
                    if (Structure.getStructure().get(j).getParent_id() == id)
                    {
                        removeFromOpen(Structure.getStructure().get(j).getId());
                    }
                }
                break;
            }
        }
    }

    private boolean elementHasChild(int id)
    {
        for (StructureElement el : Structure.getStructure())
        {
            if (el.getParent_id() == id)
            {
                return true;
            }
        }
        return false;
    }

    private void fillVarFromRequest(HttpServletRequest req)
    {
        if (req.getParameter("command") != null)
        {
            command = req.getParameter("command");
        }
        else
        {
            command = "";
        }
        if (req.getParameter("element") != null)
        {
            idForAction = Integer.valueOf(req.getParameter("element"));
        }
        else
        {
            idForAction = -1;
        }
    }

    private void printButtons(PrintWriter pw)
    {
        pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?command=add';return false;\" name=\"\" value=\"Добавить\" style=\"position:absolute;left:9px;top:51px;width:104px;height:25px;color:#FF0000;\">");
        pw.println("<input type=\"submit\" id=\"Button2\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?command=edit';return false;\" name=\"\" value=\"Редактировать\" style=\"position:absolute;left:121px;top:51px;width:104px;height:25px;color:#FF0000;\">");
        pw.println("<input type=\"submit\" id=\"Button3\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?command=delete';return false;\" name=\"\" value=\"Удалить\" style=\"position:absolute;left:235px;top:51px;width:104px;height:25px;color:#FF0000;\">");
        pw.println("<input type=\"submit\" id=\"Button4\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?open=all';return false;\" name=\"\" value=\"Открыть всё\" style=\"position:absolute;left:360px;top:51px;width:104px;height:25px;color:#16520a;\">");
        pw.println("<input type=\"submit\" id=\"Button5\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure?close=all';return false;\" name=\"\" value=\"Закрыть всё\" style=\"position:absolute;left:474px;top:51px;width:104px;height:25px;color:#16520a;\">");
    }

    private void printButtonsForCommand(PrintWriter pw, String command, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if (idForAction != -1)
        {
            if (command.equals("add"))
            {
                request.getSession().setAttribute("idforaction", String.valueOf(idForAction));
                pw.println("Добавьте элемент в " + Structure.getDeptName(idForAction) + ":<br>");
                pw.println("<form name=\"add\" method=\"get\" action=\"/laba3/Servlets.EditBoxesForStructure\">");
                pw.println("<input type=\"text\" id=\"Editbox1\" style=\"position:absolute;line-\" name=\"EditAdd\" value=\"\"  maxlength=\"200\">");
                pw.println("<input type=\"submit\" id=\"Button1\" \" name=\"\" value=\"ОК\" style=\"position:absolute;left:240px;top:83px;width:104px;height:23px;z-index:0;\">");//pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure';return false;\" name=\"\" value=\"ОК\" style=\"position:absolute;left:270px;top:83px;width:104px;height:23px;z-index:0;\">");
                pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:350px;top:83px;width:104px;height:23px;z-index:0;\">");
                pw.println("</form>");
            }
            if (command.equals("edit"))
            {
                request.getSession().setAttribute("idforaction", String.valueOf(idForAction));
                pw.println("Новое название для " + Structure.getDeptName(idForAction) + ":<br>");
                pw.println("<form name=\"add\" method=\"get\" action=\"/laba3/Servlets.EditBoxesForStructure\">");
                pw.println("<input type=\"text\" id=\"Editbox1\" style=\"position:absolute;line-\" name=\"EditEdit\" value=\"\"  maxlength=\"200\">");
                pw.println("<input type=\"submit\" id=\"Button1\" \" name=\"\" value=\"ОК\" style=\"position:absolute;left:240px;top:83px;width:104px;height:23px;z-index:0;\">");
                pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:350px;top:83px;width:104px;height:23px;z-index:0;\">");
                pw.println("</form>");
            }
            if (command.equals("delete"))
            {
                new ConnectionToDb().connectToDb(new DeleteElement(String.valueOf(idForAction)));
                returnToStartPage(response);
            }
        }
        else
        {
            pw.println("<input type=\"submit\" id=\"Button1\" onclick=\"window.location.href='/laba3/Servlets.PrintStructure';return false;\" name=\"\" value=\"Отмена\" style=\"position:absolute;left:9px;top:51px;width:104px;height:25px;color:#FF0000;\">");
        }
    }

    private void returnToStartPage(HttpServletResponse response) throws IOException
    {
        response.sendRedirect("/laba3/Servlets.PrintStructure");
    }
}
