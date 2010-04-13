/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.model.DTO;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class Fornecedor extends PessoaJuridica {

	protected String contato;

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
}
