package com.nucleo.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.nucleo.commom.Commom;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@ViewScoped
public class ProjetoTestBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Projeto projeto;
	
	@EJB
	private ProjetoDAO projetoDAO;
	
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();

	@PostConstruct
	public void init(){
		
		projeto = new Projeto();
		projeto.setCN("002");
		projeto.setDataInicio(Calendar.getInstance());
		projeto.setDataFim(Calendar.getInstance());
		projeto.getDataFim().add(Calendar.MONTH, 3);
		projeto.getDataFim().add(Calendar.DAY_OF_MONTH, 10);
		projeto.setDescricao("Projeto de test");
		projeto.setValorOriginal(new BigDecimal(2500000.00));
		projeto.setPermiteRenovacao(false);
	//	projeto.setDataInicialMedicao(Calendar.getInstance());
		
		projetoDAO.inserir(projeto, usuarioLogado.getPessoa_id());
		
		
		projeto.setDataInicialMedicao(Calendar.getInstance());
		projetoDAO.alterar(projeto, usuarioLogado.getPessoa_id());
		
		
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
}
