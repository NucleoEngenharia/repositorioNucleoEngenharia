package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.MedicaoInfraMateriaisDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.entity.medicao.MedicaoInfraMateriais;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class MedicaoInfraMateriaisDAOImpl
		extends
			DAOImpl<MedicaoInfraMateriais, Integer>
		implements
			MedicaoInfraMateriaisDAO {

	@Override
	public List<MedicaoInfraMateriais> buscarTodosPorInfraMateriais(
			InfraMateriais infra, PeriodoMedicao periodo) {

		String strQuery = "Select m From MedicaoInfraMateriais m "
				+ "Where m.itemInfraMateriais.infraMateriais.id = :infra and "
				+ "m.excluido = :excluido and "
				+ "m.periodoMedicao.id = :periodo ";

		TypedQuery<MedicaoInfraMateriais> query = em.createQuery(strQuery,
				MedicaoInfraMateriais.class);
		query.setParameter("excluido", false);
		query.setParameter("infra", infra.getId());
		query.setParameter("periodo", periodo.getId());

		return query.getResultList();
	}

	@Override
	public List<MedicaoInfraMateriais> buscarTodosPorItemInfraMateriais(
			ItemInfraMateriais item) {
		
		String strQuery = "Select m From MedicaoInfraMateriais m " +
				"Where m.excluido = :excluido and " +
				"m.itemInfraMateriais.id = :item ";
		TypedQuery<MedicaoInfraMateriais> query = em.createQuery(strQuery, MedicaoInfraMateriais.class);
		query.setParameter("excluido", false);
		query.setParameter("item", item.getId());
		
		return query.getResultList();
	}
}
