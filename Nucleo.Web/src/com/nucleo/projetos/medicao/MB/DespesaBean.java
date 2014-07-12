package com.nucleo.projetos.medicao.MB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.nucleo.dao.MedicaoDespesaDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;
import com.nucleo.projetos.cadastro.MB.PermissoesMenuBean;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.util.ObjetoSelecionado;

@ManagedBean
@ViewScoped
public class DespesaBean implements Serializable {

	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	
	@EJB
	private ProjetoDAO projetoDAO;
	@EJB
	private MedicaoDespesaDAO medicaoDespesaDAO;
	@EJB
	private MobilizacaoDAO mobilizacaoDAO;
	@EJB
	private PeriodoMedicaoDAO periodoDAO;

	private static final long serialVersionUID = 1L;
	private boolean exibirDetalhes;
	private Projeto projetoSelecionado;
	private MedicaoDespesa despesa;
	private AcessosUsuario acessoDoUsuarioLogado;
	
	private BigDecimal limiteDisponivel;
	private BigDecimal valorTotal;
	
	private List<PeriodoMedicao> periodos;
	private List<Mobilizacao> mobilizacoes;
	private List<PeriodoMedicao>periodosProjeto;
	private List<MedicaoDespesa> despesas;
	private List<Projeto> projetos;
	
	private int idPeriodoSelecionado;
	private boolean apenasLeitura = false;
	private int verificouAcesso = 0;
	public boolean isApenasLeitura() {
		if(verificouAcesso==0){
		if(acessoDoUsuarioLogado.isAdministrador()){
			apenasLeitura = false;
		}else{
		AcessosUsuario acessosUsuario = acessosUsuarioDAO.buscarAcessoDoUsuarioComMenu(usuarioLogado.getPessoa_id());
		if(permissoesMenuBean.apenasLeitura(acessosUsuario, "Lançar Despesas")){
			apenasLeitura = true;
		}
		}
		verificouAcesso=1;
		}
		return apenasLeitura;
	}

	public void setApenasLeitura(boolean apenasLeitura) {
		this.apenasLeitura = apenasLeitura;
	}
	@Inject
	private PermissoesMenuBean permissoesMenuBean;
	
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	@PostConstruct
	public void init(){
		acessoDoUsuarioLogado = Commom.getAcessoUsuarioLogado();
	}
	public List<PeriodoMedicao> getPeriodosProjeto() {
		if(periodosProjeto==null)
			periodosProjeto = periodoDAO.buscarTodosPorProjeto(projetoSelecionado);
		return periodosProjeto;
	}
	public void setPeriodosProjeto(List<PeriodoMedicao> periodosProjeto) {
		this.periodosProjeto = periodosProjeto;
	}
	public int getIdPeriodoSelecionado() {
		return idPeriodoSelecionado;
	}
	public void setIdPeriodoSelecionado(int idPeriodoSelecionado) {
		this.idPeriodoSelecionado = idPeriodoSelecionado;
	}
	private int tabLista = 0;
	
	private ObjetoSelecionado selecionado = new ObjetoSelecionado();


	public void selecionarProjeto() {
		if(selecionado.verificaSeFoiSelecionado(projetoSelecionado)){
		exibirDetalhes = true;
		tabLista = 1;
		}
	}
	public void filtraDespesasPorPeriodo(){
		despesas = new ArrayList<MedicaoDespesa>();
		despesas = medicaoDespesaDAO.buscarTodosPorPeriodo(idPeriodoSelecionado);
	}

	public void salvarDespesa() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (despesa.getId() == 0) {
				if (projetoSelecionado.isLimiteDespesa()
						&& limiteDisponivel.subtract(despesa.getValor()).compareTo(
								new BigDecimal(0)) < 0) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Atenção!", "O valor da despesa é maior que o valor disponível."));
					return;
				}
				despesa.setProjeto(projetoSelecionado);
				medicaoDespesaDAO.inserir(despesa, usuarioLogado.getPessoa_id());
			} else {
				List<StatusPeriodoEnum> statusPeriodoNaoPermitidoAlterar = new ArrayList<StatusPeriodoEnum>();
				statusPeriodoNaoPermitidoAlterar.add(StatusPeriodoEnum.APROVADO);
				
				if(statusPeriodoNaoPermitidoAlterar.contains(despesa.getPeriodo().getStatus())){
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Despesa não pode ser alterada!",
							"O período de cobrança desta despesa esta aguardando aprovação ou ja foi aprovada."));
					return;
				}
				
				BigDecimal valorOld = medicaoDespesaDAO.buscarPorID(despesa.getId()).getValor();
				if (limiteDisponivel.add(valorOld).subtract(despesa.getValor())
						.compareTo(new BigDecimal(0)) < 0) {
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor da despesa é maior que o valor disponível."));
					return;
				}
				medicaoDespesaDAO.alterar(despesa, usuarioLogado.getPessoa_id());
			}
			despesa = null;
			context.addMessage(null, new FacesMessage("Sucesso!", "Despesa salva com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void excluirDespesa(MedicaoDespesa despesaSelecionada) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			medicaoDespesaDAO.deletarPorId(despesaSelecionada.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!", "Despesa excluída com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void editarDespesa(MedicaoDespesa despesaSelecionada) {
		despesa = despesaSelecionada;
	}
	public void somaDespesas(MedicaoDespesa despesaSelecionada){
		
	}
	public void resetForm() {
		despesa = null;
	}
	

	public BigDecimal getValorTotal() {
		valorTotal = BigDecimal.ZERO;
		valorTotal = medicaoDespesaDAO.somaValorTotalDespesasProjeto(projetoSelecionado);
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isExibirDetalhes() {
		return exibirDetalhes;
	}
	public void setExibirDetalhes(boolean exibirDetalhes) {
		this.exibirDetalhes = exibirDetalhes;
	}
	public List<Projeto> getProjetos() {
		if(projetos==null){
			if(acessoDoUsuarioLogado.isAdministrador()){
				projetos = projetoDAO.listarTodos();
		}else{
			projetos = acessosUsuarioDAO.buscarAcessoDoUsuario(usuarioLogado.getPessoa_id())
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
	public List<MedicaoDespesa> getDespesas() {
		if(despesas==null)
		despesas = medicaoDespesaDAO.buscarTodosPorProjeto(projetoSelecionado);
		return despesas;
	}
	public void setDespesas(List<MedicaoDespesa> despesas) {
		this.despesas = despesas;
	}
	public MedicaoDespesa getDespesa() {
		if (despesa == null)
			despesa = new MedicaoDespesa();
		return despesa;
	}
	public void setDespesa(MedicaoDespesa despesa) {
		this.despesa = despesa;
	}
	public BigDecimal getLimiteDisponivel() {
		limiteDisponivel = projetoDAO.getLimiteDisponivelDespesa(projetoSelecionado);
		return limiteDisponivel;
	}
	public void setLimiteDisponivel(BigDecimal limiteDisponivel) {
		this.limiteDisponivel = limiteDisponivel;
	}
	public int getTabLista() {
		return tabLista;
	}
	public void setTabLista(int tabLista) {
		this.tabLista = tabLista;
	}
	public List<PeriodoMedicao> getPeriodos() {
		if (projetoSelecionado == null) {
			return null;
		}

		List<StatusPeriodoEnum> statusPermitido = new ArrayList<StatusPeriodoEnum>();
		statusPermitido.add(StatusPeriodoEnum.EMABERTO);
		statusPermitido.add(StatusPeriodoEnum.PENDENTEVALIDACAO);
		periodos = periodoDAO.buscarTodosPorProjetoStatus(projetoSelecionado, statusPermitido);
		return periodos;
	}
	public void setPeriodos(List<PeriodoMedicao> periodos) {
		this.periodos = periodos;
	}
	public List<Mobilizacao> getMobilizacoes() {
		if (projetoSelecionado == null) {
			return null;
		}else{
		mobilizacoes = mobilizacaoDAO.buscarTodosPorProjeto(projetoSelecionado);
		return mobilizacoes;
		}
	}

}
