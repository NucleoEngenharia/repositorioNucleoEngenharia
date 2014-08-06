package com.nucleo.contratos.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.contratos.entity.Funcionario;

@Remote
public interface FuncionarioDAO {
	List<Funcionario>listarTodos();
	void inserir(Funcionario funcionario,int idUsuario);
	void fazPrimeiroAcesso(Funcionario funcionario);
	Funcionario buscaFuncionarioPorCpf(Funcionario funcionario);
}
