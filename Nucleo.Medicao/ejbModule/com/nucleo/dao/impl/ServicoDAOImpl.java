package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.CargoDAO;
import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProdutoDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.cadastro.Enum.TipoCalculoEnum;
import com.nucleo.entity.cadastro.Enum.TipoServicoEnum;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;
import com.nucleo.model.calculos.client.CalculosMedicao;

@Stateless
public class ServicoDAOImpl extends DAOImpl<Servico, Integer> implements ServicoDAO {

	@EJB
	private ServicoDAO servicoDAO;
	@EJB
	private CargoDAO cargoDAO;
	@EJB
	private ProdutoDAO produtoDAO;
	@EJB
	private PeriodoMedicaoDAO periodoDAO;
	@EJB
	private MedicaoEquipeDAO medicaoEquipeDAO;
	
	@EJB
	private CalculosMedicao calculosMedicao;
	
	@Override
	public List<Servico> buscarTodosPorProjeto(Projeto projeto) {
		TypedQuery<Servico> query = em.createQuery("Select distinct s From Servico s join fetch s.projeto "
				+ "Where s.excluido = :excluido and s.projeto.id = :projeto order by s.id", Servico.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}
	@Override
	public int buscarUltimoSeqPorProjeto(Projeto projeto) {
		int lastSequencial = 0;
		try{
		TypedQuery<Servico> query = em.createQuery("Select s.numSequencial from Servico s where s.excluido = :excluido" +
				"and s.projeto = :projeto_id ", Servico.class);
		query.setParameter("projeto_id", projeto.getId());
		query.setParameter("excluido", false);
		List<Servico>list = query.getResultList();
		int cont =0;
		for(cont = 0; cont == list.size(); cont++){
		}
		}catch(NullPointerException e){
			System.out.println("Lista vazia "+e);
		}
		return lastSequencial;
	}

	@Override
	public BigDecimal getValorDisponivel(Servico servico) {
		BigDecimal valorTotalServico = new BigDecimal(0);
		valorTotalServico = servico.getValorTotal();
		BigDecimal valorUsado = new BigDecimal(0);

		if (servico.getTipo().equals(TipoServicoEnum.EQUIPE)) {
			List<Cargo> cargos = cargoDAO.buscarTodosPorServico(servico);
			for (Cargo cargo : cargos) {
				valorUsado = valorUsado.add(cargo.getValorTotal());
			}
		} else {
			List<Produto> produtos = produtoDAO.buscarTodosPorServico(servico);
			for (Produto produto : produtos) {
				if (produto.isGrupo())
					continue;
				valorUsado = valorUsado.add(produto.getValorTotal());
				System.out.println("Valor usado do produto"+valorUsado);
			}
		}

		return valorTotalServico.subtract(valorUsado);
	}

	@Override
	public List<Servico> buscarEquipesPorProjeto(Projeto projeto) {
		return buscarPorProjetoTipo(projeto, TipoServicoEnum.EQUIPE);
	}

	@Override
	public List<Servico> buscarProdutosPorProjeto(Projeto projeto) {
		return buscarPorProjetoTipo(projeto, TipoServicoEnum.PRODUTO);
	}
	//Se não souber do problema do n+1, pesquise antes de mexer nas querys
	private List<Servico> buscarPorProjetoTipo(Projeto projeto, TipoServicoEnum tipo) {
		System.out.println("Procurando projeto "+projeto.getId());
		TypedQuery<Servico> query = em.createQuery("Select distinct s From Servico s"
				+ " left join fetch s.projeto p"
				+ " join fetch p.responsavelAdm"
				+ " where s.excluido = :excluido and p = :projeto"
				+ " and s.tipo = :tipo order by s.id", Servico.class);
		query.setParameter("projeto", projeto);
		query.setParameter("excluido", false);
		query.setParameter("tipo", tipo);
		System.out.println("achou "+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	public BigDecimal getTotalMedido(Servico servico) {
		if (servico.getTipo().equals(TipoServicoEnum.EQUIPE)) {
			return getTotalMedidoEquipe(servico);
		} else {
			return getTotalMedidoProdutos(servico);
		}
	}

	private BigDecimal getTotalMedidoProdutos(Servico servico) {
		String strQuery = "select SUM(m.valorMedido) From MedicaoProduto m "
				+ " Where m.excluido = :excluido and " + " m.produto.servico.id = :servico and "
				+ " m.periodoMedicao.status = :statusPeriodo";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery, BigDecimal.class);
		query.setParameter("servico", servico.getId());
		query.setParameter("excluido", false);
		query.setParameter("statusPeriodo", StatusPeriodoEnum.APROVADO);
		return query.getSingleResult();
	}

	private BigDecimal getTotalMedidoEquipe(Servico servico) {
		BigDecimal totalMedido = BigDecimal.ZERO;

		if (servico.getProjeto().getCalculo().equals(TipoCalculoEnum.SIMPLES)) {
			totalMedido = getTotalMedidoEquipeCalculoSimples(servico);
		} else if (servico.getProjeto().getCalculo().equals(TipoCalculoEnum.POREQUIPE)) {
			totalMedido = getTotalMedidoEquipeCalculoEquipe(servico);
		} else if (servico.getProjeto().getCalculo().equals(TipoCalculoEnum.PORCOMPLEXIDADE)) {
			totalMedido = getTotalMedidoEquipeCalculoComplexidade(servico);
		}
		return totalMedido;
	}
	private BigDecimal getTotalMedidoEquipeCalculoComplexidade(Servico servico) {
		BigDecimal totalMedido = BigDecimal.ZERO;
		List<StatusPeriodoEnum> statusPeriodo = new ArrayList<StatusPeriodoEnum>();
		statusPeriodo.add(StatusPeriodoEnum.APROVADO);

		for (PeriodoMedicao periodo : periodoDAO.buscarTodosPorProjetoStatus(servico.getProjeto(),
				statusPeriodo)) {
			if (!periodo.getStatus().equals(StatusPeriodoEnum.APROVADO))
				continue;

			totalMedido = totalMedido
					.add(getTotalMedidoPeriodoCalculoComplexidade(servico, periodo));
		}
		return totalMedido;
	}

	private BigDecimal getTotalMedidoEquipeCalculoEquipe(Servico servico) {
		BigDecimal totalMedido = BigDecimal.ZERO;
		List<StatusPeriodoEnum> statusPeriodo = new ArrayList<StatusPeriodoEnum>();
		statusPeriodo.add(StatusPeriodoEnum.APROVADO);

		for (PeriodoMedicao periodo : periodoDAO.buscarTodosPorProjetoStatus(servico.getProjeto(),
				statusPeriodo)) {
			if (!periodo.getStatus().equals(StatusPeriodoEnum.APROVADO))
				continue;

			totalMedido = totalMedido.add(getTotalMedidoPeriodoCalculoEquipe(servico, periodo));
		}
		return totalMedido;
	}
	private BigDecimal getTotalMedidoEquipeCalculoSimples(Servico servico) {
		String strQuery = "select SUM(m.valorMedido) From MedicaoEquipe m "
				+ " Where m.excluido = :excluido and "
				+ " m.mobilizacao.cargo.servico.id = :servico and "
				+ " m.periodoMedicao.status = :statusPeriodo";

		TypedQuery<BigDecimal> query = em.createQuery(strQuery, BigDecimal.class);
		query.setParameter("servico", servico.getId());
		query.setParameter("excluido", false);
		query.setParameter("statusPeriodo", StatusPeriodoEnum.APROVADO);
		return query.getSingleResult();
	}

	@Override
	public BigDecimal getTotalMedidoPeriodoCalculoEquipe(Servico servico, PeriodoMedicao periodo) {
		BigDecimal diasTrabalhados = medicaoEquipeDAO.getSomaDiasTrabalhados(periodo);
		BigDecimal somaValorVendaEquipePeriodo = periodoDAO.getSomaValorVendaEquipePeriodo(periodo,
				servico);
		Long qtdProfissionais = periodoDAO.getQuantidadeProfissionais(periodo, servico);

		return calculosMedicao.formulaCalculoPorEquipe(diasTrabalhados, qtdProfissionais, somaValorVendaEquipePeriodo, periodo.getBaseCalculo());
	}
	@Override
	public BigDecimal getTotalMedidoPeriodoCalculoComplexidade(Servico servico,
			PeriodoMedicao periodo) {
		BigDecimal totalMedido = BigDecimal.ZERO;
		for (Cargo cargo : cargoDAO.buscarTodosPorServico(servico)) {
			totalMedido = totalMedido.add(cargoDAO.getTotalMedidoPeriodoCalculoComplexidade(
					periodo, cargo));
		}
		return totalMedido;
	}
	@Override
	public BigDecimal calcularTotalMedidoEquipe(Servico equipe, PeriodoMedicao periodo,
			List<MedicaoEquipe> medicoes) {
		BigDecimal totalMedido = BigDecimal.ZERO;
		
		if (equipe.getProjeto().getCalculo().equals(TipoCalculoEnum.SIMPLES)) {
				totalMedido = calculosMedicao.calculoSimples(medicoes);
			}
		// CALCULO DE EQUIPE
		if (equipe.getProjeto().getCalculo().equals(TipoCalculoEnum.POREQUIPE)) {
			totalMedido = calculosMedicao.calculoPorEquipe(periodo, medicoes);
			
			// CALCULO POR COMPLEXIDADE
		}if (equipe.getProjeto().getCalculo().equals(TipoCalculoEnum.PORCOMPLEXIDADE)) {
			totalMedido = calculosMedicao.calculoPorComplexidade(periodo, medicoes);
		}
		return totalMedido;
	}
	

}
