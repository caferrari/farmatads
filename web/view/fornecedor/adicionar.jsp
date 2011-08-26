<%@page import="core.Env, core.Link, java.sql.ResultSet, app.model.DAO.FornecedorDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Env env = (Env)request.getAttribute("env"); %>

<%
    String nome = "";
    String telefone = "";
    String email = "";
    String cnpj = "";
    String razao_social = "";
    String contato = "";
    String id = "";

    if (env.action.equals("editar") && env.pars.length >= 3)
    {
        out.print("<h2>Editar Fornecedor</h2>");
        try {
            id = env.pars[2];
            ResultSet rs = FornecedorDAO.get(id);
            rs.next();
            nome = rs.getString("nome");
            telefone = rs.getString("telefone");
            email = rs.getString("email");
            cnpj = rs.getString("cnpj");
            razao_social = rs.getString("razao_social");
            contato = rs.getString("contato");
        } catch (Exception e) {
            e.getMessage();
        }
    }else
        out.print("<h2>Adicionar Fornecedor</h2>");
%>
<form action="" method="post" accept-charset="utf-8">
    <fieldset>
    <input type="hidden" name="id" value="<% out.print(id); %>" />
	<p>
		<label>Nome:</label>
		<input type="text" name="nome" value="<% out.print(nome); %>" size="40" />
	</p>
    <p>
		<label>Razão Social:</label>
		<input type="text" name="razao_social" value="<% out.print(razao_social); %>" size="40" />
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
		<label>CNPJ</label>
		<input type="text" name="cnpj" value="<% out.print(cnpj); %>" size="15" />
	</p>
	<p>
		<label>Contato</label>
        <input type="text" name="contato" value="<% out.print(contato); %>" size="30" />
	</p>
	<p class="submit">
            <input type="submit" value="<% if (env.action.equals("adicionar")){ out.print("Adicionar"); }else{ out.print("Editar"); } %>" />
            ou <a href="<% out.print(Link.criar("fornecedor")); %>" class="goback">Voltar</a>
        </p>
	</fieldset>
</form>