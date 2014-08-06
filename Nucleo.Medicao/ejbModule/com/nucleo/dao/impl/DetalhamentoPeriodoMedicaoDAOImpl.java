package com.nucleo.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.nucleo.dao.DetalhamentoPeriodoMedicaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.medicao.DetalhamentoPeriodoMedicao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class DetalhamentoPeriodoMedicaoDAOImpl
extends DAOImpl<DetalhamentoPeriodoMedicao, Integer>
implements DetalhamentoPeriodoMedicaoDAO {
	@Override
	public DetalhamentoPeriodoMedicao buscarPorPeriodo(PeriodoMedicao periodo){
		String jpql="select d from DetalhamentoPeriodoMedicao d"
				+ " where d.excluido=:excluido and d.periodoMedicao = :periodo";
		DetalhamentoPeriodoMedicao d = new DetalhamentoPeriodoMedicao();
		try{
			d =em.createQuery(jpql, DetalhamentoPeriodoMedicao.class)
			 .setParameter("periodo", periodo)
			 .setParameter("excluido", false)
			 .getSingleResult();
	 return d;
		}catch(NoResultException no){
			d.setId(0);
			d.setPeriodoMedicao(new PeriodoMedicao());
			return d;
		}
	}
	
	@Override
	public void salvarDetalhamentoMedicao(DetalhamentoPeriodoMedicao detalhamentoPeriodoMedicao, int idUsuario){
		DetalhamentoPeriodoMedicao d = em.merge(detalhamentoPeriodoMedicao);
		super.inserir(d, idUsuario);
	}
}
