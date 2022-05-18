package servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	//Retorna apenas o id
	public Long getUsuarioLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado).getId();
		
	}
	//Retorna o objeto inteiro
	public ModelLogin getUsuarioLogadoObject(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario");

		return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado);

	}
	
}
