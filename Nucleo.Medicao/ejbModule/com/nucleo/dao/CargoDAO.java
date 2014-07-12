package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface CargoDAO extends DAO<Cargo, Integer> {

	List<PrevisaoUso> gerarPrevisoesUso(Cargo cargo);
	
	 List<Cargo>buscarTodosPorServicoEfuncao(Servico servico, String funcaoMD);

	List<PrevisaoUso> obterPrevisoesUso(Cargo cargo);

	List<Cargo> buscarTodosPorServico(Servico servico);

	List<Mobilizacao> buscarMobilizacoes(Cargo cargo);

	boolean temMobilizacao(Cargo cargo);

	void removerHistograma(Cargo cargo);

	BigDecimal getTotalMedido(Cargo cargo);

	BigDecimal getTotalMedido(Cargo cargo, PeriodoMedicao periodo);

	BigDecimal getTotalMedido(List<MedicaoEquipe> listaMedicoes, Cargo cargo);

	BigDecimal getTotalMedidoPeriodoCalculoComplexidade(PeriodoMedicao periodo, Cargo cargo);

	BigDecimal calcularTotalMedidoPeriodoCalculoComplexidade(BigDecimal medidoCargo,
			BigDecimal diasTrabalhados, BigDecimal baseCalculo);

	
	
}
