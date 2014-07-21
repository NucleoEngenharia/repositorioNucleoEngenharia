package com.nucleo.dao;


import java.math.BigDecimal;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.medicao.Justificativa;
import com.nucleo.entity.medicao.MedicaoEquipe;
@Remote
public interface JustificativaDAO extends DAO<Justificativa, Integer> {
    
    Justificativa buscarPorMedicaoEquipe(MedicaoEquipe medicaoEquipe);
	
    void salvarJustificativa(Justificativa justificativa, int idUsuario);
    
    BigDecimal somaJustificativas(Justificativa justificativa);
    	
}
