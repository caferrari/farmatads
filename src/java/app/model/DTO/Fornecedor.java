package app.model.DTO;

/**
 * Classe DTO de Fornecedor
 *
 * @group MyLastJavaApp
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
