package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.lang.String;

import app.controller.*;

//import app.RouteRequest;
import java.lang.reflect.Method;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
@WebServlet(name="app", urlPatterns={"/app/*"})
public class Dispatcher extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
        try {

			// Limpa barras do inicio e fim da uri, e quebra ela num vetor
			Env.pars = request.getPathInfo().toString().replaceAll("^/|/$", "").split("/");

			// Trata os parametros do vetor para carregar action, controller e outros parametros
			if (Env.pars.length >= 1 && !Env.pars[0].toString().equals("")){
				Env.controller = Env.pars[0];
				if (Env.pars.length >= 2 ){
					Env.action = Env.pars[1];
				}
			}

			// Define a view default como tendo o mesmo nome da action
			Env.view = Env.action;

			// Coloca o output no environment, para deixar acessivel de qualquer ponto
			Env.out = out;

			// Gera nome da classe do controller
			String controllerClass = Env.controller.substring(0, 1).toUpperCase() + Env.controller.substring(1) + "Controller";

			// Instancia controller e executa action
			try{
				Class		cClass = Class.forName("app.controller." + controllerClass);
				Method		mAction   = cClass.getMethod(Env.action);
				Object		cObj   = cClass.newInstance();
				mAction.invoke(cObj);
			}catch (ClassNotFoundException e){
				out.println("Controller não encontrado");
			}catch (NoSuchMethodException e){
				out.println("Action não encontrada");
			}catch (InstantiationException e){
				out.println("Erro ao instanciar controller");
			}catch (IllegalAccessException e){
				out.println("Acesso ao objeto proibido");
			}catch (InvocationTargetException e){
				out.println("Falha ao invocar action");
			}

			//out.println(Env.controller + "," + Env.action + "," + Env.view + "," + controllerClass);

			// Se a view estiver definida...
			if (null != Env.view){
				// ...Inclui o JSP da view
				getServletContext().getRequestDispatcher("/" + Env.controller + "/" + Env.view + ".jsp").forward(request, response);
			}
			
        } finally {
			// fecha a conexão com o banco, se estiver conectado.
			Banco.close();
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
		Env.post = false;
		processRequest(request, response);
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
		Env.post = true;
        processRequest(request, response);
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
