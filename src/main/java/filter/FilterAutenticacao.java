package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"}) // It intercepts all requests that come from the project or mapping
public class FilterAutenticacao implements Filter {

	public FilterAutenticacao() {
	}

	// Terminates processes when server is stopped
	public void destroy() {
	}

	// Intercepts requests and gives responses in the system
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest requestServletLogin = (HttpServletRequest) request;
		HttpSession session = requestServletLogin.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario");

		String urlParaAutenticar = requestServletLogin.getServletPath(); // URL being accessed

		// Validate if you are currently logged in or redirect to login screen
		if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { // not logged in

			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login");
			redireciona.forward(request, response);
			return; // Stops execution and redirects to login
			
		} else {
			chain.doFilter(request, response);
		}
	}

	// Starts processes or resources when the server uploads the project
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
