package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRpository {

	private Connection connection;

	public DAOLoginRpository() {
		connection = SingleConnectionBanco.getConnection();
	}

	// Method to validate login
	public boolean validarAutenticacao(ModelLogin modelLogin) throws SQLException {

		String sql = "select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement buscarnoBanco = connection.prepareStatement(sql);
		buscarnoBanco.setString(1, modelLogin.getLogin());
		buscarnoBanco.setString(2, modelLogin.getSenha());

		ResultSet resultado = buscarnoBanco.executeQuery();

		if (resultado.next()) {
			return true; // authenticated
		}
		return false; // Unauthenticated
	}

}
