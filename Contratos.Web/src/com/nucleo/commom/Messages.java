package com.nucleo.commom;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
	private static FacesContext context;
	
	public static void geraMensagemDeErro(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
	}
	public static void geraMensagemAviso(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação!", msg));
	}
	public static void geraMensagemFatal(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", msg));
	}
	public static void geraMensagemWarn(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", msg));
	}
}
