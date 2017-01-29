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
<%= new ConnectionToDb().writeBody(new GenerateSturcture()) %>
</body>
</html>
