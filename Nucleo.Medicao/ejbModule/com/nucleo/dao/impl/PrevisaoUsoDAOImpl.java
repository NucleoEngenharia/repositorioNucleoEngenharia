package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.PrevisaoUsoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Produto;

@Stateless
public class PrevisaoUsoDAOImpl extends DAOImpl<PrevisaoUso, Integer> implements PrevisaoUsoDAO {

	@Override
	public List<PrevisaoUso> buscarTodosPorCargo(Cargo cargo){
		String strQuery = "select p From PrevisaoUso p " +
				"Where p.excluido = :excluido and p.cargo.id = :cargo";
		TypedQuery<PrevisaoUso> query = em.createQuery(strQuery, PrevisaoUso.class);
		query.setParameter("excluido", false);
		query.setParameter("cargo", cargo.getId());
		return query.getResultList();
	}

	@Override
	public List<PrevisaoUso> buscarTodosPorProduto(Produto produto){
		String strQuery = "select p From PrevisaoUso p " +
				"Where p.excluido = :excluido and p.produto.id = :produto";
		TypedQuery<PrevisaoUso> query = em.createQuery(strQuery, PrevisaoUso.class);
		query.setParameter("excluido", false);
		query.setParameter("produto", produto.getId());
		return query.getResultList();
	}

}
