package app.controller;

import core.Controller;
import core.Link;
import app.model.DTO.Venda;
import app.model.DTO.ItemVenda;
import app.model.DAO.ItemVendaDAO;
import app.model.DTO.Produto;
import app.model.DAO.ProdutoDAO;

/**
 * Controller Item de Venda
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class ItemVendaController extends Controller {

    /**
     * Página item-entrada/excluir
     */
    public void excluir()
    {
        // Tenta excluir a entrada do banco
        try
        {
            int id_venda = Integer.parseInt(this._env.pars[2]);
            int id_produto = Integer.parseInt(this._env.pars[3]);

            // Cria Objeto Produto
            Produto p = new Produto();
            p.setId(id_produto);

            // Cria Objeto Vensa
            Venda v = new Venda();
            v.setId(id_venda);

            // Cria Objeto ItemEntrada
            ItemVenda iv = new ItemVenda();
            iv.setProduto(p);
            iv.setVenda(v);

            // restaura itens no estoque
            p.setQuantidade(ItemVendaDAO.get(iv));
            ProdutoDAO.addEstoque(p);

            // Remove ItemEntrada
            ItemVendaDAO.excluir(iv);
            // Mensagem de Sucesso
            this.POSTsuccess("Item removido com sucesso", Link.criar("venda/detalhes/" + id_venda));
        }catch (Exception e){
            // Mensagem de Erro
            this.POSTerror("Erro ao excluir item", Link.criar("venda"));
        }

    }

}
