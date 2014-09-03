package com.nucleo.contratos.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(allocationSize=1,name="detal_atividade", sequenceName="SEQ_ATIVIDADE")
public class DetalhamentoAtividade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="detal_atividade")
	private int id;
	
	@Column(length=500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar hora;
	
	@ManyToOne
	private Atividades atividades;
	
	private String anexo;

	public int getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public Calendar getHora() {
		return hora;
	}

	public Atividades getAtividades() {
		return atividades;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}

	public void setAtividades(Atividades atividades) {
		this.atividades = atividades;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	
}
