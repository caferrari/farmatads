<%@page import="core.Link, core.Env" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Env env = (Env)request.getAttribute("env"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Farmatads - 2010</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Content-Language" content="pt-br" />
        <meta http-equiv="Robots" content="noindex,nofollow" />
        <link href="<% out.print(Link.criar("css/estilo.css")); %>" media="all" rel="stylesheet" type="text/css" />
        <link href="<% out.print(Link.criar("css/login.css")); %>" media="all" rel="stylesheet" type="text/css" />
        <script src="<% out.print(Link.criar("js/jquery-1.4.2.min.js")); %>" charset="utf-8" type="text/javascript"></script>
        <script src="<% out.print(Link.criar("js/jquery-ui-1.8.custom.min.js")); %>" charset="utf-8" type="text/javascript"></script>
        <script src="<% out.print(Link.criar("js/jquery.tipTip.min.js")); %>" charset="utf-8" type="text/javascript"></script>
        <script src="<% out.print(Link.criar("js/scripts.js")); %>" charset="utf-8" type="text/javascript"></script>
        <link href="<% out.print(Link.criar("css/jquery-ui-1.8.custom.css")); %>" media="all" rel="stylesheet" type="text/css" />
        <link href="<% out.print(Link.criar("css/tiptip.css")); %>" media="all" rel="stylesheet" type="text/css" />
    </head>
<body class="login">
    <div id="principal" class="container_14">
        <div class="grid_6 push_4 alpha omega">
            <jsp:include page="<%=env.viewUri %>" flush="true" />
        </div>
    </div>

        <%
        if (null != env.message.message){
    %>
    <div id="mensagem">
        <a class="fechar" href="#">Fechar</a>
        <p class="<%=env.message.type %>"><%=env.message.getMessage() %></p>
    </div>
    <%
        }
    %>
    
</body>
</html>