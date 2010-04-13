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
public class Funcionario extends PessoaFisica {

	protected String endereco;
	protected String senha;
	protected boolean administrador;

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
