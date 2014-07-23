package com.nucleo.contratos.seguranca;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AutenticacaoPhaseListener implements PhaseListener{
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {
		FacesContext context = phaseEvent.getFacesContext();
		String paginaOrigem = context.getViewRoot().getViewId();
		if(isPageLogin(paginaOrigem)){
			return;
		}
		try{
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		HttpSession sessao = (HttpSession) context.getExternalContext().getSession(true);
		LoginBean loginBean = (LoginBean) sessao.getAttribute("loginBean");
		if(loginBean==null){
			FacesContext instance = FacesContext.getCurrentInstance();
    		ExternalContext externalContext = instance.getExternalContext();
    		externalContext.getSessionMap().put("paginaOrigem", paginaOrigem);
    		handler.handleNavigation(context, null, "/faces/login?faces-redirect=true");
		}
		}catch(Exception e){
			System.out.println("Erro ao logar"+e);
		}
	}
	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	private boolean isPageLogin(String paginaOrigem){
		boolean paginaLogin = false;
		if(paginaOrigem.equals("/login.xhtml")){
			paginaLogin = true;
		}
		return paginaLogin;
	}
}
