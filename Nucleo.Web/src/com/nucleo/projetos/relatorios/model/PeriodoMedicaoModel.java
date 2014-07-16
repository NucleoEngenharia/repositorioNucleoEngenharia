package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;

import com.nucleo.entity.medicao.PeriodoMedicao;

public class PeriodoMedicaoModel {
	
	public PeriodoMedicaoModel() {
	}
	private PeriodoMedicao periodoMedicao;
	private BigDecimal medicaoI0 = BigDecimal.ZERO;
	private BigDecimal medicaoComReajuste = BigDecimal.ZERO;
	private BigDecimal medicao = BigDecimal.ZERO;
	private BigDecimal totalSalarios= BigDecimal.ZERO;

	public BigDecimal getMedicaoI0() {
		return medicaoI0;
	}
	public void setMedicaoI0(BigDecimal medicaoI0) {
		this.medicaoI0 = medicaoI0;
	}
	
	public BigDecimal getMedicaoComReajuste() {
		return medicaoComReajuste;
	}
	public void setMedicaoComReajuste(BigDecimal medicaoComReajuste) {
		this.medicaoComReajuste = medicaoComReajuste;
	}
	public BigDecimal getMedicao() {
		return medicao;
	}
	public void setMedicao(BigDecimal medicao) {
		this.medicao = medicao;
	}
	public PeriodoMedicao getPeriodoMedicao() {
		return periodoMedicao;
	}
	public void setPeriodoMedicao(PeriodoMedicao periodoMedicao) {
		this.periodoMedicao = periodoMedicao;
	}
	public BigDecimal getTotalSalarios() {
		return totalSalarios;
	}
	public void setTotalSalarios(BigDecimal totalSalarios) {
		this.totalSalarios = totalSalarios;
	}
}
