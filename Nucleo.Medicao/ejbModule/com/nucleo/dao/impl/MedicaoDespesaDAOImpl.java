package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.MedicaoDespesaDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class MedicaoDespesaDAOImpl extends DAOImpl<MedicaoDespesa, Integer> implements MedicaoDespesaDAO {

	@Override
	public List<MedicaoDespesa> buscarTodosPorProjeto(Projeto projeto){
		String strQuery = "select distinct m from MedicaoDespesa m"
				+ " left join fetch m.mobilizacao as mob"
				+ " join fetch mob.medicoes"
				+ " left join fetch m.projeto as p"
				+ " join fetch p.responsavelAdm" 
				+" Where m.excluido = :excluido"
				+ " and p.id = :projeto";
		TypedQuery<MedicaoDespesa> query = em.createQuery(strQuery, MedicaoDespesa.class);
		query.setParameter("excluido", false);
		query.setParameter("projeto", projeto.getId());
		return query.getResultList();
	}
	
	@Override
	public List<MedicaoDespesa> buscarTodosPorPeriodo(int idPeriodo) {
		String strQuery = "select m from MedicaoDespesa m " +
				"Where m.excluido = :excluido and m.periodo.id = :periodo";
		TypedQuery<MedicaoDespesa> query = em.createQuery(strQuery, MedicaoDespesa.class);
		query.setParameter("excluido", false);
		query.setParameter("periodo", idPeriodo);
		return query.getResultList();
	}

	@Override
	public List<MedicaoDespesa> buscarTodosPorPeriodoEquipe(PeriodoMedicao periodo, Servico equipe) {
		String strQuery = "select m from MedicaoDespesa m " +
				"Where m.excluido = :excluido and " +
				"m.periodo.id = :periodo and " +
				"m.mobilizacao.cargo.servico.id = :equipe";
		TypedQuery<MedicaoDespesa> query = em.createQuery(strQuery, MedicaoDespesa.class);
		query.setParameter("excluido", false);
		query.setParameter("periodo", periodo.getId());
		query.setParameter("equipe", equipe.getId());
		return query.getResultList();
	}

	@Override
	public BigDecimal somaValorTotalDespesasProjeto(Projeto projeto) {
		return em.createQuery("select sum(md.valor) from MedicaoDespesa md where projeto =:projeto and md.excluido =:excluido", BigDecimal.class)
				.setParameter("projeto", projeto)
				.setParameter("excluido", false)
				.getSingleResult();
	}

	@Override
	public BigDecimal somaValorDespesasPeriodo(PeriodoMedicao periodo) {
	return em.createQuery("select sum(m.valor) from MedicaoDespesa m where m.periodo =:periodo and m.excluido=:excluido", BigDecimal.class)
		.setParameter("periodo", periodo)
		.setParameter("excluido", false)
		.getSingleResult();
	}

}
