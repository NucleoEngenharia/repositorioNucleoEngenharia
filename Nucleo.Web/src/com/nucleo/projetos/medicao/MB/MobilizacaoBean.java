package com.nucleo.projetos.medicao.MB;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.CargoDAO;
import com.nucleo.dao.FuncionarioContratoDAO;
import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.medicao.FuncionarioContrato;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.projetos.cadastro.MB.PermissoesMenuBean;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.util.ObjetoSelecionado;

@ManagedBean
@ViewScoped
public class MobilizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();

	@EJB
	private ProjetoDAO projetoDAO;
	@EJB
	private MobilizacaoDAO mobilizacaoDAO;
	@EJB
	private CargoDAO cargoDAO;
	@EJB
	private ServicoDAO servicoDAO;
	@EJB
	private FuncionarioContratoDAO funcContratoDAO;
	
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	private AcessosUsuario acessoDoUsuarioLogado;
	private Mobilizacao mobilizacao;
	private List<Mobilizacao> mobilizacoes;
	private List<Projeto> projetos;
	private Projeto projetoSelecionado;
	private int equipeSelecionada;
	private List<Servico> equipes;
	private List<Cargo> cargos;
	private List<Integer> cns;
	private int cnSelecionada;
	private List<FuncionarioContrato> funcionarios;
	private int idCargo;
	private boolean exibirDetalhes;
	private int tabLista = 0;
	private ObjetoSelecionado selecionado = new ObjetoSelecionado();
	private boolean apenasLeitura = false;
	private int verificouAcesso = 0;
	public boolean isApenasLeitura() {
		if(verificouAcesso==0){
		if(acessoDoUsuarioLogado.isAdministrador()){
			apenasLeitura = false;
		}else{
		AcessosUsuario acessosUsuario = acessosUsuarioDAO.buscarAcessoDoUsuarioComMenu(usuarioLogado.getPessoa_id());
		if(permissoesMenuBean.apenasLeitura(acessosUsuario, "Mobilização")){
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
	
	@Inject
	private PermissoesMenuBean permissoesMenuBean;
	
	@EJB
	private MedicaoEquipeDAO medicaoEquipeDAO;
	@PostConstruct
	public void init(){
		acessoDoUsuarioLogado = Commom.getAcessoUsuarioLogado();
		mobilizacao = new Mobilizacao();
	}
	
	public void selecionarProjeto() {
		if(selecionado.verificaSeFoiSelecionado(projetoSelecionado)){
		exibirDetalhes = true;
		tabLista = 1;
		}
	}

	public void carregaCargos() {
		if(equipeSelecionada == 0) return;
		cargos = cargoDAO.buscarTodosPorServico(servicoDAO
				.buscarPorID(equipeSelecionada));
	}

	public void carregaFuncionarios(){
		funcionarios = funcContratoDAO.buscarTodosPorCN(cnSelecionada);
	}
	
	
	private String validarMobilizacao(){
		if(mobilizacao.getDataMobilizacao().before(mobilizacao.getFuncionario().getDataAdmissao())){
			return "A data de mobilização deve ser igual ou após a data de admissão do funcionário.";
		}
		if(mobilizacao.getFuncionario().getDataRescisao() != null){
			if(mobilizacao.getDataDesmobilizacao() == null){
				return "Quando o usuário possui data de rescisão, a data de desmobilização é obrigatória.";
			}
			if(!mobilizacao.getDataMobilizacao().before(mobilizacao.getFuncionario().getDataRescisao())){
				return "A data da mobilização não pode ser igual ou após a data de rescisão do funcionario.";
			}
			if(mobilizacao.getDataDesmobilizacao().after(mobilizacao.getFuncionario().getDataRescisao())){
				return "A data de desmolização não pode ser após a data de rescisão do funcionário.";
			}
			if(mobilizacao.getDataMobilizacao().after(mobilizacao.getDataDesmobilizacao())){
				return "A data de mobilização não pode ser após a data de desmobilização.";
			}
		}
		return null;
	}
	public void salvarMobilizacao(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String mensagemErro = validarMobilizacao(); 
			if(mensagemErro != null){
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Atenção!",
						mensagemErro));
				return;
			}
			
			mobilizacao.setCargo(cargoDAO.buscarPorID(idCargo));
			if (mobilizacao.getId() == 0) {
				mobilizacaoDAO.inserir(mobilizacao, usuarioLogado.getPessoa_id());
			} else {
				mobilizacaoDAO.alterar(mobilizacao, usuarioLogado.getPessoa_id());
			}
			mobilizacao = new Mobilizacao();
			equipeSelecionada = 0;
			this.carregaCargos();
			idCargo = 0;
			cargos = null;
			mobilizacoes = null;
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Mobilização salva com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void excluirMobilizacao(Mobilizacao mobilizacaoSelecionada){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			mobilizacaoDAO.deletarPorId(mobilizacaoSelecionada.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!", "Mobilização excluída com sucesso."));
			mobilizacoes = null;
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void resetForm(){
		idCargo = 0;
		equipeSelecionada = 0;
		mobilizacao = null;
		cargos = null;
	}
	public void editarMobilizacao(Mobilizacao mobilizacaoSelecionada){
		mobilizacao = mobilizacaoSelecionada;
		cnSelecionada = mobilizacao.getFuncionario().getCn();
		carregaFuncionarios();
		equipeSelecionada = mobilizacao.getCargo().getServico().getId();
		this.carregaCargos();
		idCargo = mobilizacao.getCargo().getId();
		mobilizacoes = null;
	}
	
	public List<Projeto> getProjetos() {
		if(projetos==null){
			if(acessoDoUsuarioLogado.isAdministrador()){
				projetos = projetoDAO.listarTodos();
		}else{
			projetos =  acessosUsuarioDAO.buscarAcessoDoUsuario(usuarioLogado.getPessoa_id())
					.getProjetosAcessiveis();
		}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isExibirDetalhes() {
		return exibirDetalhes;
	}
	public void setExibirDetalhes(boolean exibirDetalhes) {
		this.exibirDetalhes = exibirDetalhes;
	}
	public List<Servico> getEquipes() {
		if (equipes == null) {
			equipes = servicoDAO.buscarEquipesPorProjeto(projetoSelecionado);
		}
		return equipes;
	}
	public void setEquipes(List<Servico> equipes) {
		this.equipes = equipes;
	}
	public List<Cargo> getCargos() {
		return cargos;
	}
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	public List<Integer> getCns() {
		if(cns == null){
			cns = funcContratoDAO.buscarTodasCN();
		}
		return cns;
	}
	public void setCns(List<Integer> cns) {
		this.cns = cns;
	}
	public Mobilizacao getMobilizacao() {
		return mobilizacao;
	}
	public void setMobilizacao(Mobilizacao mobilizacao) {
		this.mobilizacao = mobilizacao;
	}
	public int getEquipeSelecionada() {
		return equipeSelecionada;
	}
	public void setEquipeSelecionada(int equipeSelecionada) {
		this.equipeSelecionada = equipeSelecionada;
	}
	public List<Mobilizacao> getMobilizacoes() {
		if(mobilizacoes==null){
		mobilizacoes = mobilizacaoDAO.buscarTodosPorProjeto(projetoSelecionado);
		}
		return mobilizacoes;
	}
	public void setMobilizacoes(List<Mobilizacao> mobilizacoes) {
		this.mobilizacoes = mobilizacoes;
	}
	public int getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
	public int getTabLista() {
		return tabLista;
	}
	public void setTabLista(int tabLista) {
		this.tabLista = tabLista;
	}
	public List<FuncionarioContrato> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<FuncionarioContrato> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public int getCnSelecionada() {
		return cnSelecionada;
	}
	public void setCnSelecionada(int cnSelecionada) {
		this.cnSelecionada = cnSelecionada;
	}



}
