package app.model.DAO;

import app.model.DTO.Venda;
import core.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que realiza operações no banco de dados, na entidade Entrada
 * @group MyLastJavaApp
 */
public class VendaDAO
{

     /**
      * Adiciona Venda
      *
      * @param Venda v
      * @return int
      * @throws Exception
      */
     public static int adicionar(Venda v) throws Exception
     {
        // Formata data para inserir no banco de dados
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String data = df.format(new Date());

        String sql = "INSERT INTO venda (id_funcionario, id_cliente, data) " +
                     " VALUES (?, ?, ?)";
        PreparedStatement stmt = Banco.query(sql);
        stmt.setInt(1, v.getFuncionario().getId());
        stmt.setInt(2, v.getCliente().getId());
        stmt.setString(3, data);

        Banco.executar(stmt);

        ResultSet rs = Banco.consulta("SELECT max(id) FROM venda");

        if ( rs != null && rs.next() ) return rs.getInt(1);
        return 0;

     }

     /**
      * Remove Entrada do Banco de Dados
      *
      * @param int id
      * @throws Exception
      */
     public static void delete(String id) throws Exception
     {
         String sql = "DELETE FROM venda WHERE id=?";
         PreparedStatement stmt = Banco.query(sql);
         stmt.setInt(1, Integer.parseInt(id));
         Banco.executar(stmt);
     }

     /**
      * Fecha Entrada no Banco de Dados
      *
      * @param int id
      * @return void
      * @throws Exception
      */
     public static void fechar(Venda v) throws Exception
     {
         String sql = "UPDATE venda SET fechada=1, forma_pagto=?, valor_total=? WHERE id=?";
         PreparedStatement stmt = Banco.query(sql);
         stmt.setString(1, v.getFormaPagto());
         stmt.setDouble(2, v.getValorTotal());
         stmt.setInt(3, v.getId());
         Banco.executar(stmt);
     }

     /**
      * Consulta Entrada Especifica
      *
      * @param String id
      * @return ResultSet
      * @throws Exception
      */
     public static ResultSet get(String id) throws Exception
     {
        return Banco.consulta(
             "SELECT v.id, v.data, v.fechada, v.id_cliente, v.id_funcionario, " +
            "p.nome AS cliente, c.pontos, p2.nome AS funcionario, " +
            "sum( iv.valor_venda *  iv.quantidade ) AS valor FROM venda v " +
            "INNER JOIN pessoa p ON p.id = v.id_cliente " +
            "INNER JOIN cliente c ON c.id = v.id_cliente " +
            "INNER JOIN pessoa p2 ON p2.id = v.id_funcionario " +
            "LEFT JOIN item_venda iv ON iv.id_venda = v.id " +
            "WHERE v.id = " + Integer.parseInt(id) + " " +
            "GROUP BY v.id, v.data, v.fechada, v.id_cliente, " +
            "v.id_funcionario, p.nome, c.pontos, p2.nome " +
            "ORDER BY v.id DESC"
         );
     }

     /**
      * Consulta Vendas do Banco de Dados
      *
      * @return ResultSet
      * @throws Exception
      */
     public static ResultSet list() throws Exception
     {
         return Banco.consulta(
            "SELECT v.id, v.data, v.fechada, v.id_cliente, v.id_funcionario, " +
            "p.nome AS cliente, p2.nome AS funcionario, " +
            "sum( iv.valor_venda *  iv.quantidade ) AS valor FROM venda v " +
            "INNER JOIN pessoa p ON p.id = v.id_cliente " +
            "INNER JOIN pessoa p2 ON p2.id = v.id_funcionario " +
            "LEFT JOIN item_venda iv ON iv.id_venda = v.id " +
            "GROUP BY v.id, v.data, v.fechada, v.id_cliente, " +
            "v.id_funcionario, p.nome, p2.nome " +
            "ORDER BY v.id DESC"
         );
     }

     /**
      * Consulta Itens de uma Entrada especifica
      *
      * @param String id_entrada
      * @return ResultSet
      * @throws Exception
      */
     public static ResultSet listLinhas(String id_venda) throws Exception
     {
        return Banco.consulta(
         "SELECT iv.id_venda, iv.id_produto, iv.quantidade, iv.valor_venda, p.nome " +
         "FROM item_venda iv INNER JOIN produto p ON p.id = iv.id_produto " +
         "WHERE iv.id_venda=" + Integer.parseInt(id_venda));
     }

}
