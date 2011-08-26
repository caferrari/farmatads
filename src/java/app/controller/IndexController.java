package app.controller;

import core.Controller;
import core.Link;

/**
 * Controller Index
 *
 * Camada responsável por integrar View e Model
 *
 * @group MyLastJavaApp
 */
public class IndexController extends Controller {

    /**
     * Página /
     */
    public void index() {
        // redireciona para a tela de venda
        this.redirect(Link.criar("venda"));
    }
}
