package com.nucleo.contratos.cadastro.MB;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.nucleo.contratos.dao.ProjetoDAO;
import com.nucleo.contratos.entity.Projeto;
import com.nucleo.entity.cadastro.eNum.AtividadeEnum;
import com.nucleo.entity.cadastro.eNum.SetorEnum;
import com.nucleo.sap.bo.SapBOProxy;
import com.nucleo.sap.to.ProjetoTO;

@ManagedBean
@SessionScoped
public class ProjetosBean {
	private List<Projeto>projetos;
	private ProjetoTO projetosSAP[];
	private SapBOProxy sapBO;
	
	private Projeto projetoSelecionado;
	private List<AtividadeEnum>atividades;
	private List<SetorEnum>setores;
	@EJB
	private ProjetoDAO projetoDAO;
	@PostConstruct
	public void init(){
		sapBO = new SapBOProxy();
		projetos=null;
		setores = Arrays.asList(SetorEnum.values());
		atividades = Arrays.asList(AtividadeEnum.values());
		try {
			projetosSAP = sapBO.getProjetos();
		} catch (RemoteException e) {
			e.printStackTrace();
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
	private List<Projeto>converteProjSAP(ProjetoTO projetos[]){
		List<Projeto> projcts = new ArrayList<Projeto>();
		projcts = projetoDAO.listarTodos();
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
