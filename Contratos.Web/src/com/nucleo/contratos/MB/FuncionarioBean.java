package com.nucleo.contratos.MB;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.nucleo.commom.Commom;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
public class FuncionarioBean {
	@PostConstruct
	public void init(){
		funcionario = new Funcionario();
		usuarioLogado = Commom.getUsuarioLogado();
	}
	private FuncionarioTO usuarioLogado;
	private List<Funcionario>funcionarios;
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
		context = FacesContext.getCurrentInstance();
		funcionarioDAO.inserir(funcionario, usuarioLogado.getPessoa_id());
		}catch(Exception e){
			
		}
	}
	
	
}
