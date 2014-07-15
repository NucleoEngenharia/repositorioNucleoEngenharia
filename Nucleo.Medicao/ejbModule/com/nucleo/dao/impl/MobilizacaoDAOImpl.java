package com.nucleo.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Stateless
public class MobilizacaoDAOImpl extends DAOImpl<Mobilizacao, Integer> implements MobilizacaoDAO{

	@EJB
	private MedicaoEquipeDAO medicaoDAO;
	
	private boolean datasSaoDiferentes(Calendar data1, Calendar data2){
		if(data1.after(data2) || data1.before(data2)){
			return true;
		}
		return false;
	}
	
	private List<MedicaoEquipe> getMedicoesMobilizacaoAntiga(Mobilizacao mobilizacao){
		String strQuery = "select m from MedicaoEquipe m " +
				"Where m.excluido = :excluido " +
				"and m.mobilizacao.id = :mobilizacao";
		TypedQuery<MedicaoEquipe> query = em.createQuery(strQuery, MedicaoEquipe.class);
		query.setParameter("excluido", false);
		query.setParameter("mobilizacao", mobilizacao.getId());
		return query.getResultList();
	}
	
	@Override
	public void alterar(Mobilizacao mobilizacao, int usuarioAlteracao){
		try {
			Mobilizacao mobilizacaoOld = em.merge(mobilizacao);
			
			if(datasSaoDiferentes(mobilizacaoOld.getDataDesmobilizacao(), mobilizacao.getDataDesmobilizacao()) || 
					datasSaoDiferentes(mobilizacaoOld.getDataMobilizacao(), mobilizacao.getDataMobilizacao())){
				for(MedicaoEquipe medicao : this.getMedicoesMobilizacaoAntiga(mobilizacao)){
					PeriodoMedicao periodo = medicao.getPeriodoMedicao(); 
					if(mobilizacao.getDataMobilizacao().after(periodo.getDataAte()) || 
							mobilizacao.getDataDesmobilizacao().before(periodo.getDataDe())){
						medicaoDAO.deletarPorId(medicao.getId(), usuarioAlteracao);
					}
				}
			}
			super.alterar(mobilizacaoOld, usuarioAlteracao);
		}catch(NullPointerException e){
			System.out.println("Uma das datas estão vazias");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Mobilizacao> buscarTodosPorProjeto(Projeto projeto) {
		String strQuery = "select distinct m from Mobilizacao m"
				+ " left join fetch m.funcionario"
				+ " left join fetch m.cargo as c"
				+ " left join fetch c.servico as s"
				+ " left join fetch s.projeto as p"
				+ " where p.id=:projeto and m.excluido = :excluido";
		TypedQuery<Mobilizacao> query = em.createQuery(strQuery, Mobilizacao.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}
	@Override
	public List<Mobilizacao> buscarTodosPorPeriodo(PeriodoMedicao periodo) {
		String strQuery = "select distinct m from Mobilizacao m" +
				" where m.cargo.servico.projeto.id = :projetoId" +
				" and (:inicioPeriodo <= m.dataDesmobilizacao or m.dataDesmobilizacao is null)" +
				" and (:fimPeriodo >= m.dataMobilizacao)" +
				" and m.excluido = :excluido";
		TypedQuery<Mobilizacao> query = em.createQuery(strQuery, Mobilizacao.class);
		query.setParameter("projetoId", periodo.getProjeto().getId());
		query.setParameter("inicioPeriodo", periodo.getDataDe());
		query.setParameter("fimPeriodo", periodo.getDataAte());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public boolean existeMedicao(Mobilizacao mobilizacao) {
		String strQuery = "select COUNT(m) from MedicaoEquipe m " +
				"where m.mobilizacao.id = :mobilizacao " +
				"and m.excluido = :excluido";
		TypedQuery<Long> query = em.createQuery(strQuery, Long.class);
		query.setParameter("mobilizacao", mobilizacao.getId());
		query.setParameter("excluido", false);
		if(query.getSingleResult() == null || query.getSingleResult() == 0){
			return false;
		}
		return true;
	}

	@Override
	public List<Mobilizacao> buscarTodosPorServico(Servico servico) {
		String strQuery = "select m from Mobilizacao m " +
				"where m.cargo.servico.id = :servico " +
				"and m.excluido = :excluido";
		TypedQuery<Mobilizacao> query = em.createQuery(strQuery, Mobilizacao.class);
		query.setParameter("servico", servico.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public List<Mobilizacao> buscarTodosPorPeriodoServico(PeriodoMedicao periodo, Servico servico) {
		String strQuery = "select m from Mobilizacao m " +
				"where m.cargo.servico.id = :servico " +
				"and (:inicioPeriodo <= m.dataDesmobilizacao) " +
				"and (:fimPeriodo >= m.dataMobilizacao) " +
				"and m.excluido = :excluido";
		TypedQuery<Mobilizacao> query = em.createQuery(strQuery, Mobilizacao.class);
		query.setParameter("servico", servico.getId());
		query.setParameter("inicioPeriodo", periodo.getDataDe());
		query.setParameter("fimPeriodo", periodo.getDataAte());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	
	
}
