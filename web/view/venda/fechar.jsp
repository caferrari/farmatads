<%@page import="core.Env, core.Link, java.sql.ResultSet, app.model.DAO.VendaDAO, java.text.NumberFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Env env = (Env) request.getAttribute("env");%>
<% NumberFormat nf = NumberFormat.getCurrencyInstance(); %>
<%
    String id = env.pars[2];
    ResultSet rs = VendaDAO.get(id);
    rs.next();
%>
<h2>Fechar Venda: <%= id %></h2>
<form action="" method="post" accept-charset="utf-8">
    <fieldset>
        <input type="hidden" name="id" value="<% out.print(id);%>" />
        <input type="hidden" name="pontos" value="<% out.print(rs.getInt("pontos")); %>" />
        <p>
            <label>Forma de Pagamento:</label>
            <select name="forma_pgto">
                <option value="d">Dinheiro</option>
                <option value="c">Cheque</option>
                <option value="v">Cartão Visa</option>
                <option value="m">Cartão Master</option>
            </select>
        </p>
        <p>Sub-Total: <strong><% out.print(nf.format(rs.getFloat("valor")));  %></strong></p>
        <% if (rs.getInt("pontos") > 10){  %>
        <p>Desconto: <strong>10%</strong></p>
        <p>Total: <strong><% out.print(nf.format(rs.getFloat("valor") * .9));  %></strong></p>
        <% }else{ %>
        <p>Desconto: <strong>0%</strong></p>
        <p>Total: <strong><% out.print(nf.format(rs.getFloat("valor")));  %></strong></p>
        <% } %>
        <p>
            <label>Pagamento:</label>
            <input type="text" name="pago" value="" size="10" /> <span>10.57</span>
        </p>
        <p>Troco: <strong>0.00</strong></p>


        <p class="submit">
            <input type="submit" value="Adicionar" />
            ou <a href="<% out.print(Link.criar("venda/detalhes/" + id));%>" class="goback">Voltar</a>
        </p>
    </fieldset>
</form>