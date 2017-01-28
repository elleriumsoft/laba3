import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
@WebServlet("/Laba3")
public class TestServ extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<H1><b>Собственно это текст</b></H1>");
        pw.println("<H2><a href=\"/laba3/PrintDate\">Здесь 2я страница с выводом даты</a>" + "<br></H2>");
        pw.println("<H2><a href=\"/laba3/PrintStructure\">Структура мэрии</a></H2>");
        pw.println("<H2><a href=\"/laba3/TestDb\">Тест обращения к базе данных</a></H2>");
        pw.close();
    }
}
