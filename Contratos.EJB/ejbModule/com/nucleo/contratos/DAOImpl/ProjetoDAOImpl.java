package com.nucleo.contratos.DAOImpl;

import java.util.List;

import javax.ejb.Stateless;

import com.nucleo.contratos.FactorBD.Factor;
import com.nucleo.contratos.dao.ProjetoDAO;
import com.nucleo.contratos.entity.Projeto;

@Stateless
public class ProjetoDAOImpl extends Factor implements ProjetoDAO {

	@Override
	public List<Projeto> listarTodos() {
		String jpql = "select p from Projeto p";
		return em.createQuery(jpql, Projeto.class)
		.getResultList();
	}

}
