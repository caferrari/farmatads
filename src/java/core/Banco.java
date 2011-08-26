package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.Configuracao;

/**
 * Classe de Conexao e Manipulação com o Banco de Dados
 * 
 * @group MyLastJavaApp
 */
public class Banco {

    private static Connection conexao;

    /**
     * Método Abre conexao com o banco de dados
     *
     * @return Connection
     * @throws Exception
     */
    public static Connection getConexao() throws Exception {
        if (null == conexao) {
            try {
                Class.forName(Configuracao.BD_DRIVER).newInstance();
                conexao = DriverManager.getConnection(Configuracao.BD_URL, Configuracao.BD_USER, Configuracao.BD_PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new Exception("Não foi possível localizar o Driver de banco de dados.");
            } catch (SQLException e) {
                throw new Exception("Não foi possível conectar com o Banco de Dados.");
            }
        }
        return conexao;
    }

    /**
     * Metodo cria statement do sql
     *
     * @param String sql
     * @return PreparedStatement
     * @throws SQLException
     */
    public static PreparedStatement query(String sql) throws SQLException, Exception {
        return Banco.getConexao().prepareStatement(sql);
    }

    /**
     * Executa a Query
     *
     * @param stmt
     * @throws SQLException
     */
    public static boolean executar(PreparedStatement stmt) throws SQLException {
        boolean retorno = stmt.execute();
        stmt.close();
        return retorno;
    }

    /**
     * Faz uma consulta
     *
     * @param stmt
     * @throws SQLException
     */
    public static ResultSet consulta(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    /**
     * Faz uma consulta
     *
     * @param sql
     * @throws SQLException
     */
    public static ResultSet consulta(String sql) throws SQLException, Exception
    {
        PreparedStatement stmt = Banco.query(sql);
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    /**
     * Fecha a conexão com o BD
     *
     * @param stmt
     * @throws SQLException
     */
    public static void close() {
        try {
            if (null == conexao) {
                return;
            }
            Banco.getConexao().close();
        } catch (Exception e) {
            return;
        }
    }
}
