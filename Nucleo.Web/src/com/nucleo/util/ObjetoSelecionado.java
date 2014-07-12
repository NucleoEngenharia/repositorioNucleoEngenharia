package com.nucleo.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ObjetoSelecionado {
	
	public boolean verificaSeFoiSelecionado(Object object){
		boolean selecionado = false;
		try{
		if(object.equals(null)){}
		selecionado = true;
		}catch(NullPointerException e){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção!", "Selecione um objeto da lista");
		context.addMessage(null, message);
		}
		return selecionado;
	}

}
