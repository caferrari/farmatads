package app.model.DTO;

/**
 * Classe DTO do Cliente
 *
 * @group MyLastJavaApp
 */
public class Cliente extends PessoaFisica {

	protected int pontos;

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
}
