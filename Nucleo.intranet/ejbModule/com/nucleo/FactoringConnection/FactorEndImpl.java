package com.nucleo.FactoringConnection;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class FactorEndImpl{
	@Resource(mappedName="java:jboss/datasources/Endereco")
	protected DataSource dataSource;
}
