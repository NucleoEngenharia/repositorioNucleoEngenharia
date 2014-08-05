package com.nucleo.contratos.MB;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.nucleo.commom.Commom;
import com.nucleo.commom.Messages;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	@PostConstruct
	public void init(){
		funcionario = new Funcionario();
		usuarioLogado = Commom.getUsuarioLogado();
	}
	private FuncionarioTO usuarioLogado;
	private List<Funcionario>funcionarios;
	
	@SuppressWarnings("unused")
	private FacesContext context;
	@EJB
	private FuncionarioDAO funcionarioDAO;
	
	private Funcionario funcionario;

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void salvarFuncionario(){
		try{
			System.out.println("Tentando salvar");
		funcionarioDAO.inserir(funcionario, usuarioLogado.getPessoa_id());
		Messages.geraMensagemAviso("Usuário gravado com sucesso");
		funcionario = new Funcionario();
		}catch(Exception e){
			Messages.geraMensagemFatal(""+e);
		}
	}
	
	
}
