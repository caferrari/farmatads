package app.model.DAO;

import app.model.DTO.PessoaJuridica;
import core.*;
import java.sql.*;

/**
 * Classe que realiza operações no banco de dados, na entidade PessoaJuridica
 * @group MyLastJavaApp
 */
public class PessoaJuridicaDAO {

    /**
     * Adiciona Pessoa Juridica
     * 
     * @param PessoaJuridica p
     * @return int
     * @throws Exception
     */
    public static int adicionar(PessoaJuridica p) throws Exception
    {
        // Adiciona Pessoa para poder relacionar a Pessoa Juridica
        int id = PessoaDAO.criar(p);

        if (id > 0)
        {
            PreparedStatement stmt = Banco.query("INSERT INTO pessoa_juridica (id, cnpj, razao_social) VALUES (?, ?, ?)");

            stmt.setInt(1, id);
            stmt.setString(2, p.getCnpj());
            stmt.setString(3, p.getRazaoSocial());
            Banco.executar(stmt);
        }

        return id;
    }

    /**
     * Edita Pessoa Juridica
     *
     * @param PessoaJuridica p
     * @throws Exception
     */
    public static void editar(PessoaJuridica p) throws Exception
    {
        // Edita Pessoa
        PessoaDAO.editar(p);
        
        PreparedStatement stmt = Banco.query("UPDATE pessoa_juridica SET cnpj = ?, razao_social =? WHERE id = ?");
        stmt.setString(1, p.getCnpj());
        stmt.setString(2, p.getRazaoSocial());
        stmt.setInt(3, p.getId());
        Banco.executar(stmt);
    }

    /**
     * Remove Pessoa Juridica
     * 
     * @param String id
     * @throws Exception
     */
    public static void excluir(String id) throws Exception
    {
        // Ao remover pessoa remove os itens relacionados configurados para serem apagados em cascata
        PessoaDAO.excluir(id);
    }

}
