package app.controller;

import core.Controller;
import core.Link;
import app.model.DTO.Entrada;
import app.model.DAO.EntradaDAO;
import app.model.DTO.ItemEntrada;
import app.model.DAO.ItemEntradaDAO;
import java.util.Date;
import app.model.DTO.Fornecedor;
import app.model.DTO.Funcionario;
import app.model.DTO.Produto;
import app.model.DAO.ProdutoDAO;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.ResultSet;

/**
 * Controller de Entrada
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class EntradaController extends Controller {

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
            Integer id_entrada = Integer.parseInt(this.getPar("id_entrada"));
            try {
                // pega o restante dos campos
                Integer id_produto = Integer.parseInt(this.getPar("id_produto"));
                double valor_compra = Double.parseDouble(this.getPar("valor_compra").replace(',', '.'));
                Integer quantidade = Integer.parseInt(this.getPar("quantidade"));

                // não pode adicionar quantidade negativa de produtos
                if (quantidade <= 0) {
                    this.POSTerror("Quantidade tem que ser um valor numerico positivo", Link.criar("entrada/detalhes/" + id_entrada));
                    return;
                }

                Produto p = new Produto();
                p.setId(id_produto);

                Entrada e = new Entrada();
                e.setId(id_entrada);

                ItemEntrada ie = new ItemEntrada();
                ie.setProduto(p);
                ie.setEntrada(e);
                ie.setQuantidade(quantidade);
                ie.setValor_compra(valor_compra);

                // Insere o item no banco de dados
                ItemEntradaDAO.inserir(ie);

                // Mensagem de sucesso
                this.POSTsuccess("Produto inserido com sucesso na venda", Link.criar("entrada/detalhes/" + id_entrada));
                return;
            } catch (Exception e) {
            }
            // Mensagem de erro
            this.POSTerror("Erro ao inserir produto", Link.criar("entrada/detalhes/" + id_entrada));
        }
    }

    /**
     * Página entrada/adicionar
     */
    public void adicionar() {
        if (this._env.post) {

            try {
                // Cria objeto e carrega parametros
                Entrada e = new Entrada();

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date data = (Date)formatter.parse(this.getPar("data"));
                e.setData(data);

                Fornecedor f = new Fornecedor();

                f.setId(Integer.parseInt(this.getPar("fornecedor")));
                e.setFornecedor(f);

                // Carrega funcionario da Session
                HttpSession session = this._env.request.getSession();
                Integer idu = (Integer) session.getAttribute("f_id");
                Funcionario u = new Funcionario();
                u.setId(idu);

                e.setFuncionario(u);

                // Insere a entrada no banco e retorna o ID
                Integer id = EntradaDAO.adicionar(e);

                // Usando o id redireciona para a tela de detalhes
                this.POSTsuccess("Entrada criada, adicione produtos", Link.criar("entrada/detalhes/" + id));

            } catch (Exception e) {
                // Mensagem de erro.
                this.POSTerror("Data inválida", Link.criar("entrada/adicionar"));
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
                // carrega a entrada
                ResultSet rs = EntradaDAO.get(id);
                rs.next();

                // Emite erro se ela já estiver fechada
                if (rs.getInt("fechada") == 1) {
                    throw new Exception();
                }

            } catch (Exception e) {
                // Mensagem de erro
                this.POSTerror("Entrada fechada ou inexistente", Link.criar("entrada"));
                return;
            }

            // Deleta a entrada do banco
            EntradaDAO.delete(id);

            // Mensagem de sucesso
            this.POSTsuccess("Entrada cancelada com sucesso", Link.criar("entrada"));
            return;
        } catch (Exception e) {
        }
        // Mensagem de Erro
        this.POSTerror("Erro ao cancelar entrada", Link.criar("entrada"));

    }

    /**
     * Página entrada/fechar
     */
    public void fechar() {

        try {
            // Pega o parametro id da URI
            String id = this._env.pars[2];

            // Carrega a entrada do banco de dados
            ResultSet rs = EntradaDAO.get(id);

            // Valida se a entrada foi encontrada
            if (!rs.next()) {
                throw new Exception();
            }

            // Valida se a entrada já está fechada
            if (rs.getInt("fechada") == 1) {
                this.POSTerror("Entrada já foi fechada", Link.criar("entrada"));
                return;
            }

            // Valida se a entrada está vazia
            if (rs.getInt("n") == 0) {
                this.POSTerror("Entrada vazia, não pode ser fechada", Link.criar("entrada/detalhes/" + id));
                return;
            }

            // Carrega os itens da entrada
            ResultSet itens = EntradaDAO.listLinhas(id);
            while (itens.next()) {
                // Cria um objeto produto temporário
                Produto p = new Produto();
                p.setId(itens.getInt("id_produto"));
                p.setQuantidade(itens.getInt("quantidade"));
                // Atualiza quantidade no estoque
                ProdutoDAO.addEstoque(p);
            }

            // Fecha a entrada
            EntradaDAO.fechar(id);

            // Envia mensagem de sucesso
            this.POSTsuccess("Entrada fechada com sucesso", Link.criar("entrada"));
        } catch (Exception e) {
            // Mensagem de Erro
            this.POSTerror("Entrada não encontrada", Link.criar("entrada"));
        }

    }
}
