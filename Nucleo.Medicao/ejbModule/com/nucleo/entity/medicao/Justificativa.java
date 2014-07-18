package com.nucleo.entity.medicao;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize = 1, name = "seqJustificativa")
public class Justificativa extends CommomEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqJustificativa")
	private long id;
	
	private BigDecimal diasAtestado;
	
	private BigDecimal diasInjustificado;
	
	private BigDecimal diasFerias;
	
	private BigDecimal diasOutros;

	private BigDecimal faltas;
	
	private BigDecimal diasTrabalhados;
	
	public BigDecimal getDiasTrabalhados() {
		return diasTrabalhados;
	}
	public void setDiasTrabalhados(BigDecimal diasTrabalhados) {
		this.diasTrabalhados = diasTrabalhados;
	}
	private String observacao;
	
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST})
	private MedicaoEquipe medicaoEquipe;

	public BigDecimal getFaltas() {
		return faltas;
	}
	public void setFaltas(BigDecimal faltas) {
		this.faltas = faltas;
	}
	public long getId() {
		return id;
	}
	public String getObservacao() {
		return observacao;
	}

	public MedicaoEquipe getMedicaoEquipe() {
		return medicaoEquipe;
	}

	public void setId(long id) {
		this.id = id;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setMedicaoEquipe(MedicaoEquipe medicaoEquipe) {
		this.medicaoEquipe = medicaoEquipe;
	}
	public BigDecimal getDiasAtestado() {
		return diasAtestado;
	}
	public BigDecimal getDiasInjustificado() {
		return diasInjustificado;
	}
	public BigDecimal getDiasFerias() {
		return diasFerias;
	}
	public BigDecimal getDiasOutros() {
		return diasOutros;
	}
	public void setDiasAtestado(BigDecimal diasAtestado) {
		this.diasAtestado = diasAtestado;
	}
	public void setDiasInjustificado(BigDecimal diasInjustificado) {
		this.diasInjustificado = diasInjustificado;
	}
	public void setDiasFerias(BigDecimal diasFerias) {
		this.diasFerias = diasFerias;
	}
	public void setDiasOutros(BigDecimal diasOutros) {
		this.diasOutros = diasOutros;
	}
}
