package com.nucleo.projetos.relatorios.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ResumoModeloSistema {
	public ResumoModeloSistema() {
		contratosModelOleoEGas = new ArrayList<ContratosModel>();
		contratosModelEnergia = new ArrayList<ContratosModel>();
		contratosModelInfraEstrutura = new ArrayList<ContratosModel>();
		contratosModelMineracao = new ArrayList<ContratosModel>();
		contratosModelEnergiaUrbanismoeEdificacoes = new ArrayList<ContratosModel>();
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
