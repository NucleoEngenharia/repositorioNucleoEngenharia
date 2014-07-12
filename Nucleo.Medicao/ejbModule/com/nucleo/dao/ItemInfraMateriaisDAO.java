package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;

@Remote
public interface ItemInfraMateriaisDAO extends DAO<ItemInfraMateriais, Integer>{

	List<ItemInfraMateriais> buscarTodosPorInfraMateriais(InfraMateriais infra);

	BigDecimal getSaldoItem(ItemInfraMateriais item);

	boolean temMedicoes(ItemInfraMateriais item);
	
}
