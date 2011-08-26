package core;
import javax.servlet.http.HttpSession;

/**
 * Classe Para Manipulação de Mensagem
 *
 * @group MyLastJavaApp
 */
public class Message {

    public String message = null;
    public String type = null;
    private HttpSession session;

    // Construtor
    Message(HttpSession session)
    {
        this.session = session;
        if (null != session.getAttribute("f_message")){
            this.message = (String)session.getAttribute("f_message");
            this.type = (String)session.getAttribute("f_type");
        }
    }

    // Define a session de mensagem
    public void set(String message, String type)
    {
        session.setAttribute("f_message", message);
        session.setAttribute("f_type", type);
    }

    // Pega a session de mensagem e limpa
    public String getMessage()
    {
        session.setAttribute("f_message", null);
        session.setAttribute("f_type", null);
        return this.message;
    }

}
