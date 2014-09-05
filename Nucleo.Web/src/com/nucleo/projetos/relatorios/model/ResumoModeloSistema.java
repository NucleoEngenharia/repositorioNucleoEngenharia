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
		
		totalMI0DtEscolhidaInfraEstrutura= new BigDecimal(0);
		totalMRDtEscolhidaInfraEstrutura= new BigDecimal(0);
		totalMDtEscolhidaInfraEstrutura= new BigDecimal(0);
		totalSalDtEscolhidaInfraEstrutura= new BigDecimal(0);
		
		totalMI0MesAntInfraEstrutura= new BigDecimal(0);
		totalMRMesAntInfraEstrutura= new BigDecimal(0);
		totalMMesAntInfraEstrutura= new BigDecimal(0);
		totalSalMesAntInfraEstrutura= new BigDecimal(0);
		
		totalMI0ComM1InfraEstrutura= new BigDecimal(0);
		totalMRComM1InfraEstrutura= new BigDecimal(0);
		totalMComM1InfraEstrutura= new BigDecimal(0);
		totalSalComM1InfraEstrutura= new BigDecimal(0);
		
		totalMI0DtEscolhidaUrbanismo = new BigDecimal(0);
		totalMRDtEscolhidaUrbanismo= new BigDecimal(0);
		totalMDtEscolhidaUrbanismo= new BigDecimal(0);
		totalSalDtEscolhidaUrbanismo= new BigDecimal(0);
		
		totalMI0MesAntUrbanismo= new BigDecimal(0);
		totalMRMesAntUrbanismo= new BigDecimal(0);
		totalMMesAntUrbanismo= new BigDecimal(0);
		totalSalMesAntUrbanismo= new BigDecimal(0);
		
		totalMI0ComM1Urbanismo= new BigDecimal(0);
		totalMRComM1Urbanismo= new BigDecimal(0);
		totalMComM1Urbanismo= new BigDecimal(0);
		totalSalComM1Urbanismo= new BigDecimal(0);
		
		totalMI0DtEscolhidaMineracao= new BigDecimal(0);;
		totalMRDtEscolhidaMineracao= new BigDecimal(0);;
		totalMDtEscolhidaMineracao= new BigDecimal(0);;
		totalSalDtEscolhidaMineracao= new BigDecimal(0);;
		
		totalMI0MesAntMineracao= new BigDecimal(0);;
		totalMRMesAntMineracao= new BigDecimal(0);;
		totalMMesAntMineracao= new BigDecimal(0);;
		totalSalMesAntMineracao= new BigDecimal(0);;
		
		totalMI0ComM1Mineracao= new BigDecimal(0);;
		totalMRComM1Mineracao= new BigDecimal(0);;
		totalMComM1Mineracao= new BigDecimal(0);;
		totalSalComM1Mineracao= new BigDecimal(0);;
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
	
	private BigDecimal totalMI0DtEscolhidaInfraEstrutura;
	private BigDecimal totalMRDtEscolhidaInfraEstrutura;
	private BigDecimal totalMDtEscolhidaInfraEstrutura;
	private BigDecimal totalSalDtEscolhidaInfraEstrutura;
	
	private BigDecimal totalMI0MesAntInfraEstrutura;
	private BigDecimal totalMRMesAntInfraEstrutura;
	private BigDecimal totalMMesAntInfraEstrutura;
	private BigDecimal totalSalMesAntInfraEstrutura;
	
	private BigDecimal totalMI0ComM1InfraEstrutura;
	private BigDecimal totalMRComM1InfraEstrutura;
	private BigDecimal totalMComM1InfraEstrutura;
	private BigDecimal totalSalComM1InfraEstrutura;
	
	private BigDecimal totalMI0DtEscolhidaUrbanismo;
	private BigDecimal totalMRDtEscolhidaUrbanismo;
	private BigDecimal totalMDtEscolhidaUrbanismo;
	private BigDecimal totalSalDtEscolhidaUrbanismo;
	
	private BigDecimal totalMI0MesAntUrbanismo;
	private BigDecimal totalMRMesAntUrbanismo;
	private BigDecimal totalMMesAntUrbanismo;
	private BigDecimal totalSalMesAntUrbanismo;
	
	private BigDecimal totalMI0ComM1Urbanismo;
	private BigDecimal totalMRComM1Urbanismo;
	private BigDecimal totalMComM1Urbanismo;
	private BigDecimal totalSalComM1Urbanismo;
	
	private BigDecimal totalMI0DtEscolhidaMineracao;
	private BigDecimal totalMRDtEscolhidaMineracao;
	private BigDecimal totalMDtEscolhidaMineracao;
	private BigDecimal totalSalDtEscolhidaMineracao;
	
	private BigDecimal totalMI0MesAntMineracao;
	private BigDecimal totalMRMesAntMineracao;
	private BigDecimal totalMMesAntMineracao;
	private BigDecimal totalSalMesAntMineracao;
	
	private BigDecimal totalMI0ComM1Mineracao;
	private BigDecimal totalMRComM1Mineracao;
	private BigDecimal totalMComM1Mineracao;
	private BigDecimal totalSalComM1Mineracao;
	
	
	public BigDecimal getTotalMI0DtEscolhidaMineracao() {
		return totalMI0DtEscolhidaMineracao;
	}

	public BigDecimal getTotalMRDtEscolhidaMineracao() {
		return totalMRDtEscolhidaMineracao;
	}

	public BigDecimal getTotalMDtEscolhidaMineracao() {
		return totalMDtEscolhidaMineracao;
	}

	public BigDecimal getTotalSalDtEscolhidaMineracao() {
		return totalSalDtEscolhidaMineracao;
	}

	public BigDecimal getTotalMI0MesAntMineracao() {
		return totalMI0MesAntMineracao;
	}

	public BigDecimal getTotalMRMesAntMineracao() {
		return totalMRMesAntMineracao;
	}

	public BigDecimal getTotalMMesAntMineracao() {
		return totalMMesAntMineracao;
	}

	public BigDecimal getTotalSalMesAntMineracao() {
		return totalSalMesAntMineracao;
	}

	public BigDecimal getTotalMI0ComM1Mineracao() {
		return totalMI0ComM1Mineracao;
	}

	public BigDecimal getTotalMRComM1Mineracao() {
		return totalMRComM1Mineracao;
	}

	public BigDecimal getTotalMComM1Mineracao() {
		return totalMComM1Mineracao;
	}

	public BigDecimal getTotalSalComM1Mineracao() {
		return totalSalComM1Mineracao;
	}

	public void setTotalMI0DtEscolhidaMineracao(BigDecimal totalMI0DtEscolhidaMineracao) {
		this.totalMI0DtEscolhidaMineracao = totalMI0DtEscolhidaMineracao;
	}

	public void setTotalMRDtEscolhidaMineracao(BigDecimal totalMRDtEscolhidaMineracao) {
		this.totalMRDtEscolhidaMineracao = totalMRDtEscolhidaMineracao;
	}

	public void setTotalMDtEscolhidaMineracao(BigDecimal totalMDtEscolhidaMineracao) {
		this.totalMDtEscolhidaMineracao = totalMDtEscolhidaMineracao;
	}

	public void setTotalSalDtEscolhidaMineracao(BigDecimal totalSalDtEscolhidaMineracao) {
		this.totalSalDtEscolhidaMineracao = totalSalDtEscolhidaMineracao;
	}

	public void setTotalMI0MesAntMineracao(BigDecimal totalMI0MesAntMineracao) {
		this.totalMI0MesAntMineracao = totalMI0MesAntMineracao;
	}

	public void setTotalMRMesAntMineracao(BigDecimal totalMRMesAntMineracao) {
		this.totalMRMesAntMineracao = totalMRMesAntMineracao;
	}

	public void setTotalMMesAntMineracao(BigDecimal totalMMesAntMineracao) {
		this.totalMMesAntMineracao = totalMMesAntMineracao;
	}

	public void setTotalSalMesAntMineracao(BigDecimal totalSalMesAntMineracao) {
		this.totalSalMesAntMineracao = totalSalMesAntMineracao;
	}

	public void setTotalMI0ComM1Mineracao(BigDecimal totalMI0ComM1Mineracao) {
		this.totalMI0ComM1Mineracao = totalMI0ComM1Mineracao;
	}

	public void setTotalMRComM1Mineracao(BigDecimal totalMRComM1Mineracao) {
		this.totalMRComM1Mineracao = totalMRComM1Mineracao;
	}

	public void setTotalMComM1Mineracao(BigDecimal totalMComM1Mineracao) {
		this.totalMComM1Mineracao = totalMComM1Mineracao;
	}

	public void setTotalSalComM1Mineracao(BigDecimal totalSalComM1Mineracao) {
		this.totalSalComM1Mineracao = totalSalComM1Mineracao;
	}

	public BigDecimal getTotalMI0DtEscolhidaUrbanismo() {
		return totalMI0DtEscolhidaUrbanismo;
	}

	public BigDecimal getTotalMRDtEscolhidaUrbanismo() {
		return totalMRDtEscolhidaUrbanismo;
	}

	public BigDecimal getTotalMDtEscolhidaUrbanismo() {
		return totalMDtEscolhidaUrbanismo;
	}

	public BigDecimal getTotalSalDtEscolhidaUrbanismo() {
		return totalSalDtEscolhidaUrbanismo;
	}

	public BigDecimal getTotalMI0MesAntUrbanismo() {
		return totalMI0MesAntUrbanismo;
	}

	public BigDecimal getTotalMRMesAntUrbanismo() {
		return totalMRMesAntUrbanismo;
	}

	public BigDecimal getTotalMMesAntUrbanismo() {
		return totalMMesAntUrbanismo;
	}

	public BigDecimal getTotalSalMesAntUrbanismo() {
		return totalSalMesAntUrbanismo;
	}

	public BigDecimal getTotalMI0ComM1Urbanismo() {
		return totalMI0ComM1Urbanismo;
	}

	public BigDecimal getTotalMRComM1Urbanismo() {
		return totalMRComM1Urbanismo;
	}

	public BigDecimal getTotalMComM1Urbanismo() {
		return totalMComM1Urbanismo;
	}

	public BigDecimal getTotalSalComM1Urbanismo() {
		return totalSalComM1Urbanismo;
	}

	public void setTotalMI0DtEscolhidaUrbanismo(BigDecimal totalMI0DtEscolhidaUrbanismo) {
		this.totalMI0DtEscolhidaUrbanismo = totalMI0DtEscolhidaUrbanismo;
	}

	public void setTotalMRDtEscolhidaUrbanismo(BigDecimal totalMRDtEscolhidaUrbanismo) {
		this.totalMRDtEscolhidaUrbanismo = totalMRDtEscolhidaUrbanismo;
	}

	public void setTotalMDtEscolhidaUrbanismo(BigDecimal totalMDtEscolhidaUrbanismo) {
		this.totalMDtEscolhidaUrbanismo = totalMDtEscolhidaUrbanismo;
	}

	public void setTotalSalDtEscolhidaUrbanismo(BigDecimal totalSalDtEscolhidaUrbanismo) {
		this.totalSalDtEscolhidaUrbanismo = totalSalDtEscolhidaUrbanismo;
	}

	public void setTotalMI0MesAntUrbanismo(BigDecimal totalMI0MesAntUrbanismo) {
		this.totalMI0MesAntUrbanismo = totalMI0MesAntUrbanismo;
	}

	public void setTotalMRMesAntUrbanismo(BigDecimal totalMRMesAntUrbanismo) {
		this.totalMRMesAntUrbanismo = totalMRMesAntUrbanismo;
	}

	public void setTotalMMesAntUrbanismo(BigDecimal totalMMesAntUrbanismo) {
		this.totalMMesAntUrbanismo = totalMMesAntUrbanismo;
	}

	public void setTotalSalMesAntUrbanismo(BigDecimal totalSalMesAntUrbanismo) {
		this.totalSalMesAntUrbanismo = totalSalMesAntUrbanismo;
	}

	public void setTotalMI0ComM1Urbanismo(BigDecimal totalMI0ComM1Urbanismo) {
		this.totalMI0ComM1Urbanismo = totalMI0ComM1Urbanismo;
	}

	public void setTotalMRComM1Urbanismo(BigDecimal totalMRComM1Urbanismo) {
		this.totalMRComM1Urbanismo = totalMRComM1Urbanismo;
	}

	public void setTotalMComM1Urbanismo(BigDecimal totalMComM1Urbanismo) {
		this.totalMComM1Urbanismo = totalMComM1Urbanismo;
	}

	public void setTotalSalComM1Urbanismo(BigDecimal totalSalComM1Urbanismo) {
		this.totalSalComM1Urbanismo = totalSalComM1Urbanismo;
	}

	public BigDecimal getTotalMI0DtEscolhidaInfraEstrutura() {
		return totalMI0DtEscolhidaInfraEstrutura;
	}

	public BigDecimal getTotalMRDtEscolhidaInfraEstrutura() {
		return totalMRDtEscolhidaInfraEstrutura;
	}

	public BigDecimal getTotalMDtEscolhidaInfraEstrutura() {
		return totalMDtEscolhidaInfraEstrutura;
	}

	public BigDecimal getTotalSalDtEscolhidaInfraEstrutura() {
		return totalSalDtEscolhidaInfraEstrutura;
	}

	public BigDecimal getTotalMI0MesAntInfraEstrutura() {
		return totalMI0MesAntInfraEstrutura;
	}

	public BigDecimal getTotalMRMesAntInfraEstrutura() {
		return totalMRMesAntInfraEstrutura;
	}

	public BigDecimal getTotalMMesAntInfraEstrutura() {
		return totalMMesAntInfraEstrutura;
	}

	public BigDecimal getTotalSalMesAntInfraEstrutura() {
		return totalSalMesAntInfraEstrutura;
	}

	public BigDecimal getTotalMI0ComM1InfraEstrutura() {
		return totalMI0ComM1InfraEstrutura;
	}

	public BigDecimal getTotalMRComM1InfraEstrutura() {
		return totalMRComM1InfraEstrutura;
	}

	public BigDecimal getTotalMComM1InfraEstrutura() {
		return totalMComM1InfraEstrutura;
	}

	public BigDecimal getTotalSalComM1InfraEstrutura() {
		return totalSalComM1InfraEstrutura;
	}

	public void setTotalMI0DtEscolhidaInfraEstrutura(BigDecimal totalMI0DtEscolhidaInfraEstrutura) {
		this.totalMI0DtEscolhidaInfraEstrutura = totalMI0DtEscolhidaInfraEstrutura;
	}

	public void setTotalMRDtEscolhidaInfraEstrutura(BigDecimal totalMRDtEscolhidaInfraEstrutura) {
		this.totalMRDtEscolhidaInfraEstrutura = totalMRDtEscolhidaInfraEstrutura;
	}

	public void setTotalMDtEscolhidaInfraEstrutura(BigDecimal totalMDtEscolhidaInfraEstrutura) {
		this.totalMDtEscolhidaInfraEstrutura = totalMDtEscolhidaInfraEstrutura;
	}

	public void setTotalSalDtEscolhidaInfraEstrutura(BigDecimal totalSalDtEscolhidaInfraEstrutura) {
		this.totalSalDtEscolhidaInfraEstrutura = totalSalDtEscolhidaInfraEstrutura;
	}

	public void setTotalMI0MesAntInfraEstrutura(BigDecimal totalMI0MesAntInfraEstrutura) {
		this.totalMI0MesAntInfraEstrutura = totalMI0MesAntInfraEstrutura;
	}

	public void setTotalMRMesAntInfraEstrutura(BigDecimal totalMRMesAntInfraEstrutura) {
		this.totalMRMesAntInfraEstrutura = totalMRMesAntInfraEstrutura;
	}

	public void setTotalMMesAntInfraEstrutura(BigDecimal totalMMesAntInfraEstrutura) {
		this.totalMMesAntInfraEstrutura = totalMMesAntInfraEstrutura;
	}

	public void setTotalSalMesAntInfraEstrutura(BigDecimal totalSalMesAntInfraEstrutura) {
		this.totalSalMesAntInfraEstrutura = totalSalMesAntInfraEstrutura;
	}

	public void setTotalMI0ComM1InfraEstrutura(BigDecimal totalMI0ComM1InfraEstrutura) {
		this.totalMI0ComM1InfraEstrutura = totalMI0ComM1InfraEstrutura;
	}

	public void setTotalMRComM1InfraEstrutura(BigDecimal totalMRComM1InfraEstrutura) {
		this.totalMRComM1InfraEstrutura = totalMRComM1InfraEstrutura;
	}

	public void setTotalMComM1InfraEstrutura(BigDecimal totalMComM1InfraEstrutura) {
		this.totalMComM1InfraEstrutura = totalMComM1InfraEstrutura;
	}

	public void setTotalSalComM1InfraEstrutura(BigDecimal totalSalComM1InfraEstrutura) {
		this.totalSalComM1InfraEstrutura = totalSalComM1InfraEstrutura;
	}

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
