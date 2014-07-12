package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.AlteracaoPeriodoMedicao;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;
import com.nucleo.model.VO.DatasPeriodoMedicaoVO;

@Remote
public interface PeriodoMedicaoDAO extends DAO<PeriodoMedicao, Integer>{
	
	PeriodoMedicao buscarPeriodoPorDataEProjeto(Calendar dataDe, Projeto projeto);
	 
	Calendar buscarDataFinalMedicao(Projeto projeto);
	
	List<PeriodoMedicao> buscarTodosPorStatus(List<StatusPeriodoEnum> status);
	
	List<PeriodoMedicao>buscarPeriodosEmAberto();
	
	List<DatasPeriodoMedicaoVO>buscarDatasDe();
	
	/*Map<Integer, BigDecimal> valorMedidoCargos();*/
	
	List<AlteracaoPeriodoMedicao> buscarAlteracoesPeriodo(PeriodoMedicao periodo);
	
	String getVersao(PeriodoMedicao periodo);

	List<PeriodoMedicao> buscarTodosPorProjeto(Projeto projeto);

	List<PeriodoMedicao> buscarTodosPorProjetoStatus(Projeto projeto, List<StatusPeriodoEnum> status);
	
	Long getQuantidadeProfissionais(PeriodoMedicao periodo, Servico equipe);

	BigDecimal getSomaValorVendaEquipePeriodo(PeriodoMedicao periodo, Servico equipe);
	
	BigDecimal getSomaValorVendaEquipePeriodoCargo(PeriodoMedicao periodo, Servico equipe,String funcao);

	List<PeriodoMedicao>buscarTodosPorProjetoComBaseDeCalculoValida(Projeto projeto);
}
