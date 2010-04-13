package core;

import java.sql.ResultSet;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class Controller {

	public ResultSet _add(ResultSet rs, String nome){
		DataStore.add(rs, nome);
		return rs;
	}

	public String _add(String str, String nome){
		StringStore.add(str, nome);
		return str;
	}

}
