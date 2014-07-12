package com.nucleo.model.calculos.client;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface CalculosMedicao {
	BigDecimal calculoSimples(List<MedicaoEquipe>medicoes);
	BigDecimal calculoPorEquipe(PeriodoMedicao periodo,List<MedicaoEquipe>medicoes);
	BigDecimal calculoPorComplexidade(PeriodoMedicao periodo,List<MedicaoEquipe>medicoes);
	BigDecimal formulaCalculoPorEquipe(BigDecimal diasTrabalhados,
				Long qtdProfissionais, BigDecimal somaValorVendaEquipePeriodo, BigDecimal baseCalculo);
}
