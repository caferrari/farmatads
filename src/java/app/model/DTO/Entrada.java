package app.model.DTO;

import java.util.Date;

/**
 * Classe DTO de Entrada
 *
 * @group MyLastJavaApp
 */
public class Entrada {

	int id;
	Date data;
	Fornecedor fornecedor;
	Funcionario funcionario;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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
