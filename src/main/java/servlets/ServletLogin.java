package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;


public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public ServletLogin() {
       
    }

    // Receive data via URL in parameters
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	// Receive the data sent via a form
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("Login");
		String senha = request.getParameter("Senha");
		
		if (login != null && login.isEmpty() && senha != null && senha.isEmpty()) {
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe login e senha correto!" );
			redirecionar.forward(request, response);
		}
	}
}
