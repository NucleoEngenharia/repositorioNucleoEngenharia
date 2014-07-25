package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.FuncionarioContrato;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface MobilizacaoDAO extends DAO<Mobilizacao, Integer>{

	List<Mobilizacao> buscarTodosPorProjeto(Projeto projeto);
	
	boolean funcionarioMobilizado(FuncionarioContrato funcionarioContrato);
	
	List<Mobilizacao> buscarTodosPorPeriodo(PeriodoMedicao periodo);
	
	boolean existeMedicao(Mobilizacao mobilizacao);
	
	List<Mobilizacao> buscarTodosPorServico(Servico servico);

	List<Mobilizacao> buscarTodosPorPeriodoServico(PeriodoMedicao periodo, Servico servico);

}
