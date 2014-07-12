package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Despesa;
import com.nucleo.entity.cadastro.Projeto;

@Remote
public interface DespesaDAO extends DAO<Despesa, Integer> {

	List<Despesa> buscarTodosPorProjeto(Projeto projeto);

}
