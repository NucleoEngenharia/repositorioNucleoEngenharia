package com.nucleo.projetos.cadastro.MB;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

import com.nucleo.entity.cadastro.Renovacao;

@ManagedBean
@RequestScoped
public class RenovacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Renovacao renovacao;
	
	@PostConstruct
	public void init(){
		renovacao = new Renovacao();
	}

	public void salvarRenovacao(){
		RequestContext.getCurrentInstance().closeDialog(renovacao);
	}
	
	
	public Renovacao getRenovacao() {
		return renovacao;
	}
	public void setRenovacao(Renovacao renovacao) {
		this.renovacao = renovacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
