package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ResumoModeloSistema {
	public ResumoModeloSistema() {
		totalMI0DtEscolhidaOleoGas = new BigDecimal(0);
		totalMRDtEscolhidaOleoGas= new BigDecimal(0);
		totalMDtEscolhidaOleoGas= new BigDecimal(0);
		totalMMesAntOleoGas= new BigDecimal(0);
		contratosModelOleoEGas = new ArrayList<ContratosModel>();
		contratosModelEnergia = new ArrayList<ContratosModel>();
		contratosModelInfraEstrutura = new ArrayList<ContratosModel>();
		contratosModelMineracao = new ArrayList<ContratosModel>();
		contratosModelEnergiaUrbanismoeEdificacoes = new ArrayList<ContratosModel>();
		totalSalDtEscolhidaOleoGas = new BigDecimal(0);
		totalMI0MesAntOleoGas = new BigDecimal(0);
		totalMRMesAnteriorOleoGas = new BigDecimal(0);
		totalSalMesAnteriorOleoGas = new BigDecimal(0);
		totalMComM1OleoGas = new BigDecimal(0);
		totalMi0ComM1OleoGas = new BigDecimal(0);
		totalMRComM1OleoGas = new BigDecimal(0);
		totalSalComM1OleoGas = new BigDecimal(0);
		
		
		totalMI0DtEscolhidaEnergia = new BigDecimal(0);
		totalMRDtEscolhidaEnergia = new BigDecimal(0);
		totalMDtEscolhidaEnergia = new BigDecimal(0);
		totalSalDtEscolhidaEnergia = new BigDecimal(0);
		
		totalMMesAntEnergia = new BigDecimal(0);
		totalMI0MesAntEnergia = new BigDecimal(0);
		totalMRMesAnteriorEnergia = new BigDecimal(0);
		totalSalMesAnteriorEnergia = new BigDecimal(0);
		
		totalMComM1Energia = new BigDecimal(0);
		totalMi0ComM1Energia = new BigDecimal(0);
		totalMRComM1Energia = new BigDecimal(0);
		totalSalComM1Energia = new BigDecimal(0);
	}

	private Calendar dataEscolhida;
	private Calendar mesAnterior;
	private Calendar mesAnteriorM1;

	private String labelDataAvaliacao;
	private String mesCompetencia;
	private int anoCompetencia;

	private List<ContratosModel> contratosModelOleoEGas;
	private List<ContratosModel> contratosModelEnergia;
	private List<ContratosModel> contratosModelInfraEstrutura;
	private List<ContratosModel> contratosModelMineracao;
	private List<ContratosModel> contratosModelEnergiaUrbanismoeEdificacoes;
	
	private BigDecimal totalValorI0PetroleoGas;
	private BigDecimal totalMedidoI0PetroleoGas;
	private BigDecimal totalSaldoI0PetroleoGas;
	private BigDecimal totalSaldoReajustadoPetroleoGas;
	
	private BigDecimal totalValorI0Energia;
	private BigDecimal totalMedidoI0Energia;
	private BigDecimal totalSaldoI0Energia;
	private BigDecimal totalSaldoReajustadoEnergia;
	
	private BigDecimal totalValorI0InfraEstrutura;
	private BigDecimal totalMedidoI0InfraEstrutura;
	private BigDecimal totalSaldoI0InfraEstrutura;
	private BigDecimal totalSaldoReajustadoInfraEstrutura;
	
	private BigDecimal totalValorI0UrbanismoEdificacoes;
	private BigDecimal totalMedidoI0UrbanismoEdificacoes;
	private BigDecimal totalSaldoI0UrbanismoEdificacoes;
	private BigDecimal totalSaldoReajustadoUrbanismoEdificacoes;
	
	private BigDecimal totalValorI0Mineracao;
	private BigDecimal totalMedidoI0Mineracao;
	private BigDecimal totalSaldoI0Mineracao;
	private BigDecimal totalSaldoReajustadoMineracao;
	
	private BigDecimal totalGeralValorI0;
	private BigDecimal totalGeralMedidoI0;
	private BigDecimal totalGeralSaldoI0;
	private BigDecimal totalGeralSaldoReajustadoI0;
	
	private BigDecimal totalMI0DtEscolhidaOleoGas;//Total medição i0 data escolhida oleo gas
	private BigDecimal totalMRDtEscolhidaOleoGas;//total medição com reajuste data escolhida oleo gas
	private BigDecimal totalMDtEscolhidaOleoGas;//total medição data escolhida oleo gas
	private BigDecimal totalSalDtEscolhidaOleoGas;//total salarios oleogas com a data escolhida
	
	private BigDecimal totalMMesAntOleoGas;//total medição mes anterior data escolhida oleo gas
	private BigDecimal totalMI0MesAntOleoGas;//total medição I0 do mes anterior oleogas
	private BigDecimal totalMRMesAnteriorOleoGas; //total medição com reajuste mes anterior oleo gas
	private BigDecimal totalSalMesAnteriorOleoGas;//total de salarios mes anterior oleogas
	
	private BigDecimal totalMComM1OleoGas;//total medição mes anterior menos 1 oleo e gas
	private BigDecimal totalMi0ComM1OleoGas;//total mediçãoI0 mes anterior menos 1 oleo e gas
	private BigDecimal totalMRComM1OleoGas;//total medição comreajuste mes anterior menos 1 oleo e gas
	private BigDecimal totalSalComM1OleoGas;//total salarios mes anterior menos 1 oleo e gas
	
	private BigDecimal totalMI0DtEscolhidaEnergia;//Total medição i0 data escolhida energia
	private BigDecimal totalMRDtEscolhidaEnergia;//total medição com reajuste data escolhida Energia
	private BigDecimal totalMDtEscolhidaEnergia;//total medição data escolhida energia
	private BigDecimal totalSalDtEscolhidaEnergia;//total salarios energia com a data escolhida
	
	private BigDecimal totalMMesAntEnergia;//total medição mes anterior data escolhida energia
	private BigDecimal totalMI0MesAntEnergia;//total medição I0 do mes anterior energia
	private BigDecimal totalMRMesAnteriorEnergia; //total medição com reajuste mes anterior energia
	private BigDecimal totalSalMesAnteriorEnergia;//total de salarios mes anterior energia
	
	private BigDecimal totalMComM1Energia;//total medição mes anterior menos 1 energia
	private BigDecimal totalMi0ComM1Energia;//total mediçãoI0 mes anterior menos 1 energia
	private BigDecimal totalMRComM1Energia;//total medição comreajuste mes anterior menos 1 energia
	private BigDecimal totalSalComM1Energia;//total salarios mes anterior menos 1 energia
	
	public BigDecimal getTotalMI0DtEscolhidaEnergia() {
		return totalMI0DtEscolhidaEnergia;
	}

	public BigDecimal getTotalMRDtEscolhidaEnergia() {
		return totalMRDtEscolhidaEnergia;
	}

	public BigDecimal getTotalMDtEscolhidaEnergia() {
		return totalMDtEscolhidaEnergia;
	}

	public BigDecimal getTotalSalDtEscolhidaEnergia() {
		return totalSalDtEscolhidaEnergia;
	}

	public BigDecimal getTotalMMesAntEnergia() {
		return totalMMesAntEnergia;
	}

	public BigDecimal getTotalMI0MesAntEnergia() {
		return totalMI0MesAntEnergia;
	}

	public BigDecimal getTotalMRMesAnteriorEnergia() {
		return totalMRMesAnteriorEnergia;
	}

	public BigDecimal getTotalSalMesAnteriorEnergia() {
		return totalSalMesAnteriorEnergia;
	}

	public BigDecimal getTotalMComM1Energia() {
		return totalMComM1Energia;
	}

	public BigDecimal getTotalMi0ComM1Energia() {
		return totalMi0ComM1Energia;
	}

	public BigDecimal getTotalMRComM1Energia() {
		return totalMRComM1Energia;
	}

	public BigDecimal getTotalSalComM1Energia() {
		return totalSalComM1Energia;
	}

	public void setTotalMedidoI0Energia(BigDecimal totalMedidoI0Energia) {
		this.totalMedidoI0Energia = totalMedidoI0Energia;
	}

	public void setTotalSaldoI0Energia(BigDecimal totalSaldoI0Energia) {
		this.totalSaldoI0Energia = totalSaldoI0Energia;
	}

	public void setTotalMI0DtEscolhidaEnergia(BigDecimal totalMI0DtEscolhidaEnergia) {
		this.totalMI0DtEscolhidaEnergia = totalMI0DtEscolhidaEnergia;
	}

	public void setTotalMRDtEscolhidaEnergia(BigDecimal totalMRDtEscolhidaEnergia) {
		this.totalMRDtEscolhidaEnergia = totalMRDtEscolhidaEnergia;
	}

	public void setTotalMDtEscolhidaEnergia(BigDecimal totalMDtEscolhidaEnergia) {
		this.totalMDtEscolhidaEnergia = totalMDtEscolhidaEnergia;
	}

	public void setTotalSalDtEscolhidaEnergia(BigDecimal totalSalDtEscolhidaEnergia) {
		this.totalSalDtEscolhidaEnergia = totalSalDtEscolhidaEnergia;
	}

	public void setTotalMMesAntEnergia(BigDecimal totalMMesAntEnergia) {
		this.totalMMesAntEnergia = totalMMesAntEnergia;
	}

	public void setTotalMI0MesAntEnergia(BigDecimal totalMI0MesAntEnergia) {
		this.totalMI0MesAntEnergia = totalMI0MesAntEnergia;
	}

	public void setTotalMRMesAnteriorEnergia(BigDecimal totalMRMesAnteriorEnergia) {
		this.totalMRMesAnteriorEnergia = totalMRMesAnteriorEnergia;
	}

	public void setTotalSalMesAnteriorEnergia(BigDecimal totalSalMesAnteriorEnergia) {
		this.totalSalMesAnteriorEnergia = totalSalMesAnteriorEnergia;
	}

	public void setTotalMComM1Energia(BigDecimal totalMComM1Energia) {
		this.totalMComM1Energia = totalMComM1Energia;
	}

	public void setTotalMi0ComM1Energia(BigDecimal totalMi0ComM1Energia) {
		this.totalMi0ComM1Energia = totalMi0ComM1Energia;
	}

	public void setTotalMRComM1Energia(BigDecimal totalMRComM1Energia) {
		this.totalMRComM1Energia = totalMRComM1Energia;
	}

	public void setTotalSalComM1Energia(BigDecimal totalSalComM1Energia) {
		this.totalSalComM1Energia = totalSalComM1Energia;
	}

	public BigDecimal getTotalMComM1OleoGas() {
		return totalMComM1OleoGas;
	}

	public BigDecimal getTotalMi0ComM1OleoGas() {
		return totalMi0ComM1OleoGas;
	}

	public BigDecimal getTotalMRComM1OleoGas() {
		return totalMRComM1OleoGas;
	}

	public BigDecimal getTotalSalComM1OleoGas() {
		return totalSalComM1OleoGas;
	}

	public void setTotalMComM1OleoGas(BigDecimal totalMComM1OleoGas) {
		this.totalMComM1OleoGas = totalMComM1OleoGas;
	}

	public void setTotalMi0ComM1OleoGas(BigDecimal totalMi0ComM1OleoGas) {
		this.totalMi0ComM1OleoGas = totalMi0ComM1OleoGas;
	}

	public void setTotalMRComM1OleoGas(BigDecimal totalMRComM1OleoGas) {
		this.totalMRComM1OleoGas = totalMRComM1OleoGas;
	}

	public void setTotalSalComM1OleoGas(BigDecimal totalSalComM1OleoGas) {
		this.totalSalComM1OleoGas = totalSalComM1OleoGas;
	}

	public void setTotalMMesAntOleoGas(BigDecimal totalMMesAntOleoGas) {
		this.totalMMesAntOleoGas = totalMMesAntOleoGas;
	}

	public void setTotalMI0MesAntOleoGas(BigDecimal totalMI0MesAntOleoGas) {
		this.totalMI0MesAntOleoGas = totalMI0MesAntOleoGas;
	}

	public void setTotalMRMesAnteriorOleoGas(BigDecimal totalMRMesAnteriorOleoGas) {
		this.totalMRMesAnteriorOleoGas = totalMRMesAnteriorOleoGas;
	}

	public void setTotalSalMesAnteriorOleoGas(BigDecimal totalSalMesAnteriorOleoGas) {
		this.totalSalMesAnteriorOleoGas = totalSalMesAnteriorOleoGas;
	}

	public BigDecimal getTotalMI0MesAntOleoGas() {
		return totalMI0MesAntOleoGas;
	}

	public BigDecimal getTotalMRMesAnteriorOleoGas() {
		return totalMRMesAnteriorOleoGas;
	}

	public BigDecimal getTotalSalMesAnteriorOleoGas() {
		return totalSalMesAnteriorOleoGas;
	}

	public BigDecimal getTotalSalDtEscolhidaOleoGas() {
		return totalSalDtEscolhidaOleoGas;
	}

	public void setTotalSalDtEscolhidaOleoGas(BigDecimal totalSalDtEscolhidaOleoGas) {
		this.totalSalDtEscolhidaOleoGas = totalSalDtEscolhidaOleoGas;
	}

	public void setTotalMI0DtEscolhidaOleoGas(BigDecimal totalMI0DtEscolhidaOleoGas) {
		this.totalMI0DtEscolhidaOleoGas = totalMI0DtEscolhidaOleoGas;
	}

	public void setTotalMRDtEscolhidaOleoGas(BigDecimal totalMRDtEscolhidaOleoGas) {
		this.totalMRDtEscolhidaOleoGas = totalMRDtEscolhidaOleoGas;
	}

	public void setTotalMDtEscolhidaOleoGas(BigDecimal totalMDtEscolhidaOleoGas) {
		this.totalMDtEscolhidaOleoGas = totalMDtEscolhidaOleoGas;
	}

	public BigDecimal getTotalMDtEscolhidaOleoGas() {
		return totalMDtEscolhidaOleoGas;
	}

	public BigDecimal getTotalMRDtEscolhidaOleoGas() {
		return totalMRDtEscolhidaOleoGas;
	}

	public BigDecimal getTotalMI0DtEscolhidaOleoGas() {
		return totalMI0DtEscolhidaOleoGas;
	}

	public BigDecimal getTotalMMesAntOleoGas() {
		return totalMMesAntOleoGas;
	}

	public BigDecimal getTotalValorI0PetroleoGas(){
		totalValorI0PetroleoGas=somaValorI0(contratosModelOleoEGas);
		return totalValorI0PetroleoGas;
	}

	public BigDecimal getTotalMedidoI0PetroleoGas(){
		totalMedidoI0PetroleoGas=somaMedidoI0(contratosModelOleoEGas);
		return totalMedidoI0PetroleoGas;
	}
	
	public BigDecimal getTotalSaldoI0PetroleoGas() {
		totalSaldoI0PetroleoGas=somaSaldoI0(contratosModelOleoEGas);
		return totalSaldoI0PetroleoGas;
	}

	public BigDecimal getTotalSaldoReajustadoPetroleoGas(){
		totalSaldoReajustadoPetroleoGas=somaSaldoReajustado(contratosModelOleoEGas);
		return totalSaldoReajustadoPetroleoGas;
		
	}
	
	public BigDecimal getTotalValorI0Energia() {
		totalValorI0Energia=somaValorI0(contratosModelEnergia);
		return totalValorI0Energia;
	}
	

	public BigDecimal getTotalMedidoI0Energia() {
		totalMedidoI0Energia = somaMedidoI0(contratosModelEnergia);
		return totalMedidoI0Energia;
	}
	
	public BigDecimal getTotalSaldoI0Energia() {
		totalSaldoI0Energia =  somaSaldoI0(contratosModelEnergia);
		return totalSaldoI0Energia;
	}

	
	public BigDecimal getTotalSaldoReajustadoEnergia() {
		totalSaldoReajustadoEnergia = somaSaldoReajustado(contratosModelEnergia);
		return totalSaldoReajustadoEnergia;
	}
	
	
	public BigDecimal getTotalValorI0InfraEstrutura() {
		totalValorI0InfraEstrutura = somaValorI0(contratosModelInfraEstrutura);
		return totalValorI0InfraEstrutura;
	}

	public BigDecimal getTotalMedidoI0InfraEstrutura() {
		totalMedidoI0InfraEstrutura = somaMedidoI0(contratosModelInfraEstrutura);
		return totalMedidoI0InfraEstrutura;
	}

	public BigDecimal getTotalSaldoI0InfraEstrutura() {
		totalSaldoI0InfraEstrutura = somaSaldoI0(contratosModelInfraEstrutura);
		return totalSaldoI0InfraEstrutura;
	}

	public BigDecimal getTotalSaldoReajustadoInfraEstrutura() {
		totalSaldoReajustadoInfraEstrutura = somaSaldoReajustado(contratosModelInfraEstrutura);
		return totalSaldoReajustadoInfraEstrutura;
	}
	
	public BigDecimal getTotalValorI0UrbanismoEdificacoes() {
		totalValorI0UrbanismoEdificacoes = somaValorI0(contratosModelEnergiaUrbanismoeEdificacoes);
		return totalValorI0UrbanismoEdificacoes;
	}

	public BigDecimal getTotalMedidoI0UrbanismoEdificacoes() {
		totalMedidoI0UrbanismoEdificacoes = somaMedidoI0(contratosModelEnergiaUrbanismoeEdificacoes);
		return totalMedidoI0UrbanismoEdificacoes;
	}

	public BigDecimal getTotalSaldoI0UrbanismoEdificacoes() {
		totalSaldoI0UrbanismoEdificacoes = somaSaldoI0(contratosModelEnergiaUrbanismoeEdificacoes);
		return totalSaldoI0UrbanismoEdificacoes;
	}

	public BigDecimal getTotalSaldoReajustadoUrbanismoEdificacoes() {
		totalSaldoReajustadoUrbanismoEdificacoes = somaSaldoReajustado(contratosModelEnergiaUrbanismoeEdificacoes);
		return totalSaldoReajustadoUrbanismoEdificacoes;
	}

	public BigDecimal getTotalValorI0Mineracao() {
		totalValorI0Mineracao= somaValorI0(contratosModelMineracao);
		return totalValorI0Mineracao;
	}

	public BigDecimal getTotalMedidoI0Mineracao() {
		totalMedidoI0Mineracao = somaMedidoI0(contratosModelMineracao);
		return totalMedidoI0Mineracao;
	}

	public BigDecimal getTotalSaldoI0Mineracao() {
		totalSaldoI0Mineracao = somaSaldoI0(contratosModelMineracao);
		return totalSaldoI0Mineracao;
	}

	public BigDecimal getTotalSaldoReajustadoMineracao() {
		totalSaldoReajustadoMineracao = somaSaldoReajustado(contratosModelMineracao);
		return totalSaldoReajustadoMineracao;
	}

	public BigDecimal getTotalGeralValorI0() {
		totalGeralValorI0 = totalValorI0PetroleoGas.add(totalValorI0Energia);
		totalGeralValorI0 = totalGeralValorI0.add(totalValorI0InfraEstrutura);
		totalGeralValorI0 = totalGeralValorI0.add(totalValorI0Mineracao);
		totalGeralValorI0 = totalGeralValorI0.add(totalValorI0UrbanismoEdificacoes);
		return totalGeralValorI0;
	}
	

	public BigDecimal getTotalGeralMedidoI0() {
		totalGeralMedidoI0 = totalMedidoI0PetroleoGas.add(totalMedidoI0Energia);
		totalGeralMedidoI0 = totalGeralMedidoI0.add(totalMedidoI0InfraEstrutura);
		totalGeralMedidoI0 = totalGeralMedidoI0.add(totalMedidoI0Mineracao);
		totalGeralMedidoI0 = totalGeralMedidoI0.add(totalMedidoI0UrbanismoEdificacoes);
		return totalGeralMedidoI0;
	}

	public BigDecimal getTotalGeralSaldoI0() {
		totalGeralSaldoI0 = totalSaldoI0PetroleoGas.add(totalSaldoI0Energia);
		totalGeralSaldoI0 = totalGeralSaldoI0.add(totalSaldoI0InfraEstrutura);
		totalGeralSaldoI0 = totalGeralSaldoI0.add(totalSaldoI0Mineracao);
		totalGeralSaldoI0 = totalGeralSaldoI0.add(totalSaldoI0UrbanismoEdificacoes);
		return totalGeralSaldoI0;
	}

	public BigDecimal getTotalGeralSaldoReajustadoI0() {
		totalGeralSaldoReajustadoI0 = totalSaldoReajustadoPetroleoGas.add(totalSaldoReajustadoEnergia);
		totalGeralSaldoReajustadoI0 = totalGeralSaldoReajustadoI0.add(totalSaldoReajustadoInfraEstrutura);
		totalGeralSaldoReajustadoI0 = totalGeralSaldoReajustadoI0.add(totalSaldoReajustadoMineracao);
		totalGeralSaldoReajustadoI0 = totalGeralSaldoReajustadoI0.add(totalSaldoReajustadoUrbanismoEdificacoes);
		return totalGeralSaldoReajustadoI0;
	}

	private BigDecimal somaValorI0(List<ContratosModel>listModel){
		BigDecimal total = BigDecimal.ZERO;
		for(ContratosModel c: listModel){
			total = total.add(c.getProjeto().getValorOriginal());
			}
		return total;
	}
	private BigDecimal somaMedidoI0(List<ContratosModel>listModel){
		BigDecimal total = BigDecimal.ZERO;
		for(ContratosModel c: listModel){
			total = total.add(c.getMedidoI0());
			}
		return total;
	}
	private BigDecimal somaSaldoI0(List<ContratosModel>listModel){
		BigDecimal total = BigDecimal.ZERO;
		for(ContratosModel c: listModel){
			total = total.add(c.getSaldoI0());
			}
		return total;
	}
	private BigDecimal somaSaldoReajustado(List<ContratosModel>listModel){
		BigDecimal total = BigDecimal.ZERO;
		for(ContratosModel c: listModel){
			total = total.add(c.getSaldoReajustado());
			}
		return total;
	}
	
	public void setTotalSaldoI0PetroleoGas(BigDecimal totalSaldoI0PetroleoGas) {
		this.totalSaldoI0PetroleoGas = totalSaldoI0PetroleoGas;
	}

	public String getMesCompetencia() {
		return mesCompetencia;
	}

	public int getAnoCompetencia() {
		return anoCompetencia;
	}

	public void setMesCompetencia(String mesCompetencia) {
		this.mesCompetencia = mesCompetencia;
	}

	public void setAnoCompetencia(int anoCompetencia) {
		this.anoCompetencia = anoCompetencia;
	}

	public String getLabelDataAvaliacao() {
		return labelDataAvaliacao;
	}

	public void setLabelDataAvaliacao(String labelDataAvaliacao) {
		this.labelDataAvaliacao = labelDataAvaliacao;
	}

	public List<ContratosModel> getContratosModelOleoEGas() {
		return contratosModelOleoEGas;
	}

	public List<ContratosModel> getContratosModelEnergia() {
		return contratosModelEnergia;
	}

	public List<ContratosModel> getContratosModelInfraEstrutura() {
		return contratosModelInfraEstrutura;
	}

	public List<ContratosModel> getContratosModelMineracao() {
		return contratosModelMineracao;
	}

	public List<ContratosModel> getContratosModelEnergiaUrbanismoeEdificacoes() {
		return contratosModelEnergiaUrbanismoeEdificacoes;
	}

	public void setContratosModelOleoEGas(List<ContratosModel> contratosModelOleoEGas) {
		this.contratosModelOleoEGas = contratosModelOleoEGas;
	}

	public void setContratosModelEnergia(List<ContratosModel> contratosModelEnergia) {
		this.contratosModelEnergia = contratosModelEnergia;
	}

	public void setContratosModelInfraEstrutura(List<ContratosModel> contratosModelInfraEstrutura) {
		this.contratosModelInfraEstrutura = contratosModelInfraEstrutura;
	}

	public void setContratosModelMineracao(List<ContratosModel> contratosModelMineracao) {
		this.contratosModelMineracao = contratosModelMineracao;
	}

	public void setContratosModelEnergiaUrbanismoeEdificacoes(
			List<ContratosModel> contratosModelEnergiaUrbanismoeEdificacoes) {
		this.contratosModelEnergiaUrbanismoeEdificacoes = contratosModelEnergiaUrbanismoeEdificacoes;
	}

	public Calendar getDataEscolhida() {
		return dataEscolhida;
	}

	public Calendar getMesAnterior() {
		return mesAnterior;
	}

	public Calendar getMesAnteriorM1() {
		return mesAnteriorM1;
	}

	public void setDataEscolhida(Calendar dataEscolhida) {
		this.dataEscolhida = dataEscolhida;
	}

	public void setMesAnterior(Calendar mesAnterior) {
		this.mesAnterior = mesAnterior;
	}

	public void setMesAnteriorM1(Calendar mesAnteriorM1) {
		this.mesAnteriorM1 = mesAnteriorM1;
	}
}
