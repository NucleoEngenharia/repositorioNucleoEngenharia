package com.nucleo.projetos.cadastro.MB;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.projetos.map.MAPMedicao;
import com.nucleo.sap.bo.SapBO;
import com.nucleo.sap.bo.SapBOProxy;
import com.nucleo.sap.to.ProjetoTO;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.util.ObjetoSelecionado;

@ManagedBean
public class ListaProjetosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	private AcessosUsuario acessoDoUsuarioLogado;
	@EJB
	private ProjetoDAO projetoDAO;
	
	private SapBO sapBO;
	private List<ProjetoTO> projetosSAP;
	
	private List<Projeto> projetosMedicao;
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	
	private ProjetoTO projetoSAP;
	
	private Projeto projeto;
	
	private ObjetoSelecionado selecionado = new ObjetoSelecionado();
	
	@Inject
	private CadastroProjetoBean cadProjetoBean;
	
	private boolean apenasLeitura=false;

	
	@PostConstruct
	public void init(){
		sapBO = new SapBOProxy();
		projetoSAP = new ProjetoTO();
		projeto = new Projeto();
		acessoDoUsuarioLogado = Commom.getAcessoUsuarioLogado();
	}

	public String editarProjeto(){
		String destino ="";
		
		if(projetoSAP.getNome() != null){
			MAPMedicao.ProjetoTOtoProjetoJPA(projetoSAP, projeto);
			projetoDAO.inserir(projeto, usuarioLogado.getPessoa_id());
		}
		 if(selecionado.verificaSeFoiSelecionado(projeto)){
			cadProjetoBean.iniciar();
			cadProjetoBean.setProjetoSelecionado(projeto);
			destino ="editar_projeto.xhtml?faces-redirect=true";
		}
		
		return destino;
	}
	
	private void removeProjetosCadastradosListaSAP(){
		List<ProjetoTO> projetosNaoRemovidos = new ArrayList<ProjetoTO>();
		for (ProjetoTO proj : projetosSAP) {
			if(!verificaProjetoCadastradoSAP(proj, projetosMedicao)){
				projetosNaoRemovidos.add(proj);
			}
		}
		projetosSAP = projetosNaoRemovidos;
	}
	
	private boolean verificaProjetoCadastradoSAP(ProjetoTO projetoSAP, List<Projeto> projetosMedicao){
		for (Projeto projeto : projetosMedicao) {
			if(projeto.getCN().equals(projetoSAP.getCN())) return true;
		}
		return false;
	} 

	public List<ProjetoTO> getProjetosSAP() {
		if(projetosSAP == null){
			try {
				projetosSAP = Arrays.asList(sapBO.getProjetos());
				if(projetosMedicao == null){
					projetosMedicao = projetoDAO.buscarTodos();
				}
				removeProjetosCadastradosListaSAP();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return projetosSAP;
	}
	public void setProjetosSAP(List<ProjetoTO> projetosSAP) {
		this.projetosSAP = projetosSAP;
	}
	public List<Projeto> getProjetosMedicao() {
		if(projetosMedicao==null){
			if(acessoDoUsuarioLogado.isAdministrador()){
			projetosMedicao = projetoDAO.listarTodos();
		}else{
			System.out.println("Fez select");
			projetosMedicao =  acessosUsuarioDAO.buscarAcessoDoUsuario(usuarioLogado.getPessoa_id())
					.getProjetosAcessiveis();
		}
		}
		return projetosMedicao;
		
	}
	public void setProjetosMedicao(List<Projeto> projetosMedicao) {
		this.projetosMedicao = projetosMedicao;
	}
	public ProjetoTO getProjetoSAP() {
		return projetoSAP;
	}
	public void setProjetoSAP(ProjetoTO projetoSAP) {
		this.projetoSAP = projetoSAP;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public boolean isApenasLeitura() {
		return apenasLeitura;
	}

	public void setApenasLeitura(boolean apenasLeitura) {
		this.apenasLeitura = apenasLeitura;
	}
	
}
