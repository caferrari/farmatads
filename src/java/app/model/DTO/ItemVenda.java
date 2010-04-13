package app.model.DTO;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class ItemVenda {

	Venda venda;
	Produto produto;
	int quantidade;
	double valor_venda;

	public ItemVenda(Venda venda, Produto produto, int quantidade) {
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
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

	public double getValor_venda() {
		return valor_venda;
	}

	public void setValor_venda(double valor_venda) {
		this.valor_venda = valor_venda;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
