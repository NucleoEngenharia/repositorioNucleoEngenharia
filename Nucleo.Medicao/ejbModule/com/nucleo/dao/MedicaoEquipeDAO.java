package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface MedicaoEquipeDAO extends DAO<MedicaoEquipe, Integer>{

	List<MedicaoEquipe> buscarTodosPorServicoPeriodo(Servico equipe,
			PeriodoMedicao periodo);
	
	BigDecimal buscarValorVendaMedicoesPorPeriodo(PeriodoMedicao periodo);
	
	MedicaoEquipe buscarMedicao(MedicaoEquipe medicaoEquipe);
	
	BigDecimal buscarSalariosMedicoesPorPeriodo(PeriodoMedicao periodo);

	List<MedicaoEquipe> buscarTodosPorServicoCargo(Cargo cargo,
			PeriodoMedicao periodo);

	MedicaoEquipe buscarMedicaoPorMobilizacaoPeriodo(
			PeriodoMedicao periodo, Mobilizacao mobilizacao);

	BigDecimal getSomaDiasTrabalhados(PeriodoMedicao periodo);

	BigDecimal getSomaDiasTrabalhados(PeriodoMedicao periodo, Cargo cargo);
	
	 List<MedicaoEquipe>listarPorPeriodo(PeriodoMedicao periodoMedicao);
	
}
