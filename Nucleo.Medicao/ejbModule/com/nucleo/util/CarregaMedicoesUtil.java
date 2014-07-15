package com.nucleo.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;
@Stateless
public class CarregaMedicoesUtil {
	
	private Map<Integer, List<MedicaoEquipe>>medicoesEquipe;
	@EJB
	private MedicaoEquipeDAO medicaoEquipeDAO;
	@EJB
	private MobilizacaoDAO mobilizacaoDAO;
	
	public Map<Integer, List<MedicaoEquipe>>carregaMedicaoEquipes(PeriodoMedicao periodoSelecionado, List<Servico>servicoEquipes){
		medicoesEquipe = new HashMap<Integer,List<MedicaoEquipe>>();
		List<MedicaoEquipe> todasMedicoes = medicaoEquipeDAO.listarPorPeriodo(periodoSelecionado);
		List<Mobilizacao>mobilizacoesDoPeriodo = mobilizacaoDAO.buscarTodosPorPeriodo(periodoSelecionado);
		
		for (Servico equipe : servicoEquipes) {
			List<MedicaoEquipe> list = new ArrayList<MedicaoEquipe>();
			for (MedicaoEquipe medicao : todasMedicoes) {
					if (medicao.getMobilizacao().getCargo().getServico().getId() == equipe.getId()) {
								list.add(medicao);
					}
			}
					for(Mobilizacao mobilizacao:mobilizacoesDoPeriodo){
						if(!procuraMobilizacao(list, mobilizacao)&&mobilizacao.getCargo().getServico().getId()==equipe.getId()){
							MedicaoEquipe medicaoEquipe = new MedicaoEquipe();
							medicaoEquipe.setPeriodoMedicao(periodoSelecionado);
							medicaoEquipe.setMobilizacao(mobilizacao);
							medicaoEquipe.setQuantidadeMedido(BigDecimal.ZERO);
							medicaoEquipe.setValorMedido(BigDecimal.ZERO);
							list.add(medicaoEquipe);
						}
					}
					
			
			medicoesEquipe.put(equipe.getId(), list);
	}
		
		
		return medicoesEquipe;
	}
	private boolean procuraMobilizacao(List<MedicaoEquipe>medicoes, Mobilizacao mobilizacao){
		boolean achou = false;
		for(MedicaoEquipe medicaoEquipe:medicoes){
			
			if(medicaoEquipe.getMobilizacao().getId()==mobilizacao.getId()){
				achou = true;
				break;
			}
		}
		return achou;
	}
}
