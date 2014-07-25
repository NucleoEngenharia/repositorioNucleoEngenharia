package com.nucleo.seguranca.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import com.nucleo.seguranca.MB.LoginBean;

public class AutenticacaoPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		String paginaOrigem = (String) context.getViewRoot().getViewId();
		if (paginaIgnorada(paginaOrigem)) return;
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		
    	LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
    	if(loginBean == null || loginBean.getUsuarioLogado().getNome() == null){
    		FacesContext instance = FacesContext.getCurrentInstance();
    		ExternalContext externalContext = instance.getExternalContext();
    		externalContext.getSessionMap().put("paginaOrigem", paginaOrigem);
    		
    		handler.handleNavigation(context, null, "/faces/login?faces-redirect=true");
    	}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	
	private boolean paginaIgnorada(String pagina){
		boolean retorno = false;
		if(pagina.equals("/login.xhtml"))
			retorno = true;
		
		return retorno;
	}
	
	
}
