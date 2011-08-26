<%@page import="core.Env, core.Link, app.model.DAO.FornecedorDAO, java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2>Fornecedores</h2>
<ul class="buttons">
    <li><a href="<% out.print(Link.criar("fornecedor/adicionar")); %>" class="" title="Cadastrar novo Fornecedor">Adicionar</a></li>
</ul>

<%
   ResultSet rs = FornecedorDAO.list();
   if (!rs.next()){
 %>
 <p>Nenhum Fornecedor Cadastrado</p>
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
        <th>Razao Social</th>
        <th>CNPJ</th>
        <th>Ações</th>
    </tr>
    <%
        rs.beforeFirst();
        while(rs.next()) {
    %>
    <tr>
        <td><% out.print( rs.getString("nome") ); %></td>
        <td><% out.print( rs.getString("razao_social") ); %></td>
        <td><% out.print( rs.getString("cnpj") ); %></td>
        <td>
            <a href="<% out.print(Link.criar("fornecedor/editar/" + rs.getInt("id"))); %>" title="Editar Fornecedor" class="editar">Editar</a>
            <a href="<% out.print(Link.criar("fornecedor/excluir/" + rs.getInt("id"))); %>" title="Excluir Fornecedor" class="excluir confirm">Excluir</a>
        </td>
    </tr>
    <% } %>
</table>
<% } %>