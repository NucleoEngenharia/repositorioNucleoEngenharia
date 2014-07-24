package com.nucleo.entity.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.Enum.AtividadeEnum;
import com.nucleo.entity.cadastro.Enum.SetorEnum;
import com.nucleo.entity.cadastro.Enum.StatusProjetoEnum;
import com.nucleo.entity.cadastro.Enum.TipoCalculoEnum;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Entity
@SequenceGenerator(allocationSize = 1, name = "seqProjeto", sequenceName = "SEQ_PROJETO")
public class Projeto extends CommomEntity {

	public Projeto() {

		locaisExecucao = new ArrayList<LocalExecucao>();
		impostos = new ArrayList<Imposto>();
		aditivos = new ArrayList<Aditivo>();
		renovacoes = new ArrayList<Renovacao>();
		servicos = new ArrayList<Servico>();
		infraMateriais = new ArrayList<InfraMateriais>();
		periodosMedicao = new ArrayList<PeriodoMedicao>();
		medicaoDespesas = new ArrayList<MedicaoDespesa>();
		responsavelAdm = new ResponsavelAdministracao();

		limiteDespesa = true;
		despesaIntegraValor = true;
		despesaTemImposto = false;
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqProjeto", strategy = GenerationType.AUTO)
	private int id;

	@OneToMany(mappedBy = "projeto", targetEntity = Reajuste.class)
	private Set<Reajuste> reajustes;

	@OneToMany(mappedBy = "projeto", targetEntity = Despesa.class)
	private List<Despesa> despesas;

	@OneToMany(mappedBy = "projeto", targetEntity = MedicaoDespesa.class)
	private List<MedicaoDespesa> medicaoDespesas;

	@OneToMany(mappedBy = "projeto", targetEntity = PeriodoMedicao.class)
	private Set<PeriodoMedicao> medicoes;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="PROJETO_IMPOSTO",
			joinColumns=@JoinColumn(name="idProjeto"),
			inverseJoinColumns=@JoinColumn(name="idImposto"))
	@Fetch(FetchMode.JOIN)
	private List<Imposto> impostos;

	@OneToMany(mappedBy = "projeto", targetEntity = LocalExecucao.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<LocalExecucao> locaisExecucao;

	@OneToMany(mappedBy = "projeto", targetEntity = Aditivo.class)
	private List<Aditivo> aditivos;

	@OneToMany(mappedBy = "projeto", targetEntity = Renovacao.class)
	private List<Renovacao> renovacoes;

	@OneToMany(mappedBy = "projeto", targetEntity = Servico.class)
	private List<Servico> servicos;

	@OneToMany(mappedBy = "projeto", targetEntity = InfraMateriais.class)
	private List<InfraMateriais> infraMateriais;

	@OneToMany(mappedBy = "projeto", targetEntity = PeriodoMedicao.class)
	private List<PeriodoMedicao> periodosMedicao;

	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;

	@Temporal(TemporalType.DATE)
	private Calendar dataFim;

	@Temporal(TemporalType.DATE)
	private Calendar dataAberturaOIS;

	@Temporal(TemporalType.DATE)
	private Calendar dataFechamentoOIS;

	@Column(precision = 18, scale = 2)
	private BigDecimal valorOriginal;

	@Temporal(TemporalType.DATE)
	private Calendar dataInicialMedicao;

	@OneToOne(fetch=FetchType.LAZY, 
			mappedBy = "projeto", targetEntity = ResponsavelAdministracao.class,
			cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	@Fetch(FetchMode.JOIN)
	private ResponsavelAdministracao responsavelAdm;

	private String CN;
	
	@Column(length=500)
	private String objeto;

	@Column(length = 500)
	private String cNCliente;

	@Column(length = 1500)
	private String descricao;
	@Transient
	private String descricaoCurta;
	
	@Column(length = 1500)
	private String medicaoObs;

	private boolean limiteDespesa;
	private boolean despesaIntegraValor;
	private boolean despesaTemImposto;

	private boolean permiteRenovacao;

	@Temporal(TemporalType.DATE)
	private Calendar dataLimiteRenovacao;

	// formato (DD/MM)
	@Temporal(TemporalType.DATE)
	private Calendar dataLimiteReajuste;

	private StatusProjetoEnum status = StatusProjetoEnum.ATIVO;
	private SetorEnum setor;
	private AtividadeEnum atividade;
	private TipoCalculoEnum calculo = TipoCalculoEnum.SIMPLES;
	
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<InfraMateriais> getInfraMateriais() {
		return infraMateriais;
	}
	public void setInfraMateriais(List<InfraMateriais> infraMateriais) {
		this.infraMateriais = infraMateriais;
	}
	public Set<PeriodoMedicao> getMedicoes() {
		return medicoes;
	}
	public void setMedicoes(Set<PeriodoMedicao> medicoes) {
		this.medicoes = medicoes;
	}
	public ResponsavelAdministracao getResponsavelAdm() {
		return responsavelAdm;
	}
	public void setResponsavelAdm(ResponsavelAdministracao responsavelAdm) {
		this.responsavelAdm = responsavelAdm;
	}
	public Calendar getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Calendar getDataFim() {
		return dataFim;
	}
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	public Calendar getDataFechamentoOIS() {
		return dataFechamentoOIS;
	}
	public void setDataFechamentoOIS(Calendar dataFechamentoOIS) {
		this.dataFechamentoOIS = dataFechamentoOIS;
	}
	public void setDataAberturaOIS(Calendar dataAberturaOIS) {
		this.dataAberturaOIS = dataAberturaOIS;
	}
	public void setDataLimiteRenovacao(Calendar dataLimiteRenovacao) {
		this.dataLimiteRenovacao = dataLimiteRenovacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	public String getCNCliente() {
		return cNCliente;
	}
	public void setCNCliente(String cNCliente) {
		this.cNCliente = cNCliente;
	}
	public String getMedicaoObs() {
		return medicaoObs;
	}
	public void setMedicaoObs(String medicaoObs) {
		this.medicaoObs = medicaoObs;
	}
	public boolean isPermiteRenovacao() {
		return permiteRenovacao;
	}
	public void setPermiteRenovacao(boolean permiteRenovacao) {
		this.permiteRenovacao = permiteRenovacao;
	}
	public StatusProjetoEnum getStatus() {
		return status;
	}
	public void setStatus(StatusProjetoEnum status) {
		this.status = status;
	}
	public SetorEnum getSetor() {
		return setor;
	}
	public void setSetor(SetorEnum setor) {
		this.setor = setor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public AtividadeEnum getAtividade() {
		return atividade;
	}
	public void setAtividade(AtividadeEnum atividade) {
		this.atividade = atividade;
	}
	public Calendar getDataInicialMedicao() {
		return dataInicialMedicao;
	}
	public void setDataInicialMedicao(Calendar dataInicialMedicao) {
		this.dataInicialMedicao = dataInicialMedicao;
	}
	public Calendar getDataAberturaOIS() {
		return dataAberturaOIS;
	}
	public Calendar getDataLimiteRenovacao() {
		return dataLimiteRenovacao;
	}
	public boolean isLimiteDespesa() {
		return limiteDespesa;
	}
	public void setLimiteDespesa(boolean limiteDespesa) {
		this.limiteDespesa = limiteDespesa;
	}
	public List<Despesa> getDespesas() {
		return despesas;
	}
	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}
	public List<MedicaoDespesa> getMedicaoDespesas() {
		return medicaoDespesas;
	}
	public void setMedicaoDespesas(List<MedicaoDespesa> medicaoDespesas) {
		this.medicaoDespesas = medicaoDespesas;
	}
	public List<Imposto> getImpostos() {
		return impostos;
	}
	public void setImpostos(List<Imposto> impostos) {
		this.impostos = impostos;
	}
	public List<LocalExecucao> getLocaisExecucao() {
		return locaisExecucao;
	}
	public void setLocaisExecucao(List<LocalExecucao> locaisExecucao) {
		this.locaisExecucao = locaisExecucao;
	}
	public List<Aditivo> getAditivos() {
		return aditivos;
	}
	public void setAditivos(List<Aditivo> aditivos) {
		this.aditivos = aditivos;
	}
	public List<Renovacao> getRenovacoes() {
		return renovacoes;
	}
	public void setRenovacoes(List<Renovacao> renovacoes) {
		this.renovacoes = renovacoes;
	}
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public List<PeriodoMedicao> getPeriodosMedicao() {
		return periodosMedicao;
	}
	public void setPeriodosMedicao(List<PeriodoMedicao> periodosMedicao) {
		this.periodosMedicao = periodosMedicao;
	}
	public boolean isDespesaIntegraValor(){
		return despesaIntegraValor;
	}
	public void setDespesaIntegraValor(boolean despesaIntegraValor){
		this.despesaIntegraValor = despesaIntegraValor;
	}
	public boolean isDespesaTemImposto(){
		return despesaTemImposto;
	}
	public void setDespesaTemImposto(boolean despesaTemImposto){
		this.despesaTemImposto = despesaTemImposto;
	}
	public TipoCalculoEnum getCalculo() {
		return calculo;
	}
	public void setCalculo(TipoCalculoEnum calculo) {
		this.calculo = calculo;
	}
	public Set<Reajuste> getReajustes() {
		return reajustes;
	}
	public void setReajustes(Set<Reajuste> reajustes) {
		this.reajustes = reajustes;
	}
	public String getDescricaoCurta() {
		descricaoCurta = descricao;
		if(descricao.length() > 30){
			descricaoCurta = descricaoCurta.substring(0, 30);
		}
		return descricaoCurta;
	}
	public Calendar getDataLimiteReajuste() {
		return dataLimiteReajuste;
	}
	public void setDataLimiteReajuste(Calendar dataLimiteReajuste) {
		this.dataLimiteReajuste = dataLimiteReajuste;
	}
	@Override
	public boolean equals(Object o){
		if(o == null)return false;
		if(!(o instanceof Projeto)) return false;
		
		Projeto other = (Projeto) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}	
	
	
}
