package com.nucleo.MB.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CustomHandleException extends ExceptionHandlerWrapper {
	private ExceptionHandler wrapped;
	
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	
	private NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
	
	public CustomHandleException(ExceptionHandler exceptionHandler) {
		this.wrapped = exceptionHandler;
	}
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}
	@Override
	public void handle() throws FacesException {
		
		Iterator<ExceptionQueuedEvent> iterator = getHandledExceptionQueuedEvents().iterator();
	while (iterator.hasNext()) {
		
		 ExceptionQueuedEvent event = iterator.next();
         ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
         
         Throwable exception = context.getException();
         
         if(exception.getCause().equals("org.jboss.weld.context.NonexistentConversationException")){
        	 
        	 handler.handleNavigation(facesContext, null, "/faces/paginaExpirada?login=true");
        	 facesContext.renderResponse();
         }
	}
	}
	

}
