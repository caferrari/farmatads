package app.model.DAO;

import app.model.DTO.Entrada;
import core.*;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Classe que realiza operações no banco de dados, na entidade Entrada
 * @group MyLastJavaApp
 */
public class EntradaDAO
{

     /**
      * Adiciona Entrada
      *
      * @param Entrada e
      * @return int
      * @throws Exception
      */
     public static int adicionar(Entrada e) throws Exception
     {

         // Formata data para inserir no banco de dados
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String data = df.format(e.getData());

        String sql = "INSERT INTO entrada (id_funcionario, id_fornecedor, data) " +
                     " VALUES (?, ?, ?)";
        PreparedStatement stmt = Banco.query(sql);
        stmt.setInt(1, e.getFuncionario().getId());
        stmt.setInt(2, e.getFornecedor().getId());
        stmt.setString(3, data);

        Banco.executar(stmt);

        ResultSet rs = Banco.consulta("SELECT max(id) FROM entrada");

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
         String sql = "DELETE FROM entrada WHERE id=?";
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
     public static void fechar(String id) throws Exception
     {
         String sql = "UPDATE entrada SET fechada=1 WHERE id=?";
         PreparedStatement stmt = Banco.query(sql);
         stmt.setInt(1, Integer.parseInt(id));
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
            "SELECT e.id, e.data, e.id_fornecedor, e.id_funcionario, e.fechada, " +
            "pj.razao_social as fornecedor, p.nome as funcionario, count(ie.id_entrada) as n " +
            "FROM entrada e " +
            "INNER JOIN pessoa_juridica pj ON pj.id = e.id_fornecedor " +
            "INNER JOIN pessoa p ON p.id=e.id_funcionario " +
            "LEFT JOIN item_entrada ie ON ie.id_entrada = e.id " +
            "WHERE e.id=" + Integer.parseInt(id) +
            " GROUP BY  e.id, e.data, e.id_fornecedor, e.id_funcionario, e.fechada, pj.razao_social, p.nome"
         );
     }

     /**
      * Consulta Entradas do Banco de Dados
      *
      * @return ResultSet
      * @throws Exception
      */
     public static ResultSet list() throws Exception
     {
         return Banco.consulta(
            "SELECT e.id, e.data, e.fechada, e.id_funcionario, e.id_fornecedor, " +
            "pj.razao_social as fornecedor, p.nome as funcionario, " +
            "sum(ie.valor_compra * ie.quantidade) as valor " +
            "FROM entrada e " +
            "INNER JOIN pessoa_juridica pj ON pj.id = e.id_fornecedor " +
            "INNER JOIN pessoa p ON p.id=e.id_funcionario " +
            "LEFT JOIN item_entrada ie ON ie.id_entrada=e.id " +
            "GROUP BY e.id, e.data, e.id_funcionario, e.id_fornecedor, pj.razao_social, p.nome " +
            "ORDER BY e.id DESC "
         );
     }

     /**
      * Consulta Itens de uma Entrada especifica
      *
      * @param String id_entrada
      * @return ResultSet
      * @throws Exception
      */
     public static ResultSet listLinhas(String id_entrada) throws Exception
     {
        return Banco.consulta(
         "SELECT ie.id_entrada, ie.id_produto, ie.quantidade, ie.valor_compra, p.nome " +
         "FROM item_entrada ie INNER JOIN produto p ON p.id = ie.id_produto " +
         "WHERE ie.id_entrada=" + Integer.parseInt(id_entrada));
     }

}
