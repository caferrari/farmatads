package app.model.DAO;

import app.model.DTO.Fornecedor;
import core.*;
import java.sql.*;

/**
 * Classe que realiza operações no banco de dados, na entidade Fornecedor
 * @group MyLastJavaApp
 */
public class FornecedorDAO {

    /**
     * Consulta Fornecedores do Banco de Dados
     *
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet list() throws Exception
    {
        return Banco.consulta("SELECT p.id, p.nome, p.telefone, p.email, pj.cnpj, pj.razao_social, f.contato FROM pessoa p"
                              + " INNER JOIN pessoa_juridica pj ON pj.id=p.id"
                              + " INNER JOIN fornecedor f ON f.id=p.id"
                              + " ORDER BY pj.razao_social");
    }

    /**
     * Consulta Fornecedor Especifico
     *
     * @param String id
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet get(String id) throws Exception
    {
       return Banco.consulta("SELECT p.id, p.nome, p.telefone, p.email, pj.cnpj, pj.razao_social, f.contato FROM pessoa p"
                              + " INNER JOIN pessoa_juridica pj ON pj.id=p.id"
                              + " INNER JOIN fornecedor f ON f.id=p.id"
                              + " WHERE f.id=" + Integer.parseInt(id));
    }

    /**
     * Adiciona Fornecedor ao Banco de Dados
     *
     * @param Fornecedor f
     * @return int
     * @throws Exception
     */
    public static int adicionar(Fornecedor f) throws Exception
    {

        int id = PessoaJuridicaDAO.adicionar(f);

        if (id > 0)
        {
            PreparedStatement stmt = Banco.query("INSERT INTO fornecedor (id, contato) VALUES (?, ?)");

            stmt.setInt(1, id);
            stmt.setString(2, f.getContato());
            Banco.executar(stmt);
        }

        return id;
    }

    /**
     * Edita Fornecedor do Banco de Dados
     *
     * @param Fornecedor f
     * @throws Exception
     */
    public static void editar(Fornecedor f) throws Exception
    {
        PessoaJuridicaDAO.editar(f);

        PreparedStatement stmt = Banco.query("UPDATE fornecedor SET contato = ? WHERE id = ?");

        stmt.setString(1, f.getContato());
        stmt.setInt(2, f.getId());
        Banco.executar(stmt);
    }

    /**
     * Exclui Pessoa do Banco de Dados
     *
     * @param id
     * @throws Exception
     */
    public static void excluir(String id) throws Exception
    {
        // Ao Remover a Pessoa as chaves estrangeiras estão configuradas para remover
        // os itens das tabelas dependentes em cascata
        PessoaDAO.excluir(id);
    }

}