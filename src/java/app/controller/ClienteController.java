package app.controller;

import core.Controller;
import core.Link;
import app.model.DTO.Cliente;
import app.model.DAO.ClienteDAO;

/**
 * Controller de Cliente
 * 
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class ClienteController extends Controller {

    /**
     * Página cliente/ do Controller Cliente
     */
    public void index()
    {}

    /**
     * Página cliente/adicionar
     */
    public void adicionar() {

       // Verifica se foi submetido o formulario em POST para Adicionar o Cliente
       if (this._env.post) {

            // Recupera dados do Formulário e atribui valores ao Objeto Cliente
            Cliente c = new Cliente();

            c.setNome(this.getPar("nome"));
            c.setTelefone(this.getPar("telefone"));
            c.setCpf(this.getPar("cpf"));
            c.setRg(this.getPar("rg"));
            c.setEmail(this.getPar("email"));

            if (c.getNome().equals("") || c.getEmail().equals("")){
                this.POSTerror("Nome e email são obrigatórios", Link.criar("cliente/adicionar"));
                return;
            }

            // Insere Cliente no banco de dados
            try{
                // Adiciona no banco de dados passando como parametro o objeto Cliente
                ClienteDAO.adicionar(c);
                // Desabilita a renderização da View
                this.disableView(true);
            } catch (Exception e){
                // Mensagem de erro e redirecionamento
                this.POSTerror("Erro ao adicionar cliente", Link.criar("cliente/adicionar"));
                return;
            }

            // Mensagem de sucesso e redirecionamento
            this.POSTsuccess("Cliente adicionado com sucesso", Link.criar("cliente"));
            return;
	}
    }

    /**
     * Página cliente/editar
     */
    public void editar() {
        // Verifica se foi submetido o formulario em POST para Adicionar o Cliente
        if (this._env.post) {

            // Recupera dados do Formulário e atribui valores ao Objeto Cliente
            Cliente c = new Cliente();

            c.setId(Integer.parseInt(this.getPar("id")));
            c.setNome(this.getPar("nome"));
            c.setTelefone(this.getPar("telefone"));
            c.setCpf(this.getPar("cpf"));
            c.setRg(this.getPar("rg"));
            c.setEmail(this.getPar("email"));

            if (c.getNome().equals("") || c.getEmail().equals("")){
                this.POSTerror("Nome e email são obrigatórios", Link.criar("cliente/editar/" + this.getPar("id")));
                return;
            }

            try{
                // Edita Cliente
                ClienteDAO.editar(c);
            } catch (Exception e){
                // Mensagem de erro e redirecionamento
                this.POSTerror("Erro ao editar cliente", Link.criar("cliente/editar/" + this.getPar("id")));
                return;
            }

            // Mensagem de Sucesso e redirecionamento
            this.POSTsuccess("Cliente editado com sucesso", Link.criar("cliente"));
            return;
        }
        // Define qual view deve ser renderizada, por padrão é o nome do método
        this.setView("adicionar");
    }

    /**
     * Página cliente/excluir
     */
    public void excluir() {
        try{
            // Veirifica se foi passado o id do cliente para remover
            if (this._env.pars.length >= 3) {
                // Recupera ID
                String id = this._env.pars[2];
                // Remove Cliente
                ClienteDAO.excluir(id);
                // Mensagem de sucesso e rendirecionamento
                this.POSTsuccess("Cliente excluido com sucesso", Link.criar("cliente"));
            }
        }catch (Exception e) {
            // Mensagem de erro e redirecionamento
            this.POSTerror("Cliente não pode ser excluído, possuí relacionamentos", Link.criar("cliente"));
        }
    }

}