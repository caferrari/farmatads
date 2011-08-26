package app.controller;

import core.Controller;
import core.Link;
import app.model.DTO.Fornecedor;
import app.model.DAO.FornecedorDAO;

/**
 * Controller Fornecedor
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class FornecedorController extends Controller {

    /**
     * Página fornecedor/ do Controller Cliente
     */
    public void index() {
    }

    /**
     * Página fornecedor/adicionar
     */
    public void adicionar() {
        // Verifica se foi submetido o formulario em POST para Adicionar
        if (this._env.post) {

            // Cria Objetos e carrega parametros
            Fornecedor f = new Fornecedor();

            f.setNome(this.getPar("nome"));
            f.setTelefone(this.getPar("telefone"));
            f.setCnpj(this.getPar("cnpj"));
            f.setRazaoSocial(this.getPar("razao_social"));
            f.setEmail(this.getPar("email"));
            f.setContato(this.getPar("contato"));

            // Verifica se os campos básicos foram preenchidos
            if (f.getNome().equals("") || f.getEmail().equals("") || f.getRazaoSocial().equals("")) {
                // Mensagem de erro
                this.POSTerror("Nome, email e razão social são obrigatórios", Link.criar("fornecedor/adicionar"));
                return;
            }

            // Insere Fornecedor no banco de dados
            try {
                FornecedorDAO.adicionar(f);
            } catch (Exception e) {
                this.POSTerror("Erro ao adicionar fornecedor", Link.criar("fornecedor"));
            }
            this.POSTsuccess("Forcenedor adicionado com sucesso", Link.criar("fornecedor"));
        }
    }

    /**
     * Página fornecedor/editar
     */
    public void editar() {
        if (this._env.post) {
            // Recupera dados do Formulário e atribui valores ao Objeto Fornecedor
            Fornecedor f = new Fornecedor();

            f.setId(Integer.parseInt(this.getPar("id")));
            f.setNome(this.getPar("nome"));
            f.setTelefone(this.getPar("telefone"));
            f.setCnpj(this.getPar("cnpj"));
            f.setRazaoSocial(this.getPar("razao_social"));
            f.setEmail(this.getPar("email"));
            f.setContato(this.getPar("contato"));

            // Verifica se os campos básicos foram preenchidos
            if (f.getNome().equals("") || f.getEmail().equals("") || f.getRazaoSocial().equals("")) {
                // Mensagem de erro
                this.POSTerror("Nome, email e razão social são obrigatórios", Link.criar("fornecedor/editar/" + this.getPar("id")));
                return;
            }

            try {
                // Edita Fornecedor no banco de dados
                FornecedorDAO.editar(f);
            } catch (Exception e) {
                // Mensagem de erro
                this.POSTerror("Erro ao editar fornecedor", Link.criar("fornecedor/editar/" + this.getPar("id")));
                return;
            }

            // Mensagem de sucesso
            this.POSTsuccess("Fornecedor editado com sucesso", Link.criar("fornecedor"));
            return;
        }
        this.setView("adicionar");
    }

    /**
     * Página fornecedor/excluir
     */
    public void excluir() {
        try {
            // Tenta excluir o fornecedor do banco
            if (this._env.pars.length >= 3) {
                // Pega o ID da URI
                String id = this._env.pars[2];
                // Exclui do Banco de dados
                FornecedorDAO.excluir(id);

                // Mensagem de sucesso!
                this.POSTsuccess("Fornecedor excluído com sucesso", Link.criar("fornecedor"));
            }
        } catch (Exception e) {
            // Mensagem de Erro
            this.POSTerror("Fornecedor não pode ser excluído, possuí relacionamentos", Link.criar("fornecedor"));
        }
    }
}
