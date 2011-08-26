package app.model.DTO;

import app.model.DAO.UsuarioDAO;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

/**
 * Classe DTO de Usuário
 *
 * @group MyLastJavaApp
 */
public class Usuario{

	protected String email;
        protected String senha;


        public void setEmail(String email)
        {
            this.email = email;
        }

        public String getEmail()
        {
            return this.email;
        }

        public void setSenha(String senha)
        {
            this.senha = senha;
        }

        public String getSenha()
        {
            return this.senha;
        }

        public boolean autenticar(HttpSession session)
        {
            try {
                ResultSet rs = UsuarioDAO.get(this);

                if (!rs.next())
                    return false;
                else {

                    session.setAttribute("f_id", rs.getInt("id"));
                    session.setAttribute("f_email", rs.getString("email"));
                    session.setAttribute("f_nome", rs.getString("nome"));
                    session.setAttribute("f_administrador", rs.getBoolean("administrador"));

                    return true;
                }

            } catch (Exception e) {
                return false;
            }            
        }

        public void  sair(HttpSession session)
        {
            session.removeAttribute("f_id");
            session.removeAttribute("f_email");
            session.removeAttribute("f_nome");
            session.removeAttribute("f_administrador");
        }

        public static boolean isAutenticado(HttpSession session)
        {
            if (null != session.getAttribute("f_id"))
                return true;

            return false;
        }

}