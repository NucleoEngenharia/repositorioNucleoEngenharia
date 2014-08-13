package com.nucleo.contratos.seguranca;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.nucleo.commom.Messages;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.intranet.DAO.AutenticacaoDAO;
import com.nucleo.intranet.DAO.MenuDAO;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.MenuTO;

@ManagedBean
@SessionScoped
public class LoginBean{

	public LoginBean() {
		usuarioLogado = new FuncionarioTO();
	}
	private FuncionarioTO usuarioLogado;
	
	private String usuario;
	private String senha;
	private String confirmSenha;
	private boolean primeiroAcesso = false;
	private MenuModel panelMenu=null;
	@EJB
	private AutenticacaoDAO autenticacaoDAO;
	@EJB
	private FuncionarioDAO funcionarioDAO;
	@EJB
	private MenuDAO menuDAO;
	
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
	public String getConfirmSenha() {
		return confirmSenha;
	}
	public void setConfirmSenha(String confirmSenha) {
		this.confirmSenha = confirmSenha;
	}
	
	public MenuModel getPanelMenu() {
		if(panelMenu==null){
		panelMenu = new DefaultMenuModel();
		List<MenuTO> listaSubMenus = menuDAO.listarMenusFilhos(MenuDAO.MENU_CONTRATOS, "146");
		List<MenuTO>listaMenuItem = menuDAO.listarTodosMenus(MenuDAO.MENU_CONTRATOS);
		for(MenuTO m: listaSubMenus){
			DefaultSubMenu subMenu = new DefaultSubMenu(m.getDescricao());
				for(MenuTO mi: listaMenuItem){
					if(mi.getMenuPai()==m.getId()){
						DefaultMenuItem item = new DefaultMenuItem();
						item.setUrl(mi.getUrl());
						item.setValue(mi.getDescricao());
						subMenu.addElement(item);
					}
				}
				panelMenu.addElement(subMenu);
		}
		}
		return panelMenu;
	}
	public void setPanelMenu(MenuModel panelMenu) {
		this.panelMenu = panelMenu;
	}
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loginBean");
		return "/faces/login?faces-redirect=true";
	}
	public String cadastrarSenha(){
		String retorno = "";
		if(senha.equals(confirmSenha)){
			funcionarioDAO.fazPrimeiroAcesso(usuario,senha);
			this.primeiroAcesso = false;
			 Funcionario usuarioEncontrado = funcionarioDAO.buscaPorMatricula(usuario);
			 usuarioLogado.setNome(usuarioEncontrado.getNome());
			 usuarioLogado.setCpf(usuarioEncontrado.getCpf());
			 retorno = autorizaPaginacao();
		}else{
			Messages.geraMensagemDeErro("Senhas não conferem, verifique e tente novamente");
		}
		return retorno;
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
					usuarioLogado.setCpf(funcionario.getCpf());
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








