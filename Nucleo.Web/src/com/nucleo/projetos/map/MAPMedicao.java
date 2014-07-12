package com.nucleo.projetos.map;

import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Enum.AtividadeEnum;
import com.nucleo.entity.cadastro.Enum.SetorEnum;
import com.nucleo.sap.to.ProjetoTO;

public class MAPMedicao {

	public static void ProjetoTOtoProjetoJPA(ProjetoTO projTO, Projeto projJPA){
		projJPA.setCN(projTO.getCN());
		projJPA.setDescricao(projTO.getNome());
		projJPA.setSetor(SetorEnum.getPorValor((projTO.getSetor())));
		projJPA.setAtividade(AtividadeEnum.getPorValor((projTO.getAtividade())));
		projJPA.setValorOriginal(projTO.getVlOriginal());
		
		if(projTO.getDtInicio() != null) 
			projJPA.setDataInicio(projTO.getDtInicio());
		
		if(projTO.getDtFim() != null)
			projJPA.setDataFim(projTO.getDtFim());
		
	}
	
}
