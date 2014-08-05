package com.nucleo.contratos.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class CommomEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	
	@Column(nullable=false)
	private int usuarioCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataExclusao;
	
	private int usuarioExclusao;
	private boolean excluido;

	public CommomEntity() {
		excluido = false;
	}
	
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public int getUsuarioCriacao() {
		return usuarioCriacao;
	}
	public void setUsuarioCriacao(int usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}
	public Calendar getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(Calendar dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	public int getUsuarioExclusao() {
		return usuarioExclusao;
	}
	public void setUsuarioExclusao(int usuarioExclusao) {
		this.usuarioExclusao = usuarioExclusao;
	}
	public boolean isExcluido() {
		return excluido;
	}
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
