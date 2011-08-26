<%@page import="core.Env, core.Link, app.model.DAO.ProdutoDAO, java.sql.ResultSet, java.text.NumberFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% NumberFormat nf = NumberFormat.getCurrencyInstance(); %>
<h2>Produtos</h2>
<ul class="buttons">
	<li><a href="<% out.print(Link.criar("produto/adicionar")); %>" class="" title="Cadastrar novo produto">Adicionar</a></li>
</ul>

<%
   ResultSet rs = ProdutoDAO.list();

   if (!rs.next()){
 %>
 Nenhum Produto cadastrado!
 <% }else{ %>

<table>
    <colgroup>
        <col />
        <col width="100" />
        <col width="200" />
        <col width="70"/>
    </colgroup>
    <tr>
        <th>Nome</th>
        <th>Valor</th>
        <th>Unidades em Estoque</th>
        <th>Ações</th>
    </tr>
    <%
        rs.beforeFirst();
        while(rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("nome") %></td>
        <td align="right"><%= nf.format(rs.getDouble("valor_venda")) %></td>
        <td align="right"><%= rs.getInt("quantidade") %></td>
        <td>
            <a href="<% out.print(Link.criar("produto/editar/" + rs.getInt("id"))); %>" title="Editar Produto" class="editar">Editar</a>
            <a href="<% out.print(Link.criar("produto/excluir/" + rs.getInt("id"))); %>" title="Excluir Produto" class="excluir confirm">Excluir</a>
        </td>
    </tr>
    <% } %>
</table>
<% } %>