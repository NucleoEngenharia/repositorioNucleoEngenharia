package com.nucleo.dao;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.medicao.DetalhamentoPeriodoMedicao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface DetalhamentoPeriodoMedicaoDAO extends DAO<DetalhamentoPeriodoMedicao, Integer> {
	DetalhamentoPeriodoMedicao buscarPorPeriodo(PeriodoMedicao periodo);
	
	void salvarDetalhamentoMedicao(DetalhamentoPeriodoMedicao detalhamentoPeriodoMedicao, int idUsuario);
}
