<%@page import="core.Env, app.model.DAO.FornecedorDAO, core.Link, java.sql.ResultSet, java.util.Date, java.text.SimpleDateFormat, java.text.DateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Env env = (Env) request.getAttribute("env");%>
<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>

<%
   ResultSet rs = FornecedorDAO.list();
   if (!rs.next()){
 %>
 Você precisa de pelo menos um fornecedor cadastrado.
 <a href="<% out.print(Link.criar("fornecedor/adicionar"));%>" title="Cadastrar novo fornecedor">Cadastrar um agora.</a>
 <% }else{ %>
<form action="" method="post" accept-charset="utf-8">
    <fieldset>
        <p>
            <label>Data:</label>
            <input type="text" name="data" value="<%= df.format(new Date()) %>" size="10" />
        </p>
        <p>
            <label>Fornecedor:</label>
            <select name="fornecedor">
                <%
                rs.beforeFirst();
                while(rs.next()) { %>
                <option value="<%= rs.getInt("id") %>"><%= rs.getString("razao_social") %></option>
                <% } %>
            </select> ou <a href="<% out.print(Link.criar("fornecedor/adicionar"));%>" title="Cadastrar novo fornecedor">Cadastrar um agora.</a>
        </p>
        
        <p class="submit">
            <input type="submit" value="Adicionar" />
            ou <a href="<% out.print(Link.criar("entrada"));%>" class="goback">Voltar</a>
        </p>
    </fieldset>
</form>
<% } %>