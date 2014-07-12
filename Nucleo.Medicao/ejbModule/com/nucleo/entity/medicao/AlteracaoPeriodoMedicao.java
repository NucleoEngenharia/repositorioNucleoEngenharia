package com.nucleo.entity.medicao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.medicao.Enum.MotivoAlteracaoPeriodoEnum;

@Entity
@SequenceGenerator(allocationSize=1, sequenceName="SEQ_ALTERACAOPERIODO", name="seqAlteracaoPeriodo")
public class AlteracaoPeriodoMedicao extends CommomEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqAlteracaoPeriodo")
	private int id;
	@Column(length=2000)
	private String observacao;
	private MotivoAlteracaoPeriodoEnum motivo;
	
	@ManyToOne
	private PeriodoMedicao periodoMedicao;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public MotivoAlteracaoPeriodoEnum getMotivo(){
		return motivo;
	}
	public void setMotivo(MotivoAlteracaoPeriodoEnum motivo){
		this.motivo = motivo;
	}
	public String getObservacao(){
		return observacao;
	}
	public void setObservacao(String observacao){
		this.observacao = observacao;
	}
	public static long getSerialversionuid(){
		return serialVersionUID;
	}
	public PeriodoMedicao getPeriodoMedicao(){
		return periodoMedicao;
	}
	public void setPeriodoMedicao(PeriodoMedicao periodoMedicao){
		this.periodoMedicao = periodoMedicao;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof AlteracaoPeriodoMedicao)) return false;
		
		AlteracaoPeriodoMedicao other = (AlteracaoPeriodoMedicao) o;
		if(id == other.getId()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
}
