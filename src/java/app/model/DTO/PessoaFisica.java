package app.model.DTO;

/**
 * Classe DTO de PessoaFisica
 *
 * @group MyLastJavaApp
 */
public class PessoaFisica extends Pessoa {
    
    protected String cpf;
    protected String rg;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
}
