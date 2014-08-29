package com.nucleo.contratos.DAOImpl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
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
		String jpql = "select distinct f from Funcionario f"
				+ " left join fetch f.atividades a"
				+ " where f.excluido=:excluido"
				+ " and f.dtDemissao=null";
		return em.createQuery(jpql, Funcionario.class)
				.setParameter("excluido", false)
				.getResultList();
	}
	@Override
	public void deletar(Funcionario funcionario, int idUserLogado){
		Funcionario f = buscaFuncionarioPorCpf(funcionario.getCpf());
		f.setUsuarioExclusao(idUserLogado);
		f.setDataExclusao(Calendar.getInstance());
		f.setExcluido(true);
		em.merge(f);
	}
	@Override
	public void inserir(Funcionario funcionario, int idUsuario) {
		funcionario.setCpf(funcionario.getCpf().replace("-", ""));
		funcionario.setCpf(funcionario.getCpf().replace(".", ""));
		funcionario.setUsuarioCriacao(idUsuario);
		
		Funcionario f = buscaFuncionarioPorCpf(funcionario.getCpf());
		try {
			if(f.getId()>0){
				f.setCn(funcionario.getCn());
				f.setDtAdmissao(funcionario.getDtAdmissao());
				f.setMatricula(funcionario.getMatricula());
				f.setNome(funcionario.getNome());
				f.setDtDemissao(funcionario.getDtDemissao());
				em.merge(f);
			}else{
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				String senhaPadrao = "123@mudar";
				md5.update(senhaPadrao.getBytes());
				BigInteger hash = new BigInteger(1, md5.digest());
				md5.update(senhaPadrao.getBytes());
				funcionario.setSenha(hash.toString());
				em.persist(funcionario);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Funcionario buscaFuncionarioPorCpf(String cpf){
		Funcionario f = new Funcionario();
		try{
		String jpql="select f from Funcionario f"
				+ " where f.excluido=:excluido"
				+ " and f.cpf=:cpf";
		 f =em.createQuery(jpql, Funcionario.class)
				.setParameter("excluido", false)
				.setParameter("cpf", cpf)
				.getSingleResult();
		}catch(NoResultException n){
			//Usuario não encontrado
			f.setId(0);
		}
		 return f;
		
	}
	@Override
	public boolean autenticarFuncExterno(String matricula, String senha){
		boolean autenticado = false;
		long resultSet=0;
		try {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(senha.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
		String jpql="select count(f) from Funcionario f"
				+ " where f.excluido=:excluido"
				+ " and f.matricula=:matricula"
				+ " and f.senha=:senha";
		 resultSet = em.createQuery(jpql, Long.class)
		 .setParameter("excluido", false)
		 .setParameter("matricula", matricula)
		 .setParameter("senha",hash.toString())
		 .getSingleResult();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 if(resultSet==1){
			 autenticado = true;
		 }
		return autenticado;
		
	}
	@Override
	public Funcionario buscaPorMatricula(String matricula){
		Funcionario f = new Funcionario();
		String jpql = "select f from Funcionario f"
				+ " where f.excluido=:excluido"
				+ " and f.matricula=:matricula";
		try{
		f = em.createQuery(jpql, Funcionario.class)
		.setParameter("excluido", false)
		.setParameter("matricula", matricula)
		.getSingleResult();
		}catch(NoResultException n){
			f.setId(0);
		}
		return f;
	}
	@Override
	public void fazPrimeiroAcesso(String matricula, String senha) {
		Funcionario f = new Funcionario();
		try{
		f = buscaPorMatricula(matricula);
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(senha.getBytes());
		BigInteger hash = new BigInteger(1, md5.digest());
		f.setSenha(hash.toString());
		f.setPrimeiroAcesso(false);
		em.merge(f);
		}catch(NoSuchAlgorithmException e){
			System.out.println(e);
		}
	}

}
