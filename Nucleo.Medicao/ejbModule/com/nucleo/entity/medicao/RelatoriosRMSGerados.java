package com.nucleo.entity.medicao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;

@Entity
@SequenceGenerator(allocationSize =1, sequenceName = "SEQ_RELATORIOSGERADOS", name = "seqGeracaoRMS")
public class RelatoriosRMSGerados extends CommomEntity{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGeracaoRMS")
	private int id;
	
	private String nomeArquivo;
	
	private String caminhoDoArquivo;
	
	public int getId() {
		return id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public String getCaminhoDoArquivo() {
		return caminhoDoArquivo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public void setCaminhoDoArquivo(String caminhoDoArquivo) {
		this.caminhoDoArquivo = caminhoDoArquivo;
	}
}
