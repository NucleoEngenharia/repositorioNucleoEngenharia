package com.nucleo.dao;


import java.math.BigDecimal;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.medicao.Justificativa;
import com.nucleo.entity.medicao.Mobilizacao;
@Remote
public interface JustificativaDAO extends DAO<Justificativa, Integer> {
    @Override
    void inserir(Justificativa entity, int usuario);
	
    Justificativa buscarPorMobilizacao(Mobilizacao mobilizacao);
    
    BigDecimal somaJustificativas(Justificativa justificativa);
    	
}
