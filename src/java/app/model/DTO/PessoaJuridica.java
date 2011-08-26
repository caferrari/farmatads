package app.model.DTO;

/**
 * Classe DTO Pessoa Juridica
 *
 * @group MyLastJavaApp
 */
public class PessoaJuridica extends Pessoa {
    
    protected String cnpj;
    protected String razaoSocial;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
}
