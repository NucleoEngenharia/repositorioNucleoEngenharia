package com.nucleo.seguranca.to;

import java.io.Serializable;

public class UnidadeTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	
	private DepartamentoTO[] departamentos;

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
	public DepartamentoTO[] getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(DepartamentoTO[] departamentos) {
		this.departamentos = departamentos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
