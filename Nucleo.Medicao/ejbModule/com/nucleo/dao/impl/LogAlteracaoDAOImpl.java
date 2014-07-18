package com.nucleo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.nucleo.dao.LogAlteracaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.log.LogAlteracao;

@Stateless
public class LogAlteracaoDAOImpl extends DAOImpl<LogAlteracao, Integer> implements
		LogAlteracaoDAO {
	@Override
	public void inserir(LogAlteracao logAlteracao){
		LogAlteracao ultimoLog = buscaUltimoLog();
		logAlteracao.setId(ultimoLog.getId()+1);
		em.persist(logAlteracao);
		
	}
	private LogAlteracao buscaUltimoLog(){
		List<LogAlteracao>l = new ArrayList<>();
		String jpql="select l from LogAlteracao l"
				+ " order by l.id desc";
		l = em.createQuery(jpql, LogAlteracao.class)
				.getResultList();
		return l.get(0);
	}

}
