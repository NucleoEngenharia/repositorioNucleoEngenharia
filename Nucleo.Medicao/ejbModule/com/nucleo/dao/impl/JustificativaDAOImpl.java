package com.nucleo.dao.impl;


import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.nucleo.dao.JustificativaDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.medicao.Justificativa;
import com.nucleo.entity.medicao.Mobilizacao;
@Stateless
public class JustificativaDAOImpl extends DAOImpl<Justificativa, Integer> implements JustificativaDAO {
       private Justificativa justificativa;
	public Justificativa getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(Justificativa justificativa) {
		this.justificativa = justificativa;
	}
	@Override
	public void inserir(Justificativa entity, int usuario) {
		super.inserir(entity, usuario);
	}
	@Override
	public Justificativa buscarPorMobilizacao(Mobilizacao mobilizacao) {
		Justificativa justificativa = new Justificativa();
		try{
		TypedQuery<Justificativa>query = em.createQuery("select j from Justificativa j where j.excluido = :excluido " +
				"and j.mobilizacao.id = :mobilizacaoId",Justificativa.class);
		query.setParameter("excluido", false);
		query.setParameter("mobilizacaoId", mobilizacao.getId());
		if(query.getSingleResult().equals(null)){
			justificativa.setId(0);
		}else{
		 justificativa = query.getSingleResult();
		}
		}catch(NoResultException e){
			System.out.println("Nenhuma justificativa encontrada");
		}
		return justificativa;
		
	}
	@Override
	public BigDecimal somaJustificativas(Justificativa justificativa) {
		BigDecimal justificativasSomadas = new BigDecimal(0);
		try{
		justificativasSomadas = BigDecimal.valueOf(
				justificativa.getDiasAtestado().doubleValue()+
				justificativa.getDiasFerias().doubleValue()+
				justificativa.getDiasInjustificado().doubleValue()+
				justificativa.getDiasTrabalhados().doubleValue()+
				justificativa.getDiasOutros().doubleValue());
		return justificativasSomadas;
		}catch(NullPointerException e){
			return BigDecimal.ZERO;
		}
	}
	
}
