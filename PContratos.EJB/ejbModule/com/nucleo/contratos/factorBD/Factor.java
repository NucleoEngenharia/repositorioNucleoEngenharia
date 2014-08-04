package com.nucleo.contratos.factorBD;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Factor {
	
		@PersistenceContext(unitName="Contratos_LOCAL")
		protected static EntityManager em;
		

}
