package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.AlteracaoPeriodoMedicaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.medicao.AlteracaoPeriodoMedicao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class AlteracaoPeriodoMedicaoDAOImpl extends DAOImpl<AlteracaoPeriodoMedicao, Integer> implements
		AlteracaoPeriodoMedicaoDAO {

	@Override
	public List<AlteracaoPeriodoMedicao> buscarPorPeriodo(PeriodoMedicao periodoMedicao) {
		String queryStr = "Select distinct a From AlteracaoPeriodoMedicao a left join fetch a.periodoMedicao as pm left join fetch pm.projeto as p join fetch p.responsavelAdm where pm.id=:periodoId AND a.excluido = :excluido";
		TypedQuery<AlteracaoPeriodoMedicao>query = em.createQuery(queryStr, AlteracaoPeriodoMedicao.class);
		query.setParameter("excluido", false);
		query.setParameter("periodoId", periodoMedicao.getId());
		return query.getResultList();
	}

}
