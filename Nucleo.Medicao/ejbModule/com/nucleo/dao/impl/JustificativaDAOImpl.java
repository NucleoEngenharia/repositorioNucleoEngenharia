package com.nucleo.dao.impl;


import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.nucleo.dao.JustificativaDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.medicao.Justificativa;
import com.nucleo.entity.medicao.MedicaoEquipe;
@Stateless
public class JustificativaDAOImpl 
extends DAOImpl<Justificativa, Integer>
implements JustificativaDAO {
       private Justificativa justificativa;
	public Justificativa getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(Justificativa justificativa) {
		this.justificativa = justificativa;
	}
	@Override
	public Justificativa buscarPorMedicaoEquipe(MedicaoEquipe medicaoEquipe){
		Justificativa j = new Justificativa();
		String jpql="select j from Justificativa j"
				+ " left join fetch j.medicaoEquipe m"
				+ " where m.id=:medicaoEquipeId";
		try{
		j= em.createQuery(jpql, Justificativa.class)
				.setParameter("medicaoEquipeId", medicaoEquipe.getId())
				.getSingleResult();
		return j;
		}catch(NoResultException e){
			j.setId(0);
			return j;
		}
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
	@Override
	public void salvarJustificativa(Justificativa justificativa, int idUsuario){
		Justificativa newJustificativa = em.merge(justificativa);
		super.inserir(newJustificativa, idUsuario);
	}
	
}
