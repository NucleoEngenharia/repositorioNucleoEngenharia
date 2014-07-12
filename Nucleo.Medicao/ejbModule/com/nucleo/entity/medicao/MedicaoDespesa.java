package com.nucleo.entity.medicao;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.Projeto;

@Entity
@SequenceGenerator(allocationSize=1, sequenceName="SEQ_MEDICAODESPESA", name="seqMedicaoDespesa")
public class MedicaoDespesa extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMedicaoDespesa")
	private int id;
	
	@ManyToOne
	private Mobilizacao mobilizacao;
	
	@ManyToOne(optional=false)
	private Projeto projeto;
	
	@ManyToOne(optional=false)
	private PeriodoMedicao periodo;
	
	@Temporal(TemporalType.DATE)
	private Calendar periodoDe;
	@Temporal(TemporalType.DATE)
	private Calendar periodoAte;
	
	@Column(precision=18, scale=2)
	private BigDecimal valor;
	
	@Column(length=500)
	private String tipo;
	
	
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
	public Calendar getPeriodoDe() {
		return periodoDe;
	}
	public void setPeriodoDe(Calendar periodoDe) {
		this.periodoDe = periodoDe;
	}
	public Calendar getPeriodoAte() {
		return periodoAte;
	}
	public void setPeriodoAte(Calendar periodoAte) {
		this.periodoAte = periodoAte;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Projeto getProjeto(){
		return projeto;
	}
	public void setProjeto(Projeto projeto){
		this.projeto = projeto;
	}
	public PeriodoMedicao getPeriodo() {
		return periodo;
	}
	public void setPeriodo(PeriodoMedicao periodo) {
		this.periodo = periodo;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof MedicaoDespesa)) return false;
		
		MedicaoDespesa other = (MedicaoDespesa) o;
		if(id == other.getId()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
}
