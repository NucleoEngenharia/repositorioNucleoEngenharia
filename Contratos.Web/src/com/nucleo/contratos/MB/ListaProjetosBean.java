package com.nucleo.contratos.MB;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.nucleo.contratos.dao.ProjetoDAO;
import com.nucleo.contratos.entity.Projeto;
import com.nucleo.entity.cadastro.eNum.AtividadeEnum;
import com.nucleo.entity.cadastro.eNum.SetorEnum;
import com.nucleo.entity.cadastro.eNum.StatusProjetoEnum;
import com.nucleo.sap.bo.SapBOProxy;
import com.nucleo.sap.to.ProjetoTO;

@ManagedBean
@ViewScoped
public class ListaProjetosBean {
	private List<Projeto>projetos;
	private ProjetoTO projetosSAP[];
	private SapBOProxy sapBO;
	
	private Projeto projetoSelecionado;
	private List<AtividadeEnum>atividades;
	private List<SetorEnum>setores;
	private List<StatusProjetoEnum>status;
	@EJB
	private ProjetoDAO projetoDAO;
	
	@Inject EditarProjetoBean editarProjetoBean;
	
	@PostConstruct
	public void init(){
		sapBO = new SapBOProxy();
		projetos=null;
		setores = Arrays.asList(SetorEnum.values());
		status = Arrays.asList(StatusProjetoEnum.values());
		atividades = Arrays.asList(AtividadeEnum.values());
		
		try {
			projetosSAP = sapBO.getProjetos();
		} catch (RemoteException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage
			(FacesMessage.SEVERITY_ERROR, "Erro", "Falha na comunicação com sistema SAP (http://179.184.226.66:8181), entre em contato com Administrador da rede"));
		}
	}
	public List<AtividadeEnum> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<AtividadeEnum> atividades) {
		this.atividades = atividades;
	}
	public List<SetorEnum> getSetores() {
		return setores;
	}
	public void setSetores(List<SetorEnum> setores) {
		this.setores = setores;
	}
	public List<StatusProjetoEnum> getStatus() {
		return status;
	}
	public void setStatus(List<StatusProjetoEnum> status) {
		this.status = status;
	}
	private List<Projeto>converteProjSAP(ProjetoTO projetos[]){
		List<Projeto> projcts = new ArrayList<Projeto>();
		projcts = projetoDAO.listarTodos();
		try{
		for(int index=0;index<projetos.length;index++){
			ProjetoTO to = new ProjetoTO();
			Projeto projetoContr = new Projeto();
			to = projetos[index];
			if(projcts.size()>0){
				if(!proucuraCNnosProjCadastrados(to, projcts)){
					projetoContr.setCn(to.getCN());
					projetoContr.setDescricao(to.getNome());
					projetoContr.setDtInicio(to.getDtInicio());
					projetoContr.setDtFim(to.getDtFim());
					projetoContr.setSetor(SetorEnum.getPorValor(to.getSetor()));
					projetoContr.setAtividade(AtividadeEnum.getPorValor(to.getAtividade()));
					projcts.add(projetoContr);
				}
			}else{
				projetoContr.setCn(to.getCN());
				projetoContr.setDescricao(to.getNome());
				projetoContr.setDtInicio(to.getDtInicio());
				projetoContr.setDtFim(to.getDtFim());
				projetoContr.setSetor(SetorEnum.getPorValor(to.getSetor()));
				projetoContr.setAtividade(AtividadeEnum.getPorValor(to.getAtividade()));
				projcts.add(projetoContr);
			}
		}
		}catch(NullPointerException e){}
		return projcts;
	}
	private boolean proucuraCNnosProjCadastrados(ProjetoTO projetoTO,List<Projeto>projs){
		for(Projeto p:projs){
			if(p.getCn().equals(projetoTO.getCN())){
				return true;
			}
		}
		return false;
	}
	

	public List<Projeto> getProjetos() {
		if(projetos==null){
			projetos = converteProjSAP(projetosSAP);
		}
		return projetos;
	}

	public String selecionarProjeto(){
		editarProjetoBean.begin();
		editarProjetoBean.setProjetoSelecionado(projetoSelecionado);
		return "editarContratos.xhtml?faces-redirect=true";
	}
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}
	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}
	
	
}
