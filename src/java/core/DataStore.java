/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.util.Vector;
import java.sql.ResultSet;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class DataStore {

	static Vector nome = new Vector();
	static Vector rs = new Vector();

	public static void add(ResultSet rs, String nome){
		DataStore.nome.addElement(nome);
		DataStore.rs.addElement(rs);
	}

	public static ResultSet get(String index){
		try{
			int i = DataStore.nome.indexOf(index);
			return (ResultSet)DataStore.rs.get(i);
		}catch (Exception e){
			return null;
		}
	}
}
