package com.nucleo.contratos.DAOImpl;

import java.util.List;

import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.contratos.factorBD.Factor;

public class FuncionarioDAOImpl extends Factor implements FuncionarioDAO {
	@Override
	public List<Funcionario> listarTodos() {
		return null;
	}

	@Override
	public void inserir(Funcionario funcionario, int idUsuario) {
	}

	@Override
	public void fazPrimeiroAcesso(Funcionario funcionario) {
	}

}
