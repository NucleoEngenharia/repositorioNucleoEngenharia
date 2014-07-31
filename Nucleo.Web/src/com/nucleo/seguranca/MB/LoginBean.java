package com.nucleo.seguranca.MB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.nucleo.intranet.DAO.AutenticacaoDAO;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.GrupoDAO;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.entity.cadastro.controleDeAcessos.PermissoesMenu;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.MenuTO;
import com.nucleo.seguranca.to.PerfilTO;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private FuncionarioTO usuarioLogado;
	private String usuario;
	private String senha;
	private MenuModel model;
	private DefaultSubMenu subMenu;
	@EJB
	private AutenticacaoDAO autentic;
	@EJB
	private GrupoDAO grupoDAO;
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	
	private AcessosUsuario acesso ;
	public LoginBean(){
		usuarioLogado = new FuncionarioTO();
		acesso = new AcessosUsuario();
	}
	public String autenticar(){
		
		String retorno = null;
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Usuário e/ou Senha Inválidos!");
		
		
		try {
			
			int idFuncionario = autentic.autenticarUsuario(usuario, senha);
			if(idFuncionario != 0){
				usuarioLogado = autentic.getFuncionarioAutenticado(idFuncionario);
				carregaMenus();

	    		ExternalContext externalContext = fc.getExternalContext();
	    		
	    		String paginaOrigem = (String) externalContext.getSessionMap().get("paginaOrigem");
	    		externalContext.getSessionMap().remove("paginaOrigem");
	    		
	    		if(paginaOrigem == null){
	    			retorno = "/faces/index?faces-redirect=true";
	    		}else{
	    			retorno = paginaOrigem + "?faces-redirect=true";
	    		}
			} else {
				retorno = "";
				fc.addMessage("", mensagem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;		
	}

	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loginBean");
		return "/faces/login?faces-redirect=true";
	}

	private void carregaMenus(){
		acesso = acessosUsuarioDAO.buscarAcessoDoUsuario(usuarioLogado.getPessoa_id());
		if(acesso.isAdministrador()){
			carregaMenusAdministrador();
		}else{
		model = new DefaultMenuModel();
		subMenu = new DefaultSubMenu();
		subMenu.setLabel("Medição e controle");
		Set<Grupo>grupos = new HashSet<Grupo>();
		try{
		grupos = acesso.getGrupos();
		List<PermissoesMenu> menus = new ArrayList<PermissoesMenu>();
		for(Grupo grupo: grupos){
			menus = grupoDAO.buscarMenusDoGrupo(grupo,0);
			for(PermissoesMenu menu: menus){
				DefaultMenuItem item = new DefaultMenuItem(menu.getDescricao());
				item.setAjax(false);
				item.setUrl(menu.getUrl());
				subMenu.addElement(item);
			}
		}
		}catch(NullPointerException e){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Atenção","Usuário não tem nenhum grupo associado, favor entrar em contato com o administrador do sistema"));
		}
		model.addElement(subMenu);
		}
	}
	
	private void carregaMenusAdministrador(){
        
		model = new DefaultMenuModel();
		
		List<MenuTO> menusPermitidos = carregaMenusPermitidos();
		
		for (MenuTO menu : menusPermitidos) {
			
			if(menu.getMenuPai() == 0){
				subMenu = new DefaultSubMenu();
				subMenu.setLabel(menu.getDescricao());
				
				if(getMenusFilho(menu.getId()) != null){
					for (MenuTO menu01 : getMenusFilho(menu.getId())) {
						
						if(getMenusFilho(menu01.getId()) == null){
							DefaultMenuItem item = new DefaultMenuItem(menu01.getDescricao());
							item.setAjax(false);
							item.setUrl(menu01.getUrl());
							subMenu.addElement(item);
						}else{
							DefaultSubMenu submenu01 = geraSubmenu(menu01);
							subMenu.addElement(submenu01);
						}
					}
				}
				model.addElement(subMenu);
				
			}
		}
	} 

	public DefaultSubMenu geraSubmenu(MenuTO menu) {
		DefaultSubMenu submenu = new DefaultSubMenu();
		submenu.setLabel(menu.getDescricao());
		for (MenuTO m : getMenusFilho(menu.getId())) {
			if (getMenusFilho(m.getId()) == null) {
				DefaultMenuItem mi = new DefaultMenuItem();
				mi.setValue(m.getDescricao());
				mi.setUrl(m.getUrl());
				
				submenu.addElement(mi);
			} else {
				submenu.addElement(geraSubmenu(m));
			}
		}
		return submenu;
	}	
	
	private List<MenuTO> carregaMenusPermitidos(){
		List<MenuTO> menusPermitidos = new ArrayList<MenuTO>(0);
		for (PerfilTO perfil : usuarioLogado.getPerfis()) {
			for (MenuTO menu : perfil.getMenus()) {
				if(!menusPermitidos.contains(menu))
					menusPermitidos.add(menu);
			}
		}
		return menusPermitidos;
	}	

	private List<MenuTO> getMenusFilho(int idMenu){
		
		List<MenuTO> menusPermitidos = carregaMenusPermitidos();
		List<MenuTO> menusFilho = null;
		
		for (MenuTO menu : menusPermitidos) {
			if(menu.getMenuPai() == idMenu){
				if(menusFilho == null) menusFilho = new ArrayList<MenuTO>();
				menusFilho.add(menu);
			}
		}
		return menusFilho;
	}	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public FuncionarioTO getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(FuncionarioTO usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public AcessosUsuario getAcesso() {
		return acesso;
	}
	public void setAcesso(AcessosUsuario acesso) {
		this.acesso = acesso;
	}
	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
}
