<%@page import="core.Banco, java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teste de Conexao com o Banco de Dados</title>
    </head>
    <body>
        <%
            // Inicia Query String
            /*PreparedStatement stmt = Banco.query("INSERT INTO pessoa (nome, telefone, email) VALUES (?, ?, ?)");
            stmt.setString(1, "Carlos André");
            Banco.executar(stmt);*/

            // Consulta Banco de dados
            ResultSet rs = Banco.consulta("SELECT * FROM pessoa");
            while(rs.next()) {
                out.print( rs.getInt("id") );
                out.print( rs.getString("nome") );
                out.print( rs.getString("telefone") );
                out.print( rs.getString("email") );
            }
            Banco.close();
        %>
    </body>
</html>
