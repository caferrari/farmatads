<%@page import="core.Env, core.Link, java.sql.ResultSet, app.model.DAO.ProdutoDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Env env = (Env) request.getAttribute("env");%>

<%
        String nome = "";
        String lab = "";
        String desc = "";
        String val = "";
        String id = "";
        if (env.action.equals("editar") && env.pars.length >= 3) {
            out.print("<h2>Editar Produto</h2>");
            try {
                id = env.pars[2];
                ResultSet rs = ProdutoDAO.get(id);
                rs.next();
                nome = rs.getString("nome");
                lab = rs.getString("laboratorio");
                desc = rs.getString("descricao");
                Float v = rs.getFloat("valor_venda");
                val = v.toString();
            } catch (Exception e) {
            }
        } else {
            out.print("<h2>Adicionar Produto</h2>");
        }
%>
<form action="" method="post" accept-charset="utf-8">
    <fieldset>
        <input type="hidden" name="id" value="<% out.print(id);%>" />
        <p>
            <label>Nome:</label>
            <input type="text" name="nome" value="<% out.print(nome);%>" size="35" />
        </p>
        <p>
            <label>Laboratório:</label>
            <input type="text" name="laboratorio" value="<% out.print(lab);%>" size="20" />
        </p>
        <p>
            <label>Valor Unitário:</label>
            <input type="text" name="valor" value="<% out.print(val);%>" size="10" /> <span>10.57</span>
        </p>
        <p>
            <label>Descrição:</label>
            <textarea cols="50" rows="2" name="descricao"><% out.print(desc);%></textarea>
        </p>

        <p class="submit">
            <input type="submit" value="<% if (env.action.equals("adicionar")) {
                        out.print("Adicionar");
                    } else {
                        out.print("Editar");
                    }%>" />
            ou <a href="<% out.print(Link.criar("produto"));%>" class="goback">Voltar</a>
        </p>
    </fieldset>
</form>