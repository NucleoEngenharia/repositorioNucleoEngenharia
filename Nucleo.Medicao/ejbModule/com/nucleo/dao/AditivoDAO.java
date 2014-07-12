package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Aditivo;
import com.nucleo.entity.cadastro.Projeto;

@Remote
public interface AditivoDAO extends DAO<Aditivo, Integer> {

	List<Aditivo> buscarTodosPorProjeto(Projeto projeto);

}
