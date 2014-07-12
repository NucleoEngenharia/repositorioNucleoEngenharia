package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.InfraMateriaisDAO;
import com.nucleo.dao.ItemInfraMateriaisDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.entity.cadastro.Projeto;

@Stateless
public class InfraMateriaisDAOImpl extends DAOImpl<InfraMateriais, Integer>
		implements
			InfraMateriaisDAO {

	@EJB
	private ItemInfraMateriaisDAO itemDAO;
	
	@Override
	public List<InfraMateriais> buscarTodosPorProjeto(Projeto projeto) {
		TypedQuery<InfraMateriais> query = em
				.createQuery(
						"Select i From InfraMateriais i Where i.projeto.id = :projeto and i.excluido = :excluido",
						InfraMateriais.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public BigDecimal getValorDisponivel(InfraMateriais infra) {
		BigDecimal valorTotalInfra = new BigDecimal(0);
		valorTotalInfra = valorTotalInfra.add(infra.getValor());
		BigDecimal valorUsado = new BigDecimal(0);

		List<ItemInfraMateriais> itens = itemDAO.buscarTodosPorInfraMateriais(infra);
		for (ItemInfraMateriais item : itens) {
			valorUsado = valorUsado.add(item.getValor());
		}

		return valorTotalInfra.subtract(valorUsado);
	}

	@Override
	public List<ItemInfraMateriais> obterItens(InfraMateriais infra) {
		String strQuery = "select i from ItemInfraMateriais i " +
				"where i.infraMateriais.id = :infra and i.excluido = :excluido";
		TypedQuery<ItemInfraMateriais> query = em.createQuery(strQuery, ItemInfraMateriais.class);
		query.setParameter("infra", infra.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

}
