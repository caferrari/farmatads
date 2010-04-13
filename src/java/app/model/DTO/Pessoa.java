package app.model.DTO;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class Pessoa {

    protected int id;
    protected String nome;
    protected String telefone;
    protected String email;

    public Pessoa() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
