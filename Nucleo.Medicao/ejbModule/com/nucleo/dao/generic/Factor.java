package com.nucleo.dao.generic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class Factor {
	@PersistenceContext(unitName="MedicaoControle_LOCAL")
	private static EntityManager emMC;
	
	public EntityManager getEntityManagerMedicaoControle(){
		return emMC;
	}
}
