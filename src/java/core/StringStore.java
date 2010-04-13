package core;

import java.util.Vector;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class StringStore {

	static Vector nome = new Vector();
	static Vector str = new Vector();

	public static void add(String d, String nome){
		StringStore.nome.addElement(nome);
		StringStore.str.addElement(d);
	}

	public static String get(String index){
		try{
			int i = StringStore.nome.indexOf(index);
			return (String)StringStore.str.get(i);
		}catch (Exception e){
			return null;
		}
	}
}