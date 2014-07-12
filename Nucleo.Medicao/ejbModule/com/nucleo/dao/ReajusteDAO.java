package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Reajuste;

@Remote
public interface ReajusteDAO extends DAO<Reajuste, Integer> {

	List<Reajuste> buscarTodosPorProjeto(Projeto projeto);
	
	Reajuste buscarUltimoValido(Projeto projeto);
	
}
