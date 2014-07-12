package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nucleo.dao.CargoDAO;
import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;

@Stateless
public class CargoDAOImpl extends DAOImpl<Cargo, Integer> implements CargoDAO {

	@EJB
	MedicaoEquipeDAO medicaoEquipeDAO;

	@Override
	public List<PrevisaoUso> gerarPrevisoesUso(Cargo cargo) {
		String queryStr = " Select p From PeriodoMedicao p" + " where p.projeto.id = :projeto AND"
				+ " p.dataAte > :dataInicial AND" + " p.dataDe < :dataFim"
				+ " and p.excluido = :excluido";
		TypedQuery<PeriodoMedicao> query = em.createQuery(queryStr, PeriodoMedicao.class);
		query.setParameter("projeto", cargo.getServico().getProjeto().getId());
		query.setParameter("dataInicial", cargo.getServico().getDataInicial());
		query.setParameter("dataFim", cargo.getServico().getDataFim());
		query.setParameter("excluido", false);

		List<PrevisaoUso> previsoesUso = new ArrayList<PrevisaoUso>();
		for (PeriodoMedicao periodoMedic : query.getResultList()) {
			PrevisaoUso prevUso = new PrevisaoUso();
			prevUso.setCargo(cargo);
			prevUso.setPeriodoMedicao(periodoMedic);
			previsoesUso.add(prevUso);
		}
		return previsoesUso;
	}

	@Override
	public List<Cargo> buscarTodosPorServico(Servico servico) {
		TypedQuery<Cargo> query = em.createQuery(
				"Select c From Cargo c Where c.servico.id = :servico and c.excluido = :excluido",
				Cargo.class);
		query.setParameter("servico", servico.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}
	@Override
	public List<Cargo> buscarTodosPorServicoEfuncao(Servico servico, String funcaoMD){
		String jpql = "select c from Cargo c where c.servico.id =:servico and c.funcaoMD =:funcaoMD and c.excluido=:excluido";
		TypedQuery<Cargo>query = em.createQuery(jpql,Cargo.class);
		query.setParameter("servico", servico);
		query.setParameter("excluido", false);
		query.setParameter("funcaoMD", funcaoMD);
		return query.getResultList();
	}

	@Override
	public List<PrevisaoUso> obterPrevisoesUso(Cargo cargo) {
		String strQuery = "select p From PrevisaoUso p where p.cargo.id = :cargo and p.excluido = :excluido";
		TypedQuery<PrevisaoUso> query = em.createQuery(strQuery, PrevisaoUso.class);
		query.setParameter("cargo", cargo.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public boolean temMobilizacao(Cargo cargo) {
		String strQuery = "select Count(m) from Mobilizacao m "
				+ "Where m.cargo.id = :cargo and m.excluido = :excluido ";
		Query query = em.createQuery(strQuery);
		query.setParameter("cargo", cargo.getId());
		query.setParameter("excluido", false);
		return ((Long) query.getSingleResult() > 0) ? true : false;
	}

	@Override
	public void removerHistograma(Cargo cargo) {
		String strQuery = "update PrevisaoUso p " + "set excluido = :excluido, "
				+ "dataexclusao = :data " + "Where p.cargo.id = :cargo";

		Query query = em.createQuery(strQuery);
		query.setParameter("excluido", true);
		query.setParameter("data", Calendar.getInstance());
		query.setParameter("cargo", cargo.getId());
		query.executeUpdate();
	}

	@Override
	public List<Mobilizacao> buscarMobilizacoes(Cargo cargo) {
		String strQuery = "select m From Mobilizacao m "
				+ "Where m.cargo.id = :cargo and m.excluido = :excluido";
		TypedQuery<Mobilizacao> query = em.createQuery(strQuery, Mobilizacao.class);
		query.setParameter("cargo", cargo.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public BigDecimal getTotalMedido(Cargo cargo) {
		String strQuery = "select SUM(m.valorMedido) From MedicaoEquipe m "
				+ "Where m.excluido = :excluido and " + " m.mobilizacao.cargo.id = :cargo and "
				+ " m.periodoMedicao.status = :statusPeriodo ";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery, BigDecimal.class);
		query.setParameter("cargo", cargo.getId());
		query.setParameter("excluido", false);
		query.setParameter("statusPeriodo", StatusPeriodoEnum.APROVADO);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getSomaValoresMedidos(Cargo cargo, PeriodoMedicao periodo){
		String strQuery = "select SUM(m.valorMedido) From MedicaoEquipe m "
				+ "Where m.excluido = :excluido and " + " m.mobilizacao.cargo.id = :cargo and "
				+ " m.periodoMedicao.id = :periodo ";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery, BigDecimal.class);
		query.setParameter("cargo", cargo.getId());
		query.setParameter("excluido", false);
		query.setParameter("periodo", periodo.getId());
		try {
			return (query.getSingleResult() == null) ? BigDecimal.ZERO : query.getSingleResult();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}
	public BigDecimal getTotalMedido(Cargo cargo, PeriodoMedicao periodo) {
		BigDecimal somaValoresMedidos = getSomaValoresMedidos(cargo, periodo); 
		//if (periodo.getProjeto().getCalculo().equals(TipoCalculoEnum.PORCOMPLEXIDADE)) {
		//	return getTotalMedidoPeriodoCalculoComplexidade(periodo, cargo);
		//} else {
			return somaValoresMedidos;
	//	}
	}

	
	@Override
	public BigDecimal getTotalMedido(List<MedicaoEquipe> listaMedicoes, Cargo cargo) {
		BigDecimal totalMedido = BigDecimal.ZERO;
		for(MedicaoEquipe medicao : listaMedicoes){
			if(medicao.getMobilizacao().getCargo().getId() == cargo.getId()){
				totalMedido = totalMedido.add(medicao.getValorMedido());
			}
		}
		return totalMedido;
	}
	
	
	@Override
	public BigDecimal getTotalMedidoPeriodoCalculoComplexidade(PeriodoMedicao periodo, Cargo cargo) {
		BigDecimal medidoCargo = getTotalMedido(cargo, periodo);
		BigDecimal diasTrabalhados = medicaoEquipeDAO.getSomaDiasTrabalhados(periodo, cargo);
		return calcularTotalMedidoPeriodoCalculoComplexidade(medidoCargo, diasTrabalhados,
				periodo.getBaseCalculo());
	}

	/**
	 * FORMULA: diasTrabalhados / BaseCalculo * medidoCargo
	 */
	@Override
	public BigDecimal calcularTotalMedidoPeriodoCalculoComplexidade(BigDecimal medidoCargo,
			BigDecimal diasTrabalhados, BigDecimal baseCalculo) {
		if (diasTrabalhados.equals(BigDecimal.ZERO) || medidoCargo.equals(BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}

		// diasTrabalhados / BaseCalculo
		BigDecimal totalCargo = diasTrabalhados.divide(baseCalculo, 5, RoundingMode.CEILING);

		// * medidoCargo
		totalCargo = totalCargo.multiply(medidoCargo, MathContext.DECIMAL32);
		return totalCargo;
	}



}
