package com.nucleo.projetos.cadastro.MB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.nucleo.Enum.EstadosBrasilEnum;
import com.nucleo.intranet.DAO.DepartamentoDAO;
import com.nucleo.intranet.DAO.FuncionarioDAO;
import com.nucleo.intranet.DAO.UnidadeDAO;
import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.AditivoDAO;
import com.nucleo.dao.CargoDAO;
import com.nucleo.dao.DespesaDAO;
import com.nucleo.dao.ImpostoDAO;
import com.nucleo.dao.InfraMateriaisDAO;
import com.nucleo.dao.ItemInfraMateriaisDAO;
import com.nucleo.dao.LocalExecucaoDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.PrevisaoUsoDAO;
import com.nucleo.dao.ProdutoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.ReajusteDAO;
import com.nucleo.dao.RenovacaoDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.endereco.DAO.EnderecoDAO;
import com.nucleo.entity.cadastro.Aditivo;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.Despesa;
import com.nucleo.entity.cadastro.Imposto;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.entity.cadastro.LocalExecucao;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Reajuste;
import com.nucleo.entity.cadastro.Renovacao;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.cadastro.Enum.AtividadeEnum;
import com.nucleo.entity.cadastro.Enum.SetorEnum;
import com.nucleo.entity.cadastro.Enum.StatusProjetoEnum;
import com.nucleo.entity.cadastro.Enum.TipoAditivoEnum;
import com.nucleo.entity.cadastro.Enum.TipoCalculoEnum;
import com.nucleo.entity.cadastro.Enum.TipoServicoEnum;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.exception.ImpostoJaCadastradoException;
import com.nucleo.seguranca.to.DepartamentoTO;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.UnidadeTO;
import com.nucleo.util.ObjetoSelecionado;

@Named
@ConversationScoped
public class CadastroProjetoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conversation conversation;
	
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	
	private AcessosUsuario acessoDoUsuarioLogado = Commom.getAcessoUsuarioLogado();
	
	private ObjetoSelecionado selecionado = new ObjetoSelecionado();
	
	private Projeto projetoSelecionado;
	@EJB
	private EnderecoDAO enderecoDAO;
	@EJB
	private PeriodoMedicaoDAO pd;
	@EJB
	private ProjetoDAO projetoDAO;
	@EJB
	private ImpostoDAO impostoDAO;
	@EJB
	private LocalExecucaoDAO localExecucaoDAO;
	@EJB
	private ServicoDAO servicoDAO;
	@EJB
	private InfraMateriaisDAO infraMateriaisDAO;
	@EJB
	private DespesaDAO despesaDAO;
	@EJB
	private AditivoDAO aditivoDAO;
	@EJB
	private RenovacaoDAO renovacaoDAO;
	@EJB
	private CargoDAO cargoDAO;
	@EJB
	private ProdutoDAO produtoDAO;
	@EJB
	private PrevisaoUsoDAO previsaoUsoDAO;
	@EJB
	private ItemInfraMateriaisDAO itemInfraDAO;
	@EJB
	private ReajusteDAO reajusteDAO;
	@EJB
	private FuncionarioDAO funcionarioDAO;
	@EJB
	private UnidadeDAO unidadeDAO;
	@EJB
	private DepartamentoDAO departamentoDAO;
	
	private BigDecimal valorComReajuste;
	
	private BigDecimal valorAtualComReajuste;
	
	private int prazoTotalDias;
	private BigDecimal valorAtual;
	private BigDecimal valorTotalAditivos;
	private Calendar dataFimAtual;
	private String totalPrazoAditivos;
	private BigDecimal saldo;
	private BigDecimal valorDisponivel;	
	
	private List<StatusProjetoEnum> statusProjeto;

	private List<SetorEnum> setores;
	private List<AtividadeEnum> ramos;
	private List<TipoCalculoEnum> calculos;

	@SuppressWarnings("unused")
	private List<EstadosBrasilEnum> estados;
	private EstadosBrasilEnum estado;
	private List<String> cidades;
	private String cidade;

	private List<Imposto> impostosDisp;
	private List<Imposto> impostosCadas;
	private int impostoSelec;

	private List<Aditivo> aditivos;
	private Aditivo aditivo;

	private List<LocalExecucao> locaisExecucao;

	private List<Renovacao> renovacoes;

	private List<TipoServicoEnum> tiposServico;
	private Servico servico;
	private Servico servicoSelecionado;
	private List<Servico> servicos;

	private InfraMateriais infraMateriais;
	private List<InfraMateriais> listaInfraMateriais;

	private Despesa despesa;
	private List<Despesa> despesas;

	private Reajuste reajuste;
	private List<Reajuste> reajustes;
	
	private List<UnidadeTO> unidades;
	private List<DepartamentoTO> departamentos;
	private List<FuncionarioTO> funcionarios;
	
	private boolean apenasLeitura=false;
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	
	private PermissoesMenuBean permissoesMenuBean;
	
	private int verificouAcesso = 0;
	public boolean isApenasLeitura() {
		if(verificouAcesso==0){
		if(acessoDoUsuarioLogado.isAdministrador()){
			apenasLeitura = false;
		}else{
		AcessosUsuario acessosUsuario = acessosUsuarioDAO.buscarAcessoDoUsuarioComMenu(usuarioLogado.getPessoa_id());
		if(permissoesMenuBean.apenasLeitura(acessosUsuario, "Cadastro de Projetos")){
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
	@PostConstruct
	public void init() {
		setores = Arrays.asList(SetorEnum.values());
		ramos = Arrays.asList(AtividadeEnum.values());
		calculos = Arrays.asList(TipoCalculoEnum.values());
		estados = Arrays.asList(EstadosBrasilEnum.values());
		statusProjeto = Arrays.asList(StatusProjetoEnum.values());
		tiposServico = Arrays.asList(TipoServicoEnum.values());
		permissoesMenuBean = new PermissoesMenuBean();
		cidades = new ArrayList<String>();
	}

	public void salvarInformacoesProjeto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			projetoDAO.alterar(projetoSelecionado, usuarioLogado.getPessoa_id());
			projetoSelecionado = projetoDAO.buscarPorID(projetoSelecionado.getId());
			ordenaPeriodosMedicao();
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Informações atualizadas com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Error!",
					"Contate o administrador do sistema."));
			e.printStackTrace();
		}
	}

	public void iniciar() {
		conversation.begin();
		// 30 minutos para expirar
		conversation.setTimeout(1800000);
	}

	public boolean temProjetoSelecionado() {
		return !conversation.isTransient();
	}

	public void finalizar() {
		conversation.end();
	}

	// métodos Responsável
	public void carregaFuncionarios() {
		int unidade = projetoSelecionado.getResponsavelAdm().getIdUnidade();
		funcionarios = new ArrayList<FuncionarioTO>();
		if (unidade > 0) {
				funcionarios = funcionarioDAO.getFuncionariosPorUnidade(unidade);
			}
		}
	// métodos Despesas reembolsaveis
	public void salvarDespesa() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			if (despesa.getId() == 0) {
				
				if(getValorDisponivel().subtract(despesa.getValor()).compareTo(new BigDecimal(0)) < 0 &&
						projetoSelecionado.isDespesaIntegraValor()){
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor da despesa é maior que o valor disponível no projeto."));
					return;
				}
				
				despesa.setProjeto(projetoSelecionado);
				despesaDAO.inserir(despesa, usuarioLogado.getPessoa_id());
			} else {
				
				BigDecimal valorOld = despesaDAO.buscarPorID(despesa.getId()).getValor();
				if(getValorDisponivel().add(valorOld).subtract(despesa.getValor()).compareTo(new BigDecimal(0)) < 0 &&
						projetoSelecionado.isDespesaIntegraValor()){
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor da despesa é maior que o valor disponível no projeto."));
					return;
				}
				
				despesaDAO.alterar(despesa, usuarioLogado.getPessoa_id());
			}
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Despesa reembolsável salva com sucesso."));
			despesa = new Despesa();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void selecionarDespesa(Despesa despesaSelecionada) {
		despesa = despesaSelecionada;
	}
	public void removerDespesa(Despesa despesa) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(projetoSelecionado.isLimiteDespesa()){
				if(projetoSelecionado.getMedicaoDespesas().size() > 0){
					BigDecimal valorMedido = BigDecimal.ZERO;
					for (MedicaoDespesa despesaMedida : projetoSelecionado.getMedicaoDespesas()) {
						valorMedido = valorMedido.add(despesaMedida.getValor());
					}
					BigDecimal valorDespesas = BigDecimal.ZERO;
					for (Despesa despesaReembol : getDespesas()) {
						valorDespesas = valorDespesas.add(despesaReembol.getValor());
					}
					if(valorMedido.compareTo(valorDespesas.subtract(despesa.getValor())) > 0){
						context.addMessage(null, new FacesMessage("Erro!",
								"A despesa não pode ser excluída porque o Projeto tem despesas medidas ja. " +
								"O valor das despesas cadastradas tem que ser maior que o valor das despesas medidas."));
						return;
					}
				}
			}
			
			despesaDAO.deletarPorId(despesa.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Despesa reembolsável excluida com sucesso."));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void limparDespesa(){
		despesa = new Despesa();
	}

	// métodos Infra-Materiais
	public void salvarInfraMateriais() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			if (infraMateriais.getId() == 0) {
				
				if(getValorDisponivel().subtract(infraMateriais.getValor()).compareTo(new BigDecimal(0)) < 0){
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor da Infra-Materiais é maior que o valor disponível no projeto."));
					return;
				}
				
				infraMateriais.setProjeto(projetoSelecionado);
				infraMateriaisDAO.inserir(infraMateriais, usuarioLogado.getPessoa_id());
			
			} else {
				BigDecimal valorOld = infraMateriaisDAO.buscarPorID(infraMateriais.getId()).getValor();
				if(getValorDisponivel().add(valorOld).subtract(infraMateriais.getValor()).compareTo(new BigDecimal(0)) < 0){
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor da Infra-Materiais é maior que o valor disponível no projeto."));
					return;
				}
				
				infraMateriaisDAO.alterar(infraMateriais, usuarioLogado.getPessoa_id());
			}
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Infra-Estrutura e Materiais salvo com sucesso."));
			infraMateriais = new InfraMateriais();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void selecionarInfraMateriais(InfraMateriais infraSelecionado) {
		infraMateriais = infraSelecionado;
	}
	public void removerInfraMateriais(InfraMateriais infraSelecionado) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			List<ItemInfraMateriais> itens = itemInfraDAO.buscarTodosPorInfraMateriais(infraSelecionado);
			if(infraMateriaisTemMedicao(itens)){
				context.addMessage(null, new FacesMessage("Erro!",
						"A Infra Estrutura - Materiais não pode excluído pois ja possui medições cadastradas."));
				return;
			}
			
			for (ItemInfraMateriais item : itens) {
				itemInfraDAO.deletarPorId(item.getId(), usuarioLogado.getPessoa_id());
			}
			
			infraMateriaisDAO.deletarPorId(infraSelecionado.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!",
					infraSelecionado.getDescricao() + " excluido com sucesso."));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	private boolean infraMateriaisTemMedicao(List<ItemInfraMateriais> itens){
		boolean retorno = false;
		for (ItemInfraMateriais item : itens) {
			if(item.getMedicoesInfraMateriais().size() > 0){
				retorno = true;
			}
		}
		return retorno;
	}
	public String detalharInfraMateriais(InfraMateriais infraSelecionado) {
		FacesContext instance = FacesContext.getCurrentInstance();
		ExternalContext externalContext = instance.getExternalContext();
		externalContext.getFlash().put("InfraSelecionado", infraSelecionado);
		externalContext.getFlash().setKeepMessages(true);

		return "infra_item.xhtml?faces-redirect=true";
	}
	public void limparInfraMateriais(){
		infraMateriais = new InfraMateriais();
	}

	// métodos serviços
	public void salvarServico() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (servico.getId() == 0) {

				if(getValorDisponivel().subtract(servico.getValorTotal()).compareTo(new BigDecimal(0)) < 0){
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor do serviço é maior que o valor disponível no projeto."));
					return;
				}
				servico.setProjeto(projetoSelecionado);
				servicoDAO.inserir(servico, usuarioLogado.getPessoa_id());
			} else {
				BigDecimal valorOld = servicoDAO.buscarPorID(servico.getId()).getValorTotal();
				if(getValorDisponivel().add(valorOld).subtract(servico.getValorTotal()).compareTo(new BigDecimal(0)) < 0){
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor do serviço é maior que o valor disponível no projeto."));
					return;
				}
				servicoDAO.alterar(servico, usuarioLogado.getPessoa_id());
			}
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Serviço salvo com sucesso."));
			servico = null;
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void selecionarServico(){
		if(selecionado.verificaSeFoiSelecionado(servicoSelecionado)){
		servico = servicoSelecionado;
		}
	}
	public void removerServico(ActionEvent actionEvent) {
		if(selecionado.verificaSeFoiSelecionado(servicoSelecionado)){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(servicoSelecionado.getTipo().equals(TipoServicoEnum.EQUIPE)){
					List<Cargo> cargos = cargoDAO.buscarTodosPorServico(servicoSelecionado);
			 if(servicoTemMobilizacao(cargos.size())){
				for (Cargo cargo : cargoDAO.buscarTodosPorServico(servicoSelecionado)) {
					cargoDAO.deletarPorId(cargo.getId(), usuarioLogado.getPessoa_id());
					List<PrevisaoUso> previsoes = previsaoUsoDAO.buscarTodosPorCargo(cargo);
					for (PrevisaoUso previsaoUso : previsoes) {
						previsaoUsoDAO.deletarPorId(previsaoUso.getId(), usuarioLogado.getPessoa_id());
					}
				}
			 }
			}
			if(servicoSelecionado.getTipo().equals(TipoServicoEnum.PRODUTO)){
				List<Produto> produtos = produtoDAO.buscarTodosPorServico(servicoSelecionado);
				if(servicoTemMedicoes(produtos)){
					context.addMessage(null, new FacesMessage("Erro!",
							"O serviço não pode ser excluído pois ja possui medições cadastradas."));
					return;
				}
				
				for (Produto produto : produtos) {
					produtoDAO.deletarPorId(produto.getId(), usuarioLogado.getPessoa_id());
					List<PrevisaoUso> previsoes = previsaoUsoDAO.buscarTodosPorProduto(produto);
					for (PrevisaoUso previsaoUso : previsoes) {
						previsaoUsoDAO.deletarPorId(previsaoUso.getId(), usuarioLogado.getPessoa_id());
					}
				}
			}
			
			servicoDAO.deletarPorId(servicoSelecionado.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Serviço excluido com sucesso."));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
		}
	}
	public String detalharServico() {
		String url = "";
		if(selecionado.verificaSeFoiSelecionado(servicoSelecionado)){
		FacesContext instance = FacesContext.getCurrentInstance();
		ExternalContext externalContext = instance.getExternalContext();
		externalContext.getFlash()
				.put("ServicoSelecionado", servicoSelecionado);
		externalContext.getFlash().setKeepMessages(true);

		if (servicoSelecionado.getTipo().equals(TipoServicoEnum.EQUIPE)) {
			url = "servico_equipe.xhtml?faces-redirect=true";
		}
		if (servicoSelecionado.getTipo().equals(TipoServicoEnum.PRODUTO)) {
			url = "servico_produto.xhtml?faces-redirect=true";
		}
		}
		return url;
	}
	public void limparServico(){
		servico = new Servico();
	}
	private boolean servicoTemMobilizacao(int size){
		boolean retorno = false;
			if(size > 0){
				retorno = true;
			}
		return retorno;
	}
	private boolean servicoTemMedicoes(List<Produto> produtos){
		boolean retorno = false;
		for (Produto produto : produtos) {
			if(produto.getMedicoesProduto().size() > 0){
				retorno = true;
			}
		}
		return retorno;
	}

	
	// métodos aditivo
	public void salvarAditivo(String tipo) {
		aditivo.setTipo(TipoAditivoEnum.valueOf(tipo));
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			aditivo.setProjeto(projetoSelecionado);
			aditivoDAO.inserir(aditivo, usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!", 
					"Aditivo salvo com sucesso."));
			aditivo = new Aditivo();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!", 
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void removerAditivo(Aditivo aditivo) {
		aditivoDAO.deletarPorId(aditivo.getId(), usuarioLogado.getPessoa_id());
	}
	public void calcularPorcent(AjaxBehaviorEvent e) {
		if (aditivo.getValor().toString().equals("0.00")){
			aditivo.setPorcentagem(BigDecimal.ZERO);
			return;
		}
		
		BigDecimal valorAditivo = aditivo.getValor();
		BigDecimal porcent = valorAditivo.divide(
				projetoSelecionado.getValorOriginal(), RoundingMode.CEILING);
		
		aditivo.setPorcentagem(porcent.multiply(new BigDecimal(100)));
	}
	public void calcularValor(AjaxBehaviorEvent e) {
		if (aditivo.getPorcentagem().toString().equals("0")){
			aditivo.setValor(new BigDecimal(0));
			return;
		}
			
		BigDecimal percent = aditivo.getPorcentagem().divide(
				new BigDecimal(100));
		aditivo.setValor(projetoSelecionado.getValorOriginal().multiply(
				percent, MathContext.UNLIMITED));
	}

	// métodos local execucao
	public void cadastrarLocal() {
		LocalExecucao local = new LocalExecucao();
		local.setProjeto(projetoSelecionado);
		local.setCidade(cidade);
		local.setEstado(estado.getNome());

		localExecucaoDAO.inserir(local, usuarioLogado.getPessoa_id());
	}
	public void removerLocal(LocalExecucao local) {
		localExecucaoDAO.deletarPorId(local.getId(), usuarioLogado.getPessoa_id());
	}
	public void carregaCidades() {
			cidades = new ArrayList<String>();
			List<String>list = enderecoDAO.buscarCidadePorUF(estado.getValor());
			for(String cidade:list){
				cidades.add(cidade);
			}
			
	}

	// métodos imposto
	public void cadastrarImposto(){
		try {
			projetoDAO.adicionarImposto(projetoSelecionado.getId(), impostoSelec, usuarioLogado.getPessoa_id());
			projetoSelecionado.getImpostos().add(impostoDAO.buscarPorID(impostoSelec));
		} catch (ImpostoJaCadastradoException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Atenção!",
				"Projeto ja possui este imposto cadastrado."));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void removerImposto(Imposto imposto) {
		try {
			projetoDAO.removerImposto(projetoSelecionado.getId(), imposto.getId(), usuarioLogado.getPessoa_id());
			projetoSelecionado.getImpostos().remove(imposto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// métodos renovação
	public void abrirRenovacao() {
		RequestContext.getCurrentInstance().openDialog("cadRenovacao");
	}
	public void cadastrarRenovacao(SelectEvent event) {
		Renovacao renovacao = (Renovacao) event.getObject();
		renovacao.setProjeto(projetoSelecionado);
		renovacaoDAO.inserir(renovacao, usuarioLogado.getPessoa_id());
		RequestContext.getCurrentInstance().update("formInformacoes");
	}
	public void removerRenovacao(Renovacao renovacao) {
		renovacaoDAO.deletarPorId(renovacao.getId(), usuarioLogado.getPessoa_id());
	}
	
	// métodos reajuste
	public void cadastrarReajuste() {
		reajuste.setProjeto(projetoSelecionado);
		reajusteDAO.inserir(reajuste, usuarioLogado.getPessoa_id());
		reajuste = null;
	}
	public void removerReajuste(Reajuste reajuste) {
		reajusteDAO.deletarPorId(reajuste.getId(), usuarioLogado.getPessoa_id());
	}

	// gets e setters
	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}
	public BigDecimal getValorComReajuste() {
		try{
		Reajuste ultimoReajuste = reajusteDAO.buscarUltimoValido(projetoSelecionado);
		valorComReajuste = projetoSelecionado.getValorOriginal().multiply(ultimoReajuste.getIndice());
		}catch(NullPointerException e){
			valorComReajuste = new BigDecimal(0);
		}
		return valorComReajuste;
	}

	public BigDecimal getValorAtualComReajuste() {
		try{
		Reajuste ultimoReajuste = reajusteDAO.buscarUltimoValido(projetoSelecionado);
		valorAtualComReajuste = projetoSelecionado.getValorOriginal().multiply(ultimoReajuste.getIndice());
		}catch(NullPointerException e){
			valorAtualComReajuste = new BigDecimal(0);
		}
		return valorAtualComReajuste;
	}

	public void setValorComReajuste(BigDecimal valorComReajuste) {
		this.valorComReajuste = valorComReajuste;
	}

	public void setValorAtualComReajuste(BigDecimal valorAtualComReajuste) {
		this.valorAtualComReajuste = valorAtualComReajuste;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}
	public List<Aditivo> getAditivos() {
		aditivos = aditivoDAO.buscarTodosPorProjeto(projetoSelecionado);
		return aditivos;
	}
	public void setAditivos(List<Aditivo> aditivos) {
		this.aditivos = aditivos;
	}
	public Aditivo getAditivo() {
		if (aditivo == null) {
			aditivo = new Aditivo();
		}
		return aditivo;
	}
	public void setAditivo(Aditivo aditivo) {
		this.aditivo = aditivo;
	}
	public List<SetorEnum> getSetores() {
		return setores;
	}
	public void setSetores(List<SetorEnum> setores) {
		this.setores = setores;
	}
	public List<AtividadeEnum> getRamos() {
		return ramos;
	}
	public void setRamos(List<AtividadeEnum> ramos) {
		this.ramos = ramos;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public List<String> getCidades() {
		if (cidades == null)
			cidades = new ArrayList<String>();
		return cidades;
	}
	public void setCidades(List<String> cidades) {
		this.cidades = cidades;
	}
	public EstadosBrasilEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadosBrasilEnum estado) {
		this.estado = estado;
	}
	public EstadosBrasilEnum[] getEstados() {
		return EstadosBrasilEnum.values();
	}
	public void setEstados(List<EstadosBrasilEnum> estados) {
		this.estados = estados;
	}
	public List<LocalExecucao> getLocaisExecucao() {
		locaisExecucao = localExecucaoDAO.buscarTodosPorProjeto(projetoSelecionado);
		return locaisExecucao;
	}
	public void setLocaisExecucao(List<LocalExecucao> locaisExecucao) {
		this.locaisExecucao = locaisExecucao;
	}
	public List<Imposto> getImpostosDisp() {
		if (impostosDisp == null) {
			impostosDisp = impostoDAO.buscarTodos();
			this.getImpostosCadas();
			for (Imposto imposto : impostosCadas) {
				impostosDisp.remove(imposto);
			}
		}
		return impostosDisp;
	}
	public void setImpostosDisp(List<Imposto> impostosDisp) {
		this.impostosDisp = impostosDisp;
	}
	public List<Imposto> getImpostosCadas() {
		impostosCadas = projetoSelecionado.getImpostos();
		return impostosCadas;
	}
	public void setImpostosCadas(List<Imposto> impostosCadas) {
		this.impostosCadas = impostosCadas;
	}
	public int getImpostoSelec() {
		return impostoSelec;
	}
	public void setImpostoSelec(int impostoSelec) {
		this.impostoSelec = impostoSelec;
	}
	public List<StatusProjetoEnum> getStatusProjeto() {
		return statusProjeto;
	}
	public void setStatusProjeto(List<StatusProjetoEnum> statusProjeto) {
		this.statusProjeto = statusProjeto;
	}
	public List<Renovacao> getRenovacoes() {
		renovacoes = renovacaoDAO.buscarTodosPorProjeto(projetoSelecionado);
		return renovacoes;
	}
	public void setRenovacoes(List<Renovacao> renovacoes) {
		this.renovacoes = renovacoes;
	}
	public List<TipoServicoEnum> getTiposServico() {
		return tiposServico;
	}
	public void setTiposServico(List<TipoServicoEnum> tiposServico) {
		this.tiposServico = tiposServico;
	}
	public Servico getServico() {
		if (servico == null) {
			servico = new Servico();
			servico.setDataInicial(projetoSelecionado.getDataAberturaOIS());
			servico.setDataFim(projetoSelecionado.getDataFechamentoOIS());
		}
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public List<Servico> getServicos() {
		servicos = servicoDAO.buscarTodosPorProjeto(projetoSelecionado);
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public InfraMateriais getInfraMateriais() {
		if (infraMateriais == null) {
			infraMateriais = new InfraMateriais();
		}
		return infraMateriais;
	}
	public void setInfraMateriais(InfraMateriais infraMateriais) {
		this.infraMateriais = infraMateriais;
	}
	public List<InfraMateriais> getListaInfraMateriais() {
		listaInfraMateriais = infraMateriaisDAO
				.buscarTodosPorProjeto(projetoSelecionado);
		return listaInfraMateriais;
	}
	public void setListaInfraMateriais(List<InfraMateriais> listaInfraMateriais) {
		this.listaInfraMateriais = listaInfraMateriais;
	}
	public Despesa getDespesa() {
		if (despesa == null) {
			despesa = new Despesa();
		}
		return despesa;
	}
	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
	public List<Despesa> getDespesas() {
		despesas = despesaDAO.buscarTodosPorProjeto(projetoSelecionado);
		return despesas;
	}
	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}
	public List<UnidadeTO> getUnidades() {
		if (unidades == null) {
				unidades = unidadeDAO.getTodasUnidades();
		}
		return unidades;
	}
	public void setUnidades(List<UnidadeTO> unidades) {
		this.unidades = unidades;
	}
	public List<DepartamentoTO> getDepartamentos() {
		if (departamentos == null) {
				departamentos = departamentoDAO.getTodosDepartamentos();
		}
		return departamentos;
	}
	public void setDepartamentos(List<DepartamentoTO> departamentos) {
		this.departamentos = departamentos;
	}
	public List<FuncionarioTO> getFuncionarios() {
		if (funcionarios == null) {
			int unidade = projetoSelecionado.getResponsavelAdm().getIdUnidade();

			if (unidade > 0) {
					funcionarios = funcionarioDAO.getFuncionariosPorUnidade(unidade);
			} else {
				funcionarios = new ArrayList<FuncionarioTO>();
			}
		}
		return funcionarios;
	}
	public void setFuncionarios(List<FuncionarioTO> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public FuncionarioTO getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(FuncionarioTO usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getPrazoTotalDias(){
		prazoTotalDias = projetoDAO.getPrazoTotalDias(projetoSelecionado);
		return prazoTotalDias;
	}
	public void setPrazoTotalDias(int prazoTotalDias){
		this.prazoTotalDias = prazoTotalDias;
	}
	public BigDecimal getValorAtual(){
		valorAtual = projetoDAO.getValorAtual(projetoSelecionado);
		return valorAtual;
	}
	public void setValorAtual(BigDecimal valorAtual){
		this.valorAtual = valorAtual;
	}
	public BigDecimal getValorTotalAditivos(){
		valorTotalAditivos = projetoDAO.getValorTotalAditivos(projetoSelecionado);
		return valorTotalAditivos;
	}
	public void setValorTotalAditivos(BigDecimal valorTotalAditivos){
		this.valorTotalAditivos = valorTotalAditivos;
	}
	public Calendar getDataFimAtual(){
		dataFimAtual = projetoDAO.getDataFimAtual(projetoSelecionado);
		return dataFimAtual;
	}
	public void setDataFimAtual(Calendar dataFimAtual){
		this.dataFimAtual = dataFimAtual;
	}
	public String getTotalPrazoAditivos(){
		totalPrazoAditivos = projetoDAO.getTotalPrazoAditivos(projetoSelecionado);
		return totalPrazoAditivos;
	}
	public void setTotalPrazoAditivos(String totalPrazoAditivos){
		this.totalPrazoAditivos = totalPrazoAditivos;
	}
	public BigDecimal getSaldo(){
		return saldo;
	}
	public void setSaldo(BigDecimal saldo){
		this.saldo = saldo;
	}
	public BigDecimal getValorDisponivel(){
		valorDisponivel = projetoDAO.getValorDisponivel(projetoSelecionado);
		return valorDisponivel;
	}
	public void setValorDisponivel(BigDecimal valorDisponivel){
		this.valorDisponivel = valorDisponivel;
	}
	public Servico getServicoSelecionado(){
		return servicoSelecionado;
	}
	public void setServicoSelecionado(Servico servicoSelecionado){
		this.servicoSelecionado = servicoSelecionado;
	}
	public List<TipoCalculoEnum> getCalculos() {
		return calculos;
	}
	public void setCalculos(List<TipoCalculoEnum> calculos) {
		this.calculos = calculos;
	}
	public Reajuste getReajuste() {
		if(reajuste == null){
			reajuste = new Reajuste();
		}
		return reajuste;
	}
	public List<Reajuste> getReajustes() {
		reajustes = reajusteDAO.buscarTodosPorProjeto(projetoSelecionado);
		return reajustes;
	}
	public void setReajuste(Reajuste reajuste) {
		this.reajuste = reajuste;
	}
	public void setReajustes(List<Reajuste> reajustes) {
		this.reajustes = reajustes;
	}
	public void ordenaPeriodosMedicao(){
			int numSeq =1;
			for(PeriodoMedicao medicao: pd.buscarTodosPorProjeto(projetoSelecionado)){	
				medicao.setNumSequencial(numSeq);
				pd.alterar(medicao, 245);
				numSeq++;
		}
	}

}
