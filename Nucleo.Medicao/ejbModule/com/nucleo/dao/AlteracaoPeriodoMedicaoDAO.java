package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.medicao.AlteracaoPeriodoMedicao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface AlteracaoPeriodoMedicaoDAO extends DAO<AlteracaoPeriodoMedicao, Integer> {
	List<AlteracaoPeriodoMedicao> buscarPorPeriodo(PeriodoMedicao periodoMedicao);
}
