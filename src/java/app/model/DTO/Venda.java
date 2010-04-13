package app.model.DTO;

import java.util.Date;
//import java.text.SimpleDateFormat;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class Venda {

	protected int id;
	protected Date data;
	protected double desconto;
	protected Cliente cliente;
	protected Funcionario funcionario;

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
