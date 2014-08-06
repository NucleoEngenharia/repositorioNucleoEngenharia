package com.nucleo.commom;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
	private static FacesContext context;
	
	public static void geraMensagemDeErro(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! "+msg,null));
	}
	public static void geraMensagemAviso(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informa��o! "+msg,null));
	}
	public static void geraMensagemFatal(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal! "+msg,null));
	}
	public static void geraMensagemWarn(String msg){
		context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten��o! "+msg,null));
	}
}
