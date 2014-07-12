package com.nucleo.intranet.FactoringConnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
@Stateless
public class FactorImpl implements Factor{

	private Connection conn = null;
	
	@Resource(mappedName="java:jboss/datasources/intranet")
	protected DataSource dataSource;
	@Override
	public Connection doAction() {
		try {
			conn = dataSource.getConnection();
			return conn;
		}catch(NullPointerException e){
			System.out.println("A conexão é: ...");
		}
		catch (SQLException e) {
			System.out.println("Erro em Factor linha 26"+e);
		}
		return conn;
	}
}
