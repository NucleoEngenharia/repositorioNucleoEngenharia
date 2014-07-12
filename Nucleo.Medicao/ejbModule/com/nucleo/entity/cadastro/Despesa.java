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
@SequenceGenerator(allocationSize=1, name="seqDespesa", sequenceName="SEQ_DESPESA")
public class Despesa extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqDespesa")
	private int id;
	
	@ManyToOne(optional=false)
	private Projeto projeto;
	
	@Column(nullable=false, length=1500)
	private String descricao;

	@Column(nullable=false, precision=18, scale=2)
	private BigDecimal valor;

	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object o){
		
		if(o == null) return false;
		if(!(o instanceof Despesa)) return false;
		
		Despesa other = (Despesa) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
	
}
