package com.nucleo.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.RenovacaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Renovacao;

@Stateless
public class RenovacaoDAOImpl extends DAOImpl<Renovacao, Integer> implements
		RenovacaoDAO {

	@EJB
	private PeriodoMedicaoDAO periodoDAO;

	@EJB
	private ProjetoDAO projetoDAO;

	@Override
	public void inserir(Renovacao renovacao, int usuario) {

		try {

			super.inserir(renovacao, usuario);

			// pega data final do projeto antes do aditivo para começar
			Calendar dataInicial = Calendar.getInstance();
			dataInicial.setTime(periodoDAO.buscarDataFinalMedicao(
					renovacao.getProjeto()).getTime());
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);

			Projeto projeto = projetoDAO.buscarPorID(renovacao.getProjeto()
					.getId());
			projeto.getRenovacoes().add(renovacao);
			boolean cond = true;
			int numsequencial =1;
			while (cond) {
				cond = projetoDAO.cadastraPeriodoMedicao(projeto, dataInicial, numsequencial, usuario);
				dataInicial.add(Calendar.MONTH, 1);
				numsequencial++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Renovacao> buscarTodosPorProjeto(Projeto projetoSelecionado) {
		TypedQuery<Renovacao> query = em.createQuery(
				"select r From Renovacao r Where r.projeto.id = :projeto and r.excluido = :excluido",
				Renovacao.class);
		query.setParameter("projeto", projetoSelecionado.getId());
		query.setParameter("excluido", false);
		
		return query.getResultList();
	}
}
