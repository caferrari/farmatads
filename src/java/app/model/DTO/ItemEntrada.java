package app.model.DTO;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class ItemEntrada {

	Entrada entrada;
	Produto produto;
	int quantidade;
	double valor_compra;

	public ItemEntrada(Entrada entrada, Produto produto, int quantidade) {
		this.entrada = entrada;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor_compra() {
		return valor_compra;
	}

	public void setValor_compra(double valor_compra) {
		this.valor_compra = valor_compra;
	}

}
