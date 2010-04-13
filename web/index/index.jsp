<%-- 
    Document   : index.jsp
    Created on : 13/04/2010, 04:19:58
    Author     : andre
--%>
<%@page import="core.StringStore"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>View do controller index!</h1>

		<%
			out.print(StringStore.get("x"));
		%>
    </body>
</html>
