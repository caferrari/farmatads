package core;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.controller.*;
import app.AppDispatcher;

/**
 * Servlet da aplicação Farmatads
 *
 * @group MyLastJavaApp
 */
@WebServlet(name = "app", urlPatterns = {"/app/*"})
public class Dispatcher extends HttpServlet {

    public Env env = new Env();

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response, boolean post) throws ServletException, IOException {

        PrintWriter out = null;
		
        // Reseta o environment
        env.reset();

        // define parametros iniciais de ambiente
        env.post = post;
        env.request = request;
        env.response = response;
        env.message = new Message(request.getSession());

        try
        {
			// Limpa barras do inicio e fim da uri, e quebra ela num vetor
			env.pars = request.getPathInfo().toString().replaceAll("^/|/$", "").split("/");

			// Trata os parametros do vetor para carregar action, controller e outros parametros
			if (env.pars.length >= 1 && !env.pars[0].toString().equals("")) {
				env.controller = env.pars[0];
				if (env.pars.length >= 2) {
					env.action = env.pars[1];
				}
			}

                       
                        if (!env.controller.equals("relatorio")) {
                            out = response.getWriter();

                            // Define os charsets de entrada e saída de dados
                            request.setCharacterEncoding("UTF-8");
                            response.setContentType("text/html;charset=UTF-8");
                        }
			// Define a view default como tendo o mesmo nome da action
			env.view = env.action;

			// Coloca o output no environment, para deixar acessivel de qualquer ponto
			env.out = out;

			// Gera nome da classe do controller
                        // Desabilitado.. reflection nao funcionou como esperado
                        // foi criado o AppDispatcher para contornar o problema!.
			//StringBuffer controllerClass = new StringBuffer();
			//controllerClass.append(env.controller.substring(0, 1).toUpperCase()).append(env.controller.substring(1)).append("Controller");

                        // é uma requisição via ajax?
			env.ajax = (request.getHeader("X-Requested-With") != null);
			
			// Cria o frontController
			FrontController fc = new FrontController();
			fc._env = env;

                        // Action executada antes do controller da aplicação
			fc.beforeController();

            if (!response.isCommitted()){
                // Instancia controller e executa action
                AppDispatcher d = new AppDispatcher();
                boolean dispatched = d.dispatch(env, out);

                // Action executada depois do controller da app.
                fc.afterController();

                // Se o dispatch ocorreu ok, a resposta nao foi comitada e
                // a view estiver habilitada...
                if (dispatched && !response.isCommitted() && null != env.view){

                    // ...Define path da view
                    env.viewUri = "/view/" + env.controller + "/" + env.view + ".jsp";
                    request.setAttribute("env", env);
                    
                    if (env.template)
                        // Se tiver template executa o template
                        getServletContext().getRequestDispatcher("/template/" + env.templateName + ".jsp").forward(request, response);
                    else
                        // Senão executa a view direto
                        getServletContext().getRequestDispatcher(env.viewUri).forward(env.request, response);
                }
            }

            // action executada antes de renderizar na tela
            fc.beforeRender();
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
                out.close();
	}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response, false);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response, true);
	}

	/**
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
