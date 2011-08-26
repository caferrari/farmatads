package app.controller;

import core.Controller;
import core.Link;
import app.model.DTO.Entrada;
import app.model.DTO.ItemEntrada;
import app.model.DAO.ItemEntradaDAO;
import app.model.DTO.Produto;

/**
 * Controller Item de Entrada
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class ItemEntradaController extends Controller {

    /**
     * Página item-entrada/excluir
     */
    public void excluir()
    {
        // Tenta excluir a entrada do banco
        try
        {
            int id_entrada = Integer.parseInt(this._env.pars[2]);
            int id_produto = Integer.parseInt(this._env.pars[3]);

            // Cria Objeto Produto
            Produto p = new Produto();
            p.setId(id_produto);

            // Cria Objeto Entrada
            Entrada e = new Entrada();
            e.setId(id_entrada);

            // Cria Objeto ItemEntrada
            ItemEntrada ie = new ItemEntrada();
            ie.setProduto(p);
            ie.setEntrada(e);

            // Remove ItemEntrada
            ItemEntradaDAO.excluir(ie);
            // Mensagem de Sucesso
            this.POSTsuccess("Item removido com sucesso", Link.criar("entrada/detalhes/" + id_entrada));
        }catch (Exception e){
            // Mensagem de Erro
            this.POSTerror("Erro ao excluir item", Link.criar("entrada"));
        }

    }

}
