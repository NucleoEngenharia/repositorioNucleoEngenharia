package com.nucleo.contratos.DAOImpl;

import java.util.List;

import javax.ejb.Stateless;

import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.contratos.factorBD.Factor;
@Stateless
public class FuncionarioDAOImpl extends Factor implements FuncionarioDAO {
	@Override
	public List<Funcionario> listarTodos() {
		return null;
	}

	@Override
	public void inserir(Funcionario funcionario, int idUsuario) {
		funcionario.setUsuarioCriacao(idUsuario);
		em.persist(funcionario);
	}

	@Override
	public void fazPrimeiroAcesso(Funcionario funcionario) {
	}

}
