package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nucleo.dao.ItemInfraMateriaisDAO;
import com.nucleo.dao.MedicaoInfraMateriaisDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.entity.medicao.MedicaoInfraMateriais;

@Stateless
public class ItemInfraMateriaisDAOImpl extends DAOImpl<ItemInfraMateriais, Integer> implements ItemInfraMateriaisDAO{

	@EJB
	private MedicaoInfraMateriaisDAO medicaoInfraDAO;
	
	@Override
	public List<ItemInfraMateriais> buscarTodosPorInfraMateriais(
			InfraMateriais infra) {
		TypedQuery<ItemInfraMateriais> query = em.createQuery(
						"Select i From ItemInfraMateriais i " +
						"Where i.infraMateriais.id = :infra " +
						"and i.excluido = :excluido",
						ItemInfraMateriais.class);
		query.setParameter("infra", infra.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public BigDecimal getSaldoItem(ItemInfraMateriais item) {
		BigDecimal valorMedido = new BigDecimal(0);
		for(MedicaoInfraMateriais medicao : medicaoInfraDAO.buscarTodosPorItemInfraMateriais(item)){
			if(medicao.getValorMedido() != null)
				valorMedido = valorMedido.add(medicao.getValorMedido());
		}
		return item.getValor().subtract(valorMedido);
	}

	@Override
	public boolean temMedicoes(ItemInfraMateriais item) {
		String strQuery = "select Count(m) from MedicaoInfraMateriais m " +
				"Where m.itemInfraMateriais.id = :item and m.excluido = :excluido ";
		Query query = em.createQuery(strQuery);
		query.setParameter("item", item.getId());
		query.setParameter("excluido", false);
		return ((Long) query.getSingleResult() > 0) ? true : false;
	}
}
