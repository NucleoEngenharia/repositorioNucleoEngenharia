package com.nucleo.entity.cadastro;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Entity
@SequenceGenerator(allocationSize=1, name="seqUtilizacaoCargo", sequenceName="SEQ_UTILIZACAOCARGO")
public class PrevisaoUso extends CommomEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqUtilizacaoCargo")
	private int id;
	
	@ManyToOne
	private Cargo cargo;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private PeriodoMedicao periodoMedicao;
	
	private BigDecimal quantidade;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public PeriodoMedicao getPeriodoMedicao() {
		return periodoMedicao;
	}
	public void setPeriodoMedicao(PeriodoMedicao periodoMedicao) {
		this.periodoMedicao = periodoMedicao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof PrevisaoUso)) return false;
		
		PrevisaoUso other = (PrevisaoUso) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}	
}
