package com.nucleo.entity.cadastro;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize=1, name="seqInfraMateriais", sequenceName="SEQ_INFRAMATERIAIS")
public class InfraMateriais extends CommomEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqInfraMateriais")
	private int id;
	
	@ManyToOne(optional=false)
	private Projeto projeto;
	
	@OneToMany(mappedBy="infraMateriais", targetEntity=ItemInfraMateriais.class)
	private List<ItemInfraMateriais> itens;
	
	@Column(length=1500)
	private String descricao;
	
	@Column(precision=18, scale=2)
	private BigDecimal valor;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ItemInfraMateriais> getItens() {
		return itens;
	}
	public void setItens(List<ItemInfraMateriais> itens) {
		this.itens = itens;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof InfraMateriais)) return false;
		
		InfraMateriais other = (InfraMateriais) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
	
}
