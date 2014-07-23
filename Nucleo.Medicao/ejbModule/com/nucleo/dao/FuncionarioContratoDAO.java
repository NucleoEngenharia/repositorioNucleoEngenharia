package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.medicao.FuncionarioContrato;
import com.nucleo.entity.medicao.Mobilizacao;

@Remote
public interface FuncionarioContratoDAO extends DAO<FuncionarioContrato, Integer> {

	List<FuncionarioContrato>listarTodos();
	
	void salvar(FuncionarioContrato funcionarioContrato, int idUsuario);
	
	List<Integer> buscarTodasCN();

	List<FuncionarioContrato> buscarTodosPorCN(int cn);
	
	FuncionarioContrato buscarPorCPF(String cpf);

	List<Mobilizacao> buscarTodasMobilizacoes(FuncionarioContrato funcionario);
	
	boolean funcionarioExiste(FuncionarioContrato funcionarioContrato);
	
}
