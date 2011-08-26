<%@page import="core.Env, core.Link, app.model.DAO.ClienteDAO, java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2>Clientes</h2>
<ul class="buttons">
	<li><a href="<% out.print(Link.criar("cliente/adicionar")); %>" class="" title="Cadastrar novo Cliente">Adicionar</a></li>
</ul>

<%
   ResultSet rs = ClienteDAO.list();
   if (!rs.next()){
 %>
 <p>Nenhum Cliente cadastrado</p>
 <% }else{ %>
<table>
    <colgroup>
        <col />
        <col />
        <col />
        <col width="70"/>
    </colgroup>
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Pontos</th>
        <th>Ações</th>
    </tr>
    <%
        rs.beforeFirst();
        while(rs.next()) {
    %>
    <tr>
        <td><% out.print( rs.getString("nome") ); %></td>
        <td><% out.print( rs.getString("email") ); %></td>
        <td><% out.print( rs.getInt("pontos") ); %></td>
        <td>
            <a href="<% out.print(Link.criar("cliente/editar/" + rs.getInt("id"))); %>" title="Editar Cliente" class="editar">Editar</a>
            <a href="<% out.print(Link.criar("cliente/excluir/" + rs.getInt("id"))); %>" title="Excluir Cliente" class="excluir confirm">Excluir</a>
        </td>
    </tr>
    <% } %>
</table>
<% } %>