package app.controller;

import app.model.DTO.Usuario;
import core.Controller;
import core.Link;

/**
 * Controller Item de Entrada
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class LoginController extends Controller {

    /**
     * Página login
     */
    public void index()
    {
        this.setTemplate("login");
        Usuario u = new Usuario();
        u.sair(this._env.request.getSession());

        if (this._env.post) {
            // Pega parametros
            u.setEmail(this.getPar("email"));
            u.setSenha(this.getPar("senha"));

            // tenta autenticar
            try {
                if (true == u.autenticar(this._env.request.getSession())) {
                    this.POSTsuccess("Login efetuado com sucesso!", Link.criar("venda"));
                }else{
                    this.POSTerror("Usuário ou senha incorretos!", Link.criar("login"));
                }
            } catch(Exception e) {

            }
        }
    }

    /**
     * Página login/sair
     */
    public void sair()
    {
        // Faz logout do sistema
        this.setTemplate("login");
        Usuario u = new Usuario();
        u.sair(this._env.request.getSession());
        this.POSTsuccess("Logout efetuado com sucesso", Link.criar("login"));
    }
}