package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.cadastro.Servico;

@Remote
public interface ProdutoDAO extends DAO<Produto, Integer>{
	
	List<PrevisaoUso> gerarPrevisoesUso(Produto produto);

	List<PrevisaoUso> obterPrevisoesUso(Produto produto);
	
	List<Produto> buscarTodosPorServico(Servico servico);

	List<Produto> buscarTodosGruposPorServico(Servico servico);
	
	boolean temMedicoes(Produto produto);
	
	void removerCronograma(Produto produto);
}
