package com.nucleo.entity.cadastro;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize=1, sequenceName="SEQ_REAJUSTE", name="seqReajuste")
public class Reajuste extends CommomEntity{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	private Projeto projeto;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqReajuste")
	private int id;
	
	@Column(nullable=false, precision=18, scale=6)
	private BigDecimal indice = BigDecimal.ONE;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public int getId() {
		return id;
	}
	public BigDecimal getIndice() {
		return indice;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIndice(BigDecimal indice) {
		this.indice = indice;
	}
}
