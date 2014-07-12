package com.nucleo.dao;

import java.util.List;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.LocalExecucao;
import com.nucleo.entity.cadastro.Projeto;

public interface LocalExecucaoDAO extends DAO<LocalExecucao, Integer>{

	List<LocalExecucao> buscarTodosPorProjeto(Projeto projeto);

}
