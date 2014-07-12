package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class DespesaModel {

	private final BigDecimal TAXA_COFINS = BigDecimal.valueOf(0.076);
	private final BigDecimal TAXA_PIS = BigDecimal.valueOf(0.0165);
	private final BigDecimal TAXA_ISS = BigDecimal.valueOf(0.05);

	private String nomeFuncionario;
	private String funcao;
	private String periodoDespesa;
	private BigDecimal valor;
	private BigDecimal valorComImpostos;
	private BigDecimal valorAFaturar;

	private boolean temImpostos;
	private BigDecimal cofins;
	private BigDecimal pis;
	private BigDecimal iss;

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public String getFuncao() {
		return funcao;
	}
	public String getPeriodoDespesa() {
		return periodoDespesa;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public BigDecimal getValorComImpostos() {
		if(valor == null){
			return BigDecimal.ZERO;
		}
		valorComImpostos = valor.add(getCofins()).add(getPis()).add(getIss());
		return valorComImpostos;
	}
	public BigDecimal getValorAFaturar() {
		valorAFaturar = getValorComImpostos();
		return valorAFaturar;
	}
	
	public BigDecimal getCofins() {
		if(!temImpostos || valor == null){
			return BigDecimal.ZERO;
		}
		cofins = valor.multiply(TAXA_COFINS, MathContext.DECIMAL32);
		return cofins;
	}
	public BigDecimal getPis() {
		if(!temImpostos || valor == null){
			return BigDecimal.ZERO;
		}
		pis = valor.multiply(TAXA_PIS, MathContext.DECIMAL32);
		return pis;
	}
	public BigDecimal getIss() {
		if(!temImpostos || valor == null){
			return BigDecimal.ZERO;
		}
		iss = valor.multiply(TAXA_ISS, MathContext.DECIMAL32);
		return iss;
	}

	
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public void setPeriodoDespesa(String periodoDespesa) {
		this.periodoDespesa = periodoDespesa;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public void setTemImpostos(boolean temImpostos) {
		this.temImpostos = temImpostos;
	}

}
