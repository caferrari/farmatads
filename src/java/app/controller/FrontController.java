package app.controller;

import app.model.DTO.Usuario;
import core.Controller;
import core.Link;

/**
 *
 * @group MyLastJavaApp
 */
public class FrontController extends Controller {

    /**
     * Executado em todas as requisições
     */
    public void beforeController() {
        // Verifica se o usuario está logado
        if ((!this._env.controller.equals("login")) && (!Usuario.isAutenticado(this._env.request.getSession()))) {
            this.POSTerror("Você precisa estar logado para acessar o sistema", Link.criar("login"));
        }
    }

    public void afterController() {
    }

    public void beforeRender() {
    }
}
