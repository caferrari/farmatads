package app.model.DAO;

import app.model.DTO.Produto;
import core.*;
import java.sql.*;

/**
 * Classe que realiza operações no banco de dados, na entidade Produto
 * @group MyLastJavaApp
 */
public class ProdutoDAO {

    /**
     * Consulta Produtos no Banco de Dados
     *
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet list() throws Exception
    {
        return Banco.consulta("SELECT * FROM produto ORDER BY nome");
    }

    /**
     * Consulta Produtos no Banco de Dados podendo negar alguns produtos
     * 
     * @param String negar
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet list(String negar) throws Exception
    {
        return Banco.consulta("SELECT * FROM produto WHERE id NOT IN(" + negar + ") ORDER BY nome");
    }

    /**
     * Consulta Produto Especifico no Banco de Dados
     * 
     * @param String id
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet get(String id) throws Exception
    {
        return Banco.consulta("SELECT * FROM produto WHERE id =" + Integer.parseInt(id));
    }

    /**
     * Consulta para Fazer Relatorios de Produtos
     * 
     * @param Produto p
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet listReport(Produto p) throws Exception
    {
        String wh = "";
        String op = "";
        String str = "";

        if (! p.getNome().isEmpty()) {
            str += op + " nome LIKE '%" + p.getNome() + "%'";
            wh = " WHERE "; op = " AND ";
        }

        if (!p.getLaboratorio().isEmpty()) {
            str += op + " laboratorio LIKE '%" + p.getLaboratorio() + "%'";
            wh = " WHERE "; op = " AND ";
        }

        System.out.print(p.getEstoque());
        System.out.print("SELECT * FROM produto " + wh + " " + str + " ORDER BY nome ");

        return Banco.consulta("SELECT * FROM produto " + wh + " " + str + " ORDER BY nome ");
    }

    /**
     * Adiciona Produtos no Banco de Dados
     *
     * @param Produto p
     * @throws Exception
     */
    public static void adicionar(Produto p) throws Exception{
        PreparedStatement stmt = Banco.query("INSERT INTO produto (nome, descricao, laboratorio, valor_venda) VALUES (?, ?, ?, ?)");

        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getDescricao());
        stmt.setString(3, p.getLaboratorio());
        stmt.setFloat(4, p.getValor_venda());
        Banco.executar(stmt);
    }

    /**
     * Edita Produto no Banco de Dados
     * 
     * @param p
     * @throws Exception
     */
    public static void editar(Produto p) throws Exception{
        PreparedStatement stmt = Banco.query("UPDATE produto SET nome=?, descricao=?, laboratorio=?, valor_venda=? WHERE id=?");

        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getDescricao());
        stmt.setString(3, p.getLaboratorio());
        stmt.setFloat(4, p.getValor_venda());
        stmt.setInt(5, p.getId());
        Banco.executar(stmt);
    }

    /**
     * Adiciona Produto ao Estoque
     * 
     * @param Produto p
     * @throws Exception
     */
    public static void addEstoque(Produto p) throws Exception
    {
        PreparedStatement stmt = Banco.query("UPDATE produto SET quantidade=quantidade+? WHERE id=?");
        stmt.setInt(1, p.getQuantidade());
        stmt.setInt(2, p.getId());
        Banco.executar(stmt);
    }

    /**
     * Verifica unidades disponiveis no estoque
     *
     * @param Produto p
     * @return boolean
     * @throws Exception
     */
    public static boolean checkEstoque(Produto p){
        try{
            ResultSet rs = Banco.consulta("SELECT quantidade FROM produto WHERE id=" + p.getId());
            if (!rs.next()) return false;
            if (rs.getInt("quantidade") >= p.getQuantidade()) return true;
        }catch (Exception e){ }
        return false;
    }

    /**
     * Remove unidades disponiveis no estoque
     *
     * @param Produto p
     * @return boolean
     * @throws Exception
     */
    public static boolean baixaEstoque(Produto p){
        try{
            PreparedStatement stmt = Banco.query("UPDATE produto SET quantidade=quantidade-? WHERE id=?");
            stmt.setInt(1, p.getQuantidade());
            stmt.setInt(2, p.getId());
            Banco.executar(stmt);
            return true;
        }catch (Exception e){ }
        return false;
    }

    /**
     * Exclui Produto do Banco de Dados
     *
     * @param String id
     * @throws Exception
     */
    public static void excluir(String id) throws Exception
    {
         PreparedStatement stmt = Banco.query("DELETE FROM produto WHERE id=?");
         stmt.setString(1, id);
         Banco.executar(stmt);
    }

}
