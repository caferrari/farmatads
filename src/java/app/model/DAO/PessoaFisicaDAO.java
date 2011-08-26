package app.model.DAO;

import app.model.DTO.PessoaFisica;
import core.*;
import java.sql.*;

/**
 * Classe que realiza operações no banco de dados, na entidade PessoaFisica
 * @group MyLastJavaApp
 */
public class PessoaFisicaDAO {

    /**
     * Adiciona Pessoa Física
     * 
     * @param PessoaFisica p
     * @return int
     * @throws Exception
     */
    public static int adicionar(PessoaFisica p) throws Exception
    {
        // Adiciona Pessoa para poder relacionar a PessoaFisica
        int id = PessoaDAO.criar(p);

        if (id > 0)
        {
            PreparedStatement stmt = Banco.query("INSERT INTO pessoa_fisica (id, cpf, rg) VALUES (?, ?, ?)");

            stmt.setInt(1, id);
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getRg());
            Banco.executar(stmt);
        }

        return id;
    }

    /**
     * Edita Pessoa Fisica
     *
     * @param PessoaFisica p
     * @throws Exception
     */
    public static void editar(PessoaFisica p) throws Exception
    {
        // Edita Pessoa 
        PessoaDAO.editar(p);
        
        PreparedStatement stmt = Banco.query("UPDATE pessoa_fisica SET cpf = ?, rg =? WHERE id = ?");
        stmt.setString(1, p.getCpf());
        stmt.setString(2, p.getRg());
        stmt.setInt(3, p.getId());
        Banco.executar(stmt);
    }

}
