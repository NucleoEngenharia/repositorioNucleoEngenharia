package com.nucleo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;
@Stateless
public class CarregaMedicoesUtil {
	
	private Map<Integer, List<MedicaoEquipe>>medicoesEquipe;
	@EJB
	private MedicaoEquipeDAO medicaoEquipeDAO;
	
	public Map<Integer, List<MedicaoEquipe>>carregaMedicaoEquipes(PeriodoMedicao periodoSelecionado, List<Servico>servicoEquipes){
		medicoesEquipe = new HashMap<Integer,List<MedicaoEquipe>>();
		List<MedicaoEquipe> todasMedicoes = medicaoEquipeDAO.listarPorPeriodo(periodoSelecionado);
		for (Servico equipe : servicoEquipes) {
			List<MedicaoEquipe> medicoes = new ArrayList<MedicaoEquipe>();
			for (MedicaoEquipe medicao : todasMedicoes) {
				if (medicao.getMobilizacao().getCargo().getServico().getId() == equipe.getId()) {
					medicoes.add(medicao);
				}
			}
			medicoesEquipe.put(equipe.getId(), medicoes);
	}
		return medicoesEquipe;
	}
}
