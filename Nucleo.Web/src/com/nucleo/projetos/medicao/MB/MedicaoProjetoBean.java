package com.nucleo.projetos.medicao.MB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.AlteracaoPeriodoMedicaoDAO;
import com.nucleo.dao.CargoDAO;
import com.nucleo.dao.DetalhamentoPeriodoMedicaoDAO;
import com.nucleo.dao.InfraMateriaisDAO;
import com.nucleo.dao.ItemInfraMateriaisDAO;
import com.nucleo.dao.JustificativaDAO;
import com.nucleo.dao.MedicaoDespesaDAO;
import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.MedicaoInfraMateriaisDAO;
import com.nucleo.dao.MedicaoProdutoDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProdutoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.ReajusteDAO;
import com.nucleo.dao.RelatoriosRMSGeradosDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.cadastro.Reajuste;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.medicao.AlteracaoPeriodoMedicao;
import com.nucleo.entity.medicao.DetalhamentoPeriodoMedicao;
import com.nucleo.entity.medicao.Justificativa;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.MedicaoInfraMateriais;
import com.nucleo.entity.medicao.MedicaoProduto;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.RelatoriosRMSGerados;
import com.nucleo.entity.medicao.Enum.MotivoAlteracaoPeriodoEnum;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;
import com.nucleo.model.VO.DatasPeriodoMedicaoVO;
import com.nucleo.projetos.cadastro.MB.PermissoesMenuBean;
import com.nucleo.projetos.relatorios.model.EquipeModel;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.util.CarregaMedicoesUtil;
import com.nucleo.util.ObjetoSelecionado;

@ManagedBean
@ViewScoped
public class MedicaoProjetoBean implements Serializable {

	@PostConstruct
	public void init() {
		gerarPeriodos();
		valorTotalDespesas = BigDecimal.ZERO;
		acessoDoUsuarioLogado = Commom.getAcessoUsuarioLogado();
		permissoesMenuBean = new PermissoesMenuBean();
		relatorioBean = new RelatorioBean();
		selecionado = new ObjetoSelecionado();
		dataSelecionada = 0l;
		despesas = new ArrayList<MedicaoDespesa>();
		totalPeriodo = BigDecimal.ZERO;
	}
	private static final long serialVersionUID = 1L;
	@EJB
	private DetalhamentoPeriodoMedicaoDAO detalhamentoPeriodoMedicaoDAO;
	@EJB
	private AlteracaoPeriodoMedicaoDAO alteracaoPeriodoMedicaoDAO;
	@EJB
	private PeriodoMedicaoDAO periodoDAO;
	@EJB
	private ProjetoDAO projetoDAO;
	@EJB
	private InfraMateriaisDAO infraDAO;
	@EJB
	private MedicaoInfraMateriaisDAO medicaoInfraDAO;
	@EJB
	private ItemInfraMateriaisDAO itemInfraDAO;
	@EJB
	private ServicoDAO servicoDAO;
	@EJB
	private MedicaoDespesaDAO medicaoDespesaDAO;
	@EJB
	private MedicaoEquipeDAO medicaoEquipeDAO;
	private MedicaoProdutoDAO medicaoProdutoDAO;
	@EJB
	private CargoDAO cargoDAO;
	@EJB
	private AlteracaoPeriodoMedicaoDAO alteracaoPeriodoDAO;
	@EJB
	private ProdutoDAO produtoDAO;
	@EJB
	private JustificativaDAO justificativaDAO;
	@EJB
	private MobilizacaoDAO mobilizacaoDAO;
	@EJB
	private CarregaMedicoesUtil medicoesUtil;
	@EJB
	private RelatoriosRMSGeradosDAO relatorioGeradoDAO;
	@EJB
	private ReajusteDAO reajusteDAO;
	
	private RelatoriosRMSGerados relatorioEscolhido;
	private MedicaoEquipe equipeSelect;
	private AcessosUsuario acessoDoUsuarioLogado;
	private PermissoesMenuBean permissoesMenuBean;
	private StreamedContent relatorioPeriodo;
	private AlteracaoPeriodoMedicao alteracaoPeriodo;
	private ObjetoSelecionado selecionado;
	private Mobilizacao mobilizacao;
	private DetalhamentoPeriodoMedicao detalhamentoPeriodoMedicao;
	private Reajuste ultimoReajuste;
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	private List<DatasPeriodoMedicaoVO>periodosParaDataDe;
	private List<PeriodoMedicao> periodosAberto;
	private List<PeriodoMedicao> periodosValidacao;
	private List<PeriodoMedicao> periodosAprovacao;
	private List<PeriodoMedicao> periodosAprovado;
	private List<AlteracaoPeriodoMedicao>alteracaoPeriodoMedicoes;
	

	private PeriodoMedicao periodoAberto;
	private PeriodoMedicao periodoValidacao;
	private PeriodoMedicao periodoAprovacao;
	private PeriodoMedicao periodoSelecionado;
	private RelatorioBean relatorioBean;
	
	private BigDecimal valorProjeto;
	private BigDecimal saldoProjeto;
	private BigDecimal valorTotalDespesas;
	private BigDecimal totalPeriodo;
	
	private long dataSelecionada;
	
	private double diasAtestado;
	private double diasInjustificado;
	private double diasFerias;
	private double diasOutros;
	private double diasJustificados;
	private double diasTrabalhados;
	
	private boolean exibirDetalhes = false;
	private boolean apenasLeitura = false;
	private boolean verificouAcesso = false;
	private boolean exibirEquipes = false;
	
	private int tabLista = 0;

	private List<MedicaoDespesa> despesas;
	private List<InfraMateriais> infraMateriais;
	private List<Servico> servicoEquipes;
	private List<Servico> servicoProdutos;
	private List<MotivoAlteracaoPeriodoEnum> motivosAlteracao;
	private List<RelatoriosRMSGerados>relatoriosRMSgerados;
	
	private Map<Integer, List<MedicaoInfraMateriais>> medicoesInfra;
	private Map<Integer, List<MedicaoEquipe>> medicoesEquipe;
	private Map<Integer, List<MedicaoProduto>> medicoesProduto;
	
	public List<DatasPeriodoMedicaoVO> getPeriodoParaDataDe() {
		if(periodosParaDataDe==null){
			periodosParaDataDe = periodoDAO.buscarDatasDe();
		}
		return periodosParaDataDe;
	}

	public void setPeriodoParaDataDe(List<DatasPeriodoMedicaoVO> periodoParaDataDe) {
		this.periodosParaDataDe = periodoParaDataDe;
	}

	public long getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(long dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public StreamedContent getRelatorioPeriodo() {
		if(selecionado.verificaSeFoiSelecionado(periodoAprovacao)){
		relatorioPeriodo = relatorioBean.buscarRelatorio(periodoAprovacao);
		}
		return relatorioPeriodo;
	}

	public void setRelatorioPeriodo(StreamedContent relatorioPeriodo) {
		this.relatorioPeriodo = relatorioPeriodo;
	}

	public double getDiasAtestado() {
		return diasAtestado;
	}

	public double getDiasInjustificado() {
		return diasInjustificado;
	}

	
	public double getDiasFerias() {
		return diasFerias;
	}

	public double getDiasOutros() {
		return diasOutros;
	}

	public void setDiasAtestado(double diasAtestado) {
		this.diasAtestado = diasAtestado;
	}

	public void setDiasInjustificado(double diasInjustificado) {
		this.diasInjustificado = diasInjustificado;
	}

	public void setDiasFerias(double diasFerias) {
		this.diasFerias = diasFerias;
	}

	public void setDiasOutros(double diasOutros) {
		this.diasOutros = diasOutros;
	}
	public RelatoriosRMSGerados getRelatorioEscolhido() {
		return relatorioEscolhido;
	}

	public void setRelatorioEscolhido(RelatoriosRMSGerados relatorioEscolhido) {
		this.relatorioEscolhido = relatorioEscolhido;
	}
	public List<RelatoriosRMSGerados> getRelatoriosRMSgerados() {
		if(relatoriosRMSgerados==null){
			relatoriosRMSgerados = relatorioGeradoDAO.listarTodos();
		}
		return relatoriosRMSgerados;
	}

	public void setRelatoriosRMSgerados(List<RelatoriosRMSGerados> relatoriosRMSgerados) {
		this.relatoriosRMSgerados = relatoriosRMSgerados;
	}

	public Mobilizacao getMobilizacao() {
		return mobilizacao;
	}

	public void setMobilizacao(Mobilizacao mobilizacao) {
		this.mobilizacao = mobilizacao;
	}

	public BigDecimal getTotalPeriodo() {
		return totalPeriodo;
	}

	public void setTotalPeriodo(BigDecimal totalPeriodo) {
		this.totalPeriodo = totalPeriodo;
	}

	public double getDiasTrabalhados() {
		return diasTrabalhados;
	}

	public void setDiasTrabalhados(double diasTrabalhados) {
		this.diasTrabalhados = diasTrabalhados;
	}

	public double getDiasJustificados() {
		return diasJustificados;
	}

	public void setDiasJustificados(double diasJustificados) {
		this.diasJustificados = diasJustificados;
	}
	private Justificativa justificativa = new Justificativa();

	public Justificativa getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(Justificativa justificativa) {
		this.justificativa = justificativa;
	}
	
	public List<AlteracaoPeriodoMedicao> getAlteracaoPeriodoMedicoes() {
		return alteracaoPeriodoMedicoes;
	}

	public void setAlteracaoPeriodoMedicoes(List<AlteracaoPeriodoMedicao> alteracaoPeriodoMedicoes) {
		this.alteracaoPeriodoMedicoes = alteracaoPeriodoMedicoes;
	}
	public DetalhamentoPeriodoMedicao getDetalhamentoPeriodoMedicao() {
		return detalhamentoPeriodoMedicao;
	}
	public void setDetalhamentoPeriodoMedicao(DetalhamentoPeriodoMedicao detalhamentoPeriodoMedicao) {
		this.detalhamentoPeriodoMedicao = detalhamentoPeriodoMedicao;
	}
	public boolean isApenasLeitura() {
		if(!verificouAcesso){
		if(acessoDoUsuarioLogado.isAdministrador()){
			apenasLeitura = false;
		}else{
		AcessosUsuario acessosUsuario = acessosUsuarioDAO.buscarAcessoDoUsuarioComMenu(usuarioLogado.getPessoa_id());
		if(permissoesMenuBean.apenasLeitura(acessosUsuario, "Lançar Medições")){
			apenasLeitura = true;
			}
		}
		verificouAcesso=true;
		}
		return apenasLeitura;
	}

	public void setApenasLeitura(boolean apenasLeitura) {
		this.apenasLeitura = apenasLeitura;
	}
	private void gerarPeriodos(){
		periodosAberto = periodoDAO.buscarPeriodosEmAberto();
		List<StatusPeriodoEnum>status = new ArrayList<StatusPeriodoEnum>();
		status.add(StatusPeriodoEnum.PENDENTEVALIDACAO);
		periodosValidacao = periodoDAO.buscarTodosPorStatus(status);
		status.clear();
		status.add(StatusPeriodoEnum.PENDENTEAPROVACAO);
		periodosAprovacao = periodoDAO.buscarTodosPorStatus(status);
		status.clear();
		status.add(StatusPeriodoEnum.APROVADO);
		periodosAprovado =  periodoDAO.buscarTodosPorStatus(status);
	}
	private boolean reenderizaJustificativa = false;
	public boolean isReenderizaJustificativa() {
		return reenderizaJustificativa;
	}

	public void setReenderizaJustificativa(boolean reenderizaJustificativa) {
		this.reenderizaJustificativa = reenderizaJustificativa;
	}
	private double diasDevidos;
	public double getDiasDevidos() {
		return diasDevidos;
	}

	public void setDiasDevidos(double diasDevidos) {
		this.diasDevidos = diasDevidos;
	}
	private int idEquipe;
	public void pegaJustificativa(){
		justificativa = new Justificativa();
		justificativa = justificativaDAO.buscarPorMedicaoEquipe(equipeSelect);
		if (reenderizaJustificativa) {
			reenderizaJustificativa = false;
		} else {
			reenderizaJustificativa = true;
		}
	}
	public boolean justificado(MedicaoEquipe medicaoEquipe) {
		boolean justificado = false;
		justificativa = justificativaDAO.buscarPorMedicaoEquipe(medicaoEquipe);
		if (justificativa.getId() != 0) {
			justificado = true;
		}
		System.out.println(justificado);
		return justificado;
	}
	public void pegaMedicao() {
		justificativa = new Justificativa();
		reenderizaJustificativa = false;
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		int idMedicao = Integer.parseInt(map.get("medicao"));
		double valorDigitado = Double.valueOf(map.get("valorDigitado"));
		idEquipe = Integer.parseInt(map.get("equipe"));
		equipeSelect = medicaoEquipeDAO.buscarMedicao(idMedicao);
		diasTrabalhados = valorDigitado;
		double baseCalculo = periodoSelecionado.getBaseCalculo().doubleValue();
		diasDevidos = baseCalculo - valorDigitado;
		if (valorDigitado < baseCalculo) {
			if (!justificado(equipeSelect)) {
				reenderizaJustificativa = true;
			} else if (justificado(equipeSelect)) {
				justificativa = justificativaDAO.buscarPorMedicaoEquipe(equipeSelect);
				if (justificativaDAO.somaJustificativas(justificativa).doubleValue() < diasDevidos) {
					reenderizaJustificativa = true;
				} else {
					reenderizaJustificativa = false;
				}
			}
		}
	}
	public void alteraQtdMedidoCurrentObject(BigDecimal quantidadeMedido) {
		int id = 0;
		try {
			List<MedicaoEquipe> list = medicoesEquipe.get(idEquipe);
			for (MedicaoEquipe x : list) {
				if (x.getId() == equipeSelect.getId()) {
					x.setQuantidadeMedido(quantidadeMedido);
					medicoesEquipe.get(idEquipe).set(id, x);
					medicaoEquipeDAO.alterar(x, usuarioLogado.getPessoa_id());
				}
				id++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public String reprovaPeriodoMedicao() {
		periodoSelecionado.setStatus(StatusPeriodoEnum.EMABERTO);
		periodoDAO.alterar(periodoSelecionado, usuarioLogado.getPessoa_id());
		exibirDetalhes = false;
		return "medicaoProjeto.xhtml?faces-redirect=true";
	}
	public void salvarJustificativa() {
		try {
			diasJustificados = justificativa.getDiasAtestado().doubleValue()+
					justificativa.getDiasFerias().doubleValue()+
					justificativa.getDiasInjustificado().doubleValue()+
					justificativa.getDiasOutros().doubleValue();
			if (diasJustificados >= diasDevidos) {
				equipeSelect.setQuantidadeMedido(BigDecimal.valueOf(this.diasTrabalhados));
				justificativa.setFaltas(BigDecimal.valueOf(diasDevidos));
				justificativa.setDiasTrabalhados(BigDecimal.valueOf(diasTrabalhados));
				justificativa.setMedicaoEquipe(equipeSelect);
				justificativaDAO.salvarJustificativa(justificativa, usuarioLogado.getPessoa_id());
				justificativa = new Justificativa();
				diasAtestado = 0;
				diasDevidos = 0;
				diasFerias = 0;
				diasInjustificado = 0;
				diasJustificados = 0;
				diasOutros = 0;
				reenderizaJustificativa = false;
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage("Justifique todos os dias devidos");
				context.addMessage("", message);
				reenderizaJustificativa = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	public void pegaLogVersoes(){
			if(selecionado.verificaSeFoiSelecionado(periodoAprovacao)){
		alteracaoPeriodoMedicoes = alteracaoPeriodoDAO.buscarPorPeriodo(periodoAprovacao);
			}
	}
	public MedicaoEquipe getEquipeSelect() {
		return equipeSelect;
	}

	public void setEquipeSelect(MedicaoEquipe equipeSelect) {
		this.equipeSelect = equipeSelect;
	}
	public void selecionarAberto() {
		if(selecionado.verificaSeFoiSelecionado(periodoAberto))
		processarPeriodo(periodoAberto);
	}
	public void selecionarValidacao() {
		   if(selecionado.verificaSeFoiSelecionado(periodoValidacao))
			processarPeriodo(periodoValidacao);
	}
	public void selecionarAprovacao() {
		if(selecionado.verificaSeFoiSelecionado(periodoAprovacao))
			processarPeriodo(periodoAprovacao);
		
	}
	public BigDecimal getValorTotalDespesas() {
		valorTotalDespesas = BigDecimal.ZERO;
		detalhamentoPeriodoMedicao.setTotalDespesa(BigDecimal.ZERO);
		valorTotalDespesas = medicaoDespesaDAO.somaValorDespesasPeriodo(periodoSelecionado);
		detalhamentoPeriodoMedicao.setTotalDespesa(valorTotalDespesas);
		return valorTotalDespesas;
	}
	public void setValorTotalDespesas(BigDecimal valorTotalDespesas) {
		this.valorTotalDespesas = valorTotalDespesas;
	}
	private void processarPeriodo(PeriodoMedicao periodo) {
		periodoSelecionado = periodo;
		carregaMedicoesInfra();
		carregaServicoEquipes();
		carregaServicoProdutos();
		this.detalhamentoPeriodoMedicao= detalhamentoPeriodoMedicaoDAO.buscarPorPeriodo(periodoSelecionado);
		ultimoReajuste = reajusteDAO.buscarUltimoValido(periodo.getProjeto());
		tabLista = 10;
		exibirDetalhes = true;

		valorProjeto = projetoDAO.getValorAtual(periodoSelecionado.getProjeto());
		saldoProjeto = projetoDAO.getSaldo(periodoSelecionado.getProjeto());
	}
	private boolean salvarDetalhamentoMedicao(){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean salvo = false;
				try{
					try{
					if(detalhamentoPeriodoMedicao.getTotalDespesa().compareTo(BigDecimal.ZERO)>0){
					}
				}catch(NullPointerException e){
					detalhamentoPeriodoMedicao.setTotalDespesa(BigDecimal.ZERO);
				}
				detalhamentoPeriodoMedicao.getTotalMedicaoI0().add(detalhamentoPeriodoMedicao.getTotalDespesa());
				detalhamentoPeriodoMedicao.setTotalReajuste(detalhamentoPeriodoMedicao.getTotalMedicaoI0().divide(ultimoReajuste.getIndice(),5).subtract(detalhamentoPeriodoMedicao.getTotalMedicaoI0()));
				detalhamentoPeriodoMedicao.setMedicaoComReajuste(detalhamentoPeriodoMedicao.getTotalMedicaoI0().add(detalhamentoPeriodoMedicao.getTotalDespesa()).add(detalhamentoPeriodoMedicao.getTotalReajuste()));
				detalhamentoPeriodoMedicao.setTotalSalarios(medicaoEquipeDAO.buscarSalariosMedicoesPorPeriodo(periodoSelecionado));
				detalhamentoPeriodoMedicao.setTotalValorVenda(medicaoEquipeDAO.buscarValorVendaMedicoesPorPeriodo(periodoSelecionado));
				if(detalhamentoPeriodoMedicao.getId()==0){
					detalhamentoPeriodoMedicao.setPeriodoMedicao(periodoSelecionado);
					detalhamentoPeriodoMedicaoDAO.salvarDetalhamentoMedicao(detalhamentoPeriodoMedicao, usuarioLogado.getPessoa_id());
					context.addMessage(null, new FacesMessage("Sucesso!",
							"Medições dos funcionários salvo com sucesso."));
					salvo = true;
			 }else{
				 detalhamentoPeriodoMedicaoDAO.alterar(detalhamentoPeriodoMedicao, usuarioLogado.getPessoa_id());
				 salvo = true;
			 }
			}catch(Exception e){
				System.out.println(e);
				salvo = false;
			}finally{
				detalhamentoPeriodoMedicao = new DetalhamentoPeriodoMedicao();
			}
		return salvo;
	}
	// WORKFLOW
	public String salvarApenas() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!salvarInfraMateriais())
				return "";
			if (!salvarMedicoesProduto())
				return"";
			if (!salvarMedicoesEquipe())
				return"";
			if(!salvarDetalhamentoMedicao())
				return "";
			exibirDetalhes = false;
			tabLista = 10;
			Commom.removeMessagesContext();
			context.addMessage(
					null,
					new FacesMessage("Sucesso!",
							"Todos os dados da medição foram salvos, porém o período ainda permanece no status atual."));
			return "/faces/Projetos/Medicao/medicaoProjeto.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
			return null;
		}
	}
	public String salvarEnviarAvaliacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!salvarInfraMateriais()){
				System.out.println("!salvarInfraMateriais()");
				return null;
			}
			if (!salvarMedicoesProduto()){
				System.out.println("!salvarMedicoesProduto()");
				return null;
			}
			if (!salvarMedicoesEquipe()){
				System.out.println("!salvarMedicoesEquipe()");
				return null;
				
			}
			if(!salvarDetalhamentoMedicao()){
				System.out.println("!salvarDetalhamentoMedicao()");
				return null;
			}
			periodoSelecionado.setStatus(StatusPeriodoEnum.PENDENTEVALIDACAO);
			periodoDAO.alterar(periodoSelecionado, usuarioLogado.getPessoa_id());
			exibirDetalhes = false;
			tabLista = 10;

			Commom.removeMessagesContext();
			context.addMessage(
					null,
					new FacesMessage(
							"Sucesso!",
							"Todas as medições foram salvas e o período de medição foi enviado para avaliação da Medição e Controle."));
			return "/faces/Projetos/Medicao/medicaoProjeto.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
			return null;
		}
	}
	public String salvarGerarRelatoriaAprovacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!salvarInfraMateriais())
				return "";
			if (!salvarMedicoesProduto())
				return"";
			if (!salvarMedicoesEquipe())
				return "";
			if(!salvarDetalhamentoMedicao())
				return "";
			if (!relatorioBean.gerarRelatorio(periodoSelecionado, medicoesEquipe)) {
				context.addMessage(null, new FacesMessage("Erro ao gerar relatório!",
						"Por favor, contate o administrador do sistema. Relatório não foi gerado."));
				return"";
			}

			periodoSelecionado.setStatus(StatusPeriodoEnum.PENDENTEAPROVACAO);
			periodoDAO.alterar(periodoSelecionado, usuarioLogado.getPessoa_id());

			exibirDetalhes = false;
			tabLista = 10;

			Commom.removeMessagesContext();
			context.addMessage(
					null,
					new FacesMessage("Sucesso!",
							"Todas as medições foram salvas e o período de medição agora está aguardando aprovação do cliente."));
			return "/faces/Projetos/Medicao/medicaoProjeto.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
			return null;
		}
	}
	public void aprovarPeriodo() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			periodoSelecionado.setStatus(StatusPeriodoEnum.APROVADO);
			periodoDAO.alterar(periodoSelecionado, usuarioLogado.getPessoa_id());

			exibirDetalhes = false;
			tabLista = 10;

			Commom.removeMessagesContext();
			context.addMessage(null,
					new FacesMessage("Sucesso!", "O período " + periodoSelecionado.getId()
							+ " agora esta aguardando a geração da nota fiscal."));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public String salvarAlteracaoPeriodoAprovacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!salvarInfraMateriais())
				return"";
			if (!salvarMedicoesProduto())
				return"";
			if (!salvarMedicoesEquipe())
				return"";
			if(!salvarDetalhamentoMedicao())
				return "";
			if (!relatorioBean.gerarRelatorio(periodoSelecionado, medicoesEquipe)) {
				context.addMessage(null, new FacesMessage("Erro!",
						"Por favor, contate o administrador do sistema. Relatório não foi gerado."));
				return"";
			}

			if (alteracaoPeriodo.getMotivo().equals(MotivoAlteracaoPeriodoEnum.DOISMOTIVOS)) {
				AlteracaoPeriodoMedicao altCliente = new AlteracaoPeriodoMedicao();
				altCliente.setMotivo(MotivoAlteracaoPeriodoEnum.CLIENTE);
				altCliente.setObservacao(alteracaoPeriodo.getObservacao());
				altCliente.setPeriodoMedicao(periodoSelecionado);

				AlteracaoPeriodoMedicao altNucleo = new AlteracaoPeriodoMedicao();
				altNucleo.setMotivo(MotivoAlteracaoPeriodoEnum.NUCLEO);
				altNucleo.setObservacao(alteracaoPeriodo.getObservacao());
				altNucleo.setPeriodoMedicao(periodoSelecionado);

				alteracaoPeriodoDAO.inserir(altCliente, usuarioLogado.getPessoa_id());
				alteracaoPeriodoDAO.inserir(altNucleo, usuarioLogado.getPessoa_id());
			} else {
				alteracaoPeriodo.setPeriodoMedicao(periodoSelecionado);
				alteracaoPeriodoDAO.inserir(alteracaoPeriodo, usuarioLogado.getPessoa_id());
			}

			alteracaoPeriodo = null;

			exibirDetalhes = false;
			tabLista = 10;

			Commom.removeMessagesContext();
			context.addMessage(null,
					new FacesMessage("Sucesso!", "Todas as medições foram salvas."));
			return "/faces/Projetos/Medicao/medicaoProjeto.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
			return "";
		}

	}
	public StreamedContent baixarRelatorio(PeriodoMedicao periodoAprovacao){
		return relatorioBean.buscarRelatorio(periodoAprovacao);
	}
	public StreamedContent baixarRelatorioRMS(RelatoriosRMSGerados relatorioEscolhido) {
		return relatorioBean.baixarRelatorioRMS(relatorioEscolhido);
	}


	public String gerarVersao(PeriodoMedicao periodo) {
		return periodoDAO.getVersao(periodo);
	}

	public String gerarRelatorioRMS(){
		String destino="";
		try{
		Calendar data = Calendar.getInstance();
		data.setTimeInMillis(dataSelecionada);
		relatorioBean.gerarRelatorioRMS(data);
		destino="relatorios.xhtml?faces-redirect=true";
		}catch (Exception e) {
			System.out.println(e);
		}
		return destino;
		
	}
	// Infra Estrutura - Materiais
	private void carregaMedicoesInfra() {
		infraMateriais = infraDAO.buscarTodosPorProjeto(periodoSelecionado.getProjeto());
		medicoesInfra = new HashMap<Integer, List<MedicaoInfraMateriais>>();
		for (InfraMateriais infra : infraMateriais) {
			medicoesInfra.put(infra.getId(), medicoesInfraMateriais(infra));
		}
	}
	private List<MedicaoInfraMateriais> medicoesInfraMateriais(InfraMateriais infra) {

		List<MedicaoInfraMateriais> medicoesInfra = medicaoInfraDAO.buscarTodosPorInfraMateriais(
				infra, periodoSelecionado);
		if (medicoesInfra.size() == 0) {
			for (ItemInfraMateriais item : infraDAO.obterItens(infra)) {
				MedicaoInfraMateriais medicao = new MedicaoInfraMateriais();
				medicao.setItemInfraMateriais(item);
				medicao.setPeriodoMedicao(periodoSelecionado);

				medicaoInfraDAO.inserir(medicao, usuarioLogado.getPessoa_id());
				medicoesInfra.add(medicao);
			}
		}
		return medicoesInfra;
	}
	public BigDecimal saldoItemInfra(ItemInfraMateriais item) {
		BigDecimal saldo = item.getValor();
		for (InfraMateriais infra : infraMateriais) {
			for (MedicaoInfraMateriais medicao : medicoesInfra.get(infra.getId())) {
				if (medicao.getItemInfraMateriais().equals(item)) {
					if (medicao.getValorMedido() == null)
						continue;
					saldo = saldo.subtract(medicao.getValorMedido());
				}
			}
		}
		return saldo;
	}
	public void calcularPorcentagemItemInfra(MedicaoInfraMateriais medicao) {
		if (medicao.getValorMedido().equals(BigDecimal.ZERO)) {
			medicao.setQuantidadeMedida(new BigDecimal(0));
			return;
		}

		BigDecimal porcent = medicao.getValorMedido().divide(
				medicao.getItemInfraMateriais().getValor(), 6, RoundingMode.CEILING);
		medicao.setQuantidadeMedida(porcent.multiply(BigDecimal.valueOf(100)));
	}
	public void calcularValorItemInfra(MedicaoInfraMateriais medicao) {
		if (medicao.getQuantidadeMedida().equals(BigDecimal.ZERO)) {
			medicao.setValorMedido(BigDecimal.ZERO);
			return;
		}

		BigDecimal percent = medicao.getQuantidadeMedida().divide(BigDecimal.valueOf(100), 6,
				RoundingMode.CEILING);
		medicao.setValorMedido(medicao.getItemInfraMateriais().getValor()
				.multiply(percent, MathContext.UNLIMITED));
	}
	public boolean salvarInfraMateriais() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			for (InfraMateriais infra : infraMateriais) {
				for (MedicaoInfraMateriais medicao : medicoesInfra.get(infra.getId())) {
					if (saldoItemInfra(medicao.getItemInfraMateriais()).compareTo(BigDecimal.ZERO) < 0) {
						Commom.removeMessagesContext();
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Materiais - Infra Estrutura -> "
										+ medicao.getItemInfraMateriais().getDescricao(),
								"Valor medido não pode ultrapassar o saldo disponível."));
						return false;
					}
				}
			}
			for (InfraMateriais infra : infraMateriais) {
				for (MedicaoInfraMateriais medicao : medicoesInfra.get(infra.getId())) {
					medicaoInfraDAO.alterar(medicao, usuarioLogado.getPessoa_id());
				}
			}
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Medição da Infra Estrutura - Materiais salvo com sucesso."));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
			return false;
		}
	}

	// Serviço - PRODUTO
	private void carregaServicoProdutos() {
		servicoProdutos = servicoDAO.buscarProdutosPorProjeto(periodoSelecionado.getProjeto());
		medicoesProduto = new HashMap<Integer, List<MedicaoProduto>>();
		for (Servico produto : servicoProdutos) {
			medicoesProduto.put(produto.getId(), medicoesServicoProduto(produto));
		}
	}
	private List<MedicaoProduto> medicoesServicoProduto(Servico servico) {
		servico.setProdutos(produtoDAO.buscarTodosPorServico(servico));
		if (servico.getProdutos().isEmpty())
			return new ArrayList<MedicaoProduto>();

		ArrayList<MedicaoProduto> medicoes = new ArrayList<MedicaoProduto>();
		for (Produto produto : servico.getProdutos()) {
			MedicaoProduto medicao = medicaoProdutoDAO.buscarMedicaoPorProdutoPeriodo(
					periodoSelecionado, produto);
			if (medicao == null) {
				medicao = new MedicaoProduto();
				medicao.setGrupo(produto.isGrupo());
				medicao.setProduto(produto);
				medicao.setPeriodoMedicao(periodoSelecionado);
				medicao.setQuantidadeMedida(BigDecimal.ZERO);
				medicaoProdutoDAO.inserir(medicao, usuarioLogado.getPessoa_id());
				
			}
			medicoes.add(medicao);
		}

		Collections.sort(medicoes, new Comparator<MedicaoProduto>() {
			public int compare(MedicaoProduto p1, MedicaoProduto p2) {
				String codigoP1 = p1.getProduto().getCodigo();
				String codigoP2 = p2.getProduto().getCodigo();
				return codigoP1.compareToIgnoreCase(codigoP2);
			}
		});

		return medicoes;
	}
	public boolean salvarMedicoesProduto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			for (Servico produtos : servicoProdutos) {
				for (MedicaoProduto medicao : medicoesProduto.get(produtos.getId())) {
					if (medicao.getProduto().isGrupo())
						continue;
					if (saldoProduto(medicao.getProduto()).compareTo(BigDecimal.ZERO) < 0) {
						Commom.removeMessagesContext();
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Serviços -> Produtos -> " + medicao.getProduto().getDescricao(),
								"Quantidade medida não pode ultrapassar a quantidade disponível."));
						return false;
					}
				}
			}

			for (Servico produtos : servicoProdutos) {
				for (MedicaoProduto medicao : medicoesProduto.get(produtos.getId())) {
					medicaoProdutoDAO.alterar(medicao, usuarioLogado.getPessoa_id());
				}
			}
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Medições dos produtos salvo com sucesso."));
			return true;
		}catch(NullPointerException e){
			//Quando produtos for null
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
			return false;
		}
	}
	public BigDecimal saldoProduto(Produto produto) {
		BigDecimal saldo = produto.getQuantidade();
		for (Servico produtos : servicoProdutos) {
			for (MedicaoProduto medicao : medicoesProduto.get(produtos.getId())) {
				if (medicao.getProduto().equals(produto)) {
					if (medicao.isGrupo())
						continue;
					saldo = saldo.subtract(medicao.getQuantidadeMedida());
				}
			}
		}
		return saldo;
	}

	// Serviço - EQUIPE
	public void atualizarBaseCalculo() {
		try {
			periodoDAO.alterar(periodoSelecionado, usuarioLogado.getPessoa_id());
			carregaServicoEquipes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void carregaServicoEquipes() {
		servicoEquipes = null;
		exibirEquipes = false;

		if (periodoSelecionado.getBaseCalculo() != null
				&& !periodoSelecionado.getBaseCalculo().toString().equals("0.00")) {
			servicoEquipes = servicoDAO.buscarEquipesPorProjeto(periodoSelecionado.getProjeto());
			medicoesEquipe = new HashMap<Integer, List<MedicaoEquipe>>();
			medicoesEquipe = medicoesUtil.carregaMedicaoEquipes(periodoSelecionado, servicoEquipes);
			exibirEquipes = true;
		}
	}
	
	public boolean salvarMedicoesEquipe() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			for (Servico equipe : servicoEquipes) {
				for (MedicaoEquipe medicao : medicoesEquipe.get(equipe.getId())) {
					if (saldoCargo(medicao.getMobilizacao().getCargo()).compareTo(BigDecimal.ZERO) < 0) {
						Commom.removeMessagesContext();
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Atenção!", "Serviços -> Equipes -> Função (MD) "
										+ medicao.getMobilizacao().getCargo().getFuncaoMD()
										+ " ultrapassou o limite de medição disponível."));
						return false;
					}
				}
			}
			for (Servico equipe : servicoEquipes) {
				for (MedicaoEquipe medicao : medicoesEquipe.get(equipe.getId())) {
					MedicaoEquipe medicaoNew = medicaoEquipeDAO.buscarMedicao(medicao.getId());
					if(medicaoNew.getId()==0){
						medicaoEquipeDAO.inserir(medicao, usuarioLogado.getPessoa_id());
					}else{
					medicaoEquipeDAO.alterar(medicao, usuarioLogado.getPessoa_id());
					}
				}
			}
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Medições dos funcionários salvo com sucesso."));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
			return false;
		}
	}
	public BigDecimal saldoCargo(Cargo cargo) {
		BigDecimal saldo = cargo.getValorTotal();
		for (Servico equipe : servicoEquipes) {
			for (MedicaoEquipe medicao : medicoesEquipe.get(equipe.getId())) {
				if (medicao.getMobilizacao().getCargo().getId() == cargo.getId()) {
					saldo = saldo.subtract(medicao.getValorMedido());
				}
			}
		}
		return saldo;
	}
	public BigDecimal calcularTotalMedidoEquipe(Servico equipe) {
		BigDecimal totalEquipe = BigDecimal.ZERO;
		totalEquipe = servicoDAO.calcularTotalMedidoEquipe(equipe, periodoSelecionado,
				medicoesEquipe.get(equipe.getId()));
		totalPeriodo = totalPeriodo.add(totalEquipe);
		detalhamentoPeriodoMedicao.setTotalMedicaoI0(totalPeriodo);
		return totalEquipe;
	}
	
	public List<PeriodoMedicao> getPeriodosAberto() {
		return periodosAberto;
	}
	public void setPeriodosAberto(List<PeriodoMedicao> periodosAberto) {
		this.periodosAberto = periodosAberto;
	}
	public List<PeriodoMedicao> getPeriodosValidacao() {
		return periodosValidacao;
	}
	public void setPeriodosValidacao(List<PeriodoMedicao> periodosValidacao) {
		this.periodosValidacao = periodosValidacao;
	}
	public List<PeriodoMedicao> getPeriodosAprovacao() {
		return periodosAprovacao;
	}
	public void setPeriodosAprovacao(List<PeriodoMedicao> periodosAprovacao) {
		this.periodosAprovacao = periodosAprovacao;
	}
	public List<PeriodoMedicao> getPeriodosAprovado() {
		return periodosAprovado;
	}
	public void setPeriodosAprovado(List<PeriodoMedicao> periodosAprovado) {
		this.periodosAprovado = periodosAprovado;
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
	public BigDecimal getValorProjeto() {
		if (valorProjeto == null) {
			valorProjeto = new BigDecimal(0);
		}
		return valorProjeto;
	}
	public void setValorProjeto(BigDecimal valorProjeto) {
		this.valorProjeto = valorProjeto;
	}
	public BigDecimal getSaldoProjeto() {
		if (saldoProjeto == null) {
			saldoProjeto = new BigDecimal(0);
		}
		return saldoProjeto;
	}
	public void setSaldoProjeto(BigDecimal saldoProjeto) {
		this.saldoProjeto = saldoProjeto;
	}
	public PeriodoMedicao getPeriodoAberto() {
		if (periodoAberto == null) {
			periodoAberto = new PeriodoMedicao();
		}
		return periodoAberto;
	}
	public void setPeriodoAberto(PeriodoMedicao periodoAberto) {
		this.periodoAberto = periodoAberto;
	}
	public PeriodoMedicao getPeriodoValidacao() {
		if (periodoValidacao == null) {
			periodoValidacao = new PeriodoMedicao();
		}
		return periodoValidacao;
	}
	public void setPeriodoValidacao(PeriodoMedicao periodoValidacao) {
		this.periodoValidacao = periodoValidacao;
	}
	public PeriodoMedicao getPeriodoAprovacao() {
		if (periodoAprovacao == null) {
			periodoAprovacao = new PeriodoMedicao();
		}
		return periodoAprovacao;
	}
	public void setPeriodoAprovacao(PeriodoMedicao periodoAprovacao) {
		this.periodoAprovacao = periodoAprovacao;
	}
	public PeriodoMedicao getPeriodoSelecionado() {
		return periodoSelecionado;
	}
	public void setPeriodoSelecionado(PeriodoMedicao periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}
	public List<InfraMateriais> getInfraMateriais() {
		return infraMateriais;
	}
	public void setInfraMateriais(List<InfraMateriais> infraMateriais) {
		this.infraMateriais = infraMateriais;
	}
	public Map<Integer, List<MedicaoInfraMateriais>> getMedicoesInfra() {
		return medicoesInfra;
	}
	public void setMedicoesInfra(Map<Integer, List<MedicaoInfraMateriais>> medicoesInfra) {
		this.medicoesInfra = medicoesInfra;
	}
	public int getTabLista() {
		return tabLista;
	}
	public void setTabLista(int tabLista) {
		this.tabLista = tabLista;
	}
	public List<Servico> getServicoEquipes() {
		return servicoEquipes;
	}
	public void setServicoEquipes(List<Servico> servicoEquipes) {
		this.servicoEquipes = servicoEquipes;
	}
	public boolean isExibirEquipes() {
		return exibirEquipes;
	}
	public void setExibirEquipes(boolean exibirEquipes) {
		this.exibirEquipes = exibirEquipes;
	}
	public Map<Integer, List<MedicaoEquipe>> getMedicoesEquipe() {
		return medicoesEquipe;
	}
	public void setMedicoesEquipe(Map<Integer, List<MedicaoEquipe>> medicoesEquipe) {
		this.medicoesEquipe = medicoesEquipe;
	}
	public List<Servico> getServicoProdutos() {
		return servicoProdutos;
	}
	public void setServicoProdutos(List<Servico> servicoProdutos) {
		this.servicoProdutos = servicoProdutos;
	}
	public Map<Integer, List<MedicaoProduto>> getMedicoesProduto() {
		return medicoesProduto;
	}
	public void setMedicoesProduto(Map<Integer, List<MedicaoProduto>> medicoesProduto) {
		this.medicoesProduto = medicoesProduto;
	}
	public AlteracaoPeriodoMedicao getAlteracaoPeriodo() {
		if (alteracaoPeriodo == null) {
			alteracaoPeriodo = new AlteracaoPeriodoMedicao();
		}
		return alteracaoPeriodo;
	}
	public void setAlteracaoPeriodo(AlteracaoPeriodoMedicao alteracaoPeriodo) {
		this.alteracaoPeriodo = alteracaoPeriodo;
	}
	public List<MotivoAlteracaoPeriodoEnum> getMotivosAlteracao() {
		motivosAlteracao = Arrays.asList(MotivoAlteracaoPeriodoEnum.values());
		return motivosAlteracao;
	}
	public void setMotivosAlteracao(List<MotivoAlteracaoPeriodoEnum> motivosAlteracao) {
		this.motivosAlteracao = motivosAlteracao;
	}

	public List<MedicaoDespesa> getDespesas() {
		if (selecionado.verificaSeFoiSelecionado(periodoSelecionado) && despesas!=null) {
			despesas = medicaoDespesaDAO.buscarTodosPorPeriodo(periodoSelecionado.getId());
		}
		return despesas;
	}
}
