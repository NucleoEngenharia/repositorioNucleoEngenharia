package com.nucleo.contratos.DAOImpl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.nucleo.contratos.dao.AtividadeDAO;
import com.nucleo.contratos.entity.Atividades;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.contratos.factorBD.Factor;

@Stateless
public class AtividadeDAOImpl extends Factor implements AtividadeDAO{
	
	@Override
	public List<Atividades> buscarPorData(Calendar data) {
		String jpql = "select a from Atividades a"
				+ " where a.data=:data";
		return em.createQuery(jpql, Atividades.class)
		.setParameter("data", data)
		.getResultList();
	}

	@Override
	public List<Atividades> buscarTodas() {
		String jpql = "select a from Atividades a";
				return em.createQuery(jpql, Atividades.class)
			    .getResultList();
	}

	@Override
	public List<Atividades> buscarPorFuncionario(Funcionario funcionario) {
		String jpql = "select a from Atividades a"
				+ " where a.funcionario=:func";
		try{
		return em.createQuery(jpql, Atividades.class)
		.setParameter("func", funcionario)
		.getResultList();
		}catch(NoResultException e){
			return new ArrayList<Atividades>();
		}
	}

	@Override
	public Atividades buscarPorDataEFuncionario(Calendar data, Funcionario funcionario) {
		String jpql = "select distinct a From Atividades a"
				+ " left join fetch a.funcionario f "
				+ " left join fetch a.detalhamentoAtividade da"
				+ " where a.data=:data"
				+ " and f.id=:func";
		try{
		return em.createQuery(jpql, Atividades.class)
		.setParameter("data", data)
		.setParameter("func", funcionario.getId())
		.getSingleResult();
		}catch(NoResultException e){
			Atividades a = new Atividades();
			a.setId(0);
			return a;
		}
	}

	@Override
	public void inserir(Atividades atividades, Funcionario funcionario) {
		Atividades a = buscarPorDataEFuncionario(atividades.getData(), funcionario);
		 if(a.getId()==0){
			 a.setFuncionario(funcionario);
			 em.persist(a);
		 }
	}
	@Override
	public void alterar(Atividades atividades, Funcionario funcionario){
		em.merge(atividades);
	}

	@Override
	public List<Atividades> listPorFuncId(int funcId) {
		List<Atividades>a=new ArrayList<Atividades>();
		String jpql = "select a from Atividades a"
				+ " where a.funcionario.id=:funcId"
				+ " and a.excluido=:excluido";
		try{
		a = em.createQuery(jpql, Atividades.class)
				.setParameter("funcId", funcId)
				.setParameter("excluido", false)
				.getResultList();
		return a;
		}catch(NoResultException e){
			return a;
		}
	}

}
