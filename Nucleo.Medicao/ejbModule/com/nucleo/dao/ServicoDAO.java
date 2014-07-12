package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface ServicoDAO extends DAO<Servico, Integer> {
   
	int buscarUltimoSeqPorProjeto(Projeto projeto);
	
	List<Servico> buscarTodosPorProjeto(Projeto projeto);

	List<Servico> buscarEquipesPorProjeto(Projeto projeto);

	BigDecimal getValorDisponivel(Servico servico);

	List<Servico> buscarProdutosPorProjeto(Projeto projeto);

	BigDecimal getTotalMedido(Servico servico);

	BigDecimal getTotalMedidoPeriodoCalculoEquipe(Servico servico, PeriodoMedicao periodo);

	BigDecimal getTotalMedidoPeriodoCalculoComplexidade(Servico servico, PeriodoMedicao periodo);

	BigDecimal calcularTotalMedidoEquipe(Servico equipe, PeriodoMedicao periodo, List<MedicaoEquipe> medicoes);
	
}
