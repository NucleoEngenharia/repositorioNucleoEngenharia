package com.nucleo.seguranca.to;

import java.io.Serializable;


public class DepartamentoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	
	private UnidadeTO unidade;
	private FuncionarioTO[] funcionarios;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public UnidadeTO getUnidade() {
		return unidade;
	}
	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}
	public FuncionarioTO[] getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(FuncionarioTO[] funcionarios) {
		this.funcionarios = funcionarios;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
