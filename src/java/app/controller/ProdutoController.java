package app.controller;

import core.Controller;
import core.Env;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class ProdutoController extends Controller {

	public void index(){
        this.disableView(true);
    }

}
