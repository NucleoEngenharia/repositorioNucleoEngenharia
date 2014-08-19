package com.nucleo.contratos.MB;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.nucleo.contratos.entity.Projeto;
import com.nucleo.entity.cadastro.eNum.AtividadeEnum;
import com.nucleo.entity.cadastro.eNum.StatusProjetoEnum;

@Named
@ConversationScoped
public class EditarProjetoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@PostConstruct
	public void init(){
		atividades = Arrays.asList(AtividadeEnum.values());
		status = Arrays.asList(StatusProjetoEnum.values());
	}
	@Inject
	private Conversation conversation;
	private Projeto projetoSelecionado;
	private List<AtividadeEnum>atividades;
	private List<StatusProjetoEnum>status;
	
	public void begin(){
		if(conversation.isTransient()){
		conversation.begin();
		}
	}
	public void end(){
		if(!conversation.isTransient()){
		conversation.end();
		}
	}
	public boolean temConversation(){
		return conversation.isTransient();
	}
	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}
	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}
	public List<AtividadeEnum> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<AtividadeEnum> atividades) {
		this.atividades = atividades;
	}
	public List<StatusProjetoEnum> getStatus() {
		return status;
	}
	public void setStatus(List<StatusProjetoEnum> status) {
		this.status = status;
	}
	
}
