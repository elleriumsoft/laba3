<%@ page import="Data.Employee" %>
<%@ page import="Data.EmployeeProcessing" %>
<%@ page import="Data.OccupationElement" %>
<%@ page import="RequestsToDatabase.Finder.CommandForFind" %><%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 19.02.2017
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Поиск сотрудника мэрии</title>
</head>
<h1 style="color:#191970"><b>Поиск сотрудника в мэрии</b></h1>
<body>

<%! String resultFind = "-"; %>

<br>
<input type="submit" id="Button1" onclick="window.location.href='/laba3/index.jsp';return false;" name="" value="Вернуться в меню" style="position:absolute;left:460px;top:18px;width:184px;height:25px;">
<%
        if (request.getParameter("find") == null)
        {
%>
            <input type="submit" id="Button2" onclick="window.location.href='/laba3/FinderJsp.jsp?find=1';return false;" name="" value="По имени" style="position:absolute;left:6px;top:70px;width:175px;height:25px;color:#FF0000;">
            <input type="submit" id="Button3" onclick="window.location.href='/laba3/FinderJsp.jsp?find=2';return false;" name="" value="По должности" style="position:absolute;left:186px;top:70px;width:175px;height:25px;color:#FF0000;">
            <input type="submit" id="Button4" onclick="window.location.href='/laba3/FinderJsp.jsp?find=3';return false;" name="" value="По дате рождения" style="position:absolute;left:366px;top:70px;width:175px;height:25px;color:#FF0000;">
<%
        }
        else
        {
%>
            <input type="submit" id="Button2" onclick="window.location.href='/laba3/FinderJsp.jsp';return false;" name="" value="Отмена" style="position:absolute;left:6px;top:50px;width:95px;height:25px;">
            <br>
            <form name="Find" method="get" action="/laba3/FinderJsp.jsp">
<%
                switch (Integer.valueOf(request.getParameter("find")))
                {
                case 1:
%>
                    <b>Введите имя полностью или его часть</b><br>
                    <td><input type="text" id="Editbox1" name="FindName" value=""  maxlength="125"></td>
<%
                    break;
                case 2:
%>
                    <b>Выберите должность для поиска</b><br>
                    <td>
                    <select size="8" required size = "1" name="FindOcc">
                    <option disabled>Выберите должность</option>
<%
                    Employee employee = new EmployeeProcessing().loadEmployee(0);
                    for (OccupationElement occElement : employee.getOcc())
                    {
%>
                        <option value=<%=occElement.getId()%>><%=occElement.getName()%></option>
<%
                    }
%>
                    </select>
                    </td>
                    <br><br>
<%
                    break;
               case 3:
%>
                    <b>Выберите интервал дат для поиска</b><br>
                    <td><input type="date" id="Editbox2" name="Date1" value=""  maxlength="10"></td>
                     -
                    <td><input type="date" id="Editbox3" name="Date2" value=""  maxlength="10"></td>
                    <br><br>
<%
                }
%>
                <td><input type="submit" id="Button10" name="" value="Начать поиск"></td>
                </form>
<%
        }
        resultFind = new CommandForFind().findElements(request);
%>
            <br>
<%
            if (!resultFind.equals("-"))
            {
%>
                <%=resultFind%>
<%
            }
%>

</body>
</html>
