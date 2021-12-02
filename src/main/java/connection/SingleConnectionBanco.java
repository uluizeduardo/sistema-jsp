package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String url = "jdbc:postgresql://localhost:5432/sistema-jsp?autoreconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;

	// Allows to connect to the database if the class is called directly (no
	// instance)
	static {
		conectar();
	}

	// This constructor allows you to connect to the database through an instance
	public SingleConnectionBanco() {// When it has an instance it will connect
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); // Load the bank connection driver
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false);// To not make changes to the bank without our command
				System.out.println("Conexão realizada com sucesso! ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Returns the connection state
	public static Connection getConnection() {
		return connection;
	}

}
