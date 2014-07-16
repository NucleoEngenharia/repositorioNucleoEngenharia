package com.nucleo.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.AlteracaoPeriodoMedicao;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.Enum.MotivoAlteracaoPeriodoEnum;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;
import com.nucleo.model.VO.DatasPeriodoMedicaoVO;

@Stateless
public class PeriodoMedicaoDAOImpl extends DAOImpl<PeriodoMedicao, Integer>
implements PeriodoMedicaoDAO{
	@EJB
	private ProjetoDAO projetoDAO;
	@Override
	public Calendar buscarDataFinalMedicao(Projeto projeto) {
		String strQuery = "select max(p.dataAte) from PeriodoMedicao p " +
				"where p.projeto.id = :projeto and p.excluido = :excluido";
		TypedQuery<Calendar> query = em.createQuery(strQuery, Calendar.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return (Calendar) query.getSingleResult();
	}
	@Override
	public List<DatasPeriodoMedicaoVO>buscarDatasDe(){
		String jpql="select distinct new com.nucleo.model.VO.DatasPeriodoMedicaoVO(p.descricao, p.dataDe)"
				+ " from PeriodoMedicao p"
				+ " where p.excluido = :excluido order by p.dataDe asc";
		return em.createQuery(jpql, DatasPeriodoMedicaoVO.class)
				.setParameter("excluido", false)
				.getResultList();
	}
	@Override
	public List<PeriodoMedicao> buscarTodosPorStatus(List<StatusPeriodoEnum>status) {
		String strQuery = "select distinct(pm) from PeriodoMedicao pm"
				+ " join fetch pm.projeto as p"
				+ " left join fetch p.impostos"
				+ " join fetch p.responsavelAdm"
				+ " left join fetch pm.alteracoes as a"
				+ " left join fetch pm.detalhamentoPeriodoMedicao"
				+" Where pm.excluido =:excluido"
				+" and pm.status =:statusPeriodo"
				+" order by pm.id";
		TypedQuery<PeriodoMedicao> query = em.createQuery(strQuery, PeriodoMedicao.class);
		query.setParameter("excluido", false);
		query.setParameter("statusPeriodo", status);
		return query.getResultList();
	}
	@Override
	public List<PeriodoMedicao> buscarPeriodosEmAberto() {
		List<PeriodoMedicao> periodos = new ArrayList<>();
		List<Projeto>projetos = projetoDAO.listarTodos();
		for(Projeto projeto : projetos){ 
			String strQuery = "select distinct(pm) from PeriodoMedicao pm"
					+ " left join fetch pm.projeto as p"
					+ " left join fetch p.impostos"
					+ " join fetch p.responsavelAdm"
					+ " left join fetch pm.alteracoes as a"
					+ " left join fetch pm.detalhamentoPeriodoMedicao" +
					" Where p.id = :projeto "+
					" and p.status=0" +
					" and pm.excluido = :excluido" +
					" and pm.status = :statusPeriodo" +
					" order by pm.id ";
			TypedQuery<PeriodoMedicao> query = em.createQuery(strQuery, PeriodoMedicao.class);
			query.setParameter("projeto", projeto.getId());
			query.setParameter("excluido", false);
			query.setParameter("statusPeriodo", StatusPeriodoEnum.EMABERTO);
			try{
			periodos.add(query.getResultList().get(0));
			}catch(IndexOutOfBoundsException e){}
		
		}
		return periodos;
	}
	@Override
	public List<AlteracaoPeriodoMedicao> buscarAlteracoesPeriodo(PeriodoMedicao periodo) {
		String strQuery = "select distinct a From AlteracaoPeriodoMedicao a left join fetch a.periodoMedicao as pm left join fetch pm.projeto as p join fetch p.responsavelAdm " +
				"Where a.periodoMedicao.id = :periodo and a.excluido = :excluido"; 
		TypedQuery<AlteracaoPeriodoMedicao> query = em.createQuery(strQuery, AlteracaoPeriodoMedicao.class);
		query.setParameter("periodo", periodo.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}
	
	@Override
	public String getVersao(PeriodoMedicao periodo) {
		char motivoCliente = '0';
		int motivoNucleo = 0;
		for (AlteracaoPeriodoMedicao alteracao : buscarAlteracoesPeriodo(periodo)) {
			if (alteracao.getMotivo().equals(MotivoAlteracaoPeriodoEnum.NUCLEO)) {
				motivoNucleo++;
			} else {
				if (motivoCliente == '0')
					motivoCliente = 'A';
				else
					motivoCliente++;
			}
		}
		return motivoNucleo + "." + motivoCliente;
	}

	@Override
	public List<PeriodoMedicao> buscarTodosPorProjeto(Projeto projeto) {
		String strQuery = "select pm from PeriodoMedicao pm"
				+ " left join fetch pm.projeto as p"
				+ " join fetch p.responsavelAdm"
				+" Where pm.excluido = :excluido " +
				"and p.id = :projeto order by pm.id ";
		TypedQuery<PeriodoMedicao> query = em.createQuery(strQuery, PeriodoMedicao.class);
		query.setParameter("excluido", false);
		query.setParameter("projeto", projeto.getId());
		return query.getResultList();
	}

	@Override
	public List<PeriodoMedicao> buscarTodosPorProjetoStatus(Projeto projeto,
			List<StatusPeriodoEnum> status) {
		String strQuery = "select p from PeriodoMedicao p " +
				"Where p.excluido = :excluido " +
				"and p.projeto.id = :projeto " +
				"and p.status in :status " +
				"order by p.id asc";
		TypedQuery<PeriodoMedicao> query = em.createQuery(strQuery, PeriodoMedicao.class);
		query.setParameter("excluido", false);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("status", status);
		return query.getResultList();
	}

	@Override
	public Long getQuantidadeProfissionais(PeriodoMedicao periodo, Servico equipe) {
		String strQuery = "select COUNT(m) from MedicaoEquipe m " +
				"Where m.excluido = :excluido " +
				"and m.periodoMedicao.id = :periodo " +
				"and m.mobilizacao.cargo.servico.id = :equipe";
		Query query = em.createQuery(strQuery);
		query.setParameter("excluido", false);
		query.setParameter("periodo", periodo.getId());
		query.setParameter("equipe", equipe.getId());
		try {
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return Long.valueOf(0);
		}
	}

	@Override
	public BigDecimal getSomaValorVendaEquipePeriodo(PeriodoMedicao periodo, Servico equipe) {
		String strQuery = "select SUM(m.mobilizacao.cargo.valorVenda) from MedicaoEquipe m " +
				"Where m.excluido = :excluido " +
				"and m.periodoMedicao.id = :periodo " +
				"and m.mobilizacao.cargo.servico.id = :equipe";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery, BigDecimal.class);
		query.setParameter("excluido", false);
		query.setParameter("periodo", periodo.getId());
		query.setParameter("equipe", equipe.getId());
		try {
			return (BigDecimal)query.getSingleResult();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public BigDecimal getSomaValorVendaEquipePeriodoCargo(PeriodoMedicao periodo, Servico equipe,
			String funcao) {
		String strQuery = "select SUM(m.mobilizacao.cargo.valorVenda) from MedicaoEquipe m " +
				"where m.excluido = :excluido" +
				" and m.periodoMedicao.id = :periodo " +
				" and m.mobilizacao.cargo.servico.id = :equipe"+
				" and m.mobilizacao.cargo.funcaoMD = :funcao";
		TypedQuery<BigDecimal> query = em.createQuery(strQuery, BigDecimal.class);
		query.setParameter("excluido", false);
		query.setParameter("periodo", periodo.getId());
		query.setParameter("equipe", equipe.getId());
		query.setParameter("funcao", funcao);
		try {
			return (BigDecimal)query.getSingleResult();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}
	@Override
	public List<PeriodoMedicao>buscarTodosPorProjetoComBaseDeCalculoValida(Projeto projeto){
		BigDecimal menorBaseDeCalculo = BigDecimal.ZERO;
		String jpql = "select p from PeriodoMedicao p where p.projeto = :projeto and  p.baseCalculo > :baseCalculo ";
		return em.createQuery(jpql, PeriodoMedicao.class)
		.setParameter("projeto", projeto)
		.setParameter("baseCalculo", menorBaseDeCalculo)
		.getResultList();
	}
	@Override
	public PeriodoMedicao buscarPeriodoPorDataEProjeto(Calendar dataDe, Projeto projeto){
		PeriodoMedicao periodoMedicao = new PeriodoMedicao();
		try{
			String nativeQuery = "select pm.* from periodomedicao pm"
					+ " inner join projeto as p on(p.id=pm.projeto_id)"
					+ " where pm.dataDe = '"+dataDe.getTime()+"'"
					+ " and p.id='"+projeto.getId()+"';";
			periodoMedicao= (PeriodoMedicao) em.createNativeQuery(nativeQuery, PeriodoMedicao.class)
					.getSingleResult();
			PeriodoMedicao novo = em.merge(periodoMedicao);
		return novo;
		}catch(NoResultException e){
			System.out.println("Projeto "+projeto.getId()+" n�o tem periodo com a data de "+dataDe.getTime());
			return periodoMedicao;
		}
	}
	@Override
	public void alterar(PeriodoMedicao periodoVelho, int idUsuario){
		PeriodoMedicao periodoNovo = em.merge(periodoVelho);
		super.alterar(periodoNovo, idUsuario);
	}
}
