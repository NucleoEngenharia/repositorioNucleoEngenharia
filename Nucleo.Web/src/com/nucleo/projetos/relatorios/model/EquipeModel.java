package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alysson.santos
 *
 */
public class EquipeModel {

	public EquipeModel() {
		cargos = new ArrayList<CargoModel>();
		mobilizacoes = new ArrayList<MobilizacaoModel>();
		despesas = new ArrayList<DespesaModel>();
	}

	private List<CargoModel> cargos;
	private List<MobilizacaoModel> mobilizacoes;
	private List<DespesaModel> despesas;

	private BigDecimal valorUnitario;
	private BigDecimal valorParcial;
	private BigDecimal totalMedicoesAprovadas;
	private BigDecimal totalMedicoesPendentes;
	private BigDecimal valorAcumulado;
	private BigDecimal saldo;
	private String descricao;

	private BigDecimal totalSalarioBase;
	private BigDecimal totalValorVendaMobilizado;
	
	private int qtdDespesas;
	
	private BigDecimal somaSalariosBase;
	private BigDecimal somaValoresVenda;
	private int qtdMobilizacoes;
	
	
	public List<CargoModel> getCargos() {
		return cargos;
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
		if (getTotalMedicoesAprovadas().equals(BigDecimal.ZERO)
				|| getTotalMedicoesPendentes().equals(BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}
		valorAcumulado = getTotalMedicoesAprovadas().add(getTotalMedicoesPendentes());
		return valorAcumulado;
	}
	public BigDecimal getSaldo() {
		if (getTotalMedicoesAprovadas().equals(BigDecimal.ZERO) || valorParcial == null) {
			return BigDecimal.ZERO;
		}
		saldo = valorParcial.subtract(getTotalMedicoesAprovadas(), MathContext.DECIMAL32);
		return saldo;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getTotalMedicoesAprovadas() {
		if(totalMedicoesAprovadas != null){
			return totalMedicoesAprovadas;
		}

		if (cargos.size() == 0) {
			return BigDecimal.ZERO;
		}
		totalMedicoesAprovadas = BigDecimal.ZERO;
		for (CargoModel cargo : cargos) {
			totalMedicoesAprovadas = totalMedicoesAprovadas.add(cargo.getValorMedicoesAprovadas());
		}
		return totalMedicoesAprovadas;
	}
	public BigDecimal getTotalMedicoesPendentes() {
		if(totalMedicoesPendentes == null){
			totalMedicoesPendentes = BigDecimal.ZERO;
		}
		return totalMedicoesPendentes;
	}
	public List<MobilizacaoModel> getMobilizacoes() {
		return mobilizacoes;
	}
	public BigDecimal getTotalSalarioBase() {
		if(mobilizacoes.size() == 0){
			return BigDecimal.ZERO;
		}
		totalSalarioBase = BigDecimal.ZERO;
		for (MobilizacaoModel mobilizacao : mobilizacoes) {
			if(mobilizacao.getSalarioBase() == null) continue;
			totalSalarioBase = totalSalarioBase.add(mobilizacao.getSalarioBase());
		}
		return totalSalarioBase;
	}
	public BigDecimal getTotalValorVendaMobilizado() {
		if(mobilizacoes.size() == 0){
			return BigDecimal.ZERO;
		}
		totalValorVendaMobilizado = BigDecimal.ZERO;
		for (MobilizacaoModel mobilizacao : mobilizacoes) {
			if(mobilizacao.getValorVenda() == null) continue;
			totalValorVendaMobilizado = totalValorVendaMobilizado.add(mobilizacao.getValorVenda());
		}
		return totalValorVendaMobilizado;
	}
	public BigDecimal getSomaSalariosBase() {
		if(mobilizacoes.size() == 0){
			return BigDecimal.ZERO;
		}
		somaSalariosBase = BigDecimal.ZERO;
		for(MobilizacaoModel mobil : mobilizacoes){
			if(mobil.getSalarioBase() == null) continue; 
			somaSalariosBase = somaSalariosBase.add(mobil.getSalarioBase());
		}
		return somaSalariosBase;
	}
	public BigDecimal getSomaValoresVenda() {
		if(mobilizacoes.size() == 0){
			return BigDecimal.ZERO;
		}
		somaValoresVenda = BigDecimal.ZERO;
		for(MobilizacaoModel mobil : mobilizacoes){
			if(mobil.getValorVenda() == null) continue; 
			somaValoresVenda = somaValoresVenda.add(mobil.getValorVenda());
		}
		return somaValoresVenda;
	}
	public int getQtdMobilizacoes() {
		qtdMobilizacoes = mobilizacoes.size();
		return qtdMobilizacoes;
	}
	public List<DespesaModel> getDespesas() {
		return despesas;
	}
	public int getQtdDespesas() {
		qtdDespesas = despesas.size();
		return qtdDespesas;
	}
	public void setDespesas(List<DespesaModel> despesas) {
		this.despesas = despesas;
	}
	public void setTotalValorVendaMobilizado(BigDecimal totalValorVendaMobilizado) {
		this.totalValorVendaMobilizado = totalValorVendaMobilizado;
	}
	public void setTotalSalarioBase(BigDecimal totalSalarioBase) {
		this.totalSalarioBase = totalSalarioBase;
	}
	public void setMobilizacoes(List<MobilizacaoModel> mobilizacoes) {
		this.mobilizacoes = mobilizacoes;
	}
	public void setTotalMedicoesPendentes(BigDecimal totalMedicoesPendentes) {
		this.totalMedicoesPendentes = totalMedicoesPendentes;
	}
	public void setTotalMedicoesAprovadas(BigDecimal totalMedicoesAprovadas) {
		this.totalMedicoesAprovadas = totalMedicoesAprovadas;
	}
	public void setCargos(List<CargoModel> cargos) {
		this.cargos = cargos;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}
	public void setValorAcumulado(BigDecimal valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setSomaSalariosBase(BigDecimal somaSalariosBase) {
		this.somaSalariosBase = somaSalariosBase;
	}
	public void setSomaValoresVenda(BigDecimal somaValoresVenda) {
		this.somaValoresVenda = somaValoresVenda;
	}

	
}
