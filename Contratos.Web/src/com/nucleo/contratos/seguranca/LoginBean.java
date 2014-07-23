package com.nucleo.contratos.seguranca;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.nucleo.intranet.DAO.AutenticacaoDAO;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@SessionScoped
public class LoginBean{

	public LoginBean() {
		usuarioLogado = new FuncionarioTO();
	}
	private FuncionarioTO usuarioLogado;
	
	private String usuario;
	private String senha;
	@EJB
	private AutenticacaoDAO autenticacaoDAO;
	
	public FuncionarioTO getUsuarioLogado() {
		return usuarioLogado;
	}
	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	private FacesContext fc = FacesContext.getCurrentInstance();

	public String autenticar(){
		String retorno  = "";
		int idUsuario = autenticacaoDAO.autenticarUsuario(usuario, senha);
		if(idUsuario!=0){
		this.usuarioLogado= autenticacaoDAO.getFuncionarioAutenticado(idUsuario);
		
		ExternalContext externalContext = fc.getExternalContext();
		
		String paginaOrigem = (String) externalContext.getSessionMap().get("paginaOrigem");
		externalContext.getSessionMap().remove("paginaOrigem");
		if(paginaOrigem == null){
			retorno = "/faces/index?faces-redirect=true";
		}else{
			retorno = paginaOrigem + "?faces-redirect=true";
		}
		}else{
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção!", "Usuário e/ou senha inválidos, verifique e tente novamente"));
		}
		return retorno;
	}
}
