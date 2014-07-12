package com.nucleo.model.VO;

import java.io.Serializable;
import java.util.Calendar;

public class DatasPeriodoMedicaoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	public DatasPeriodoMedicaoVO() {
	}
	public DatasPeriodoMedicaoVO(String descricao, Calendar datade){
		this.descricao = descricao;
		this.dataDe = datade;
	}
	private String descricao;
	private Calendar dataDe;
	public String getDescricao() {
		return descricao;
	}
	public Calendar getDataDe() {
		return dataDe;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setDataDe(Calendar dataDe) {
		this.dataDe = dataDe;
	}
}
