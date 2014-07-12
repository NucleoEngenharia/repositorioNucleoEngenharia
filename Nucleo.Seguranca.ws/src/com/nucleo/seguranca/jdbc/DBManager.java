package com.nucleo.seguranca.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {

	public static Connection obterConexao() {
		
		Connection connection = null;
		
		String usuario = "intranet_sistema";
		String senha = "Sistema!@#321";
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/intranet", usuario, senha);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
