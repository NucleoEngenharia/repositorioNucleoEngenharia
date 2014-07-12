package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MobilizacaoModel {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private String nome;
	private String funcao;
	private BigDecimal salarioBase;
	private BigDecimal valorVenda;
	private Calendar dataMobilizacao;
	private String dataMobilizacaoString;
	private Calendar dataDesmobilizacao;
	private String dataDesmobilizacaoString;
	private BigDecimal diasTrabalhados;
	private BigDecimal baseMedida;
	private BigDecimal valorMedido;
	private BigDecimal baseCalculo;
	
	
	public String getNome() {
		return nome;
	}
	public String getFuncao() {
		return funcao;
	}
	public BigDecimal getSalarioBase() {
		return salarioBase;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public BigDecimal getDiasTrabalhados() {
		return diasTrabalhados;
	}
	public BigDecimal getBaseMedida() {
		if(baseCalculo == null || diasTrabalhados == null){
			return BigDecimal.ZERO;
		}
		baseMedida = diasTrabalhados.divide(baseCalculo, 2, RoundingMode.CEILING);
		return baseMedida;
	}
	public BigDecimal getValorMedido() {
		return valorMedido;
	}
	public Calendar getDataMobilizacao() {
		return dataMobilizacao;
	}
	public String getDataMobilizacaoString() {
		dataMobilizacaoString = dateFormat.format(dataMobilizacao.getTime());
		return dataMobilizacaoString;
	}
	public Calendar getDataDesmobilizacao() {
		return dataDesmobilizacao;
	}
	public String getDataDesmobilizacaoString() {
		dataDesmobilizacaoString = dateFormat.format(dataDesmobilizacao.getTime());
		return dataDesmobilizacaoString;
	}
	public BigDecimal getBaseCalculo() {
		return baseCalculo;
	}
	
	
	public void setBaseCalculo(BigDecimal baseCalculo) {
		this.baseCalculo = baseCalculo;
	}
	public void setDataMobilizacao(Calendar dataMobilizacao) {
		this.dataMobilizacao = dataMobilizacao;
	}
	public void setDataDesmobilizacao(Calendar dataDesmobilizacao) {
		this.dataDesmobilizacao = dataDesmobilizacao;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public void setSalarioBase(BigDecimal salarioBase) {
		this.salarioBase = salarioBase;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public void setDiasTrabalhados(BigDecimal diasTrabalhados) {
		this.diasTrabalhados = diasTrabalhados;
	}
	public void setValorMedido(BigDecimal valorMedido) {
		this.valorMedido = valorMedido;
	}
	
}
