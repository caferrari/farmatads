package app.controller;

import app.model.DAO.EntradaDAO;
import core.Controller;
import core.Link;
import app.model.DTO.Venda;
import app.model.DAO.VendaDAO;
import app.model.DTO.ItemVenda;
import app.model.DAO.ItemVendaDAO;
import app.model.DTO.Cliente;
import app.model.DTO.Funcionario;
import app.model.DTO.Produto;
import app.model.DAO.ProdutoDAO;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

/**
 * Controller de Venda
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class VendaController extends Controller {

    /**
     * Página entrada
     */
    public void index() {
    }

    /**
     * Página entrada/detalhes
     */
    public void detalhes() {
        if (this._env.post) {
            // pega o id da entrada
            Integer id_venda = Integer.parseInt(this.getPar("id_venda"));
            try {
                // pega o restante dos campos
                Integer id_produto = Integer.parseInt(this.getPar("id_produto"));
                Integer quantidade = Integer.parseInt(this.getPar("quantidade"));

                // não pode adicionar quantidade negativa de produtos
                if (quantidade <= 0) {
                    this.POSTerror("Quantidade tem que ser um valor numerico positivo", Link.criar("venda/detalhes/" + id_venda));
                    return;
                }


                Produto p = new Produto();
                p.setId(id_produto);
                p.setQuantidade(quantidade);

                if (!ProdutoDAO.checkEstoque(p)){
                    this.POSTerror("Estoque insuficiente para este produto", Link.criar("venda/detalhes/" + id_venda));
                    return;
                }

                Venda v = new Venda();
                v.setId(id_venda);

                ItemVenda iv = new ItemVenda();
                iv.setProduto(p);
                iv.setVenda(v);
                iv.setQuantidade(quantidade);

                // baixa itens do estoque
                ProdutoDAO.baixaEstoque(p);

                // Insere o item no banco de dados
                ItemVendaDAO.inserir(iv);

                // Mensagem de sucesso
                this.POSTsuccess("Produto inserido com sucesso na venda", Link.criar("venda/detalhes/" + id_venda));
                return;
            } catch (Exception e) {
            }
            // Mensagem de erro
            this.POSTerror("Erro ao inserir produto", Link.criar("entrada/detalhes/" + id_venda));
        }
    }

    /**
     * Página entrada/adicionar
     */
    public void adicionar() {
        if (this._env.post) {

            try {
                // Cria objeto e carrega parametros
                Venda v = new Venda();

                Cliente c = new Cliente();

                c.setId(Integer.parseInt(this.getPar("cliente")));
                v.setCliente(c);

                // Carrega funcionario da Session
                HttpSession session = this._env.request.getSession();
                Integer idu = (Integer) session.getAttribute("f_id");
                Funcionario u = new Funcionario();
                u.setId(idu);

                v.setFuncionario(u);
                
                // Insere a entrada no banco e retorna o ID
                Integer id = VendaDAO.adicionar(v);

                // Usando o id redireciona para a tela de detalhes
                this.POSTsuccess("Venda criada, adicione produtos", Link.criar("venda/detalhes/" + id));
            } catch (Exception e) {
                // Mensagem de erro.
                this.POSTerror(e.getMessage(), Link.criar("venda/adicionar"));
            }
        }
    }

    /**
     * Página entrada/cancelar
     */
    public void cancelar() {
        try {
            // pega o primeiro parametro da URI
            String id = this._env.pars[2];

            try {

                if (0 == ItemVendaDAO.getQtdVenda(id)) {
                    VendaDAO.delete(id);
                } else {
                    this.POSTerror("Para cancelar a venda remova os itens", Link.criar("venda/detalhes/" + id));
                return;
                }
                

            } catch (Exception e) {
                // Mensagem de erro
                this.POSTerror("Venda cancelada ou inexistente", Link.criar("venda"));
                return;
            }

            // Mensagem de sucesso
            this.POSTsuccess("Venda cancelada com sucesso", Link.criar("venda"));
            return;
        } catch (Exception e) {
        }
        // Mensagem de Erro
        this.POSTerror("Erro ao cancelar entrada", Link.criar("entrada"));

    }



    /**
     * Página entrada/detalhes
     */
    public void fechar() {

        if (this._env.post) {
            // pega o id da venda
            Integer id_venda = Integer.parseInt(this._env.pars[2]);
            try {

                Venda v = new Venda();
                v.setId(id_venda);
                v.setformaPagto(this.getPar("forma_pgto"));
                v.setValorTotal(ItemVendaDAO.getValorVenda(this._env.pars[2]));
              
                VendaDAO.fechar(v);
                

                // Mensagem de sucesso
                this.POSTsuccess("Venda fechada com sucesso.", Link.criar("venda"));
                return;
            } catch (Exception e) {
            }
            // Mensagem de erro
            this.POSTerror("Erro ao fechar venda", Link.criar("venda/detalhes/" + id_venda));
        }
    }
}
