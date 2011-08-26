package app.model.DAO;

import app.model.DTO.Usuario;
import core.*;
import java.sql.*;

/**
 * Classe que realiza operações no banco de dados, na entidade Usuario
 * @group MyLastJavaApp
 */
public class UsuarioDAO {

    /**
     * Consulta Funcionario/Usuario para Autenticar
     *
     * @param Usuario u
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet get(Usuario u) throws Exception
    {
        PreparedStatement consulta = Banco.query("SELECT p.id, p.email, p.nome, f.administrador FROM pessoa p "
                                                 + " INNER JOIN funcionario f ON f.id=p.id "
                                                 + " WHERE p.email = ? AND f.senha = ?"
                                                 + " LIMIT 1");
        consulta.setString(1, u.getEmail());
        consulta.setString(2, u.getSenha());

        return Banco.consulta(consulta);
    }

}