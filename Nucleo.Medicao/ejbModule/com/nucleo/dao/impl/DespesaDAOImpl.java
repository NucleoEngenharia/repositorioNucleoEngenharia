package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.DespesaDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Despesa;
import com.nucleo.entity.cadastro.Projeto;

@Stateless
public class DespesaDAOImpl extends DAOImpl<Despesa, Integer> implements DespesaDAO {

	@Override
	public List<Despesa> buscarTodosPorProjeto(Projeto projeto){
		System.out.println("Começou aqui");
		TypedQuery<Despesa> query = em.createQuery(
				"Select d From Despesa d"
				+ " left join fetch d.projeto as p"
				+ " join fetch p.responsavelAdm"
				+ " join fetch p.impostos"
				+ " Where p.id = :projeto"
				+ " and d.excluido = :excluido", Despesa.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

}
