package app.controller;

import core.Controller;
import core.Link;
import app.model.DTO.Produto;
import app.model.DAO.ProdutoDAO;

/**
 * Controller Produto
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class ProdutoController extends Controller {

    /**
     * Página produto
     */
    public void index(){
        
    }

     /**
     * Página produto/editar
     */
    public void editar(){
        if (this._env.post) {

            // Carrega objeto e preenche com dados
            Produto p = new Produto();
            p.setId(Integer.parseInt(this.getPar("id")));
            p.setLaboratorio(this.getPar("laboratorio"));
            p.setNome(this.getPar("nome"));
            p.setDescricao(this.getPar("descricao"));

            // Verifica se o valor é um numero valido
            try {
                p.setValor_venda(Float.parseFloat(this.getPar("valor").replace(',', '.')));
            }catch (Exception e)
            {
                // Emite erro de valor inválido
                this.POSTerror("Valor deve ser numérico", Link.criar("produto/editar/" + this.getPar("id")));
                return;
            }

            // Verifica se o nome foi preenchito
            if (p.getNome().equals("")){
                this.POSTerror("Nome é obrigatório", Link.criar("produto/editar/" + this.getPar("id")));
                return;
            }

            try{
                // Edita produto no BD
                ProdutoDAO.editar(p);
            } catch (Exception e){
                // Exibe mensagem de erro
                this.POSTerror("Erro ao editar produto", Link.criar("produto/editar/" + this.getPar("id")));
            }
            // Exibe mensagem de sucesso
            this.POSTsuccess("Produto editado com sucesso", Link.criar("produto"));
            return;
        }
        this._env.view = "adicionar";
    }

     /**
     * Página produto/excluir
     */
    public void excluir(){
        // Tenta excluir do Banco
        try{
            if (this._env.pars.length >= 3) {
                String id = this._env.pars[2];
                ProdutoDAO.excluir(id);
                this.POSTsuccess("Produto excluído com sucesso", Link.criar("produto"));
            }
        }catch (Exception e)
        {
            this.POSTerror("Produto não pode ser excluído, possuí relacionamentos", Link.criar("produto"));
        }
        
    }

     /**
     * Página produto/adicionar
     */
    public void adicionar()
    {
        if (this._env.post) {
            Produto p = new Produto();

            p.setLaboratorio(this.getPar("laboratorio"));
            p.setNome(this.getPar("nome"));
            p.setDescricao(this.getPar("descricao"));
            
            try {
                p.setValor_venda(Float.parseFloat(this.getPar("valor").replace(',', '.')));
            }catch (Exception e)
            {
                this.POSTerror("Valor deve ser numérico", Link.criar("produto/adicionar/"));
                return;
            }

                
            if (p.getNome().equals("")){
                this.POSTerror("Nome é obrigatorio", Link.criar("produto/adicionar/"));
                return;
            }

            try{
                ProdutoDAO.adicionar(p);
            } catch (Exception e){
                this.POSTerror("Erro ao adicionar produto", Link.criar("produto/adicionar"));
            }

            this.POSTsuccess("Produto adicionado com sucesso", Link.criar("produto"));
            return;
        }
    }

}
