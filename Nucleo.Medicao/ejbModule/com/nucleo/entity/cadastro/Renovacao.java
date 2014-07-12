package com.nucleo.entity.cadastro;

import java.math.BigDecimal;
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

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize=1, name="seqRenovacao", sequenceName="SEQ_RENOVACAO")
public class Renovacao extends CommomEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqRenovacao")
	private int id;
	
	@ManyToOne(optional=false)
	private Projeto projeto;
	
	@Column(precision=18, scale=2)
	private BigDecimal valor;
	
	private int ano;
	private int mes;
	private int dia;
	private boolean aceitaOutraRenovacao;
	
	@Temporal(TemporalType.DATE)
	private Calendar limiteOutraRenovacao;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public boolean isAceitaOutraRenovacao() {
		return aceitaOutraRenovacao;
	}
	public void setAceitaOutraRenovacao(boolean aceitaOutraRenovacao) {
		this.aceitaOutraRenovacao = aceitaOutraRenovacao;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Calendar getLimiteOutraRenovacao() {
		return limiteOutraRenovacao;
	}
	public void setLimiteOutraRenovacao(Calendar limiteOutraRenovacao) {
		this.limiteOutraRenovacao = limiteOutraRenovacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof Renovacao)) return false;
		
		Renovacao other = (Renovacao) o;
		if(other.getId() == id) return true;
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
	
}
