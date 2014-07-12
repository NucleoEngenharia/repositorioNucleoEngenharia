package com.nucleo.entity.log;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(allocationSize = 1, name = "seqProjetoLog", sequenceName = "SEQ_PROJETOLOG")
public class LogAlteracao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProjetoLog")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

	private int idRegistro;
	private String tabela;
	private String coluna;
	
	@Column(length=3000)
	private String valorAntigo;
	
	@Column(length=3000)
	private String valorNovo;
	
	private int usuarioAlteracao;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getColuna() {
		return coluna;
	}
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	public String getValorAntigo() {
		return valorAntigo;
	}
	public void setValorAntigo(String valorAntigo) {
		this.valorAntigo = valorAntigo;
	}
	public String getValorNovo() {
		return valorNovo;
	}
	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}
	public int getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(int usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

}
