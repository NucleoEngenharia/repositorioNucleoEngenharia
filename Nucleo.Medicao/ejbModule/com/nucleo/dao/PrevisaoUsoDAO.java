package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Produto;

@Remote
public interface PrevisaoUsoDAO extends DAO<PrevisaoUso, Integer>{

	List<PrevisaoUso> buscarTodosPorCargo(Cargo cargo);

	List<PrevisaoUso> buscarTodosPorProduto(Produto produto);

}
