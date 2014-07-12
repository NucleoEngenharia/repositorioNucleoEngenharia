package com.nucleo.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.AditivoDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Aditivo;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Enum.TipoAditivoEnum;

@Stateless
public class AditivoDAOImpl extends DAOImpl<Aditivo, Integer> implements
		AditivoDAO {

	@EJB
	private PeriodoMedicaoDAO periodoDAO;

	@EJB
	private ProjetoDAO projetoDAO;

	@Override
	public void inserir(Aditivo aditivo, int usuario) {

		try {

			super.inserir(aditivo, usuario);

			if (aditivo.getTipo().equals(TipoAditivoEnum.PRAZO)) {
				// pega data final do projeto antes do aditivo para começar
				Calendar dataInicial = Calendar.getInstance();
				dataInicial.setTime(periodoDAO.buscarDataFinalMedicao(
						aditivo.getProjeto()).getTime());
				dataInicial.add(Calendar.DAY_OF_MONTH, 1);
				
				Projeto projeto = projetoDAO.buscarPorID(aditivo.getProjeto().getId());
				projeto.getAditivos().add(aditivo);
				boolean cond = true;
				int numsequencial =1;
				while (cond) {
					cond = projetoDAO.cadastraPeriodoMedicao(projeto,
							dataInicial, numsequencial, usuario);
					dataInicial.add(Calendar.MONTH, 1);
					numsequencial++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Aditivo> buscarTodosPorProjeto(Projeto projeto){
		TypedQuery<Aditivo> query = em.createQuery(
				"Select a From Aditivo a " +
				"Where a.projeto.id = :projeto and a.excluido = :excluido", Aditivo.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}
}
