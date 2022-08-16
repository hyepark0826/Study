<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>a.jsp</title>
</head>
<body>
<%String greeting = (String)request.getAttribute("greeting"); %>
<h1><%=greeting %></h1>
</body>
</html>