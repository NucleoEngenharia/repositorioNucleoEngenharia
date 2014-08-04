package com.nucleo.FactoringConnection;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class FactorIntranetImpl {
	
	@Resource(mappedName="java:jboss/datasources/intranet")
	protected DataSource dataSource;
}
