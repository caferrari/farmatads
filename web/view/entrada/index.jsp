<%@page import="core.Env, core.Link, app.model.DAO.EntradaDAO, java.sql.ResultSet, java.util.Date, java.text.NumberFormat, java.text.SimpleDateFormat, java.text.DateFormat" %>
<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>
<% NumberFormat nf = NumberFormat.getCurrencyInstance(); %>
<h2>Entrada de Produtos</h2>
<ul class="buttons">
	<li><a href="<% out.print(Link.criar("entrada/adicionar")); %>" class="" title="Adicionar nova entrada de produtos">Adicionar</a></li>
</ul>

<%
   ResultSet rs = EntradaDAO.list();

   if (!rs.next()){
 %>
 Nenhuma Entrada cadastrado!
 <% }else{ %>

<table>
    <colgroup>
        <col width="12" />
        <col width="90" />
        <col />
        <col />
        <col />
        <col width="70"/>
    </colgroup>
    <tr>
        <th>#</th>
        <th>Data</th>
        <th>Funcionário</th>
        <th>Fornecedor</th>
        <th>Valor</th>
        <th>Ações</th>
    </tr>
    <%
        rs.beforeFirst();
        while(rs.next()) {
    %>
    <tr>
        <td><% out.print( rs.getInt("id") ); %></td>
        <td><% out.print( df.format(rs.getDate("data")) ); %></td>
        <td><% out.print( rs.getString("funcionario") ); %></td>
        <td><% out.print( rs.getString("fornecedor") ); %></td>
        <td><% out.print( nf.format(rs.getDouble("valor")) ); %></td>
        <td>
            <a href="<% out.print(Link.criar("entrada/detalhes/" + rs.getInt("id"))); %>" title="Vizualizar e Editar entrada" class="editar">Editar</a>
            <% if (rs.getInt("fechada")==0) { %>
            <a href="<% out.print(Link.criar("entrada/cancelar/" + rs.getInt("id"))); %>" title="Cancelar Entrada" class="excluir confirm">Cancelar</a>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<% } %>