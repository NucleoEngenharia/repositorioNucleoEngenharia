package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.entity.medicao.MedicaoInfraMateriais;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface MedicaoInfraMateriaisDAO extends DAO<MedicaoInfraMateriais, Integer>{

	List<MedicaoInfraMateriais> buscarTodosPorInfraMateriais(InfraMateriais infra, PeriodoMedicao periodo);

	List<MedicaoInfraMateriais> buscarTodosPorItemInfraMateriais(ItemInfraMateriais item);
	
}
