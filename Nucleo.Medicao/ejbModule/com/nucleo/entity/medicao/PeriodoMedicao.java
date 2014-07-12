package com.nucleo.entity.medicao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;

@Entity
@Cacheable
@SequenceGenerator(allocationSize = 1, sequenceName = "SEQ_PERIODOMEDICAO", name = "seqPeriodoMedicao")
public class PeriodoMedicao extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPeriodoMedicao")
	private int id;

	@ManyToOne
	private Projeto projeto;
	
	@OneToMany(mappedBy = "periodoMedicao", targetEntity = MedicaoInfraMateriais.class)
	private List<MedicaoInfraMateriais> medicoesInfraMateriais;

	@OneToMany(mappedBy = "periodoMedicao", targetEntity = MedicaoEquipe.class)
	private List<MedicaoEquipe> medicoesEquipe;

	@OneToMany(mappedBy = "periodoMedicao", targetEntity = MedicaoProduto.class)
	private List<MedicaoProduto> medicoesProduto;

	@OneToMany(mappedBy = "periodoMedicao", targetEntity = PrevisaoUso.class)
	private List<PrevisaoUso> previsaoUso;

	@OneToMany(mappedBy = "periodoMedicao", targetEntity = AlteracaoPeriodoMedicao.class)
	private Set<AlteracaoPeriodoMedicao> alteracoes;
	
	@OneToOne(mappedBy="periodoMedicao", fetch=FetchType.LAZY,targetEntity=DetalhamentoPeriodoMedicao.class,
			  cascade={CascadeType.ALL,CascadeType.MERGE})
	private DetalhamentoPeriodoMedicao detalhamentoPeriodoMedicao;

	@Temporal(TemporalType.DATE)
	private Calendar dataDe;

	@Temporal(TemporalType.DATE)
	private Calendar dataAte;

	@Column(length = 500)
	private String descricao;
    
	private int numSequencial;
	private StatusPeriodoEnum status;
	private BigDecimal baseCalculo;
	
	public DetalhamentoPeriodoMedicao getDetalhamentoPeriodoMedicao() {
		return detalhamentoPeriodoMedicao;
	}
	public void setDetalhamentoPeriodoMedicao(DetalhamentoPeriodoMedicao detalhamentoPeriodoMedicao) {
		this.detalhamentoPeriodoMedicao = detalhamentoPeriodoMedicao;
	}

	@Transient
	private String anoMesPeriodo;
	
	public int getNumSequencial() {
		return numSequencial;
	}
	public void setNumSequencial(int numSequencial) {
		this.numSequencial = numSequencial;
	}
	public PeriodoMedicao() {
		status = StatusPeriodoEnum.EMABERTO;
	}
	public String getAnoMesPeriodo(){
		SimpleDateFormat format = new SimpleDateFormat("MMMM/yyyy");
		anoMesPeriodo = format.format(dataAte.getTime());
		return anoMesPeriodo;
	}
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
	public List<MedicaoEquipe> getMedicoesEquipe() {
		return medicoesEquipe;
	}
	public void setMedicoesEquipe(List<MedicaoEquipe> medicoesEquipe) {
		this.medicoesEquipe = medicoesEquipe;
	}
	public List<MedicaoProduto> getMedicoesProduto() {
		return medicoesProduto;
	}
	public void setMedicoesProduto(List<MedicaoProduto> medicoesProduto) {
		this.medicoesProduto = medicoesProduto;
	}
	public List<PrevisaoUso> getPrevisaoUso() {
		return previsaoUso;
	}
	public void setPrevisaoUso(List<PrevisaoUso> previsaoUso) {
		this.previsaoUso = previsaoUso;
	}
	public Calendar getDataDe() {
		return dataDe;
	}
	public void setDataDe(Calendar dataDe) {
		this.dataDe = dataDe;
	}
	public Calendar getDataAte() {
		return dataAte;
	}
	public void setDataAte(Calendar dataAte) {
		this.dataAte = dataAte;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getBaseCalculo() {
		return baseCalculo;
	}
	public void setBaseCalculo(BigDecimal baseCalculo) {
		this.baseCalculo = baseCalculo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public StatusPeriodoEnum getStatus() {
		return status;
	}
	public void setStatus(StatusPeriodoEnum status) {
		this.status = status;
	}
	public List<MedicaoInfraMateriais> getMedicoesInfraMateriais() {
		return medicoesInfraMateriais;
	}
	public void setMedicoesInfraMateriais(
			List<MedicaoInfraMateriais> medicoesInfraMateriais) {
		this.medicoesInfraMateriais = medicoesInfraMateriais;
	}
	public Set<AlteracaoPeriodoMedicao> getAlteracoes() {
		return alteracoes;
	}
	public void setAlteracoes(Set<AlteracaoPeriodoMedicao> alteracoes) {
		this.alteracoes = alteracoes;
	}
	
	
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof PeriodoMedicao))
			return false;

		PeriodoMedicao other = (PeriodoMedicao) o;
		if (other.getId() == id) {
			return true;
		}

		return false;
	}

	public int hashCode() {
		return id * 32;
	}
}
