<%@ page import="view.finder.Form" %>
<%@ page import="view.finder.Finder" %>
<%@page import="java.util.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Поиск сотрудника мэрии</title>
    </head>
    <h1 style="color:#191970"><b>Поиск сотрудника в мэрии</b></h1>
    <body>
        <br>
        <input type="submit" id="Button1" onclick="window.location.href='/laba3/index.jsp';return false;" name="" value="Вернуться в меню" style="position:absolute;left:460px;top:18px;width:184px;height:25px;">

        <div id="buttons">
            <input type="submit" id="Button2" onclick="window.location.href='/laba3/EmployeeFindForm.jsp?form=byName';return false;" name="" value="По имени"           style="position:absolute;left:6px;  top:70px;width:175px;height:25px;color:#FF0000;">
            <input type="submit" id="Button3" onclick="window.location.href='/laba3/EmployeeFindForm.jsp?form=byOccupation';return false;" name="" value="По должности" style="position:absolute;left:186px;top:70px;width:175px;height:25px;color:#FF0000;">
            <input type="submit" id="Button4" onclick="window.location.href='/laba3/EmployeeFindForm.jsp?form=byBirth';return false;" name="" value="По дате рождения"  style="position:absolute;left:366px;top:70px;width:175px;height:25px;color:#FF0000;">
            <input type="submit" id="UseMagicNumbersNever9372" onclick="window.location.href='/laba3/EmployeeFindForm.jsp';return false;" name="" value="Отмена"        style="position:absolute;left:546px;top:70px;width:175px;height:25px;">
        </div>
        <br>

<%=     new Form().build("/laba3/EmployeeFindForm.jsp", request.getParameter("form")) %>

<%=     new Finder().execute(request.getParameter("find"), request.getParameterMap()) %>

</body>
</html>
