package com.nucleo.projetos.cadastro.MB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.GrupoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.seguranca.to.FuncionarioTO;

@Named
@ConversationScoped
public class AssociarUsuarioProjetoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		acessosUsuario = new AcessosUsuario();
		projetosSelecionados = new ArrayList<Projeto>();
	}

	@Inject
	private Conversation conversation;
	@EJB
	private ProjetoDAO projetoDAO;
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	@EJB
	private GrupoDAO grupoDAO;

	private List<Grupo> grupos;
	
	private List<Grupo>gruposSelecionados;

	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	private List<Projeto> projetos;
	private FuncionarioTO usuarioSelecionado;
	private AcessosUsuario acessosUsuario;
	private List<Projeto> projetosSelecionados;

	public List<Grupo> getGrupos() {
		if (grupos == null) {
			carregaProjetosEGrupos();
		}
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Projeto> getProjetosSelecionados() {
		return projetosSelecionados;
	}

	public List<Grupo> getGruposSelecionados() {
		return gruposSelecionados;
	}

	public void setGruposSelecionados(List<Grupo> gruposSelecionados) {
		this.gruposSelecionados = gruposSelecionados;
	}

	public void setProjetosSelecionados(List<Projeto> projetosSelecionados) {
		this.projetosSelecionados = projetosSelecionados;
	}

	public AcessosUsuario getAcessosUsuario() {
		return acessosUsuario;
	}

	public void setAcessosUsuario(AcessosUsuario acessosUsuario) {
		this.acessosUsuario = acessosUsuario;
	}

	public FuncionarioTO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(FuncionarioTO usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Projeto> getProjetos() {
		if (projetos == null){
			carregaProjetosEGrupos();
		}
		return projetos;
	}
	private void carregaProjetosEGrupos(){
		if(!acessosUsuario.isAdministrador()){
			projetos = projetoDAO.listarTodos();
			projetosSelecionados=acessosUsuario.getProjetosAcessiveis();
			grupos = grupoDAO.listarGrupos();
			try{
			gruposSelecionados = new ArrayList<Grupo>(acessosUsuario.getGrupos());
			}catch(NullPointerException e){
			}
		}
		
	}
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public void begin() {
		conversation.begin();
	}

	public boolean temUsuarioSelecionado() {
		return !conversation.isTransient();
	}

	public void endConversation() {
		conversation.end();
	}

	public void associarProjeto() {
		if (acessosUsuario.getId() == 0) {
			acessosUsuario = new AcessosUsuario();
			acessosUsuario.setId_usuario(usuarioSelecionado.getPessoa_id());
			acessosUsuario.setProjetosAcessiveis(projetosSelecionados);
			acessosUsuarioDAO.inserir(acessosUsuario,usuarioLogado.getPessoa_id());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "projetos associados com sucesso!"));
		} else if (acessosUsuario.isAdministrador()) {
			acessosUsuario.setProjetosAcessiveis(null);
			acessosUsuarioDAO.alterar(acessosUsuario,
					usuarioLogado.getPessoa_id());
			acessosUsuario = new AcessosUsuario();
			acessosUsuario=acessosUsuarioDAO.buscarAcessoDoUsuario(usuarioSelecionado.getPessoa_id());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "alterações realizadas com sucesso com sucesso!"));
		} else {
			acessosUsuario.setProjetosAcessiveis(projetosSelecionados);
			acessosUsuarioDAO.alterar(acessosUsuario,
					usuarioLogado.getPessoa_id());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "alterações realizadas com sucesso!"));
		}
		acessosUsuario = new AcessosUsuario();
		acessosUsuario=acessosUsuarioDAO.buscarAcessoDoUsuario(usuarioSelecionado.getPessoa_id());
		projetos=null;
	}

	public void associarGrupo() {
		if (acessosUsuario.getId() == 0) {
			acessosUsuario = new AcessosUsuario();
			acessosUsuario.setId_usuario(usuarioSelecionado.getPessoa_id());
			acessosUsuario.setGrupos(new HashSet<Grupo>(gruposSelecionados));
			acessosUsuarioDAO.inserir(acessosUsuario,
					usuarioLogado.getPessoa_id());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Grupos associados com sucesso!"));
		} else if (!acessosUsuario.isAdministrador()) {
			acessosUsuario.setGrupos(new HashSet<Grupo>(gruposSelecionados));
			acessosUsuarioDAO.alterar(acessosUsuario,
					usuarioLogado.getPessoa_id());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Grupos alterados com sucesso!"));
		}
		acessosUsuario = new AcessosUsuario();
		acessosUsuario=acessosUsuarioDAO.buscarAcessoDoUsuario(usuarioSelecionado.getPessoa_id());
		grupos=null;
	}

}
