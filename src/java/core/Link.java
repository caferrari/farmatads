package core;

import app.Configuracao;

/**
 * Classe para manipular Link
 *
 * @group MyLastJavaApp
 */
public class Link {

        /**
         * Cria Link
         * 
         * @return String
         */
	static public String criar(){
		return Configuracao.APP_ROOT;
	}

        /**
         * Cria link de acordo com a Página
         * 
         * @param String path
         * @return String
         */
	static public String criar(String path){
		if (path.matches("^.*\\.(css|js|jsp|jpg|gif|png)$"))
			return Configuracao.ROOT.concat("template/".concat(path));
		else
			return Configuracao.APP_ROOT + path;
	}
}
