package servlets;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletRelatorioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletRelatorioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equals("imprimirRelatorioUsuario")) {
			
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			
			request.setAttribute("dataInicial",dataInicial);
			request.setAttribute("dataFinal", dataFinal);
			request.getRequestDispatcher("principal/relatorio-usuario.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
