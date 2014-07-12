package com.nucleo.projetos.cadastro.MB;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.intranet.DAO.FuncionarioDAO;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.util.ObjetoSelecionado;

@ManagedBean
@ViewScoped
public class ControleAcessoBean {
	@PostConstruct
	public void init(){
		 selecionado = new ObjetoSelecionado();
		 funcionariosMedicaoControle = funcionarioDAO.getUsuariosMedicaoEControle();
		
	}
	private ObjetoSelecionado selecionado;
	private List<FuncionarioTO>funcionariosMedicaoControle;
	
	private FuncionarioTO funcionarioSelecionado;
	@EJB
	private FuncionarioDAO funcionarioDAO;
	
	private AcessosUsuario acessoUsuario;
	@EJB
	private AcessosUsuarioDAO acessoUsuarioDAO;
	
	@Inject
	private AssociarUsuarioProjetoBean associarUsuarioProjetoBean;

	public List<FuncionarioTO> getFuncionariosMedicaoControle() {
		return funcionariosMedicaoControle;
	}

	public void setFuncionariosMedicaoControle(List<FuncionarioTO> funcionariosMedicaoControle) {
		this.funcionariosMedicaoControle = funcionariosMedicaoControle;
	}

	public FuncionarioTO getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(FuncionarioTO funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}
	
	public AcessosUsuario getAcessoUsuario() {
		return acessoUsuario;
	}

	public void setAcessoUsuario(AcessosUsuario acessoUsuario) {
		this.acessoUsuario = acessoUsuario;
	}

	public String associarUsuario(){
		String destino="";
		if(selecionado.verificaSeFoiSelecionado(funcionarioSelecionado)){
		associarUsuarioProjetoBean.begin();
		associarUsuarioProjetoBean.setUsuarioSelecionado(funcionarioSelecionado);
		AcessosUsuario acessoDoUsuario = acessoUsuarioDAO.buscarAcessoDoUsuario(funcionarioSelecionado.getPessoa_id());
		associarUsuarioProjetoBean.setAcessosUsuario(acessoDoUsuario);
		destino = "associarProjeto.xhtml?faces-redirect=true";
		}
		return destino;
	}

}
