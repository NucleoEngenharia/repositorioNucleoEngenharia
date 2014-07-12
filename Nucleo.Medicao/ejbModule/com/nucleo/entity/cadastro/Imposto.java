package com.nucleo.entity.cadastro;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize=1, name="seqImposto", sequenceName="SEQ_IMPOSTO")
public class Imposto extends CommomEntity {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqImposto")
	private int id;
	
	@Column(length=1500)
	private String descricao;
	
	@Column(precision=5, scale=2)
	private BigDecimal taxa;
	
	private String identificacaoSAP;
	
	private String inativo;

	public Imposto(){
		inativo = "N";
	}
	
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
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
    public String getIdentificacaoSAP() {
		return identificacaoSAP;
	}
	public void setIdentificacaoSAP(String identificacaoSAP) {
		this.identificacaoSAP = identificacaoSAP;
	}
	public String getInativo() {
		return inativo;
	}
	public void setInativo(String inativo) {
		this.inativo = inativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
    public boolean equals(Object o) {
        if(o instanceof Imposto && o != null){
        	Imposto other = (Imposto) o;
            if(descricao.equals(other.getDescricao()) 
	        		&& other.getId() == id
	        		&& other.getTaxa().equals(taxa))
            	return true;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
    	return id * 32;
    }
    
}
