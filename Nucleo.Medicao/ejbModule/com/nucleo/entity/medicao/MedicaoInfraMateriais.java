package com.nucleo.entity.medicao;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.ItemInfraMateriais;

@Entity
@SequenceGenerator(allocationSize=1, sequenceName="SEQ_MEDICAOINFRAMATERIAIS", name="seqMedicaoInfraMateriais")
public class MedicaoInfraMateriais extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMedicaoInfraMateriais")
	private int id;
	
	@ManyToOne(optional=false)
	private ItemInfraMateriais itemInfraMateriais;
	
	@ManyToOne(optional=false)
	private PeriodoMedicao periodoMedicao;
	
	@Column(precision=9, scale=6)
	private BigDecimal quantidadeMedida;
	
	@Column(precision=18, scale=2)
	private BigDecimal valorMedido;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ItemInfraMateriais getItemInfraMateriais() {
		return itemInfraMateriais;
	}
	public void setItemInfraMateriais(ItemInfraMateriais itemInfraMateriais) {
		this.itemInfraMateriais = itemInfraMateriais;
	}
	public PeriodoMedicao getPeriodoMedicao() {
		return periodoMedicao;
	}
	public void setPeriodoMedicao(PeriodoMedicao periodoMedicao) {
		this.periodoMedicao = periodoMedicao;
	}
	public BigDecimal getQuantidadeMedida() {
		return quantidadeMedida;
	}
	public void setQuantidadeMedida(BigDecimal quantidadeMedida) {
		this.quantidadeMedida = quantidadeMedida;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getValorMedido() {
		return valorMedido;
	}
	public void setValorMedido(BigDecimal valorMedido) {
		this.valorMedido = valorMedido;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof MedicaoInfraMateriais)) return false;
		
		MedicaoInfraMateriais other = (MedicaoInfraMateriais) o;
		if(id == other.getId()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
	
	
}
