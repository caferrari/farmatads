<%@page import="core.Env, core.Link, app.model.DAO.VendaDAO, java.sql.ResultSet, java.util.Date, java.text.NumberFormat, java.text.SimpleDateFormat, java.text.DateFormat" %>
<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>
<% NumberFormat nf = NumberFormat.getCurrencyInstance(); %>
<h2>Venda de Produtos</h2>
<ul class="buttons">
	<li><a href="<% out.print(Link.criar("venda/adicionar")); %>" class="" title="Adicionar nova venda de produtos">Adicionar</a></li>
</ul>

<%
   ResultSet rs = VendaDAO.list();

   if (!rs.next()){
 %>
 Nenhuma Venda cadastrada!
 <% }else{ %>

<table>
    <colgroup>
        <col width="12" />
        <col width="20" />
        <col width="90" />
        <col />
        <col />
        <col width="100" />
        <col />
        <col width="70"/>
    </colgroup>
    <tr>
        <th>#</th>
        <th>Aberta</th>
        <th>Data</th>
        <th>Funcionário</th>
        <th>Cliente</th>
        <th>Valor</th>
        <th>N. Fiscal</th>
        <th>Ações</th>
    </tr>
    <%
        rs.beforeFirst();
        while(rs.next()) {
    %>
    <tr>
        <td><% out.print( rs.getInt("id") ); %></td>
        <td>
            <% if (rs.getInt("fechada")==0) { %>
            Sim
            <% }else{ %>
            Não
            <% } %>
        </td>
        <td><% out.print( df.format(rs.getDate("data")) ); %></td>
        <td><% out.print( rs.getString("funcionario") ); %></td>
        <td><% out.print( rs.getString("cliente") ); %></td>
        <td><% out.print( nf.format(rs.getDouble("valor")) ); %></td>
        <td class="tx-cent">
        <% if (rs.getInt("fechada")== 1) { %>
            <a href="<% out.print(Link.criar("relatorio/notafiscal/?codigo=" + rs.getInt("id") + "&tipo=print&origem=venda")); %>" title="Emitir" class="confirm">Emitir</a</td>
        <% } else { %>
            -
        <% } %>
        <td>
            <a href="<% out.print(Link.criar("venda/detalhes/" + rs.getInt("id"))); %>" title="Vizualizar e Venda entrada" class="editar">Editar</a>
            <% if (rs.getInt("fechada")==0) { %>
            <a href="<% out.print(Link.criar("venda/cancelar/" + rs.getInt("id"))); %>" title="Cancelar Venda" class="excluir confirm">Cancelar</a>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<% } %>
