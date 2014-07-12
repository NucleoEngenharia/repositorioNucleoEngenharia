package com.nucleo.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.nucleo.dao.MedicaoProdutoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.medicao.MedicaoProduto;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class MedicaoProdutoDAOImpl extends DAOImpl<MedicaoProduto, Integer> implements MedicaoProdutoDAO{

	@Override
	public MedicaoProduto buscarMedicaoPorProdutoPeriodo(PeriodoMedicao periodo, Produto produto){
		try {
			String strQuery = "Select m From MedicaoProduto m "
					+ "Where m.produto.id = :produto and "
					+ "m.excluido = :excluido and "
					+ "m.periodoMedicao.id = :periodo ";

			TypedQuery<MedicaoProduto> query = em.createQuery(strQuery,
					MedicaoProduto.class);
			query.setParameter("excluido", false);
			query.setParameter("produto", produto.getId());
			query.setParameter("periodo", periodo.getId());

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
