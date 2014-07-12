package com.nucleo.intranet.FactoringConnection;


import java.sql.Connection;

import javax.ejb.Remote;

@Remote
public interface Factor {
	
	Connection doAction();
}
