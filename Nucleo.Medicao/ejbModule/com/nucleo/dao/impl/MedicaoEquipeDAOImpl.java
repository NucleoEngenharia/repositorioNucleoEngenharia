package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class MedicaoEquipeDAOImpl extends DAOImpl<MedicaoEquipe, Integer> implements MedicaoEquipeDAO{

	@EJB
	MobilizacaoDAO mobilizacaoDAO;
	
	@Override
	public List<MedicaoEquipe> buscarTodosPorServicoCargo(Cargo cargo, PeriodoMedicao periodo) {
		String strQuery = "Select distinct m From MedicaoEquipe m "
				+ "Where m.mobilizacao.cargo.id = :cargo and "
				+ "m.excluido = :excluido and "
				+ "m.periodoMedicao.id = :periodo ";
		TypedQuery<MedicaoEquipe> query = em.createQuery(strQuery,
				MedicaoEquipe.class);
		query.setParameter("excluido", false);
		query.setParameter("servico", cargo.getId());
		query.setParameter("periodo", periodo.getId());
		return query.getResultList();
	}
	
	@Override
	public List<MedicaoEquipe> buscarTodosPorServicoPeriodo(Servico equipe,
			PeriodoMedicao periodo) {
		
		String strQuery = "Select m From MedicaoEquipe m "
				+ "Where m.mobilizacao.cargo.servico.id = :servico and "
				+ "m.excluido = :excluido and "
				+ "m.periodoMedicao.id = :periodo ";

		TypedQuery<MedicaoEquipe> query = em.createQuery(strQuery,
				MedicaoEquipe.class);
		query.setParameter("excluido", false);
		query.setParameter("servico", equipe.getId());
		query.setParameter("periodo", periodo.getId());

		return query.getResultList();
	}

	@Override
	public MedicaoEquipe buscarMedicaoPorMobilizacaoPeriodo(
			PeriodoMedicao periodo, Mobilizacao mobilizacao) {
		try {
			String strQuery = "Select m From MedicaoEquipe m "
					+ "Where m.mobilizacao.id = :mobilizacao and "
					+ "m.excluido = :excluido and "
					+ "m.periodoMedicao.id = :periodo ";

			TypedQuery<MedicaoEquipe> query = em.createQuery(strQuery,
					MedicaoEquipe.class);
			query.setParameter("excluido", false);
			query.setParameter("mobilizacao", mobilizacao.getId());
			query.setParameter("periodo", periodo.getId());
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	@Override
	public List<MedicaoEquipe>listarPorPeriodo(PeriodoMedicao periodoMedicao){
		List<MedicaoEquipe> medicoes = new ArrayList<MedicaoEquipe>();
		String jpql = "select m from MedicaoEquipe m "
				+ " left join fetch m.periodoMedicao as pm"
				+ " left join fetch m.mobilizacao as mob"
				+ " left join fetch mob.funcionario"
				+ " left join fetch mob.cargo as c"
				+ " left join fetch c.servico as s"
				+ " left join fetch s.projeto as p"
				+ " join fetch p.responsavelAdm"
				+ " where m.excluido =:excluido"
				+ " and pm.id=:periodoMedicao_id";
		        medicoes = em.createQuery(jpql, MedicaoEquipe.class)
		        .setParameter("excluido", false)
		        .setParameter("periodoMedicao_id", periodoMedicao.getId())
		        .getResultList();
		return medicoes;
	}
	
	@Override
	public BigDecimal getSomaDiasTrabalhados(PeriodoMedicao periodo) {
		String strQuery = "Select SUM(m.quantidadeMedido) From MedicaoEquipe m "
				+ "Where m.excluido = :excluido and "
				+ "m.periodoMedicao.id = :periodo ";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery,
				BigDecimal.class);
		query.setParameter("excluido", false);
		query.setParameter("periodo", periodo.getId());
		
		try {
			return (query.getSingleResult() == null) ? BigDecimal.ZERO : query.getSingleResult();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public BigDecimal getSomaDiasTrabalhados(PeriodoMedicao periodo, Cargo cargo) {
		String strQuery = "Select SUM(m.quantidadeMedido) From MedicaoEquipe m "
				+ "Where m.excluido = :excluido and "
				+ "m.periodoMedicao.id = :periodo and "
				+ "m.mobilizacao.cargo.id = :cargo ";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery,
				BigDecimal.class);
		query.setParameter("excluido", false);
		query.setParameter("periodo", periodo.getId());
		query.setParameter("cargo", cargo.getId());
		
		try {
			return (query.getSingleResult() == null) ? BigDecimal.ZERO : query.getSingleResult();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}


}