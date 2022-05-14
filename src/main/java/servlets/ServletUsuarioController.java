package servlets;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {

	}

	// Consult and Delete
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarLogin(idUser);

				List<ModelLogin> modelLogins = daoUsuarioRepository.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Excluído com sucesso!!! ");
				// Return to same page
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarLogin(idUser);

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserajax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUsuarioLogado(request));

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosJsonUser);

				response.addHeader("totalPagina", "" + daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUsuarioLogado(request)));
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {

				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");

				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioListOffSet(nomeBusca, super.getUsuarioLogado(request), Integer.parseInt(pagina));

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosJsonUser);

				response.addHeader("totalPagina", "" + daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUsuarioLogado(request)));
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioPorId(id, super.getUsuarioLogado(request));

				List<ModelLogin> modelLogins = daoUsuarioRepository.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Usuário em Edição!");
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<ModelLogin> modelLogins = daoUsuarioRepository.listarUsuarios(super.getUsuarioLogado(request));

				request.setAttribute("msg", "Usuários Carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downLoadFoto")) {

				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioPorId(idUser, super.getUsuarioLogado(request));
				
				if (modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtensaofotouser());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().split("\\,")[1]));
				}
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListPaginado(this.getUsuarioLogado(request), offset);
				
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else {
				// Return to same page
				List<ModelLogin> modelLogins = daoUsuarioRepository.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}

	}

	// Save and update
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso!";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				
				Part part = request.getPart("fileFoto"); // Take the picture from the screen
				
				if(part.getSize() > 0) {
					byte [] foto = IOUtils.toByteArray(part.getInputStream()); // Convert image to byte
					String imagemBase64 = "data:image/"  + part.getContentType().split("\\/")[1] + ";base64," +  new Base64().encodeBase64String(foto);
					
					modelLogin.setFotouser(imagemBase64);
					modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
				}
			}

			// Check if the login already exists
			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login, informe outro login";
			} else {
				if (modelLogin.idNovo()) {
					msg = "Gravado com sucesso!";
				} else {
					msg = "Atualizado com sucesso!";
				}
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUsuarioLogado(request));
			}

			List<ModelLogin> modelLogins = daoUsuarioRepository.listarUsuarios(super.getUsuarioLogado(request));
			request.setAttribute("modelLogins", modelLogins);

			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUsuarioLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
