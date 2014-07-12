package com.nucleo.projetos.relatorios.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.nucleo.util.Data;

public class RelatorioMensal {

	public RelatorioMensal() {
		equipes = new ArrayList<EquipeModel>();
	}

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private List<EquipeModel> equipes;

	private BigDecimal indiceReajuste;
	private BigDecimal valorReajuste;

	private Calendar inicioPeriodo;
	private Calendar fimPeriodo;
	private Calendar inicioContrato;
	private Calendar fimContrato;

	private String inicioPeriodoString;
	private String fimPeriodoString;
	private String inicioContratoString;
	private String fimContratoString;

	private int ordemPeriodo;
	private String descPeriodo;

	private int totalDiasProjeto;
	private int diasRestantesProjeto;
	private int diasPassadosProjeto;
	private double porcentRealizado;

	private String numContrato;
	private String cn;
	private String descricaoProjeto;
	private String nomeResponsavel;
	private String nomeGerenteNucleo;
	private String nomeGerenteRelacionamento;
	private String nomeFiscal;
	private String objeto;


	private BigDecimal saldoProjeto;
	private BigDecimal valorContrato;

	private BigDecimal valorBrutoMedicao;

	private BigDecimal totalMedicaoPeriodo;
	private BigDecimal totalMedicaoEquipesPeriodo;
	private BigDecimal valorAcumuladoAtePeriodo;

	private double provisaoProxMes;

	// DESPESAS REEMBOLSÁVEIS
	private BigDecimal totalValorDespesasSemImpostos;
	private BigDecimal totalValorDespesasComImpostos;
	private BigDecimal totalAFaturarDespesas;
	private BigDecimal totalCofinsDespesas;
	private BigDecimal totalPisDespesas;
	private BigDecimal totalIssDespesas;

	public String getNomeFiscal() {
		return nomeFiscal;
	}
	public void setNomeFiscal(String nomeFiscal) {
		this.nomeFiscal = nomeFiscal;
	}
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public String getNomeGerenteNucleo() {
		return nomeGerenteNucleo;
	}
	public String getNomeGerenteRelacionamento() {
		return nomeGerenteRelacionamento;
	}
	public String getObjeto() {
		return objeto;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	public void setNomeGerenteNucleo(String nomeGerenteNucleo) {
		this.nomeGerenteNucleo = nomeGerenteNucleo;
	}
	public void setNomeGerenteRelacionamento(String nomeGerenteRelacionamento) {
		this.nomeGerenteRelacionamento = nomeGerenteRelacionamento;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public List<EquipeModel> getEquipes() {
		return equipes;
	}
	public Calendar getInicioPeriodo() {
		return inicioPeriodo;
	}
	public Calendar getFimPeriodo() {
		return fimPeriodo;
	}
	public int getOrdemPeriodo() {
		return ordemPeriodo;
	}
	public String getDescPeriodo() {
		SimpleDateFormat df = new SimpleDateFormat("MMMM/yyyy");
		descPeriodo = df.format(fimPeriodo.getTime());
		return descPeriodo;
	}
	public int getTotalDiasProjeto() {
		if (inicioContrato == null || fimContrato == null) {
			return 0;
		}
		totalDiasProjeto = Data
				.diferencaEntreDatas(inicioContrato.getTime(), fimContrato.getTime());
		return totalDiasProjeto;
	}
	public int getDiasRestantesProjeto() {
		diasRestantesProjeto = getTotalDiasProjeto() - getDiasPassadosProjeto();
		return diasRestantesProjeto;
	}
	public int getDiasPassadosProjeto() {
		if (inicioContrato == null || fimPeriodo == null) {
			return 0;
		}

		diasPassadosProjeto = Data.diferencaEntreDatas(inicioContrato.getTime(),
				fimPeriodo.getTime());
		return diasPassadosProjeto;
	}
	public double getPorcentRealizado() {
		porcentRealizado = BigDecimal.valueOf(getDiasPassadosProjeto())
				.divide(BigDecimal.valueOf(getTotalDiasProjeto()), 2, RoundingMode.CEILING)
				.doubleValue();
		return porcentRealizado;
	}
	public String getNumContrato() {
		return numContrato;
	}
	public BigDecimal getSaldoProjeto() {
		return saldoProjeto;
	}
	public BigDecimal getValorContrato() {
		return valorContrato;
	}
	public double getProvisaoProxMes() {
		provisaoProxMes = getSaldoProjeto().divide(totalMedicaoPeriodo, 2, RoundingMode.CEILING)
				.doubleValue();
		return provisaoProxMes;
	}
	public Calendar getInicioContrato() {
		return inicioContrato;
	}
	public Calendar getFimContrato() {
		return fimContrato;
	}
	public BigDecimal getTotalMedicaoEquipesPeriodo() {
		if (equipes.size() == 0) {
			return BigDecimal.ZERO;
		}
		totalMedicaoEquipesPeriodo = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			totalMedicaoEquipesPeriodo = totalMedicaoEquipesPeriodo.add(equipe
					.getTotalMedicoesPendentes());
		}
		return totalMedicaoEquipesPeriodo;
	}
	public BigDecimal getTotalMedicaoPeriodo() {
		if (equipes.size() == 0) {
			return BigDecimal.ZERO;
		}
		totalMedicaoPeriodo = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			totalMedicaoPeriodo = totalMedicaoPeriodo.add(equipe.getTotalMedicoesPendentes());

			if (equipe.getDespesas().size() == 0)
				continue;
			for (DespesaModel despesa : equipe.getDespesas()) {
				totalMedicaoPeriodo = totalMedicaoPeriodo.add(despesa.getValorAFaturar());
			}
		}
		return totalMedicaoPeriodo;
	}
	public BigDecimal getValorAcumuladoAtePeriodo() {
		if (equipes.size() == 0) {
			return BigDecimal.ZERO;
		}
		valorAcumuladoAtePeriodo = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			valorAcumuladoAtePeriodo = valorAcumuladoAtePeriodo.add(equipe
					.getTotalMedicoesAprovadas());
		}
		valorAcumuladoAtePeriodo = valorAcumuladoAtePeriodo.add(getTotalMedicaoPeriodo());
		return valorAcumuladoAtePeriodo;
	}
	public String getInicioPeriodoString() {
		inicioPeriodoString = dateFormat.format(inicioPeriodo.getTime());
		return inicioPeriodoString;
	}
	public String getFimPeriodoString() {
		fimPeriodoString = dateFormat.format(fimPeriodo.getTime());
		return fimPeriodoString;
	}
	public String getInicioContratoString() {
		inicioContratoString = dateFormat.format(inicioContrato.getTime());
		return inicioContratoString;
	}
	public String getFimContratoString() {
		fimContratoString = dateFormat.format(fimContrato.getTime());
		return fimContratoString;
	}

	public BigDecimal getTotalValorDespesasSemImpostos() {
		if (equipes.size() == 0)
			return BigDecimal.ZERO;
		totalValorDespesasSemImpostos = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			if (equipe.getDespesas().size() == 0)
				continue;
			for (DespesaModel despesa : equipe.getDespesas()) {
				totalValorDespesasSemImpostos = totalValorDespesasSemImpostos.add(despesa
						.getValor());
			}
		}
		return totalValorDespesasSemImpostos;
	}
	public BigDecimal getTotalValorDespesasComImpostos() {
		if (equipes.size() == 0)
			return BigDecimal.ZERO;
		totalValorDespesasComImpostos = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			if (equipe.getDespesas().size() == 0)
				continue;
			for (DespesaModel despesa : equipe.getDespesas()) {
				totalValorDespesasComImpostos = totalValorDespesasComImpostos.add(despesa
						.getValorComImpostos());
			}
		}
		return totalValorDespesasComImpostos;
	}
	public BigDecimal getTotalAFaturarDespesas() {
		if (equipes.size() == 0)
			return BigDecimal.ZERO;
		totalAFaturarDespesas = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			if (equipe.getDespesas().size() == 0)
				continue;
			for (DespesaModel despesa : equipe.getDespesas()) {
				totalAFaturarDespesas = totalAFaturarDespesas.add(despesa.getValorAFaturar());
			}
		}
		return totalAFaturarDespesas;
	}
	public BigDecimal getTotalCofinsDespesas() {
		if (equipes.size() == 0)
			return BigDecimal.ZERO;
		totalCofinsDespesas = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			if (equipe.getDespesas().size() == 0)
				continue;
			for (DespesaModel despesa : equipe.getDespesas()) {
				totalCofinsDespesas = totalCofinsDespesas.add(despesa.getCofins());
			}
		}
		return totalCofinsDespesas;
	}
	public BigDecimal getTotalPisDespesas() {
		if (equipes.size() == 0)
			return BigDecimal.ZERO;
		totalPisDespesas = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			if (equipe.getDespesas().size() == 0)
				continue;
			for (DespesaModel despesa : equipe.getDespesas()) {
				totalPisDespesas = totalPisDespesas.add(despesa.getPis());
			}
		}
		return totalPisDespesas;
	}
	public BigDecimal getTotalIssDespesas() {
		if (equipes.size() == 0)
			return BigDecimal.ZERO;
		totalIssDespesas = BigDecimal.ZERO;
		for (EquipeModel equipe : equipes) {
			if (equipe.getDespesas().size() == 0)
				continue;
			for (DespesaModel despesa : equipe.getDespesas()) {
				totalIssDespesas = totalIssDespesas.add(despesa.getIss());
			}
		}
		return totalIssDespesas;
	}
	public BigDecimal getIndiceReajuste() {
		return indiceReajuste;
	}
	public BigDecimal getValorReajuste() {
		if(getTotalMedicaoPeriodo().compareTo(BigDecimal.ZERO) == 0 || 
				indiceReajuste == null){
			return BigDecimal.ZERO;
		}
		valorReajuste = getTotalMedicaoPeriodo().multiply(indiceReajuste.subtract(BigDecimal.ONE),
				MathContext.DECIMAL32);
		return valorReajuste;
	}
	public BigDecimal getValorBrutoMedicao() {
		if(getTotalMedicaoPeriodo().compareTo(BigDecimal.ZERO) == 0 || 
				indiceReajuste == null){
			return BigDecimal.ZERO;
		}
		valorBrutoMedicao = getTotalMedicaoPeriodo().multiply(indiceReajuste,
				MathContext.DECIMAL32);
		return valorBrutoMedicao;
	}
	public String getCn() {
		return cn;
	}
	public String getDescricaoProjeto() {
		return descricaoProjeto;
	}
	
	
	
	public void setCn(String cn) {
		this.cn = cn;
	}
	public void setDescricaoProjeto(String descricaoProjeto) {
		this.descricaoProjeto = descricaoProjeto;
	}
	public void setIndiceReajuste(BigDecimal indiceReajuste) {
		this.indiceReajuste = indiceReajuste;
	}
	public void setTotalDiasProjeto(int totalDiasProjeto) {
		this.totalDiasProjeto = totalDiasProjeto;
	}
	public void setTotalValorDespesasSemImpostos(BigDecimal totalValorDespesasSemImpostos) {
		this.totalValorDespesasSemImpostos = totalValorDespesasSemImpostos;
	}
	public void setTotalValorDespesasComImpostos(BigDecimal totalValorDespesasComImpostos) {
		this.totalValorDespesasComImpostos = totalValorDespesasComImpostos;
	}
	public void setTotalAFaturarDespesas(BigDecimal totalAFaturarDespesas) {
		this.totalAFaturarDespesas = totalAFaturarDespesas;
	}
	public void setTotalCofinsDespesas(BigDecimal totalCofinsDespesas) {
		this.totalCofinsDespesas = totalCofinsDespesas;
	}
	public void setTotalPisDespesas(BigDecimal totalPisDespesas) {
		this.totalPisDespesas = totalPisDespesas;
	}
	public void setTotalIssDespesas(BigDecimal totalIssDespesas) {
		this.totalIssDespesas = totalIssDespesas;
	}
	public void setValorAcumuladoAtePeriodo(BigDecimal valorAcumuladoAtePeriodo) {
		this.valorAcumuladoAtePeriodo = valorAcumuladoAtePeriodo;
	}
	public void setInicioContrato(Calendar inicioContrato) {
		this.inicioContrato = inicioContrato;
	}
	public void setFimContrato(Calendar fimContrato) {
		this.fimContrato = fimContrato;
	}
	public void setEquipes(List<EquipeModel> equipes) {
		this.equipes = equipes;
	}
	public void setInicioPeriodo(Calendar inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}
	public void setFimPeriodo(Calendar fimPeriodo) {
		this.fimPeriodo = fimPeriodo;
	}
	public void setOrdemPeriodo(int ordemPeriodo) {
		this.ordemPeriodo = ordemPeriodo;
	}
	public void setPorcentRealizado(double porcentRealizado) {
		this.porcentRealizado = porcentRealizado;
	}
	public void setNumContrato(String numContrato) {
		this.numContrato = numContrato;
	}
	public void setSaldoProjeto(BigDecimal saldoProjeto) {
		this.saldoProjeto = saldoProjeto;
	}
	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}
	public void setProvisaoProxMes(double provisaoProxMes) {
		this.provisaoProxMes = provisaoProxMes;
	}

}
