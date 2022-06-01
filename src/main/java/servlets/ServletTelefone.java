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
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {

				String userPai = request.getParameter("userPai");

				String idTelefone = request.getParameter("idTelefone");

				telefoneRepository.deleteTelecone(Long.parseLong(idTelefone), Long.parseLong(userPai));
				
				ModelLogin modelLogin = usuarioRepository.consultarUsuarioPorId(Long.parseLong(userPai));

				List<ModelTelefone> modelTelefones = telefoneRepository.listarTelefones((modelLogin.getId()));
				request.setAttribute("modelTelefones", modelTelefones);

				request.setAttribute("msg", "Telefone excluido com sucesso !");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;

			}

			if (iduser != null && !iduser.isEmpty()) {

				ModelLogin modelLogin = usuarioRepository.consultarUsuarioPorId(Long.parseLong(iduser));
				
				List<ModelTelefone> modelTelefones = telefoneRepository.listarTelefones((modelLogin.getId()));	 
				request.setAttribute("modelTelefones", modelTelefones);
				
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
			String userPai = request.getParameter("iduser");
			String numero = request.getParameter("numero");
			
			if (!telefoneRepository.validaTelefone(numero, Long.valueOf(userPai))) {
				
				ModelTelefone modelTelefone = new ModelTelefone();
				
				modelTelefone.setNumero(numero);
				modelTelefone.setUsuario_pai_id(usuarioRepository.consultarUsuarioPorId(Long.parseLong(userPai)));
				modelTelefone.setUsuario_cad_id(super.getUsuarioLogadoObject(request));
				
				telefoneRepository.gravarTelefone(modelTelefone);
				
				request.setAttribute("msg", "Salvo com sucesso!");

			}else {
				request.setAttribute("msg", "O telefone informado já existe!");
			}
			
			List<ModelTelefone> modelTelefones = telefoneRepository.listarTelefones(Long.parseLong(userPai));
			
			ModelLogin modelLogin = usuarioRepository.consultarUsuarioPorId(Long.parseLong(userPai));
			
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelTelefones", modelTelefones);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
