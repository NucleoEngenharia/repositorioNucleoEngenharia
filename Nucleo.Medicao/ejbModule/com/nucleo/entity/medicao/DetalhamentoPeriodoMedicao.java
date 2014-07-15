package com.nucleo.entity.medicao;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize=1, name="SeqDetalPeriodoMed", sequenceName="SEQ_DETALHAMENTO_PERIODO_MEDICAO")
public class DetalhamentoPeriodoMedicao extends CommomEntity {
	public DetalhamentoPeriodoMedicao() {
		totalMedicaoI0 = BigDecimal.ZERO;
		totalReajuste = BigDecimal.ZERO;
		medicaoComReajuste = BigDecimal.ZERO;
		totalValorVenda = BigDecimal.ZERO;
		totalDespesa = BigDecimal.ZERO;
		totalSalarios = BigDecimal.ZERO;
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SeqDetalPeriodoMed")
	private int id;
	
	@OneToOne
	private PeriodoMedicao periodoMedicao;
	
	@Column(precision=18, scale=2)
	private BigDecimal totalMedicaoI0;
	
	@Column(precision=18, scale=2)
	private BigDecimal totalReajuste;
	
	@Column(precision=18, scale=2)
	private BigDecimal medicaoComReajuste;
	
	@Column(precision=18, scale=2)
	private BigDecimal totalDespesa;
	
	@Column(precision=18, scale=2)
	private BigDecimal totalSalarios;
	
	@Column(precision=18,scale=2)
	private BigDecimal totalValorVenda;

	public int getId() {
		return id;
	}

	public PeriodoMedicao getPeriodoMedicao() {
		return periodoMedicao;
	}

	public BigDecimal getTotalMedicaoI0() {
		return totalMedicaoI0;
	}

	public BigDecimal getTotalReajuste() {
		return totalReajuste;
	}

	public BigDecimal getMedicaoComReajuste() {
		return medicaoComReajuste;
	}

	public BigDecimal getTotalDespesa() {
		return totalDespesa;
	}

	public BigDecimal getTotalSalarios() {
		return totalSalarios;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPeriodoMedicao(PeriodoMedicao periodoMedicao) {
		this.periodoMedicao = periodoMedicao;
	}

	public void setTotalMedicaoI0(BigDecimal totalMedicaoI0) {
		this.totalMedicaoI0 = totalMedicaoI0;
	}

	public void setTotalReajuste(BigDecimal totalReajuste) {
		this.totalReajuste = totalReajuste;
	}

	public void setMedicaoComReajuste(BigDecimal medicaoComReajuste) {
		this.medicaoComReajuste = medicaoComReajuste;
	}

	public void setTotalDespesa(BigDecimal totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

	public void setTotalSalarios(BigDecimal totalSalarios) {
		this.totalSalarios = totalSalarios;
	}

	public BigDecimal getTotalValorVenda() {
		return totalValorVenda;
	}

	public void setTotalValorVenda(BigDecimal totalValorVenda) {
		this.totalValorVenda = totalValorVenda;
	}
	
	
}
