package core;

import java.sql.ResultSet;
import java.lang.String;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 * @author Glesio Paiva <glesio@gmail.com>
 */
public class Controller {

	protected ResultSet _add(String nome, ResultSet rs) {
		DataStore.add(nome, rs);
		return rs;
	}

	protected String _add(String nome, String str) {
		DataStore.add(nome, str);
		return str;
	}

	protected void disableView(Boolean view) {
		if (true == view) {
			Env.view = null;
		}
	}
}
