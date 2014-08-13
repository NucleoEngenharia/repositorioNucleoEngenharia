package com.nucleo.contratos.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(allocationSize=1, name="seqCDH", sequenceName="SEQ_CDH")
public class ControleDeHorarios implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seqCDH")
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@ManyToOne
	private Funcionario funcionario;
	
	private String rangeChegada;
	
	private String rangeVoltaAlmoco;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar hrChegada;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar hrSaidaAlmoco;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar hrChegadaAlmoco;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar hrSaida;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar tempoTrabalhado;
	
	private String justificativa;

	public int getId() {
		return id;
	}

	public Calendar getData() {
		return data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Calendar getHrChegada() {
		return hrChegada;
	}

	public Calendar getHrSaidaAlmoco() {
		return hrSaidaAlmoco;
	}

	public Calendar getHrChegadaAlmoco() {
		return hrChegadaAlmoco;
	}

	public Calendar getHrSaida() {
		return hrSaida;
	}
	
	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setHrChegada(Calendar hrChegada) {
		this.hrChegada = hrChegada;
	}

	public void setHrSaidaAlmoco(Calendar hrSaidaAlmoco) {
		this.hrSaidaAlmoco = hrSaidaAlmoco;
	}

	public void setHrChegadaAlmoco(Calendar hrChegadaAlmoco) {
		this.hrChegadaAlmoco = hrChegadaAlmoco;
	}

	public void setHrSaida(Calendar hrSaida) {
		this.hrSaida = hrSaida;
	}

	public Calendar getTempoTrabalhado() {
		return tempoTrabalhado;
	}

	public void setTempoTrabalhado(Calendar tempoTrabalhado) {
		this.tempoTrabalhado = tempoTrabalhado;
	}

	public String getRangeChegada() {
		return rangeChegada;
	}

	public String getRangeVoltaAlmoco() {
		return rangeVoltaAlmoco;
	}

	public void setRangeChegada(String rangeChegada) {
		this.rangeChegada = rangeChegada;
	}

	public void setRangeVoltaAlmoco(String rangeVoltaAlmoco) {
		this.rangeVoltaAlmoco = rangeVoltaAlmoco;
	}
	
	

}
