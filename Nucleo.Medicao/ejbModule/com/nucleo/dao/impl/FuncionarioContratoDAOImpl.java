package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import com.nucleo.dao.FuncionarioContratoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.medicao.FuncionarioContrato;
import com.nucleo.entity.medicao.Mobilizacao;

@Stateless
public class FuncionarioContratoDAOImpl
extends DAOImpl<FuncionarioContrato, Integer>
implements FuncionarioContratoDAO {
	@Override
	public List<FuncionarioContrato>listarTodos(){
		String jpql="select distinct f from FuncionarioContrato f"
				+ " where f.excluido=:excluido";
		return em.createQuery(jpql, FuncionarioContrato.class)
				.setParameter("excluido", false)
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
				"Select f from FuncionarioContrato f"
				+ " left join fetch f.mobilizacoes m"
				+ " left join fetch m.cargo c"
				+ " left join fetch c.servico s"
				+" Where f.cn =:cn and f.excluido =:excluido", FuncionarioContrato.class);
		query.setParameter("cn", cn);
		query.setParameter("excluido", false);
		return query.getResultList();
	}
	@Override
	public List<FuncionarioContrato>listarTodosPorCPF(String cpf){
		String jpql = "select f from FuncionarioContrato f"
				+ " where f.cpf = :cpf"
				+ " and f.excluido = :excluido";
		return em.createQuery(jpql, FuncionarioContrato.class)
				.setParameter("cpf", cpf)
				.setParameter("excluido", false)
				.getResultList();
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
		String strQuery = "select f from FuncionarioContrato f"
				+ " where f.cpf = :func and f.excluido = :excluido";
		TypedQuery<FuncionarioContrato> query = em.createQuery(strQuery, FuncionarioContrato.class);
		query.setParameter("func", funcionarioContrato.getCpf());
		query.setParameter("excluido", false);
		if(query.getResultList().size()>0){
			existe = true;
		}
		return existe;
	}

	
	private FuncionarioContrato buscarOUltimo(){
		String jpql = "select f from FuncionarioContrato f"
				+ " where f.excluido=:excluido order by f.id desc";
		List<FuncionarioContrato>f;
		f = em.createQuery(jpql, FuncionarioContrato.class)
				.setParameter("excluido", false)
				.getResultList();
		return f.get(0);
	}
	@Override
	public void salvar(FuncionarioContrato funcionarioContrato, int idUsuario){
		//Próximo programador arrumar esse método
		//ass.: alysson
		//data: fev/2014
		funcionarioContrato.setId(buscarOUltimo().getId()+1);
		super.inserir(funcionarioContrato, idUsuario);
		
	}
	@Override
	public FuncionarioContrato buscaPorCPF(FuncionarioContrato funcionarioContrato, int idUsuario) {
		FuncionarioContrato f = new FuncionarioContrato();
		try{
		String strQuery = "select f from FuncionarioContrato f where f.cpf = :cpf and f.excluido = :excluido";
		TypedQuery<FuncionarioContrato> query = em.createQuery(strQuery, FuncionarioContrato.class);
		query.setParameter("cpf", funcionarioContrato.getCpf());
		query.setParameter("excluido", false);
		f = query.getSingleResult();
		return f;
		}catch (NonUniqueResultException e) {
			f.setId(0);
			return f;
		}catch (NoResultException e) {
			salvar(funcionarioContrato, idUsuario);
			f = buscaPorCPF(funcionarioContrato, idUsuario);
			return f;
		}
	}
}
