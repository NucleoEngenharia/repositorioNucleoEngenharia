package com.nucleo.contratos.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import com.nucleo.contratos.entity.Atividades;
import com.nucleo.contratos.entity.Funcionario;

@Remote
public interface AtividadeDAO {
	List<Atividades> buscarPorData(Calendar data);
	List<Atividades>buscarTodas();
	List<Atividades>buscarPorFuncionario(Funcionario funcionario);
	Atividades buscarPorDataEFuncionario(Calendar data, Funcionario funcionario);
	void inserir(Atividades atividades, Funcionario funcionario);
	void alterar(Atividades atividades, Funcionario funcionario);
}
