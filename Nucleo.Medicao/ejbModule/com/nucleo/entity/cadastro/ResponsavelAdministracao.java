package com.nucleo.entity.cadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import com.nucleo.entity.CommomEntity;

@Entity
public class ResponsavelAdministracao extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int idResponsavel;
	
	@Column(length=500)
	private String nomeProposto;
	
	@Column(length=500)
	private String nomeSupervisor;
	
	@Column(length=500)
	private String nomeGerenteNucleo;
	
	@Column(length=500)
	private String nomeGerenteRelacionamento;
	
	@Column(length = 500)
	private String fiscal;

	private int idUnidade;
	
	@Column(length=500)
	private String nomeUnidade;

	@OneToOne
	private Projeto projeto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNomeSupervisor() {
		return nomeSupervisor;
	}

	public void setNomeSupervisor(String nomeSupervisor) {
		this.nomeSupervisor = nomeSupervisor;
	}
	public String getNomeGerenteRelacionamento() {
		return nomeGerenteRelacionamento;
	}

	public String getFiscal() {
		return fiscal;
	}
	public void setNomeGerenteRelacionamento(String nomeGerenteRelacionamento) {
		this.nomeGerenteRelacionamento = nomeGerenteRelacionamento;
	}

	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}
	public int getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(int idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getNomeGerenteNucleo() {
		return nomeGerenteNucleo;
	}

	public void setNomeGerenteNucleo(String nomeGerenteNucleo) {
		this.nomeGerenteNucleo = nomeGerenteNucleo;
	}

	public String getNomeProposto() {
		return nomeProposto;
	}

	public void setNomeProposto(String nomeProposto) {
		this.nomeProposto = nomeProposto;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(int idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	@Override
    public boolean equals(Object o) {
		if(o == null) return false;
	    if(!(o instanceof ResponsavelAdministracao)) return false;

	    ResponsavelAdministracao other = (ResponsavelAdministracao) o;
	    if(other.getId() != id) return false;
	    if(other.getIdUnidade() != idUnidade) return false;
	    if(other.getIdResponsavel() != idResponsavel) return false;
	    
	    if(nomeProposto != null && !nomeProposto.equals(other.getNomeProposto())) return false;
	    if(nomeUnidade != null && !nomeUnidade.equals(other.getNomeUnidade())) return false;

	    return true;
    }
	
	public String identificationToLog(){
		return "Unidade: " + idUnidade + "|" + nomeUnidade + "; Responsável: " + idResponsavel + "|" + nomeProposto; 
	}
	
}
