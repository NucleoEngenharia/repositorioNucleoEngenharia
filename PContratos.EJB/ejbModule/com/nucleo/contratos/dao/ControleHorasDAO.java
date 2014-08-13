package com.nucleo.contratos.dao;

import java.util.Calendar;

import javax.ejb.Remote;

import com.nucleo.contratos.entity.ControleDeHorarios;
import com.nucleo.contratos.entity.Funcionario;

@Remote
public interface ControleHorasDAO {
	void registarInicioAtividades(ControleDeHorarios controleDeHorarios);
	void registarPonto(ControleDeHorarios controleDeHorarios);
	ControleDeHorarios buscarHorariosPorData(Calendar data, Funcionario funcionario);

}
