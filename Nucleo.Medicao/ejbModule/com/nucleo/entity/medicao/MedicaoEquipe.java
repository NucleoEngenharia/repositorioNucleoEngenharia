package com.nucleo.entity.medicao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize = 1, sequenceName = "SEQ_MEDICAOEQUIPE", name = "seqMedicaoEquipe")
public class MedicaoEquipe extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMedicaoEquipe")
	private int id;
	@ManyToOne(optional=false)
	private Mobilizacao mobilizacao;
	@ManyToOne(optional=false)
	private PeriodoMedicao periodoMedicao;
	private BigDecimal quantidadeMedido;

	@Column(precision=18, scale=2)
	private BigDecimal valorMedido;

	public BigDecimal getValorMedido() {
		if (quantidadeMedido.equals(BigDecimal.ZERO))
			return BigDecimal.ZERO;
		if (mobilizacao == null)
			return BigDecimal.ZERO;
		if (periodoMedicao == null)
			return BigDecimal.ZERO;
		if (periodoMedicao.getBaseCalculo().equals(BigDecimal.ZERO))
			return BigDecimal.ZERO;

		BigDecimal qtdMedida = quantidadeMedido
				.divide(periodoMedicao.getBaseCalculo(), 4, RoundingMode.CEILING);
		valorMedido =  mobilizacao
				.getCargo()
				.getValorVenda()
				.multiply(qtdMedida, MathContext.UNLIMITED);
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
	public Mobilizacao getMobilizacao() {
		return mobilizacao;
	}
	public void setMobilizacao(Mobilizacao mobilizacao) {
		this.mobilizacao = mobilizacao;
	}
	public PeriodoMedicao getPeriodoMedicao() {
		return periodoMedicao;
	}
	public void setPeriodoMedicao(PeriodoMedicao periodoMedicao) {
		this.periodoMedicao = periodoMedicao;
	}
	public BigDecimal getQuantidadeMedido() {
		return quantidadeMedido;
	}
	public void setQuantidadeMedido(BigDecimal quantidadeMedido) {
		this.quantidadeMedido = quantidadeMedido;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof MedicaoEquipe)) return false;
		
		MedicaoEquipe other = (MedicaoEquipe) o;
		if(id == other.getId()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
}
