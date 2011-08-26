package app.model.DTO;

/**
 * Classe DTO do Funcionario
 *
 * @group MyLastJavaApp
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
