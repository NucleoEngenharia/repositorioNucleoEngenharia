package com.nucleo.entity.medicao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.Cargo;

@Entity
@SequenceGenerator(allocationSize=1, sequenceName="SEQ_MOBILIZACAO", name="seqMobilizacao")
public class Mobilizacao extends CommomEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMobilizacao")
	private int id;
	
	@ManyToOne
	private Cargo cargo;
	
	@OneToMany(mappedBy="mobilizacao", targetEntity=MedicaoDespesa.class)
	private List<MedicaoDespesa> despesas;
	
	@OneToMany(mappedBy="mobilizacao", targetEntity=MedicaoEquipe.class)
	private List<MedicaoEquipe> medicoes;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataMobilizacao;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataDesmobilizacao;
	
	@ManyToOne(optional=false)
	private FuncionarioContrato funcionario;
	
	@Transient
	private BigDecimal totalJaMedido;
	
	@OneToMany(mappedBy="mobilizacao", targetEntity=Justificativa.class)
	private List<Justificativa> justificativa;
	
	public List<Justificativa> getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(List<Justificativa> justificativa) {
		this.justificativa = justificativa;
	}
	public BigDecimal getTotalJaMedido(){
		try {
			totalJaMedido = BigDecimal.ZERO;
			for (MedicaoEquipe medicaoEquipe : medicoes) {
				totalJaMedido = totalJaMedido.add(medicaoEquipe.getValorMedido());
			}
		} catch (Exception e) {
			System.out.println("Falta as medições das Equipes na classe Mobilização");
		}
		return totalJaMedido;
	}
	public int getId() {
		return id;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Calendar getDataMobilizacao() {
		return dataMobilizacao;
	}
	public void setDataMobilizacao(Calendar dataMobilizacao) {
		this.dataMobilizacao = dataMobilizacao;
	}
	public Calendar getDataDesmobilizacao() {
		return dataDesmobilizacao;
	}
	public void setDataDesmobilizacao(Calendar dataDesmobilizacao) {
		this.dataDesmobilizacao = dataDesmobilizacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<MedicaoDespesa> getDespesas() {
		return despesas;
	}
	public void setDespesas(List<MedicaoDespesa> despesas) {
		this.despesas = despesas;
	}
	public List<MedicaoEquipe> getMedicoes() {
		return medicoes;
	}
	public void setMedicoes(List<MedicaoEquipe> medicoes) {
		this.medicoes = medicoes;
	}
	public FuncionarioContrato getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioContrato funcionario) {
		this.funcionario = funcionario;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof Mobilizacao)) return false;
		
		Mobilizacao other = (Mobilizacao) o;
		if(other.getId() == id) return true;
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
}
