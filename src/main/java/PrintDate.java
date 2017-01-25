import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/PrintDate")
public class PrintDate extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formating = new SimpleDateFormat("dd.MM.YYYY    HH:mm:ss");
        String dateTime = formating.format(currentTime);
        pw.println("<H1><b>Текущее время:</b></H1>");
        pw.println("<H2>" + dateTime + "</H2>");
        pw.flush();
    }
}
