package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.nucleo.dao.AditivoDAO;
import com.nucleo.dao.DespesaDAO;
import com.nucleo.dao.InfraMateriaisDAO;
import com.nucleo.dao.MedicaoDespesaDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.RenovacaoDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Aditivo;
import com.nucleo.entity.cadastro.Despesa;
import com.nucleo.entity.cadastro.Imposto;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Reajuste;
import com.nucleo.entity.cadastro.Renovacao;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.cadastro.Enum.MotivoBloqueioProjeto;
import com.nucleo.entity.cadastro.Enum.TipoAditivoEnum;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;
import com.nucleo.exception.ImpostoJaCadastradoException;
import com.nucleo.util.Data;

@Stateless
public class ProjetoDAOImpl extends DAOImpl<Projeto, Integer>implements	ProjetoDAO {

	@EJB
	private PeriodoMedicaoDAO periodoDAO;
	@EJB
	private AditivoDAO aditivoDAO;
	@EJB
	private RenovacaoDAO renovacaoDAO;
	@EJB
	private ServicoDAO servicoDAO;
	@EJB
	private InfraMateriaisDAO infraDAO;
	@EJB
	private DespesaDAO despesaDAO;
	@EJB
	private MedicaoDespesaDAO medicaoDespesaDAO;

	SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public List<Projeto>listarTodos(){
		String jpql = "select distinct(p) from Projeto p" +
				" join fetch p.responsavelAdm" +
				" left join fetch p.impostos" +
				" where p.excluido=:excluido";
		return em.createQuery(jpql, Projeto.class)
				.setParameter("excluido", false)
				.getResultList();
	}
	@Override
	public List<Projeto>listarTodosComReajuste(){
		String jpql  = "select distinct p from Projeto p"
				+ " join fetch p.responsavelAdm"
				+ " left join fetch p.impostos"
				+ " inner join p.reajustes as r"
				+ " where p.excluido=:excluido and r.id >=:reajuste"
				+ " and r.excluido=:excluido";
		return em.createQuery(jpql, Projeto.class)
				.setParameter("excluido", false)
				.setParameter("reajuste", 0)
				.getResultList();
	}
	
	@Override
	public void alterar(Projeto projeto, int usuario) {

		try {
			Projeto projetoOld = this.buscarPorID(projeto.getId());
			estruturaMedicao(projeto, projetoOld, usuario);

			super.alterar(projeto, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void estruturaMedicao(Projeto projeto, Projeto projetoOld,
			int usuario) throws GerarEstruturaMedicaoProjeto, Exception {

		// verifica se a data inicial da medição foi cadastrada
		// para criar estrutura de medição
		if (projetoOld.getDataInicialMedicao() == null
				&& projeto.getDataInicialMedicao() != null) {

			// verifica se data inicial da medição é maior que data fim do
			// projeto
			if (projeto.getDataFim().before(projeto.getDataInicialMedicao())) {
				throw new GerarEstruturaMedicaoProjeto(
						"Data inicial da medição é maior que data final do projeto.");
			}

			// Exemplo: Inicial: 10/01/2012 Final: 09/02/2012
			Calendar dataMedicaoInicial = Calendar.getInstance();
			dataMedicaoInicial.setTime(projeto.getDataInicialMedicao()
					.getTime());

			boolean cond = true;
			int numsequencial = 1;
			while (cond) {
				cond = cadastraPeriodoMedicao(projeto, dataMedicaoInicial,
						numsequencial, usuario);
				dataMedicaoInicial.add(Calendar.MONTH, 1);
				numsequencial++;
			}
		}

	}

	private Calendar calculaDataFinal(Calendar dtInicial) {
		Calendar dtFinal = Calendar.getInstance();
		dtFinal.setTime(dtInicial.getTime());
		dtFinal.add(Calendar.MONTH, 1);
		dtFinal.add(Calendar.DAY_OF_MONTH, -1);
		return dtFinal;
	}

	public boolean cadastraPeriodoMedicao(Projeto projeto,
			Calendar dataInicial,int numsequencial, int usuario) {

		Calendar dtInicial = Calendar.getInstance();
		dtInicial.setTime(dataInicial.getTime());

		Calendar dataFinal = calculaDataFinal(dtInicial);

		if (dtInicial.compareTo(getDataFimAtual(projeto)) == 0)
			return false;

		boolean retorno = true;

		if (getDataFimAtual(projeto).before(dataFinal)) {
			dataFinal = getDataFimAtual(projeto);
			retorno = false;
		}

		PeriodoMedicao periodo = new PeriodoMedicao();
		periodo.setStatus(StatusPeriodoEnum.EMABERTO);
		periodo.setNumSequencial(numsequencial);
		periodo.setDataDe(dtInicial);
		periodo.setDataAte(dataFinal);
		periodo.setProjeto(projeto);
		periodo.setDescricao("Medição de "
				+ formatData.format(dtInicial.getTime()) + " até "
				+ formatData.format(dataFinal.getTime()));

		periodoDAO.inserir(periodo, usuario);
		return retorno;
	}

	@Override
	public int getPrazoTotalDias(Projeto projeto) {
		if (projeto.getDataAberturaOIS() == null
				|| projeto.getDataFechamentoOIS() == null)
			return 0;

		Calendar dtFinal = Calendar.getInstance();
		dtFinal.setTime(projeto.getDataFechamentoOIS().getTime());

		Calendar dtInicio = Calendar.getInstance();
		dtInicio.setTime(projeto.getDataAberturaOIS().getTime());

		List<Aditivo> aditivos = aditivoDAO.buscarTodosPorProjeto(projeto);
		for (Aditivo aditivo : aditivos) {
			dtFinal.add(Calendar.YEAR, aditivo.getAno());
			dtFinal.add(Calendar.MONTH, aditivo.getMes());
			dtFinal.add(Calendar.DAY_OF_MONTH, aditivo.getDia());
		}
		List<Renovacao> renovacoes = renovacaoDAO
				.buscarTodosPorProjeto(projeto);
		for (Renovacao renov : renovacoes) {
			dtFinal.add(Calendar.DAY_OF_MONTH, renov.getDia());
			dtFinal.add(Calendar.MONTH, renov.getMes());
			dtFinal.add(Calendar.YEAR, renov.getAno());
		}

		return Data.diferencaEntreDatas(dtInicio.getTime(), dtFinal.getTime());
	}

	@Override
	public BigDecimal getValorAtual(Projeto projeto) {
		BigDecimal valorAtual = new BigDecimal(0);
		valorAtual = valorAtual.add(projeto.getValorOriginal());

		List<Aditivo> aditivos = aditivoDAO.buscarTodosPorProjeto(projeto);
		for (Aditivo aditivo : aditivos) {
			if (aditivo.getTipo() == TipoAditivoEnum.PRECO) {
				valorAtual = valorAtual.add(aditivo.getValor());
			}
		}
		List<Renovacao> renovacoes = renovacaoDAO
				.buscarTodosPorProjeto(projeto);
		for (Renovacao renov : renovacoes) {
			valorAtual = valorAtual.add(renov.getValor());
		}
		return valorAtual;
	}

	@Override
	public BigDecimal getValorTotalAditivos(Projeto projeto) {
		BigDecimal valorTotalAditivos = new BigDecimal(0);

		List<Aditivo> aditivos = aditivoDAO.buscarTodosPorProjeto(projeto);
		for (Aditivo aditivo : aditivos) {
			if (aditivo.getTipo() == TipoAditivoEnum.PRECO) {
				valorTotalAditivos = valorTotalAditivos.add(aditivo.getValor());
			}
		}
		List<Renovacao> renovacoes = renovacaoDAO
				.buscarTodosPorProjeto(projeto);
		for (Renovacao renovacao : renovacoes) {
			valorTotalAditivos = valorTotalAditivos.add(renovacao.getValor());
		}
		return valorTotalAditivos;
	}

	@Override
	public Calendar getDataFimAtual(Projeto projeto) {
		if (projeto.getDataFim() == null)
			return null;

		Calendar dataFimAtual = Calendar.getInstance();
		dataFimAtual.setTime(projeto.getDataFim().getTime());

		List<Aditivo> aditivos = aditivoDAO.buscarTodosPorProjeto(projeto);
		for (Aditivo aditivo : aditivos) {
			if (aditivo.getTipo() == TipoAditivoEnum.PRAZO) {
				dataFimAtual.add(Calendar.DAY_OF_MONTH, aditivo.getDia());
				dataFimAtual.add(Calendar.MONTH, aditivo.getMes());
				dataFimAtual.add(Calendar.YEAR, aditivo.getAno());
			}
		}
		List<Renovacao> renovacoes = renovacaoDAO
				.buscarTodosPorProjeto(projeto);
		for (Renovacao renov : renovacoes) {
			dataFimAtual.add(Calendar.DAY_OF_MONTH, renov.getDia());
			dataFimAtual.add(Calendar.MONTH, renov.getMes());
			dataFimAtual.add(Calendar.YEAR, renov.getAno());
		}

		return dataFimAtual;
	}

	@Override
	public String getTotalPrazoAditivos(Projeto projeto) {
		int anos = 0;
		int meses = 0;
		int dias = 0;

		List<Aditivo> aditivos = aditivoDAO.buscarTodosPorProjeto(projeto);
		for (Aditivo aditivo : aditivos) {
			if (aditivo.getTipo() == TipoAditivoEnum.PRAZO) {
				dias = dias + aditivo.getDia();
				meses = meses + aditivo.getMes();
				anos = anos + aditivo.getAno();
			}
		}
		List<Renovacao> renovacoes = renovacaoDAO
				.buscarTodosPorProjeto(projeto);
		for (Renovacao renov : renovacoes) {
			dias = dias + renov.getDia();
			meses = meses + renov.getMes();
			anos = anos + renov.getAno();
		}

		String strAno = (anos > 1) ? " anos" : " ano";
		String strMes = (meses > 1) ? " meses" : " mes";
		String strDia = (dias > 1) ? " dias" : " dia";

		String totalPrazoAditivos = null;
		if (anos > 0 && meses == 0 && dias == 0)
			totalPrazoAditivos = "+ " + anos + strAno;
		if (anos > 0 && meses > 0 && dias == 0)
			totalPrazoAditivos = "+ " + anos + strAno + " e " + meses + strMes;
		if (anos > 0 && dias > 0 && meses == 0)
			totalPrazoAditivos = "+ " + anos + strAno + " e " + dias + strDia;
		if (anos > 0 && meses > 0 && dias > 0)
			totalPrazoAditivos = "+ " + anos + strAno + ", " + meses + strMes
					+ " e " + anos + strDia;

		if (meses > 0 && anos == 0 && dias == 0)
			totalPrazoAditivos = "+ " + meses + strMes;
		if (meses > 0 && dias > 0 && anos == 0)
			totalPrazoAditivos = "+ " + meses + strMes + " e " + dias + strDia;

		if (dias > 0 && meses == 0 && anos == 0)
			totalPrazoAditivos = "+ " + dias + strDia;

		return totalPrazoAditivos;
	}

	@Override
	public BigDecimal getSaldo(Projeto projeto) {
		return getValorAtual(projeto).subtract(getTotalMedido(projeto),
				MathContext.DECIMAL32);
	}

	@Override
	public BigDecimal getValorDisponivel(Projeto projeto) {
		BigDecimal valorTotalProjeto = new BigDecimal(0);
		valorTotalProjeto = valorTotalProjeto.add(getValorAtual(projeto));
		BigDecimal valorUsado = new BigDecimal(0);

		List<Servico> servicos = servicoDAO.buscarTodosPorProjeto(projeto);
		for (Servico servico : servicos) {
			try{
			valorUsado = valorUsado.add(servico.getValorTotal());
			}catch (NullPointerException e) {
				valorUsado = valorUsado.add(BigDecimal.ZERO);
			}
		}
		List<InfraMateriais> infraMateriais = infraDAO
				.buscarTodosPorProjeto(projeto);
		for (InfraMateriais infra : infraMateriais) {
			try{
			valorUsado = valorUsado.add(infra.getValor());
			}catch(NullPointerException e){
				valorUsado = valorUsado.add(BigDecimal.ZERO);
			}
		}
		List<Despesa> despesas = despesaDAO.buscarTodosPorProjeto(projeto);
		for (Despesa despesa : despesas) {
			if (projeto.isDespesaIntegraValor()) {
				try{
				valorUsado = valorUsado.add(despesa.getValor());
				}catch(NullPointerException e){
					valorUsado = valorUsado.add(BigDecimal.ZERO);
				}
			}
		}

		return valorTotalProjeto.subtract(valorUsado);
	}

	@Override
	public BigDecimal getLimiteDisponivelDespesa(Projeto projeto) {
		BigDecimal limite = new BigDecimal(0);
		if (projeto.isLimiteDespesa()) {
			List<Despesa> despesas = despesaDAO.buscarTodosPorProjeto(projeto);
			for (Despesa despesa : despesas) {
				limite = limite.add(despesa.getValor());
			}
			List<MedicaoDespesa> despesasMedidas = medicaoDespesaDAO
					.buscarTodosPorProjeto(projeto);
			for (MedicaoDespesa despesa : despesasMedidas) {
				limite = limite.subtract(despesa.getValor());
			}
		}
		return limite;
	}

	@Override
	public BigDecimal getTotalMedido(Projeto projeto) {
		BigDecimal totalMedido = BigDecimal.ZERO;
		totalMedido = totalMedido.add(getTotalMedidoEquipes(projeto))
				.add(getTotalMedidoProdutos(projeto))
				.add(getTotalMedidoItens(projeto));

		if (projeto.isDespesaIntegraValor()) {
			totalMedido = totalMedido.add(getTotalMedidoDespesas(projeto));
		}
		return totalMedido;
	}

	private BigDecimal getTotalMedidoEquipes(Projeto projeto) {
		String strQuery = "select SUM(m.valorMedido) From MedicaoEquipe m "
				+ " Where m.excluido = :excluido and "
				+ " m.mobilizacao.cargo.servico.projeto.id = :projeto and "
				+ " m.periodoMedicao.status = :statusPeriodo ";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery,
				BigDecimal.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		query.setParameter("statusPeriodo", StatusPeriodoEnum.APROVADO);
		
		BigDecimal medido = query.getSingleResult();
		return (medido == null) ? BigDecimal.ZERO : medido;
	}
	private BigDecimal getTotalMedidoProdutos(Projeto projeto) {
		String strQuery = "select SUM(m.valorMedido) From MedicaoProduto m "
				+ " Where m.excluido = :excluido and "
				+ " m.produto.servico.projeto.id = :projeto and "
				+ " m.periodoMedicao.status = :statusPeriodo ";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery,
				BigDecimal.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		query.setParameter("statusPeriodo", StatusPeriodoEnum.APROVADO);

		BigDecimal medido = query.getSingleResult();
		return (medido == null) ? BigDecimal.ZERO : medido;
	}
	private BigDecimal getTotalMedidoItens(Projeto projeto) {
		String strQuery = "select SUM(m.valorMedido) From MedicaoInfraMateriais m "
				+ " Where m.excluido = :excluido and "
				+ " m.itemInfraMateriais.infraMateriais.projeto.id = :projeto and "
				+ " m.periodoMedicao.status = :statusPeriodo ";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery,
				BigDecimal.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		query.setParameter("statusPeriodo", StatusPeriodoEnum.APROVADO);
		
		BigDecimal medido = query.getSingleResult();
		return (medido == null) ? BigDecimal.ZERO : medido;
	}
	private BigDecimal getTotalMedidoDespesas(Projeto projeto) {
		String strQuery = "select SUM(m.valor) From MedicaoDespesa m "
				+ " Where m.excluido = :excluido and "
				+ " m.projeto.id = :projeto and " 
				+ " m.periodo.status = :status";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery,
				BigDecimal.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("status", StatusPeriodoEnum.APROVADO);
		query.setParameter("excluido", false);

		BigDecimal medido = query.getSingleResult();
		return (medido == null) ? BigDecimal.ZERO : medido;
	}

	// funções de imposto
	private Imposto obterImposto(int idImposto) {
		Imposto imp = em.find(Imposto.class, idImposto);
		if (imp == null) {
			throw new NoResultException("Não existe imposto cadastrado.");
		}
		return imp;
	}
	@Override
	public void adicionarImposto(int idProjeto, int idImposto, int usuario)
			throws ImpostoJaCadastradoException {
		Projeto projeto = buscarPorID(idProjeto);
		Imposto imposto = obterImposto(idImposto);
		if (projeto.getImpostos().contains(imposto)) {
			throw new ImpostoJaCadastradoException();
		} else {
			projeto.getImpostos().add(imposto);
		}
		alterar(projeto, usuario);
	}
	@Override
	public void removerImposto(int idProjeto, int idImposto, int usuario)
			throws Exception {
		Projeto projeto = buscarPorID(idProjeto);
		Imposto imposto = obterImposto(idImposto);
		if (projeto.getImpostos().contains(imposto)) {
			projeto.getImpostos().remove(imposto);
		} else {
			throw new Exception("Imposto não esta existe no Projeto.");
		}
		alterar(projeto, usuario);
	}
	@Override
	public List<Imposto> obterTodosImpostos(Projeto projeto) {
		String sqlQuery = "Select i From Imposto i where i.projeto.id = :projeto";
		TypedQuery<Imposto> query = em.createQuery(sqlQuery, Imposto.class);
		query.setParameter("projeto", projeto.getId());
		return query.getResultList();
	}
	
	@Override
	public Reajuste buscarUltimoReajusteValido(Projeto projeto) {
		String strQuery = "select r from Reajuste r " + 
				"Where r.projeto.id = :projeto and " + 
				"r.excluido = :excluido " + 
				"order by r.dataCriacao desc";
		TypedQuery<Reajuste> query = em.createQuery(strQuery, Reajuste.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList().get(0);
	}
	
	// bloqueio do projeto 
	@Override
	public MotivoBloqueioProjeto getMotivoBloqueio(Projeto projeto) {
		return null;
	}
	@Override
	public boolean isBloqueado(Projeto projeto) {
		boolean bloqueado = false;
		if(projeto.getDataLimiteReajuste() != null){
				Calendar limiteReajuste = projeto.getDataLimiteReajuste();
				if(limiteReajuste.before(Calendar.getInstance())){
					// TODO fazer a lógica para identificar se o indice ja foi cadastrado para o ano atual
					bloqueado = true;
				}
		}
		return bloqueado;
	}

}


/*
 * 	public boolean isBloqueado() {
		bloqueado = false;
		if(dataLimiteReajuste != null){
			if(!dataLimiteReajuste.isEmpty()){
				Calendar limiteReajuste = Calendar.getInstance();
				int dia = Integer.parseInt(dataLimiteReajuste.substring(0, 2));
				int mes = Integer.parseInt(dataLimiteReajuste.substring(3));
				limiteReajuste.set(Calendar.DAY_OF_MONTH, dia);
				limiteReajuste.set(Calendar.MONTH, mes - 1);
				if(limiteReajuste.before(Calendar.getInstance())){
					bloqueado = true;
				}
			}
		}
		return bloqueado;
	}
	public MotivoBloqueioProjeto getMotivoBloqueio(){
		if(dataLimiteReajuste != null || !dataLimiteReajuste.isEmpty()){
			Calendar limiteReajuste = Calendar.getInstance();
			int dia = Integer.parseInt(dataLimiteReajuste.substring(0, 1));
			int mes = Integer.parseInt(dataLimiteReajuste.substring(3, 4));
			limiteReajuste.set(Calendar.YEAR, mes, dia);
			
			if(limiteReajuste.before(Calendar.getInstance())){
				return MotivoBloqueioProjeto.REAJUSTE;
			}
		}
		return null;		
	}
 * */
 