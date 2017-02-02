<%@ page import="StructurePackage.Structure" %>
<%@ page import="Connections.ConnectionToDb" %>
<%@ page import="Connections.GenerateSturcture" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Структура мэрии</title>
</head>
<style>
    <%@include file='css/tree.css' %>
</style>
<body>
<H2>Структура мэрии</H2>
<input type="submit" id="Button1" onclick="window.location.href='/laba3/PrintStructureJsp.jsp?command=add';return false;" name="" value="Добавить" style="position:absolute;width:96px;height:25px;">
<!--<b><a href="/PrintStructureJsp.jsp?command=add" Добавить></a></b> -->
<b><a href="/PrintStructureJsp.jsp?command=edit" Редактировать></a></b>
<%= new ConnectionToDb().writeBody(new GenerateSturcture()) %>
</body>
</html>
