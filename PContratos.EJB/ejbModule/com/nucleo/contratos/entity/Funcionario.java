package com.nucleo.contratos.entity;

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

@Entity
@SequenceGenerator(allocationSize=1, name="seqFuncionario", sequenceName="SEQ_FUNC")
public class Funcionario extends CommomEntity {
	private static final long serialVersionUID = 1L;

	public Funcionario() {
		primeiroAcesso=true;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seqFuncionario")
	private int id;
	
	private String matricula;
	
	private String senha;
	
	private int cn;
	
	private String nome;
	
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Calendar dtAdmissao;
	
	@Temporal(TemporalType.DATE)
	private Calendar dtDemissao;
	
	private boolean primeiroAcesso;
	
	@OneToMany(mappedBy="funcionario", targetEntity=ControleDeHorarios.class)
	private List<ControleDeHorarios>controleDeHorarios;
	
	@OneToMany(mappedBy="funcionario", targetEntity=Atividades.class)
	private List<Atividades>atividades;

	public int getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public int getCn() {
		return cn;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public Calendar getDtAdmissao() {
		return dtAdmissao;
	}

	public Calendar getDtDemissao() {
		return dtDemissao;
	}

	public boolean isPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setCn(int cn) {
		this.cn = cn;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDtAdmissao(Calendar dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public void setDtDemissao(Calendar dtDemissao) {
		this.dtDemissao = dtDemissao;
	}

	public void setPrimeiroAcesso(boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	public List<ControleDeHorarios> getControleDeHorarios() {
		return controleDeHorarios;
	}

	public void setControleDeHorarios(List<ControleDeHorarios> controleDeHorarios) {
		this.controleDeHorarios = controleDeHorarios;
	}

	public List<Atividades> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividades> atividades) {
		this.atividades = atividades;
	}
	
	
}
