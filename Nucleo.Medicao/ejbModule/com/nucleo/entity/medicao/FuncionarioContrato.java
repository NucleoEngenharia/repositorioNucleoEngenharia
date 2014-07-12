package com.nucleo.entity.medicao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize=1, sequenceName="SEQ_FUNCIONARIOCONTRATO", name="seqFuncionarioContrato")
public class FuncionarioContrato extends CommomEntity {

	public FuncionarioContrato() {
		pj = false;
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqFuncionarioContrato")
	private int id;
	
	private boolean pj;
	private String cnpj;
	private String nomeEmpresa;
	
	private String nomeCompleto;
	private String cpf;
	private BigDecimal salario;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataAdmissao;
	@Temporal(TemporalType.DATE)
	private Calendar dataRescisao;
	private int cn;
	
	@OneToMany(mappedBy="funcionario", targetEntity=Mobilizacao.class)
	List<Mobilizacao> mobilizacoes;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public Calendar getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Calendar dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Calendar getDataRescisao() {
		return dataRescisao;
	}
	public void setDataRescisao(Calendar dataRescisao) {
		this.dataRescisao = dataRescisao;
	}
	public int getCn() {
		return cn;
	}
	public void setCn(int cn) {
		this.cn = cn;
	}
	public List<Mobilizacao> getMobilizacoes() {
		return mobilizacoes;
	}
	public void setMobilizacoes(List<Mobilizacao> mobilizacoes) {
		this.mobilizacoes = mobilizacoes;
	} 
	public boolean isPj() {
		return pj;
	}
	public String getCnpj() {
		return cnpj;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setPj(boolean pj) {
		this.pj = pj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
	
	
	
	@Override
	public boolean equals(Object o){
		if(o == null)return false;
		if(!(o instanceof FuncionarioContrato)) return false;
		
		FuncionarioContrato other = (FuncionarioContrato) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}	
}
