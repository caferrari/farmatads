<%@page import="core.Env, app.model.DAO.ClienteDAO, core.Link, java.sql.ResultSet, java.util.Date, java.text.SimpleDateFormat, java.text.DateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Env env = (Env) request.getAttribute("env");%>
<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>

<%
   ResultSet rs = ClienteDAO.list();
   if (!rs.next()){
 %>
 Você precisa de pelo menos um cliente cadastrado.
 <a href="<% out.print(Link.criar("cliente/adicionar"));%>" title="Cadastrar novo cliente">Cadastrar um agora.</a>
 <% }else{ %>
<form action="" method="post" accept-charset="utf-8">
    <fieldset>
        <p>
            <label>Cliente</label>
            <select name="cliente">
                <%
                rs.beforeFirst();
                while(rs.next()) { %>
                <option value="<%= rs.getInt("id") %>"><%= rs.getString("nome") %> (<%= rs.getString("pontos") %> Pontos)</option>
                <% } %>
            </select> ou <a href="<% out.print(Link.criar("cliente/adicionar"));%>" title="Cadastrar novo cliente">Cadastrar um agora.</a>
        </p>
        
        <p class="submit">
            <input type="submit" value="Adicionar" />
            ou <a href="<% out.print(Link.criar("entrada"));%>" class="goback">Voltar</a>
        </p>
    </fieldset>
</form>
<% } %>