package com.nucleo.sap.to;

import java.math.BigDecimal;


public class ImpostoTO {
	
	private String wtCode;
	private String wtName;
	private BigDecimal taxa;
	private String inativo;
	
	public String getWtCode() {
		return wtCode;
	}
	public void setWtCode(String wtCode) {
		this.wtCode = wtCode;
	}
	public String getWtName() {
		return wtName;
	}
	public void setWtName(String wtName) {
		this.wtName = wtName;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public String getInativo() {
		return inativo;
	}
	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	
	
}
