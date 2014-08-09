package com.nucleo.contratos.seguranca;



import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.nucleo.commom.Messages;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Funcionario;
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
	private boolean primeiroAcesso = false;
	@EJB
	private AutenticacaoDAO autenticacaoDAO;
	@EJB
	private FuncionarioDAO funcionarioDAO;
	
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
		int idUsuario = autenticacaoDAO.autenticarUsuario(usuario, senha);
		if(idUsuario!=0){
		usuarioLogado = autenticacaoDAO.getFuncionarioAutenticado(idUsuario);
		retorno = autorizaPaginacao();
		return retorno;
		}else{
			if(funcionarioDAO.autenticarFuncExterno(usuario,senha)){
				Funcionario funcionario = funcionarioDAO.buscaPorMatricula(usuario);
				if(funcionario.getId()!=0 && funcionario.isPrimeiroAcesso()==false){
					usuarioLogado.setNome(funcionario.getNome());
					retorno = autorizaPaginacao();
					return retorno;
				}else if(funcionario.getId()!=0 && funcionario.isPrimeiroAcesso()){
					primeiroAcesso=true;
				}
			}else{
			Messages.geraMensagemDeErro("Usuário e/ou senha inválidos, verifique e tente novamente");
			}
		}
		System.out.println(retorno);
		return retorno;
	}
	public String autorizaPaginacao(){
		String retorno ="";
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		String paginaOrigem = (String) externalContext.getSessionMap().get("paginaOrigem");
		externalContext.getSessionMap().remove("paginaOrigem");
		if(paginaOrigem == null){
			retorno = "/faces/index?faces-redirect=true";
		}else{
			retorno =  paginaOrigem + "?faces-redirect=true";
		}
		return retorno;
	}
	public boolean isPrimeiroAcesso() {
		return primeiroAcesso;
	}
	public void setPrimeiroAcesso(boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}
	
	
}








