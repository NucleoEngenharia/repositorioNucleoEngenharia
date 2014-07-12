package com.nucleo.util;

import java.util.Calendar;

public class DataPeriodoMedicao {
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
