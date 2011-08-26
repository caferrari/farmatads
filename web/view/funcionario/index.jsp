<%@page import="core.Env, core.Link, app.model.DTO.Funcionario, app.model.DAO.FuncionarioDAO, java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2>Funcionários</h2>
<ul class="buttons">
<li><a href="<% out.print(Link.criar("funcionario/adicionar")); %>" class="" title="Cadastrar novo funcionario">Adicionar</a></li>
</ul>

<%
Funcionario f = new Funcionario();
ResultSet rs = FuncionarioDAO.list(f);
%>
<form method="get" action="">
<table>
    <colgroup>
        <col />
        <col />
        <col width="120"/>
        <col width="70"/>
    </colgroup>
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Administrador</th>
        <th>Ações</th>
    </tr>
    <!--tr>
        <th><input type="text" name="nome" class="tam-95-pc" value="<% //out.print(f.getNome()); %>" /></th>
        <th>-</th>
        <th>
            <select name="administrador">
                <option value="">:: TODOS ::</option>
                <option value="1">Sim</option>
                <option value="0">Não</option>
            </select>
        </th>
        <th><input type="submit" name="filtrar" value="OK" /></th>
    </tr-->
    <%
    if (rs.next()) {

        rs.beforeFirst();
        while(rs.next()) {
    %>
    <tr>
        <td><% out.print( rs.getString("nome") ); %></td>
        <td><% out.print( rs.getString("email") ); %></td>
        <% if (rs.getInt("administrador")==1){ %>
        <td>Sim</td>
        <% }else{ %>
        <td>Não</td>
        <% } %>
        <td>
            <a href="<% out.print(Link.criar("funcionario/editar/" + rs.getInt("id"))); %>" class="editar" title="Editar Funcionário">Editar</a>
            <a href="<% out.print(Link.criar("funcionario/excluir/" + rs.getInt("id"))); %>" class="excluir confirm" title="Excluir Funcionário">Excluir</a>
        </td>
    </tr>
    <% }
    } else { %>
    <tr>
        <td colspan="4">Nenhum Registro Encontrado</td>
    </tr>
    <% } %>
</table>
</form>