package com.nucleo.contratos.seguranca;


import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loginBean");
		return "/faces/login?faces-redirect=true";
	}
	public String autenticar(){
		String retorno  = "";
		FacesContext fc = FacesContext.getCurrentInstance();
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
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e/ou senha inválidos, verifique e tente novamente",null));
		}
		System.out.println(retorno);
		return retorno;
	}
	
}








