package core;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe para Integrar Servlet Controller
 *
 * @group MyLastJavaApp
 */
public class Env {
    
    public boolean post = false;
    public boolean template = true;
    public boolean ajax = false;

    public String controller = "index";
    public String action = "index";
    public String templateUri = "/template/";
    public String templateName = "index";

    public String view = null;
    public String viewUri = null;

    public HttpServletRequest request = null;
    public HttpServletResponse response = null;

    public String[] pars = null;
    public PrintWriter out = null;

    public Message message = null;

    public void reset()
    {
        this.post = false;
        this.template = true;
        this.ajax = false;

        this.controller = "index";
        this.action = "index";
        this.templateUri = "/template/";
        this.templateName = "index";

        this.view = null;
        this.viewUri = null;

        this.request = null;
        this.response = null;

        this.pars = null;
        this.out = null;

        //this.message = new Message(this.request.getSession());
    }
}
