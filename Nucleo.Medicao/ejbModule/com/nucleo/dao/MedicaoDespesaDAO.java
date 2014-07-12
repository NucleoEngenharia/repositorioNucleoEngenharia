package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface MedicaoDespesaDAO extends DAO<MedicaoDespesa, Integer>{

	List<MedicaoDespesa> buscarTodosPorProjeto(Projeto projeto);

	List<MedicaoDespesa> buscarTodosPorPeriodo(int idPeriodo);

	List<MedicaoDespesa> buscarTodosPorPeriodoEquipe(PeriodoMedicao periodo, Servico equipe);
	
	BigDecimal somaValorTotalDespesasProjeto(Projeto projeto);
	
	BigDecimal somaValorDespesasPeriodo(PeriodoMedicao medicao);
	
}
