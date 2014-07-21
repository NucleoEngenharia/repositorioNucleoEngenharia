package com.nucleo.projetos.cadastro.MB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.nucleo.intranet.DAO.MenuDAO;
import com.nucleo.commom.Commom;
import com.nucleo.dao.GrupoDAO;
import com.nucleo.dao.PermissoesMenuDAO;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.entity.cadastro.controleDeAcessos.PermissoesMenu;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.MenuTO;

@ManagedBean
@ViewScoped
public class CadastroGrupoBean {
	
	@PostConstruct
	public void init(){
		grupo = new Grupo();
		menuSelecionado = new MenuTO();
		carregaPermissoes();
		salvar = true;
		editar=false;
	}
	private boolean salvar;
	private boolean editar;
	
	FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	
	
	private List<String>permissoes;
	private List<Grupo>grupos;
	private List<MenuTO>menus;
	
	private List<PermissoesMenu>menusByGrupoSelecionado;
	private MenuTO menuSelecionado;
	
	private Grupo grupo;
	private Grupo grupoSelecionado;
	
	public List<PermissoesMenu> getMenusByGrupoSelecionado() {
		if(menusByGrupoSelecionado==null){
			menusByGrupoSelecionado = grupoDAO.buscarMenusDoGrupo(grupoSelecionado);
		}
		return menusByGrupoSelecionado;
	}
	public void setMenusByGrupoSelecionado(List<PermissoesMenu> menusByGrupoSelecionado) {
		this.menusByGrupoSelecionado = menusByGrupoSelecionado;
	}
	@EJB
	private GrupoDAO grupoDAO;
	@EJB
	private MenuDAO menuDAO;
	@EJB
	private PermissoesMenuDAO permissoesMenuDAO;
	
	private boolean criarGrupo = true;
	private boolean associarMenus = false;

	
	public boolean isSalvar() {
		return salvar;
	}
	public boolean isEditar() {
		return editar;
	}
	public void setSalvar(boolean salvar) {
		this.salvar = salvar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	public MenuTO getMenuSelecionado() {
		return menuSelecionado;
	}
	public void setMenuSelecionado(MenuTO menuSelecionado) {
		this.menuSelecionado = menuSelecionado;
	}
	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public boolean isCriarGrupo() {
		return criarGrupo;
	}

	public void setCriarGrupo(boolean criarGrupo) {
		this.criarGrupo = criarGrupo;
	}
	
	public boolean isAssociarMenus() {
		return associarMenus;
	}

	public void setAssociarMenus(boolean associarMenus) {
		this.associarMenus = associarMenus;
	}

	public List<MenuTO> getMenus() {
		if(menus==null){
			menus = menuDAO.listarMenusMedicao();
		}
		return menus;
	}

	public void setMenus(List<MenuTO> menus) {
		this.menus = menus;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	public List<Grupo> getGrupos() {
		if(grupos==null){
			grupos = grupoDAO.listarGrupos();
		}
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	private void carregaPermissoes(){
		permissoes = new ArrayList<String>();
		permissoes.add("Liberado");
		permissoes.add("Leitura");
	}
	public void salvarGrupo(){
		try{
		grupoDAO.inserir(grupo, usuarioLogado.getPessoa_id());
		grupo = new Grupo();
		grupos = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String alterarGrupo(){
		String retorno = "";
		try{
		grupoDAO.alterar(grupo, usuarioLogado.getPessoa_id());
		grupo = new Grupo();
		grupos = null;
		retorno = "cadastra_grupos.xhtml?faces-redirect=true";
		return retorno;
		}catch(Exception e){
			e.printStackTrace();
			return retorno;
		}
	}
	public void deletarGrupo(Grupo grupo){
		try{
		grupoDAO.deletar(grupo, usuarioLogado.getPessoa_id());
		grupo = new Grupo();
		grupos = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void editarGrupo(Grupo grupo){
		salvar = false;
		editar = true;
		this.grupo = grupo;
	}
	public void associarMenu(){
		criarGrupo = false;
		associarMenus = true;
	}
	public void associaMenuAoGrupo(){
		PermissoesMenu permissoesMenu = new PermissoesMenu();
		permissoesMenu.setDescricao(menuSelecionado.getDescricao());
		permissoesMenu.setUrl(menuSelecionado.getUrl());
		permissoesMenu.setGrupo(grupoSelecionado);
		menusByGrupoSelecionado.add(permissoesMenu);
		grupoSelecionado.setMenus(new HashSet<PermissoesMenu>(menusByGrupoSelecionado));
		salvarPermissoes();
	}
	public List<PermissoesMenu>buscarPorGrupo(Grupo grupo){
		List<PermissoesMenu>menusPermitidos = new ArrayList<PermissoesMenu>();
		menusPermitidos = permissoesMenuDAO.buscarPermissoesPorGrupo(grupo);
		return menusPermitidos;
	}
	public void salvarPermissoes(){
		try{
		grupoDAO.alterar(grupoSelecionado, usuarioLogado.getPessoa_id());
		menusByGrupoSelecionado = null;
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	public void alterarPermissao(PermissoesMenu menu){
		permissoesMenuDAO.alterar(menu, usuarioLogado.getPessoa_id());
	}
	public void dessassociarPermissoes(PermissoesMenu menu){
		permissoesMenuDAO.deletarPermissao(menu, usuarioLogado.getPessoa_id());
		menusByGrupoSelecionado = null;
	}
	
}
