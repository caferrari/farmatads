package core;

import java.sql.ResultSet;
import java.util.Vector;

/**
 * Classe de Controller
 *
 * @group MyLastJavaApp
 */
public class Controller {

    public Env _env;

    /**
     * Adiciona Atributo ResultSet ao Request
     * 
     * @param String nome
     * @param ResultSet rs
     * @return ResultSet
     */
    protected ResultSet _add(String nome, ResultSet rs) {
        _env.request.setAttribute(nome, rs);
        return rs;
    }

    /**
     * Adicionar Atributo Null ao Request
     *
     * @param String nome
     */
    protected void _addNull(String nome) {
        _env.request.setAttribute(nome, null);
    }

    /**
     * Adicionar Atributo String ao Request
     * 
     * @param String nome
     * @param String str
     */
    protected void _add(String nome, String str) {
        _env.request.setAttribute(nome, str);
    }

    /**
     * Adicionar Atributo Vetor ao Request
     *
     * @param String nome
     * @param String v
     */
    protected void _add(String nome, Vector v) {
        _env.request.setAttribute(nome, v);
    }

    /**
     * Redireciona
     * 
     * @param String url
     */
    protected void redirect(String url)
    {
        this._env.response.setHeader("location", url);
        this._env.response.setStatus(this._env.response.SC_MOVED_TEMPORARILY);
        try{
            this._env.response.sendRedirect(url);
        }
        catch (Exception e)
        { }
    }

    /**
     * Mensagem de Sucesso
     * 
     * @param String message
     * @param String link
     */
    protected void POSTsuccess(String message, String link)
    {
        this._env.message.set(message, "ok");
        this.redirect(link);
    }

    /**
     * Mensagem de Erro
     * 
     * @param String message
     * @param String link
     */
    protected void POSTerror(String message, String link)
    {
        this._env.message.set(message, "erro");
        this.redirect(link);
    }

    /**
     * Recupera Parametro
     * 
     * @param String nome
     * @return String
     */
    protected String getPar(String nome)
    {
        return this._env.request.getParameter(nome);
    }

    /**
     * Desavilita View
     * 
     * @param String view
     */
    protected void disableView(boolean view) {
        if (true == view) {
            _env.view = null;
        }
    }

    /**
     * Desabilita Template
     * 
     * @param String template
     */
    protected void disableTemplate(boolean template) {
        _env.template = !template;
    }

    /**
     * Define Template a ser renderizada
     * 
     * @param template
     */
    protected void setTemplate(String template) {
        _env.templateName = template;
    }

    /**
     * Define View a ser renderizada
     *
     * @param template
     */
    protected void setView(String view) {
        _env.view = view;
    }

}
