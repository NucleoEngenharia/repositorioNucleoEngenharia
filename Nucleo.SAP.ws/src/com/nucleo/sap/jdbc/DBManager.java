package com.nucleo.sap.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	public static Connection obterConexao() {

		Connection connection = null;
//		String connectionUrl = "jdbc:sqlserver://192.168.100.18;user=sa;password=5y3LNg1v;databaseName=SBO_NUCLEO_OFICIAL;";
		String connectionUrl = "jdbc:sqlserver://SAPSERVER;instanceName=SAPNUCLEO;user=sa;password=d29m12@;databaseName=SBO_NUCLEO_OFICIAL;";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
