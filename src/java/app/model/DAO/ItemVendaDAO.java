package app.model.DAO;

import app.model.DTO.ItemVenda;
import core.*;
import java.sql.*;


/**
 * Classe que realiza operações no banco de dados, na entidade ItemEntrada
 * @group MyLastJavaApp
 */
public class ItemVendaDAO {

    /**
     * Insere Item de Venda no Banco de Dados
     *
     * @param ItemEntrada i
     * @throws Exception
     */
    public static void inserir(ItemVenda i) throws Exception{

        ResultSet rs = ProdutoDAO.get(Integer.toString(i.getProduto().getId()));
        rs.next();
        i.setValor_venda(rs.getFloat("valor_venda"));
        rs.close();

        PreparedStatement stmt = Banco.query(
               "INSERT INTO item_venda " +
               "(id_produto, id_venda, quantidade, valor_venda)" +
               " VALUES (?, ?, ?, ?)");

        stmt.setInt(1, i.getProduto().getId());
        stmt.setInt(2, i.getVenda().getId());
        stmt.setInt(3, i.getQuantidade());
        stmt.setDouble(4, i.getValor_venda());
        Banco.executar(stmt);
    }

    /**
     * Remove Item de Entrada do Banco de Dados
     *
     * @param ItemEntrada i
     * @throws Exception
     */
    public static void excluir(ItemVenda i) throws Exception{
        PreparedStatement stmt = Banco.query("DELETE FROM item_venda WHERE id_produto=? AND id_venda=?");
        stmt.setInt(1, i.getProduto().getId());
        stmt.setInt(2, i.getVenda().getId());
        Banco.executar(stmt);
    }

    /**
     * Pega quantidade de produtos da venda
     *
     * @param ItemEntrada i
     * @throws Exception
     */
    public static int getQtdVenda(String id) throws Exception{
        try{
            ResultSet rs = Banco.consulta("SELECT quantidade FROM item_venda WHERE id_venda=" + Integer.parseInt(id) );
            if (!rs.next()) return 0;
            return rs.getInt("quantidade");
        }catch (Exception e){ }
        return 0;
    }

     /**
     * Retorna o valor da Venda
     *
     * @param ItemEntrada i
     * @throws Exception
     */
    public static int getValorVenda(String id) throws Exception{
        try{
            ResultSet rs = Banco.consulta("SELECT sum(valor_venda) as valor FROM item_venda WHERE id_venda=" + Integer.parseInt(id) );
            if (!rs.next()) return 0;
            return rs.getInt("valor");
        }catch (Exception e){ }
        return 0;
    }



    /**
     * Pega quantidade de produtos da entrada
     *
     * @param ItemEntrada i
     * @throws Exception
     */
    public static int get(ItemVenda i) throws Exception{
        try{
            ResultSet rs = Banco.consulta("SELECT quantidade FROM item_venda WHERE id_venda=" + i.getVenda().getId() + " AND id_produto=" + i.getProduto().getId() );
            if (!rs.next()) return 0;
            return rs.getInt("quantidade");
        }catch (Exception e){ }
        return 0;
    }
}
