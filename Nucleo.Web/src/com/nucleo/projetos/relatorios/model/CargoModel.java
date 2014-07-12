package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CargoModel {

	private String descricao;
	private double un;
	private int quant;
	private BigDecimal valorUnitario;
	private BigDecimal valorParcial;
	private BigDecimal valorMedicoesAprovadas;
	private BigDecimal valorMedicaoPendente;
	private BigDecimal valorAcumulado;
	private BigDecimal saldo;
	private double porcentExecutado;

	public String getDescricao() {
		return descricao;
	}
	public double getUn() {
		return un;
	}
	public int getQuant() {
		return quant;
	}
	public BigDecimal getValorUnitario() {
		if(valorUnitario == null) return BigDecimal.ZERO;
		return valorUnitario;
	}
	public BigDecimal getValorParcial() {
		if(valorParcial == null) return BigDecimal.ZERO;
		return valorParcial;
	}
	public BigDecimal getValorAcumulado() {
		if (valorMedicaoPendente == null || valorMedicoesAprovadas == null) {
			return BigDecimal.ZERO;
		}
		valorAcumulado = valorMedicaoPendente.add(valorMedicoesAprovadas);
		return valorAcumulado;
	}
	public BigDecimal getSaldo() {
		if (valorParcial == null && valorMedicoesAprovadas == null) {
			return BigDecimal.ZERO;
		}
		if (valorMedicoesAprovadas == null) {
			valorMedicoesAprovadas = BigDecimal.ZERO;
		}
		saldo = valorParcial.subtract(valorMedicoesAprovadas, MathContext.DECIMAL32);
		return saldo;
	}
	public double getPorcentExecutado() {
		if (getValorAcumulado().equals(BigDecimal.ZERO) || valorParcial == null) {
			return 0;
		}
		porcentExecutado = getValorAcumulado().divide(valorParcial, 2, RoundingMode.CEILING)
				.doubleValue();
		return porcentExecutado;
	}
	public BigDecimal getValorMedicoesAprovadas() {
		if(valorMedicoesAprovadas == null) return BigDecimal.ZERO;
		return valorMedicoesAprovadas;
	}
	public BigDecimal getValorMedicaoPendente() {
		if (valorMedicaoPendente == null)
			valorMedicaoPendente = BigDecimal.ZERO;
		return valorMedicaoPendente;
	}

	
	public void setValorMedicoesAprovadas(BigDecimal valorMedicoesAprovadas) {
		this.valorMedicoesAprovadas = valorMedicoesAprovadas;
	}
	public void setValorMedicaoPendente(BigDecimal valorMedicaoPendente) {
		this.valorMedicaoPendente = valorMedicaoPendente;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setUn(double un) {
		this.un = un;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

}
