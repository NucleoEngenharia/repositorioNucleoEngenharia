package com.nucleo.seguranca.to;

import java.io.Serializable;
import java.util.Date;

public class FuncionarioTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	private int pessoa_id;
	private String senha;
	private String email;
	private Date dt_adm;
	private Date dt_nasc;
	private String ramal;
	private boolean status;
	private String nome;
	private String cpf;
	
	private PerfilTO[] perfis;
	
	public int getPessoa_id() {
		return pessoa_id;
	}
	public void setPessoa_id(int pessoa_id) {
		this.pessoa_id = pessoa_id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDt_adm() {
		return dt_adm;
	}
	public void setDt_adm(Date dt_adm) {
		this.dt_adm = dt_adm;
	}
	public Date getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public PerfilTO[] getPerfis() {
		return perfis;
	}
	public void setPerfis(PerfilTO[] perfis) {
		this.perfis = perfis;
	}

	
	
}
