package com.nucleo.contratos.DAO;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.contratos.entity.Projeto;

@Remote
public interface ProjetoDAO {
	List<Projeto>listarTodos();
}
