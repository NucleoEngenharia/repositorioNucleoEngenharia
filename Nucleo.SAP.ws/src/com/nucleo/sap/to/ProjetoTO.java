package com.nucleo.sap.to;

import java.math.BigDecimal;
import java.util.Date;

public class ProjetoTO {

	private String CN; // PrjCode
	private String nome; // PrjName
	private Date dtInicio; // U_Proj_DtInicio
	private Date dtFim; // U_Proj_DtFim
	private BigDecimal vlOriginal; // U_Proj_VlrOrigProj
	private int setor; // U_Proj_Setor
	private int atividade; // U_Proj_Atividade

	public String getCN() {
		return CN;
	}

	public void setCN(String cN) {
		CN = cN;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public BigDecimal getVlOriginal() {
		return vlOriginal;
	}

	public void setVlOriginal(BigDecimal vlOriginal) {
		this.vlOriginal = vlOriginal;
	}

	public int getSetor() {
		return setor;
	}

	public void setSetor(int setor) {
		this.setor = setor;
	}

	public int getAtividade() {
		return atividade;
	}

	public void setAtividade(int atividade) {
		this.atividade = atividade;
	}



	
}
