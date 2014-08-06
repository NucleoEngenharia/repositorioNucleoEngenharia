package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.nucleo.dao.RelatoriosRMSGeradosDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.medicao.RelatoriosRMSGerados;

@Stateless
public class RelatoriosRMSGeradosDAOImpl extends DAOImpl<RelatoriosRMSGerados, Integer> implements RelatoriosRMSGeradosDAO {
	@Override
	public List<RelatoriosRMSGerados>listarTodos(){
		String jpql = "select rms from  RelatoriosRMSGerados rms"
				+ "  where rms.excluido=:excluido";
		return em.createQuery(jpql, RelatoriosRMSGerados.class)
				.setParameter("excluido", false)
				.getResultList();
	}
	@Override
	public RelatoriosRMSGerados buscarPorNome(RelatoriosRMSGerados relatorio){
		String jpql="select rms from RelatoriosRMSGerados rms"
				+ " where rms.excluido=:excluido and rms.nomeArquivo =:nome";
		RelatoriosRMSGerados rms = new RelatoriosRMSGerados();
		try{
		rms = em.createQuery(jpql, RelatoriosRMSGerados.class)
				.setParameter("excluido", false)
				.setParameter("nome", relatorio.getNomeArquivo())
				.getSingleResult();
		return rms;
		}catch(NoResultException e){
			rms.setId(0);
			return rms;
		}
	}
}
