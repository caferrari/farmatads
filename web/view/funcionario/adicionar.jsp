<%@page import="core.Env, core.Link, java.sql.ResultSet, app.model.DAO.FuncionarioDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Env env = (Env)request.getAttribute("env"); %>

<%
    String nome = "";
    String telefone = "";
    String email = "";
    String cpf = "";
    String rg = "";
    String endereco = "";
    boolean admin = false;
    String id = "";

    if (env.action.equals("editar") && env.pars.length >= 3)
    {
        out.print("<h2>Editar Funcionario</h2>");
        try {
            id = env.pars[2];
            ResultSet rs = FuncionarioDAO.get(id);
            rs.next();
            nome = rs.getString("nome");
            telefone = rs.getString("telefone");
            email = rs.getString("email");
            cpf = rs.getString("cpf");
            rg = rs.getString("rg");
            endereco = rs.getString("endereco");
            admin = rs.getBoolean("administrador");
        } catch (Exception e) { }
    }else
        out.print("<h2>Adicionar Funcionario</h2>");
%>
<form action="" method="post" accept-charset="utf-8">
	<fieldset>
    <input type="hidden" name="id" value="<% out.print(id); %>" />
	<p>
		<label>Nome:</label>
		<input type="text" name="nome" value="<% out.print(nome); %>" size="40" />
	</p>
    <p>
		<label>Telefone:</label>
		<input type="text" name="telefone" value="<% out.print(telefone); %>" size="20" />
	</p>
    <p>
		<label>Email:</label>
		<input type="text" name="email" value="<% out.print(email); %>" size="30" />
	</p>
    <p>
		<label>CPF:</label>
		<input type="text" name="cpf" value="<% out.print(cpf); %>" size="12" />
	</p>
    <p>
		<label>RG:</label>
		<input type="text" name="rg" value="<% out.print(rg); %>" size="15" />
	</p>
	<p>
		<label>Endereço</label>
                <input type="text" name="endereco" value="<% out.print(endereco); %>" size="40" />
	</p>
    <p>
		<label>Administrador:</label>
                <input type="checkbox" name="admin" value="s" <% if (true == admin) { out.print("checked"); } %> />
	</p>
	<p>
		<label>Senha:
        <% if (env.action.equals("editar")){ %> (Deixe em branco para não alterar) <% } %>
        </label>
		<input type="password" name="senha" value="" size="20" />
	</p>

	<p class="submit">
        <input type="submit" value="<% if (env.action.equals("adicionar")){ out.print("Adicionar"); }else{ out.print("Editar"); } %>" />
        ou <a href="<% out.print(Link.criar("funcionario")); %>" class="goback">Voltar</a>
	</p>
	</fieldset>
</form>