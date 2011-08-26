<%@page import="core.Env, core.Link, app.model.DAO.VendaDAO, app.model.DAO.ProdutoDAO, java.sql.ResultSet, java.util.Date, java.text.SimpleDateFormat, java.text.DateFormat, java.text.NumberFormat" %>
<% Env env = (Env) request.getAttribute("env");%>
<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>
<% NumberFormat nf = NumberFormat.getCurrencyInstance(); %>

<h2>Venda de Produtos</h2>

<%
ResultSet info = VendaDAO.get(env.pars[2]);
info.next();
%>

<div class="info">
<p><strong>Cliente: </strong> <%= info.getString("cliente") %> (<%= info.getString("pontos") %> Pontos)</p>
<p><strong>Funcionário: </strong> <%= info.getString("funcionario") %></p>
<p><strong>Data: </strong> <%= df.format(info.getDate("data")) %></p>
</div>

<br />

<%
ResultSet rs = ProdutoDAO.list();

if (!rs.next()){
 %>
Você precisa de pelo menos um produto cadastrado.
 <a href="<% out.print(Link.criar("produto/adicionar"));%>" title="Cadastrar novo produto">Cadastrar um agora</a>.
<% }else{
ResultSet itens = VendaDAO.listLinhas(env.pars[2]);
%>
<form action="" method="post" accept-charset="utf-8">
<table>
    <colgroup>
        <col />
        <col width="90" />
        <col />
        <col />
        <col width="30"/>
    </colgroup>
    <tr>
        <th>Produto</th>
        <th>Quantidade</th>
        <th>Valor Compra</th>
        <th>Total</th>
        <th>Ações</th>
    </tr>
    <%
    float sum = 0;
    String negar = "0";
    while(itens.next()) {
        sum += (itens.getInt("quantidade") * itens.getFloat("valor_venda"));
        negar = negar + "," + itens.getInt("id_produto");
    %>
    <tr>
        <td><%= itens.getString("nome") %></td>
        <td align="right"><%= itens.getInt("quantidade") %></td>
        <td align="right"><%= nf.format(itens.getFloat("valor_venda")) %></td>
        <td align="right"><%= nf.format(itens.getInt("quantidade") * itens.getFloat("valor_venda")) %></td>
        <td>
            <% if (info.getInt("fechada")==0){ %>
            <a href="<% out.print(Link.criar("item-venda/excluir/" + itens.getInt("id_venda") + "/" + itens.getInt("id_produto") )); %>" title="Remover Item" class="excluir confirm">Excluir</a>
            <% } %>
        </td>
    </tr>
    <% } %>
    <tr>
        <td colspan="3" align="right"><strong>Total: </strong></td>
        <td align="right"><strong><%= nf.format(sum) %></strong></td>
        <td></td>
    </tr>
     <%
        rs = ProdutoDAO.list(negar);
        if (rs.next() && info.getInt("fechada")==0){
     %>
    <tr>
        <td>
            <input type="hidden" name="id_venda" value="<%= env.pars[2] %>" />
            <select name="id_produto">
                <%
                    rs = ProdutoDAO.list(negar);
                    rs.beforeFirst();
                    while(rs.next()) {
                %>
                <option value="<%= rs.getInt("id") %>"><%= rs.getString("nome") %> (<%= nf.format(rs.getFloat("valor_venda")) %>)</option>
                <% } %>
            </select>
        </td>
        <td align="right"><input class="tright" type="text" name="quantidade" size="5" value="1" /></td>
        <td colspan="3" align="right"><input type="submit" value="adicionar" /></td>
    </tr>
    <%
    }
    %>
</table>
</form>

<p class="submit tright">
<a href="<% out.print(Link.criar("venda/")); %>" class="submit goback" title="Voltar para a tela anterior">Voltar</a>
<% if (info.getInt("fechada")==0){ %>
    <a href="<% out.print(Link.criar("venda/cancelar/" + env.pars[2])); %>" class="submit confirm" title="Cancelar venda e excluir registro">Cancelar</a>
<% } %>
</p>

<% if (info.getInt("fechada")==0){ %>
<form action="<% out.print(Link.criar("venda/fechar/" + env.pars[2])); %>" method="post" accept-charset="utf-8">
    <select name="forma_pgto">
        <option value="d">Dinheiro</option>
        <option value="c">Cheque</option>
        <option value="v">Cartão Visa</option>
        <option value="m">Cartão Master</option>
    </select>
    <input type="submit" value="Fechar Venda" />
</form>
<% } %>



<% } %>