package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;

import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Reajuste;

public class ContratosModel {
public ContratosModel() {
	periodoComDataEscolhida = new PeriodoMedicaoModel();
	periodoComMesAnterior = new PeriodoMedicaoModel();
	periodoComM1 = new PeriodoMedicaoModel();
}
	private Projeto projeto;
	
	private BigDecimal medidoI0;
	
	private BigDecimal saldoI0;
	
	private BigDecimal saldoReajustado;
	
	private Reajuste reajuste;
	
	private PeriodoMedicaoModel periodoComDataEscolhida;
	
	private PeriodoMedicaoModel periodoComMesAnterior;
	
	private PeriodoMedicaoModel periodoComM1;
	
	public PeriodoMedicaoModel getPeriodoComDataEscolhida() {
		return periodoComDataEscolhida;
	}

	public void setPeriodoComDataEscolhida(PeriodoMedicaoModel periodoComDataEscolhida) {
		this.periodoComDataEscolhida = periodoComDataEscolhida;
	}

	public PeriodoMedicaoModel getPeriodoComMesAnterior() {
		return periodoComMesAnterior;
	}

	public PeriodoMedicaoModel getPeriodoComM1() {
		return periodoComM1;
	}

	public void setPeriodoComMesAnterior(PeriodoMedicaoModel periodoComMesAnterior) {
		this.periodoComMesAnterior = periodoComMesAnterior;
	}

	public void setPeriodoComM1(PeriodoMedicaoModel periodoComM1) {
		this.periodoComM1 = periodoComM1;
	}

	public Reajuste getReajuste() {
		return reajuste;
	}

	public void setReajuste(Reajuste reajuste) {
		this.reajuste = reajuste;
	}

	public BigDecimal getSaldoReajustado() {
		return saldoReajustado;
	}

	public void setSaldoReajustado(BigDecimal saldoReajustado) {
		this.saldoReajustado = saldoReajustado;
	}

	public BigDecimal getSaldoI0() {
		return saldoI0;
	}

	public void setSaldoI0(BigDecimal saldoI0) {
		this.saldoI0 = saldoI0;
	}

	public BigDecimal getMedidoI0() {
		return medidoI0;
	}

	public void setMedidoI0(BigDecimal medidoI0) {
		this.medidoI0 = medidoI0;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}
