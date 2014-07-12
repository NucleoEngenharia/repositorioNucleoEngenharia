package com.nucleo.projetos.medicao.MB;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.FuncionarioContratoDAO;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.medicao.FuncionarioContrato;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.projetos.cadastro.MB.PermissoesMenuBean;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@ViewScoped
public class ProfissionaisBean implements Serializable {

	@EJB
	private FuncionarioContratoDAO funcionarioDAO;

	private static final long serialVersionUID = 1L;

	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();

	private FuncionarioContrato profissional;

	private List<FuncionarioContrato> profissionais;

	private int verificouAcesso = 0;
	private boolean apenasLeitura = false;
	
	private PermissoesMenuBean permissoesMenuBean;
	private AcessosUsuario acessosDoUsuarioLogado;
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	@PostConstruct
	public void init(){
		acessosDoUsuarioLogado = Commom.getAcessoUsuarioLogado();
		permissoesMenuBean = new PermissoesMenuBean();
	}
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (profissional.getId() == 0) {
				funcionarioDAO.inserir(profissional, usuarioLogado.getPessoa_id());
			} else {

				for (Mobilizacao mobilizacao : funcionarioDAO.buscarTodasMobilizacoes(profissional)) {
					if (profissional.getDataAdmissao().after(mobilizacao.getDataMobilizacao())) {
						String dtMobil = Commom.converterDataParaString(mobilizacao
								.getDataMobilizacao().getTime(), "dd/MM/YYYY");

						context.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_WARN,
										"Atenção, alteração não foi salva;",
										"Este funcionário possui uma mobilização do dia "
												+ dtMobil
												+ " no projeto "+mobilizacao.getCargo().getServico().getProjeto().getCN()+", a data de admissão não pode ser posterior a esta data"));
						return;
					}
					if (profissional.getDataRescisao() != null
							&& profissional.getDataRescisao().before(
									mobilizacao.getDataDesmobilizacao())) {
						String dtMobil = Commom.converterDataParaString(mobilizacao
								.getDataDesmobilizacao().getTime(), "dd/MM/YYYY");

						context.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_WARN,
										"Atenção, alteração não foi salva;",
										"Este funcionário possui uma desmobilização do dia "
												+ dtMobil
												+ " no projeto "+mobilizacao.getCargo().getServico().getProjeto().getCN()+", a data de rescisão não pode ser anterior a esta data"));
						return;
					}
				}
				funcionarioDAO.alterar(profissional, usuarioLogado.getPessoa_id());
			}
			profissional = null;
			context.addMessage(null,
					new FacesMessage("Sucesso!", "Profissional salvo com sucesso."));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
				"Por favor, contate o administrador do sistema."));
		}
	}
	public void editar(FuncionarioContrato profSelecionado) {
		profissional = profSelecionado;
	}
	public void excluir(FuncionarioContrato profSelecionado) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			funcionarioDAO.deletarPorId(profSelecionado.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Profissional excluído com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public boolean isApenasLeitura() {
		if(verificouAcesso==0){
		if(acessosDoUsuarioLogado.isAdministrador()){
			apenasLeitura = false;
		}else{
		AcessosUsuario acessosUsuario = acessosUsuarioDAO.buscarAcessoDoUsuarioComMenu(usuarioLogado.getPessoa_id());
		if(permissoesMenuBean.apenasLeitura(acessosUsuario, "Profissionais")){
			apenasLeitura = true;
			}
		}
		}
		verificouAcesso = 1;
		return apenasLeitura;
	}
	public void setApenasLeitura(boolean apenasLeitura) {
		this.apenasLeitura = apenasLeitura;
	}
	public void resetForm(){
		profissional = new FuncionarioContrato();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FuncionarioContrato getProfissional() {
		if (profissional == null) {
			profissional = new FuncionarioContrato();
		}
		return profissional;
	}
	public void setProfissional(FuncionarioContrato profissional) {
		this.profissional = profissional;
	}
	public List<FuncionarioContrato> getProfissionais() {
		if(profissionais==null){
		profissionais = funcionarioDAO.listarTodos();
		}
		return profissionais;
	}

	public void setProfissionais(List<FuncionarioContrato> profissionais) {
		this.profissionais = profissionais;
	}

}