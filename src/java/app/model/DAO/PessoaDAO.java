package app.model.DAO;

import app.model.DTO.Pessoa;
import core.*;
import java.sql.*;

/**
 * Classe que realiza operações no banco de dados, na entidade Pessoa
 * @group MyLastJavaApp
 */
public class PessoaDAO {

    /**
     * Adiciona/Cria Pessoa no Banco de dados
     *
     * @param Pessoa p
     * @return int
     * @throws Exception
     */
    public static int criar(Pessoa p) throws Exception
    {
        PreparedStatement stmt = Banco.query("INSERT INTO pessoa (nome, telefone, email) VALUES (?, ?, ?)");

        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getTelefone());
        stmt.setString(3, p.getEmail());
        Banco.executar(stmt);

        ResultSet rs = Banco.consulta("SELECT max(id) FROM pessoa");

        if ( rs != null && rs.next() )
        {
            return rs.getInt(1);
        }

        return 0;
    }

    /**
     * Edita Pessoa no Banco de Dados
     *
     * @param Pessoa p
     * @throws Exception
     */
    public static void editar(Pessoa p) throws Exception
    {
        PreparedStatement stmt = Banco.query("UPDATE pessoa SET nome = ?, telefone = ?, email = ? WHERE id = ?");

        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getTelefone());
        stmt.setString(3, p.getEmail());
        stmt.setInt(4, p.getId());
        Banco.executar(stmt);
    }

    /**
     * Exclui Pessoa do Banco de Dados
     * 
     * @param String id
     * @throws Exception
     */
    public static void excluir(String id) throws Exception
    {
         // Ao remover pessoa remove os itens relacionados configurados para serem apagados em cascata
         PreparedStatement stmt = Banco.query("DELETE FROM pessoa WHERE id=?");
         stmt.setString(1, id);
         Banco.executar(stmt);
    }

}