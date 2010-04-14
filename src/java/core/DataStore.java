package core;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 * @author Glesio Paiva <glesio@gmail.com>
 */
public class DataStore {

	static HashMap map = new HashMap();

	/**
	 *
	 * @param nome
	 * @param rs
	 */
	public static void add(String nome, ResultSet rs) {
		DataStore.map.put(nome, rs);
	}

	/**
	 *
	 * @param index
	 * @return ResultSet
	 */
	public static ResultSet getResutSet(String index) {
		return (ResultSet) DataStore.map.get(index);
	}

	/**
	 *
	 * @param d
	 * @param nome
	 */
	public static void add(String nome, String valor) {
		DataStore.map.put(nome, valor);
	}

	/**
	 *
	 * @param index
	 * @return
	 */
	public static String getString(String index) {
		return (String) DataStore.map.get(index);
	}
}
