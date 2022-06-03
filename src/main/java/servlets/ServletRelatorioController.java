package servlets;

import java.io.IOException;
import java.sql.Date;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletRelatorioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	public ServletRelatorioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			DAOUsuarioRepository usuarioRepository = new DAOUsuarioRepository();
			DAOTelefoneRepository telefoneRepository = new DAOTelefoneRepository();
	 		
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equals("imprimirRelatorioUsuario")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if(dataInicial == null || dataInicial.isEmpty() ||  dataFinal == null || dataFinal.isEmpty()) {
					
					request.setAttribute("listaUsuarios", usuarioRepository.consultarUsuarioRelatorio(super.getUsuarioLogado(request)));
					request.setAttribute("listaTelefones", telefoneRepository.listarTelefones(super.getUsuarioLogado(request)));
				}
				
				request.setAttribute("dataInicial",dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/relatorio-usuario.jsp").forward(request, response);
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
