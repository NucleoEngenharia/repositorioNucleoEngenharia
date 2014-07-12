package com.nucleo.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nucleo.dao.ProdutoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class ProdutoDAOImpl extends DAOImpl<Produto, Integer>
		implements
			ProdutoDAO {

	@Override
	public List<PrevisaoUso> gerarPrevisoesUso(Produto produto) {
		String queryStr = " Select p From PeriodoMedicao p"
				+ " where p.projeto.id = :projeto AND"
				+ " p.dataAte > :dataInicial AND" + " p.dataDe < :dataFim"
				+ " and p.excluido = :excluido";
		TypedQuery<PeriodoMedicao> query = em.createQuery(queryStr,
				PeriodoMedicao.class);
		query.setParameter("projeto", produto.getServico().getProjeto().getId());
		query.setParameter("dataInicial", produto.getServico().getDataInicial());
		query.setParameter("dataFim", produto.getServico().getDataFim());
		query.setParameter("excluido", false);

		List<PrevisaoUso> previsoesUso = new ArrayList<PrevisaoUso>();
		for (PeriodoMedicao periodoMedic : query.getResultList()) {
			PrevisaoUso prevUso = new PrevisaoUso();
			prevUso.setProduto(produto);
			prevUso.setPeriodoMedicao(periodoMedic);
			previsoesUso.add(prevUso);
		}
		return previsoesUso;
	}

	@Override
	public List<Produto> buscarTodosPorServico(Servico servico) {
		TypedQuery<Produto> query = em.createQuery("Select p From Produto p "
				+ " Where p.servico.id = :servico and p.excluido = :excluido",Produto.class);
		query.setParameter("servico", servico.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public List<Produto> buscarTodosGruposPorServico(Servico servico) {
		TypedQuery<Produto> query = em.createQuery("Select p From Produto p "
				+ "Where p.servico.id = :servico "
				+ "and p.excluido = :excluido " + "and p.isGrupo = :grupo ",
				Produto.class);
		query.setParameter("servico", servico.getId());
		query.setParameter("excluido", false);
		query.setParameter("grupo", true);
		return query.getResultList();
	}

	@Override
	public List<PrevisaoUso> obterPrevisoesUso(Produto produto) {
		String strQuery = "select p From PrevisaoUso p " +
				"Where p.produto.id = :produto " +
				"and p.excluido = :excluido";
		TypedQuery<PrevisaoUso> query = em.createQuery(strQuery,
				PrevisaoUso.class);
		query.setParameter("produto", produto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public boolean temMedicoes(Produto produto) {
		String strQuery = "select Count(m) from MedicaoProduto m " +
				"Where m.produto.id = :produto " +
				"and m.excluido = :excluido ";
		Query query = em.createQuery(strQuery);
		query.setParameter("produto", produto.getId());
		query.setParameter("excluido", false);
		return ((Long) query.getSingleResult() > 0) ? true : false;
	}

	@Override
	public void removerCronograma(Produto produto) {
		String strQuery = "update PrevisaoUso p "
				+ "set excluido = :excluido, " + "dataexclusao = :data "
				+ "Where p.produto.id = :produto";

		Query query = em.createQuery(strQuery);
		query.setParameter("excluido", true);
		query.setParameter("data", Calendar.getInstance());
		query.setParameter("produto", produto.getId());
		query.executeUpdate();
	}

}
