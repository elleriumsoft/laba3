<%@ page import="Data.Structure" %>
<%@ page import="Data.StructureProcessing" %>
<%@ page import="RequestsToDatabase.ConnectionToDb" %>
<%@ page import="RequestsToDatabase.Structure.DeleteElement" %>
<%@ page import="view.structure.Commands" %>

<html>

<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

    <title>Структура мэрии</title>
</head>
<body>

<%! Structure structure; %>

<%
   structure = StructureProcessing.loadStructure(request);
   structure.verifyForOpenList(request);
%>

<h1 style="color:#191970"><b>Структура мэрии</b></h1>

<% if (request.getParameter("command") == null) {%>
    <input type= "submit" id= "Button1 " onclick= "window.location.href='/laba3/PrintStructure.jsp?command=add';return false; " name= " " value= "Добавить " style= "position:absolute;left:9px;top:51px;width:104px;height:25px;color:#FF0000; ">
    <input type= "submit" id= "Button2 " onclick= "window.location.href='/laba3/PrintStructure.jsp?command=edit';return false; " name= " " value= "Редактировать " style= "position:absolute;left:121px;top:51px;width:104px;height:25px;color:#FF0000; ">
    <input type= "submit" id= "Button3 " onclick= "window.location.href='/laba3/PrintStructure.jsp?command=delete';return false; " name= " " value= "Удалить " style= "position:absolute;left:235px;top:51px;width:104px;height:25px;color:#FF0000; ">
    <input type= "submit" id= "Button4 " onclick= "window.location.href='/laba3/PrintStructure.jsp?open=all';return false; " name= " " value= "Открыть всё " style= "position:absolute;left:360px;top:51px;width:104px;height:25px;color:#16520a; ">
    <input type= "submit" id= "Button5 " onclick= "window.location.href='/laba3/PrintStructure.jsp?close=all';return false; " name= " " value= "Закрыть всё " style= "position:absolute;left:474px;top:51px;width:104px;height:25px;color:#16520a; ">
    <input type= "submit" id= "Button5 " onclick= "window.location.href='/laba3/PrintStructure.jsp?renew=1';return false; " name= " " value= "Обновить данные " style= "position:absolute;left:588px;top:51px;width:154px;height:25px;color:#16520a; ">
<% }
   else {
       if (request.getParameter("command").equals("delete") && request.getParameter("element") != null)
       {
           new ConnectionToDb().connectToDb(new DeleteElement(structure, request.getParameter("element")));
           response.sendRedirect("/laba3/PrintStructure.jsp?renew=1");
       }
       else
       {
           request.getSession().setAttribute("idforaction", request.getParameter("element"));%>
           <%= new Commands().build(structure.getDeptName(request.getParameter("element")), request.getParameter("command")) %>
<%
       }
   } %>

<br>
<input type="submit" id="Button1" onclick="window.location.href='/laba3/index.jsp';return false;" name="" value="Вернуться в меню" style="position:absolute;left:310px;top:18px;width:184px;height:25px;">

<%= structure.printStructure(request.getParameter("command")) %>

</body>
</html>
