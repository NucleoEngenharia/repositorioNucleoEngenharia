package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.nucleo.dao.ReajusteDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Reajuste;

@Stateless
public class ReajusteDAOImpl extends DAOImpl<Reajuste, Integer> implements ReajusteDAO {

	@Override
	public List<Reajuste> buscarTodosPorProjeto(Projeto projeto) {
		String strQuery = "select r from Reajuste r " +
			"Where r.projeto.id = :projeto and " +
			"r.excluido = :excluido " +
			"order by r.dataCriacao desc";
		TypedQuery<Reajuste> query = em.createQuery(strQuery, Reajuste.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	private Reajuste buscarReajuste(Projeto projeto){
		System.out.println("Reajuste buscarReajuste(Projeto projeto){...}");
		String strQuery = "select r from Reajuste r"
				+ " left join fetch r.projeto as p"
				+" Where p.id = :projetoId and" +
				" r.excluido = :excluido";
			return em.createQuery(strQuery, Reajuste.class)
			.setParameter("projetoId", projeto.getId())
			.setParameter("excluido", false)
			.getSingleResult();
	}
	@Override
	public Reajuste buscarUltimoValido(Projeto projeto) {
		try{
		String strQuery = "select distinct r from Reajuste r" + 
				" Where r.projeto.id = :projeto and" + 
				" r.excluido = :excluido" + 
				" order by r.dataCriacao desc";
		TypedQuery<Reajuste> query = em.createQuery(strQuery, Reajuste.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList().get(0);
		}catch(IndexOutOfBoundsException e){
			try{
			return buscarReajuste(projeto);
			}catch(NoResultException n){
				System.out.println("projeto"+projeto.getCN()+" não tem reajuste");
				return null;
			}
		}
	}
}