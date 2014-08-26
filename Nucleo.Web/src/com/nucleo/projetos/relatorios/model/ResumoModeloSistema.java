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
