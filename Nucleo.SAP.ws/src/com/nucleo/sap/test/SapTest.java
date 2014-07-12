package com.nucleo.sap.test;

import org.junit.Assert;
import org.junit.Test;

import com.nucleo.sap.bo.SapBO;
import com.nucleo.sap.to.ImpostoTO;
import com.nucleo.sap.to.ProjetoTO;

public class SapTest {

	private SapBO sapBO;
	
	public SapTest() {
		sapBO = new SapBO();
	}
	
	@Test
	public void testarListaProjetosSap(){
		ProjetoTO[] projetos = sapBO.getProjetos();
		Assert.assertNotNull(projetos);
	}	
	
	@Test
	public void testarListaImpostosSap(){
		ImpostoTO[] impostos = sapBO.getImpostos();
		Assert.assertNotNull(impostos);
	}
}
