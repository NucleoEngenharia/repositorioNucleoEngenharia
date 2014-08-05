package com.nucleo.commom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import com.nucleo.contratos.seguranca.LoginBean;
import com.nucleo.seguranca.to.FuncionarioTO;

public class Commom {

	private static FacesContext context() {
		return FacesContext.getCurrentInstance();
	}
	

	public static FuncionarioTO getUsuarioLogado() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		return loginBean.getUsuarioLogado();
	}

	public static void removeMessagesContext() {
		Iterator<FacesMessage> messages = context().getMessages();
		while (messages.hasNext()) {
			messages.next();
			messages.remove();
		}
	}

	public static Object lookup(String className) {
		Object classeDAO = null;
		InitialContext ic = null;
		try {
			ic = new InitialContext();
			try {
				classeDAO = ic.lookup("java:global/Contratos.Web/" + className);
			} catch (NamingException e) {
				try {
					classeDAO = ic.lookup("java:global/Contratos/PContratos.EJB/" + className);
				} catch (NamingException e2) {
					e2.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classeDAO;
	}
	
	public static String converterDataParaString(Date data, String formato){
		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.format(data);
	}
	
	
}