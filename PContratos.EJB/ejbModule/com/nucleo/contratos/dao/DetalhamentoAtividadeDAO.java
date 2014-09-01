package com.nucleo.contratos.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.contratos.entity.Atividades;
import com.nucleo.contratos.entity.DetalhamentoAtividade;

@Remote
public interface DetalhamentoAtividadeDAO {
	List<DetalhamentoAtividade>buscarPorAtividade(Atividades atividade);
}
