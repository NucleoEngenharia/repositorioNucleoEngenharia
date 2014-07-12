package com.nucleo.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Imposto;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Reajuste;
import com.nucleo.entity.cadastro.Enum.MotivoBloqueioProjeto;

@Remote
public interface ProjetoDAO extends DAO<Projeto, Integer>{
	
	boolean cadastraPeriodoMedicao(Projeto projeto, Calendar dataInicial,int numsequencial, int usuario);
	
	int getPrazoTotalDias(Projeto projeto);
	
	List<Projeto>listarTodosComReajuste();

	BigDecimal getValorAtual(Projeto projeto);

	BigDecimal getValorTotalAditivos(Projeto projeto);

	Calendar getDataFimAtual(Projeto projeto);

	String getTotalPrazoAditivos(Projeto projeto);

	BigDecimal getSaldo(Projeto projeto);

	BigDecimal getValorDisponivel(Projeto projeto);

	BigDecimal getLimiteDisponivelDespesa(Projeto projeto);	
	
	// funções Impostos
	void adicionarImposto(int idProjeto, int idImposto, int usuario) throws Exception;
	void removerImposto(int idProjeto, int idImposto, int usuario) throws Exception;
	List<Imposto> obterTodosImpostos(Projeto projeto);
	
	BigDecimal getTotalMedido(Projeto projeto);
	
	Reajuste buscarUltimoReajusteValido(Projeto projeto);

	
	// reajustes
	boolean isBloqueado(Projeto projeto);
	MotivoBloqueioProjeto getMotivoBloqueio(Projeto projeto);

	List<Projeto> listarTodos();
	
}
