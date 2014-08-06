package com.nucleo.contratos.DAOImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.contratos.factorBD.Factor;
@Stateless
public class FuncionarioDAOImpl extends Factor implements FuncionarioDAO {
	@Override
	public List<Funcionario> listarTodos() {
		String jpql = "select f from Funcionario f"
				+ " where f.excluido=:excluido"
				+ " and f.dtDemissao=null";
		return em.createQuery(jpql, Funcionario.class)
				.setParameter("excluido", false)
				.getResultList();
	}

	@Override
	public void inserir(Funcionario funcionario, int idUsuario) {
		funcionario.setCpf(funcionario.getCpf().replace("-", ""));
		funcionario.setCpf(funcionario.getCpf().replace(".", ""));
		funcionario.setUsuarioCriacao(idUsuario);
		
		Funcionario f = buscaFuncionarioPorCpf(funcionario);
			if(f.getId()>0){
			f.setCn(funcionario.getCn());
			f.setDtAdmissao(funcionario.getDtAdmissao());
			f.setMatricula(funcionario.getMatricula());
			f.setNome(funcionario.getNome());
			f.setDtDemissao(funcionario.getDtDemissao());
			em.merge(f);
			}else{
				em.persist(funcionario);
			}
	}
	@Override
	public Funcionario buscaFuncionarioPorCpf(Funcionario funcionario){
		Funcionario f = new Funcionario();
		try{
		String jpql="select f from Funcionario f"
				+ " where f.excluido=:excluido"
				+ " and f.cpf=:cpf";
		 f =em.createQuery(jpql, Funcionario.class)
				.setParameter("excluido", false)
				.setParameter("cpf", funcionario.getCpf())
				.getSingleResult();
		}catch(NoResultException n){
			//Usuario não encontrado
			f.setId(0);
		}
		 return f;
		
	}
	@Override
	public void fazPrimeiroAcesso(Funcionario funcionario) {
	}

}
