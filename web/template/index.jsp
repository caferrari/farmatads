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
        <script src="<% out.print(Link.criar("js/jquery-1.4.2.min.js")); %>" charset="utf-8" type="text/javascript"></script>
        <script src="<% out.print(Link.criar("js/jquery-ui-1.8.custom.min.js")); %>" charset="utf-8" type="text/javascript"></script>
        <script src="<% out.print(Link.criar("js/jquery.tipTip.min.js")); %>" charset="utf-8" type="text/javascript"></script>
        <script src="<% out.print(Link.criar("js/scripts.js")); %>" charset="utf-8" type="text/javascript"></script>
        <link href="<% out.print(Link.criar("css/jquery-ui-1.8.custom.css")); %>" media="all" rel="stylesheet" type="text/css" />
        <link href="<% out.print(Link.criar("css/tiptip.css")); %>" media="all" rel="stylesheet" type="text/css" />
    </head>
<body class="default c_<%= env.controller %> v_<%= env.view %>">
    <div id="principal" class="container_14">

        <div id="topo" class="grid_14">
            <div class="cabecalho">
                <div class="grid_10 alpha">
                    <h1><a href="<% out.print(Link.criar()); %>" class="logo" title="Ir para a página inicial">Farmatads</a></h1>
                </div>
                <div class="grid_4 omega informacao">
                    <div class="linha-1">
                        <span class="usuario"><% out.print(request.getSession().getAttribute("f_nome")); %>
                            (<% if (request.getSession().getAttribute("f_administrador").equals(true) ){ %>
                            Administrador
                        <% }else{ %>
                            Operador
                        <% } %>)
                        </span>
                    </div>
                    <div class="linha-2">
                        <a href="<% out.print(Link.criar("login/sair")); %>" class="sair" title="Sair do sistema e voltar para a tela de login">Sair</a>
                    </div>
                </div>
            </div>
            <div class="barra">
                <div class="grid_12 alpha">
                    <ul class="menu">
                        <li><a href="<% out.print(Link.criar("venda")); %>" class="venda" title="Venda">Venda</a></li>
                        <li><a href="<% out.print(Link.criar("cliente")); %>" class="cliente" title="Gerênciar Clientes">Clientes</a></li>
                        <% if (request.getSession().getAttribute("f_administrador").equals(true) ){ %>
                        <li><a href="<% out.print(Link.criar("funcionario")); %>" class="funcionario" title="Gerênciar Funcionários">Funcionários</a></li>
                        <% } %>
                        <li><a href="<% out.print(Link.criar("fornecedor")); %>" class="fornecedor" title="Gerênciar Fornecedores">Fornecedores</a></li>
                        <li><a href="<% out.print(Link.criar("produto")); %>" class="produto" title="Gerênciar Produtos">Produtos</a></li>
                        <li><a href="<% out.print(Link.criar("entrada")); %>" class="entrada" title="Entrada de produtos">Entrada</a></li>
                        <li><a href="<% out.print(Link.criar("relatorio")); %>" class="relatorio" title="Relatórios">Relatórios</a></li>
                    </ul>
                </div>
                <div class="grid_5 omega">
                    
                </div>
            </div>
        </div>
        <div class="grid_14 caixa">
            <div class="conteudo_14">
                <jsp:include page="<%=env.viewUri %>" flush="true" />
            </div>
        </div>
        <div id="rodape" class="grid_14 alpha omega">
            <div class="grid_9 omega">
                 © 2010  - Farmatads
            </div>
            <div class="grid_5 omega tx-dir">
               Desenvolvido por: MyLastJavaApp
            </div>
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