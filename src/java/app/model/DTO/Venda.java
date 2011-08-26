package app.model.DTO;

import java.util.Date;

/**
 * Classe DTO de Venda
 *
 * @group MyLastJavaApp
 */
public class Venda {

	protected int id;
	protected Date data;
	protected double desconto;
        protected double valorTotal;
        protected String formaPagto;
	protected Cliente cliente;
	protected Funcionario funcionario;

    public Venda(){
        this.data = new Date();
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

        public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

        public String getFormaPagto() {
		return formaPagto;
	}

	public void setformaPagto(String formaPagto) {
		this.formaPagto = formaPagto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
