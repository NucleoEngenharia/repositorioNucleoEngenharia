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
@SequenceGenerator(allocationSize=1,name="seqAtividade", sequenceName="SEQ_ATIVIDADE")
public class Atividades extends CommomEntity implements Serializable{
	public Atividades() {
		this.data = Calendar.getInstance();
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqAtividade")
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@Column(length=500)
	private String texto;
	
	@ManyToOne
	private Funcionario funcionario;

	public int getId() {
		return id;
	}

	public Calendar getData() {
		return data;
	}

	public String getTexto() {
		return texto;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
