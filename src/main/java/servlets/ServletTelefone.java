package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository usuarioRepository = new DAOUsuarioRepository();
	private DAOTelefoneRepository telefoneRepository = new DAOTelefoneRepository();
       
    public ServletTelefone() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String iduser = request.getParameter("iduser");

			if (iduser != null && !iduser.isEmpty()) {

				ModelLogin modelLogin = usuarioRepository.consultarUsuarioPorId(Long.parseLong(iduser));
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

			} else {
				List<ModelLogin> modelLogins = usuarioRepository.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", usuarioRepository.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setNumero(numero);
			modelTelefone.setUsuario_pai_id(usuarioRepository.consultarUsuarioPorId(Long.parseLong(usuario_pai_id)));
			modelTelefone.setUsuario_cad_id(super.getUsuarioLogadoObject(request));
			
			telefoneRepository.gravarTelefone(modelTelefone);
			
			request.setAttribute("msg", "Salvo com sucesso!");
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
