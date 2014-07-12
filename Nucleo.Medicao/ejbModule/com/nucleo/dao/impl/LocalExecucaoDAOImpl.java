package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.LocalExecucaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.LocalExecucao;
import com.nucleo.entity.cadastro.Projeto;

@Stateless
public class LocalExecucaoDAOImpl extends DAOImpl<LocalExecucao, Integer> implements
		LocalExecucaoDAO {

	@Override
	public List<LocalExecucao> buscarTodosPorProjeto(Projeto projeto){
		TypedQuery<LocalExecucao> query = em
				.createQuery(
						"Select l from LocalExecucao l where l.projeto.id = :projeto and l.excluido = :excluido",
						LocalExecucao.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}
}