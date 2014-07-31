package com.nucleo.contratos.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.contratos.entity.Projeto;

@Remote
public interface ProjetoDAO {
	List<Projeto>listarTodos();
}
