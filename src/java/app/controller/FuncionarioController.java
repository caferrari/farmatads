package app.controller;

import core.Controller;
import core.Link;
import app.model.DTO.Funcionario;
import app.model.DAO.FuncionarioDAO;

/**
 * Controller Funcionario
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class FuncionarioController extends Controller {

    /**
     * Página funcionario
     */
    public void index() {
        // é administrador?
        if (!this._env.request.getSession().getAttribute("f_administrador").equals(true)) {
            this.POSTerror("Você não tem permissão para acessar esta area", Link.criar("venda"));
            return;
        }
    }

    /**
     * Página funcionario/adicionar
     */
    public void adicionar() {
        // é administrador?
        if (!this._env.request.getSession().getAttribute("f_administrador").equals(true)) {
            this.POSTerror("Você não tem permissão para acessar esta area", Link.criar("venda"));
            return;
        }
        if (this._env.post) {
            // Cria objeto e preenche com os dados
            Funcionario f = new Funcionario();

            f.setNome(this.getPar("nome"));
            f.setTelefone(this.getPar("telefone"));
            f.setCpf(this.getPar("cpf"));
            f.setRg(this.getPar("rg"));
            f.setEmail(this.getPar("email"));
            f.setSenha(this.getPar("senha"));
            f.setEndereco(this.getPar("endereco"));
            f.setAdministrador(false);
            if (null != this.getPar("admin")) {
                if (this.getPar("admin").equals("s")) {
                    f.setAdministrador(true);
                }
            }

            // Valida as informações básicas
            if (f.getNome().equals("") || f.getEmail().equals("") || f.getSenha().equals("")){
                // Mensagem de erro
                this.POSTerror("Nome, email e senha são obrigatórios", Link.criar("funcionario/adicionar"));
                return;
            }


            try {
                // Insere no banco
                FuncionarioDAO.adicionar(f);
            } catch (Exception e) {
                // Mensagem de erro
                this.POSTerror("Erro ao adicionar funcionário", Link.criar("funcionario"));
                return;
            }

            // Mensagem de sucesso
            this.POSTsuccess("Funcionário adicionado com sucesso", Link.criar("funcionario"));
        }
    }

    /**
     * Página funcionario/editar
     */
    public void editar() {
        // é administrador?
        if (!this._env.request.getSession().getAttribute("f_administrador").equals(true)) {
            this.POSTerror("Você não tem permissão para acessar esta area", Link.criar("venda"));
            return;
        }
        if (this._env.post) {

            Funcionario f = new Funcionario();

            f.setId(Integer.parseInt(this.getPar("id")));
            f.setNome(this.getPar("nome"));
            f.setTelefone(this.getPar("telefone"));
            f.setCpf(this.getPar("cpf"));
            f.setRg(this.getPar("rg"));
            f.setEmail(this.getPar("email"));
            f.setSenha(this.getPar("senha"));
            f.setEndereco(this.getPar("endereco"));
            f.setAdministrador(false);
            if (null != this.getPar("admin")) {
                if (this.getPar("admin").equals("s")) {
                    f.setAdministrador(true);
                }
            }

            if (f.getNome().equals("") || f.getEmail().equals("")){
                this.POSTerror("Nome, email são obrigatórios", Link.criar("funcionario/editar/" + this.getPar("id")));
                return;
            }

            try {
                FuncionarioDAO.editar(f);
            } catch (Exception e) {
                this.POSTerror("Erro ao excluir funcionário", Link.criar("funcionario/editar/" + this.getPar("id")));
            }

            this.POSTsuccess("Funcionário editado com sucesso", Link.criar("funcionario"));
            return;
        }
        this.setView("adicionar");
    }

    /**
     * Página funcionario/excluir
     */
    public void excluir() {
        // é administrador?
        if (!this._env.request.getSession().getAttribute("f_administrador").equals(true)) {
            this.POSTerror("Você não tem permissão para acessar esta area", Link.criar("venda"));
            return;
        }
        try {
            if (this._env.pars.length >= 3) {
                String id = this._env.pars[2];
                FuncionarioDAO.excluir(id);
                this.POSTsuccess("Funcionário excluído com sucesso", Link.criar("funcionario"));
            }
        } catch (Exception e) {
            this.POSTerror("Funcionário não pode ser excluído, possuí relacionamentos", Link.criar("funcionario"));
        }
    }
}
