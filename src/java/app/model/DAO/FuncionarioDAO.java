package app.model.DAO;

import app.model.DTO.Funcionario;
import core.*;
import java.sql.*;

/**
 * Classe que realiza operações no banco de dados, na entidade Funcionario
 * @group MyLastJavaApp
 */
public class FuncionarioDAO {

    /**
     * Consulta Funcionário
     *
     * @param Funcionario f
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet list (Funcionario f) throws Exception
    {
        String wh = "";
        String op = "";
        String str = "";

        return Banco.consulta(" SELECT p.id, p.nome, p.telefone, p.email, pf.cpf, pf.rg, f.administrador, f.endereco FROM pessoa p " +
                              " INNER JOIN pessoa_fisica pf ON pf.id=p.id " +
                              " INNER JOIN funcionario f ON f.id=p.id " +
                              wh + str +
                              " ORDER BY f.administrador, p.nome");
    }

    /**
     * Consulta Funcionário Especifico
     *
     * @param int id
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet get (String id) throws Exception
    {
       return Banco.consulta("SELECT p.id, p.nome, p.telefone, p.email, pf.cpf, pf.rg, f.administrador, f.endereco FROM pessoa p " +
                             "INNER JOIN pessoa_fisica pf ON pf.id=p.id " +
                             "INNER JOIN funcionario f ON f.id=p.id " +
                             "WHERE f.id=" + Integer.parseInt(id));
    }

    /**
     * Adiciona Funcionário ao Banco de Dados
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static int adicionar(Funcionario f) throws Exception
    {

        int id = PessoaFisicaDAO.adicionar(f);

        if (id > 0)
        {
            PreparedStatement stmt = Banco.query("INSERT INTO funcionario (id, endereco, senha, administrador) VALUES (?, ?, ?, ?)");

            stmt.setInt(1, id);
            stmt.setString(2, f.getEndereco());
            stmt.setString(3, f.getSenha());
            stmt.setBoolean(4, f.isAdministrador());
            Banco.executar(stmt);
        }

        return id;
    }

    /**
     * Edita Funcionário do Banco de Dados
     *
     * @param Funcionario f
     * @throws Exception
     */
    public static void editar(Funcionario f) throws Exception
    {
        PessoaFisicaDAO.editar(f);
        PreparedStatement stmt;
        if (f.getSenha().equals("")){
            stmt = Banco.query("UPDATE funcionario SET endereco=?, administrador = ? WHERE id = ?");
            stmt.setString(1, f.getEndereco());
            stmt.setBoolean(2, f.isAdministrador());
            stmt.setInt(3, f.getId());
        }else{
            stmt = Banco.query("UPDATE funcionario SET endereco=?, senha = ?,  administrador = ? WHERE id = ?");
            stmt.setString(1, f.getEndereco());
            stmt.setString(2, f.getSenha());
            stmt.setBoolean(3, f.isAdministrador());
            stmt.setInt(4, f.getId());
        }
        Banco.executar(stmt);
    }

    /**
     * Exclui Funcionário do Banco de Dados
     * 
     * @param String id
     * @throws Exception
     */
    public static void excluir(String id) throws Exception
    {
        // Ao Remover a Pessoa as chaves estrangeiras estão configuradas para remover
        // os itens das tabelas dependentes em cascata
        PessoaDAO.excluir(id);
    }

}