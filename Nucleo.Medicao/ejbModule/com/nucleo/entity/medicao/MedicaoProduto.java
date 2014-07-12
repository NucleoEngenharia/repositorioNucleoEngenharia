package com.nucleo.entity.medicao;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.Produto;

@Entity
@SequenceGenerator(allocationSize=1, sequenceName="SEQ_MEDICAOPRODUTO", name="seqMedicaoProduto")
public class MedicaoProduto extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMedicaoProduto")
	private int id;
	
	@ManyToOne(optional=false)
	private Produto produto;
	
	@ManyToOne(optional=false)
	private PeriodoMedicao periodoMedicao;
	
	private BigDecimal quantidadeMedida;

	private boolean grupo;
	
	@Column(precision=18, scale=2)
	private BigDecimal valorMedido;
	
	public BigDecimal getValorMedido(){
		if(produto == null) return BigDecimal.ZERO;
		if(produto.isGrupo()) return null;
		if(produto.getValorVenda().equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
		if(quantidadeMedida == null || quantidadeMedida.equals(BigDecimal.ZERO)) return BigDecimal.ZERO; 
		
		valorMedido = quantidadeMedida.multiply(produto.getValorVenda(), MathContext.UNLIMITED);
		return valorMedido;
	}
	public void setValorMedido(BigDecimal valorMedido) {
		this.valorMedido = valorMedido;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public BigDecimal getQuantidadeMedida(){
		return quantidadeMedida;
	}
	public void setQuantidadeMedida(BigDecimal quantidadeMedida){
		this.quantidadeMedida = quantidadeMedida;
	}
	public static long getSerialversionuid(){
		return serialVersionUID;
	}
	public boolean isGrupo(){
		return grupo;
	}
	public void setGrupo(boolean grupo){
		this.grupo = grupo;
	}
	
	
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof MedicaoProduto)) return false;
		
		MedicaoProduto other = (MedicaoProduto) o;
		if(id == other.getId()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
}
