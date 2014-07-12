package com.nucleo.dao.generic;

import java.util.List;

public interface DAO<T,K> {
	
	void inserir(T entity, int usuario);
	
	void deletar(T entity, int usuario);
	
	void deletarPorId(K id, int usuario);
	
	T buscarPorID(K id);
	
	void alterar(T entity, int usuario);
	
	List<T> buscarTodos();
	
}