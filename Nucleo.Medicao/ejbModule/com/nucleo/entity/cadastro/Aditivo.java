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
import com.nucleo.entity.cadastro.Enum.TipoAditivoEnum;

@Entity
@SequenceGenerator(allocationSize=1, name="seqAditivo", sequenceName="SEQ_ADITIVO")
public class Aditivo extends CommomEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqAditivo")
	private int id;

	@ManyToOne(optional=false)
	private Projeto projeto;
	
	@Column(nullable=false)
	private TipoAditivoEnum tipo;
	
	@Column(precision=18, scale=2)
	private BigDecimal valor;
	
	@Column(precision=9, scale=6)
	private BigDecimal porcentagem;
	
	@Column(length=1000)
	private String observacao;
	
	private int ano;
	private int mes;
	private int dia;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoAditivoEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoAditivoEnum tipo) {
		this.tipo = tipo;
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
	public BigDecimal getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null)return false;
		if(!(o instanceof Aditivo)) return false;
		
		Aditivo other = (Aditivo) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
	
}
