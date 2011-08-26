package app.model.DAO;

import app.model.DTO.ItemEntrada;
import core.*;
import java.sql.*;


/**
 * Classe que realiza operações no banco de dados, na entidade ItemEntrada
 * @group MyLastJavaApp
 */
public class ItemEntradaDAO {

    /**
     * Insere Item de Entrada no Banco de Dados
     *
     * @param ItemEntrada i
     * @throws Exception
     */
    public static void inserir(ItemEntrada i) throws Exception{
        PreparedStatement stmt = Banco.query(
               "INSERT INTO item_entrada " +
               "(id_produto, id_entrada, quantidade, valor_compra)" +
               " VALUES (?, ?, ?, ?)");

        stmt.setInt(1, i.getProduto().getId());
        stmt.setInt(2, i.getEntrada().getId());
        stmt.setInt(3, i.getQuantidade());
        stmt.setDouble(4, i.getValor_compra());
        Banco.executar(stmt);
    }

    /**
     * Remove Item de Entrada do Banco de Dados
     *
     * @param ItemEntrada i
     * @throws Exception
     */
    public static void excluir(ItemEntrada i) throws Exception{
        PreparedStatement stmt = Banco.query("DELETE FROM item_entrada WHERE id_produto=? AND id_entrada=?");
        stmt.setInt(1, i.getProduto().getId());
        stmt.setInt(2, i.getEntrada().getId());
        Banco.executar(stmt);
    }
}
