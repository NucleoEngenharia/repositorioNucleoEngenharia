package com.nucleo.seguranca.test;

import org.junit.Assert;
import org.junit.Test;

import com.nucleo.seguranca.bo.ResponsavelBO;
import com.nucleo.seguranca.to.FuncionarioTO;

public class ResponsavelTeste {

	private ResponsavelBO respBO;
	
	public ResponsavelTeste(){
		respBO = new ResponsavelBO();
	}
	
	@Test
	public void FuncionariosPorUnidadeDepartamento(){
		int unidade = 8;
		int depart = 3;
		
		FuncionarioTO[] funcs = respBO.getFuncionarios(unidade, depart);
		
		for (FuncionarioTO funcTO : funcs) {
			Assert.assertNotNull(funcTO);
		}
	}
}
