package app.model.DTO;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class Produto {

	protected int id;
	protected String nome;
	protected String laboratorio;
	protected int quantidade;
	protected double valor_venda;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor_venda() {
		return valor_venda;
	}

	public void setValor_venda(double valor_venda) {
		this.valor_venda = valor_venda;
	}

}
