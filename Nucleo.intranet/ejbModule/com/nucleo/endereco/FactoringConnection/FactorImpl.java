package com.nucleo.endereco.FactoringConnection;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class FactorImpl {
	@Resource(mappedName="java:jboss/datasources/Endereco")
	protected DataSource dataSource;

}
