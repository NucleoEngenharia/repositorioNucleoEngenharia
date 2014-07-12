package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.FuncionarioContratoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.medicao.FuncionarioContrato;
import com.nucleo.entity.medicao.Mobilizacao;

@Stateless
public class FuncionarioContratoDAOImpl extends DAOImpl<FuncionarioContrato, Integer>
		implements
			FuncionarioContratoDAO {
	@Override
	public List<FuncionarioContrato>listarTodos(){
		return em.createQuery("select distinct f from FuncionarioContrato f", FuncionarioContrato.class)
		.getResultList();
	}
	@Override
	public List<Integer> buscarTodasCN() {
		TypedQuery<Integer> query = em.createQuery(
				"select DISTINCT f.cn from FuncionarioContrato f "
						+ "Where f.excluido = :excluido Order by f.cn", Integer.class);
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public List<FuncionarioContrato> buscarTodosPorCN(int cn) {
		TypedQuery<FuncionarioContrato> query = em.createQuery(
				"Select f from FuncionarioContrato f join fetch f.mobilizacoes as m left join fetch m.cargo as c left join fetch c.servico"
						+" Where f.cn = :cn and f.excluido = :excluido", FuncionarioContrato.class);
		query.setParameter("cn", cn);
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public List<Mobilizacao> buscarTodasMobilizacoes(FuncionarioContrato funcionario) {

		String strQuery = "select m from Mobilizacao m " +
				"where m.funcionario.id = :func and m.excluido = :excluido";

		TypedQuery<Mobilizacao> query = em.createQuery(strQuery, Mobilizacao.class);
		query.setParameter("func", funcionario.getId());
		query.setParameter("excluido", false);
		
		return query.getResultList();
	}

	@Override
	public boolean funcionarioExiste(FuncionarioContrato funcionarioContrato) {
		boolean existe = false;
		String strQuery = "select f from FuncionarioContrato f where f.cpf = :func and f.excluido = :excluido";
		TypedQuery<FuncionarioContrato> query = em.createQuery(strQuery, FuncionarioContrato.class);
		query.setParameter("func", funcionarioContrato.getCpf());
		query.setParameter("excluido", false);
		if(query.getResultList().size()==1){
			existe = true;
		}
		return existe;
	}

	@Override
	public FuncionarioContrato buscarPorCPF(String cpf) {
		String strQuery = "select f from FuncionarioContrato f where f.cpf = :cpf and f.excluido = :excluido";
		TypedQuery<FuncionarioContrato> query = em.createQuery(strQuery, FuncionarioContrato.class);
		query.setParameter("cpf", cpf);
		query.setParameter("excluido", false);
		return query.getSingleResult();
	}
}
