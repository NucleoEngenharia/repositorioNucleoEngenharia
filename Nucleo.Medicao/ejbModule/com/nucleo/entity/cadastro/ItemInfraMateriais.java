package com.nucleo.entity.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.nucleo.entity.medicao.MedicaoInfraMateriais;

@Entity
@SequenceGenerator(allocationSize=1, name="seqItemInfraMateriais", sequenceName="SEQ_ITEMINFRAMATERIAIS")
public class ItemInfraMateriais extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqItemInfraMateriais")
	private int id;
	
	@ManyToOne
	private InfraMateriais infraMateriais;
	
	@OneToMany(mappedBy="itemInfraMateriais", targetEntity=MedicaoInfraMateriais.class)
	private List<MedicaoInfraMateriais> medicoesInfraMateriais;
	
	@Column(precision=18, scale=2)
	private BigDecimal valor;
	
	@Column(precision=9, scale=6)
	private BigDecimal porcentagem;

	@Column(length=1500)
	private String descricao;
	
	
	public ItemInfraMateriais(){
		medicoesInfraMateriais = new ArrayList<MedicaoInfraMateriais>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public InfraMateriais getInfraMateriais() {
		return infraMateriais;
	}
	public void setInfraMateriais(InfraMateriais infraMateriais) {
		this.infraMateriais = infraMateriais;
	}
	public List<MedicaoInfraMateriais> getMedicoesInfraMateriais() {
		return medicoesInfraMateriais;
	}
	public void setMedicoesInfraMateriais(
			List<MedicaoInfraMateriais> medicoesInfraMateriais) {
		this.medicoesInfraMateriais = medicoesInfraMateriais;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof ItemInfraMateriais)) return false;
		
		ItemInfraMateriais other = (ItemInfraMateriais) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}	
	
}
