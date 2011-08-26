package app.model.DTO;

/**
 * Classe DTO de Produto
 *
 * @group MyLastJavaApp
 */
public class Produto {

	protected int id;
	protected String nome;
	protected String laboratorio;
    protected String descricao;
	protected int quantidade;
	protected float valor_venda;
    protected String estoque;

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

        public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

        public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public float getValor_venda() {
		return valor_venda;
	}

	public void setValor_venda(float valor_venda) {
		this.valor_venda = valor_venda;
	}

}
