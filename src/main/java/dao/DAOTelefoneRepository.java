package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {
	
	private Connection connection;
	private DAOUsuarioRepository daoUsuarioRepository =  new DAOUsuarioRepository();
	
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	//Método responsável por inserir os dados do telefone
	public void gravarTelefone (ModelTelefone modeltelefone) throws Exception{
		
		String sql = "insert into telefone (numero, usuario_pai_id, usuario_cad_id) values (?,?,?);";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, modeltelefone.getNumero());
		preparedStatement.setLong(2, modeltelefone.getUsuario_pai_id().getId());
		preparedStatement.setLong(3, modeltelefone.getUsuario_cad_id().getId());
		
		preparedStatement.execute();
		connection.commit();
	}

	// Método responsável por deletar dados do telefone
	public void deleteTelecone(Long idTelefone, Long userPai) throws Exception {
		String sql = "delete from telefone where id = ? and usuario_pai_id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, idTelefone);
		preparedStatement.setLong(2, userPai);
		preparedStatement.executeUpdate();
		
		connection.commit();
	}
	
	public List<ModelTelefone> listarTelefones(Long userPai) throws Exception{
		
		List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
		
		String sql = "select * from telefone where usuario_pai_id = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, userPai);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		while(resultado.next()) {
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId(resultado.getLong("id"));
			modelTelefone.setNumero(resultado.getString("numero"));
			modelTelefone.setUsuario_cad_id(daoUsuarioRepository.consultarUsuarioPorId(resultado.getLong("usuario_cad_id")));
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultarUsuarioPorId(resultado.getLong("usuario_pai_id")));
			
			telefones.add(modelTelefone);
		}
		return telefones;
	}
	
public List<ModelTelefone> listarTelefonesPorID(Long idUsuario) throws Exception{
		
		List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
		
		String sql = "select * from telefone where usuario_pai_id = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUsuario);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		while(resultado.next()) {
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId(resultado.getLong("id"));
			modelTelefone.setNumero(resultado.getString("numero"));
			modelTelefone.setUsuario_cad_id(daoUsuarioRepository.consultarUsuarioPorId(resultado.getLong("usuario_cad_id")));
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultarUsuarioPorId(resultado.getLong("usuario_pai_id")));
			
			telefones.add(modelTelefone);
		}
		return telefones;
	}
	
	public boolean validaTelefone(String telefone, Long idUsuario) throws Exception {
		String sql = "select count(1) > 0  as existe from telefone where usuario_pai_id = ? and numero = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, idUsuario);
		preparedStatement.setString(2, telefone);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		resultado.next();
		
		return resultado.getBoolean("existe");
	}
}
