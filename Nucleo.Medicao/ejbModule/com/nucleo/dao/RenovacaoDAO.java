package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Renovacao;

@Remote
public interface RenovacaoDAO extends DAO<Renovacao, Integer>{

	List<Renovacao> buscarTodosPorProjeto(Projeto projetoSelecionado);

}
