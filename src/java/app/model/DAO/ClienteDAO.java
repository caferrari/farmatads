package app.model.DAO;

import app.model.DTO.Cliente;
import core.*;
import java.sql.*;

/** 
 * Classe que realiza operações no banco de dados, na entidade Cliente
 * @group MyLastJavaApp
 */
public class ClienteDAO {

    /**
     * Lista Clientes castrados
     *
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet list() throws Exception
    {
        return Banco.consulta("SELECT p.id, p.nome, p.telefone, p.email, pf.cpf, pf.rg, c.pontos FROM pessoa p " +
                              "INNER JOIN pessoa_fisica pf ON pf.id=p.id " +
                              "INNER JOIN cliente c ON c.id=p.id " +
                              "ORDER BY p.nome");
    }

    /**
     * Retorna Cliente Especifico
     *
     * @param Sting id
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet get(String id) throws Exception
    {
       return Banco.consulta("SELECT p.id, p.nome, p.telefone, p.email, pf.cpf, pf.rg, c.pontos FROM pessoa p " +
                             "INNER JOIN pessoa_fisica pf ON pf.id=p.id " +
                             "INNER JOIN cliente c ON c.id=p.id " +
                             "WHERE c.id=" + Integer.parseInt(id));
    }

    /**
     * Adiciona Cliente ao Banco de Dados
     *
     * @param Cliente c
     * @return int
     * @throws Exception
     */
    public static int adicionar(Cliente c) throws Exception
    {

        // Adiciona dados do Cliente a Pessoa Física
        int id = PessoaFisicaDAO.adicionar(c);

        // Se inserir pessoa física adiciona cliente
        if (id > 0)
        {
            PreparedStatement stmt = Banco.query("INSERT INTO cliente (id, pontos) VALUES (?, ?)");

            stmt.setInt(1, id);
            stmt.setInt(2, c.getPontos());
            Banco.executar(stmt);
        }

        return id;
    }

    /**
     * Altera dados do Cliente
     *
     * @param Cliente c
     * @throws Exception
     */
    public static void editar(Cliente c) throws Exception
    {
        // Atualiza dados do cliente referente a pessoa fisica
        PessoaFisicaDAO.editar(c);

        // Altera Ciente no banco de dados
        PreparedStatement stmt = Banco.query("UPDATE cliente SET pontos = ? WHERE id = ?");

        stmt.setInt(1, c.getPontos());
        stmt.setInt(2, c.getId());        
        Banco.executar(stmt);
    }

    /**
     * Remove Cliente
     *
     * @param int id
     * @throws Exception
     */
    public static void excluir(String id) throws Exception
    {
        // Ao Remover a Pessoa as chaves estrangeiras estão configuradas para remover
        // os itens das tabelas dependentes em cascata
        PessoaDAO.excluir(id);
    }

}