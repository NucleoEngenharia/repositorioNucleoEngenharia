package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Imposto;
import com.nucleo.entity.cadastro.Projeto;

@Remote
public interface ImpostoDAO extends DAO<Imposto, Integer>{

	List<Imposto> buscarTodosPorProjeto(Projeto projeto);

	void sincronizarImpostoSAP(List<Imposto> impostos, int usuario);

}
