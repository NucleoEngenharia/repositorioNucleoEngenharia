package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.entity.cadastro.Projeto;

@Remote
public interface InfraMateriaisDAO extends DAO<InfraMateriais, Integer>{
	List<InfraMateriais> buscarTodosPorProjeto(Projeto projeto);
	
	BigDecimal getValorDisponivel(InfraMateriais infra);

	List<ItemInfraMateriais> obterItens(InfraMateriais infra);
}
