<%@ page import="Data.EmployeeProcessing" %>
<%@ page import="Data.StructureProcessing" %>
<%@ page import="RequestsToDatabase.ConnectionToDb" %>
<%@ page import="RequestsToDatabase.Employee.DeleteEmployee" %>
<%@ page import="Data.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 19.02.2017
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

<title>Печать элемента</title>
</head>

<body>
<%
    if (request.getParameter("id") == null)
    {
        response.sendRedirect("/laba3/Servlets.PrintStructure");
    }

    if (request.getParameter("command") != null && request.getParameter("command").equals("delete"))
    {
        if (request.getParameter("idemployee") != null && Integer.valueOf(request.getParameter("idemployee")) != -1)
        {
            new ConnectionToDb().connectToDb(new DeleteEmployee(Integer.valueOf(request.getParameter("idemployee"))));
            response.sendRedirect("/laba3/PrintElementJsp.jsp?id=" + request.getParameter("id"));
        }
    }

    if (request.getParameter("idemployee") != null)
    {
        session.setAttribute("idforaction2", request.getParameter("idemployee"));
    }
    else
    {
        session.setAttribute("idforaction2", "-1");
    }
    session.setAttribute("iddept", request.getParameter("id"));
%>

<h1 style="color:#191970"><b><%=StructureProcessing.loadStructure(request).getDeptName(Integer.valueOf(request.getParameter("id")))%></b></h1>

<input type="submit" id="Button1" onclick="window.location.href='/laba3/Servlets.PrintStructure';return false;" name="" value="Вернуться к структуре" style="position:absolute;left:8px;top:50px;width:184px;height:25px;z-index:0;">
<br>
<h2 style="color:#191970"><b>Сотрудники</b></h2>
<%
    if (request.getParameter("command") == null)
    {
%>
<input type="submit" id="Button2" onclick="window.location.href=<%=getLink(request.getParameter("id"), "add")%>;return false;" name="" value="Добавить" style="position:absolute;left:8px;top:136px;width:104px;height:25px;color:#FF0000;">
<input type="submit" id="Button3" onclick="window.location.href=<%=getLink(request.getParameter("id"), "edit")%>;return false;" name="" value="Редактировать" style="position:absolute;left:120px;top:136px;width:104px;height:25px;color:#FF0000;">
<input type="submit" id="Button4" onclick="window.location.href=<%=getLink(request.getParameter("id"), "delete")%>;return false;" name="" value="Удалить" style="position:absolute;left:234px;top:136px;width:104px;height:25px;color:#FF0000;">
<%
}
else
{
%>
<input type="submit" id="Button5" onclick="window.location.href='/laba3/PrintElementJsp.jsp?id=<%=request.getParameter("id")%>';return false;" name="" value="Отмена" style=\"position:absolute;left:8px;top:136px;width:104px;height:25px;color:#FF0000;">
<%
    }
%>
<br>
<%!
    Employee employee;
%>
<%
    employee =new EmployeeProcessing().loadEmployee(Integer.valueOf(request.getParameter("id")));
%>
    <%=employee.printEmployee(request.getParameter("command"), Integer.valueOf(request.getParameter("id")), request.getParameter("idemployee"))%>
</body>

<%!
private String getLink(String id, String command)
{
    return "'/laba3/PrintElementJsp.jsp?id=" + id + "&command=" + command + "'";
}
%>
</html>
