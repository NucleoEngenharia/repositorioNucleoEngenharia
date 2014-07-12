package com.nucleo.model.calculos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.model.calculos.client.CalculosMedicao;

@Stateless
public class CalculosMedicaoImpl implements CalculosMedicao{
	private BigDecimal totalMedido = new BigDecimal(0);
	
	@EJB
	private PeriodoMedicaoDAO periodoDAO;
	
	//Cálculo Simples
	@Override
	public BigDecimal calculoSimples(List<MedicaoEquipe>medicoes){
		totalMedido = BigDecimal.ZERO;
		for (MedicaoEquipe medicao : medicoes) {
			totalMedido = totalMedido.add(medicao.getValorMedido());
		}
		return totalMedido;
	}
	@Override
	public BigDecimal calculoPorEquipe(PeriodoMedicao periodo,List<MedicaoEquipe>medicoes){
		BigDecimal diasTrabalhados = BigDecimal.ZERO;
		Long qtdProfissionais = 0l;
		BigDecimal somaValorVendaEquipePeriodo = BigDecimal.ZERO;
		totalMedido = BigDecimal.ZERO;
		for (MedicaoEquipe medicao : medicoes) {
			diasTrabalhados = diasTrabalhados.add(medicao.getQuantidadeMedido());
			somaValorVendaEquipePeriodo = somaValorVendaEquipePeriodo.add(medicao.getMobilizacao().getCargo().getValorVenda());
		}
		qtdProfissionais = Long.valueOf(medicoes.size());
		totalMedido = formulaCalculoPorEquipe(diasTrabalhados,
				qtdProfissionais, somaValorVendaEquipePeriodo, periodo.getBaseCalculo());
		return totalMedido;
	}
	@Override
	public BigDecimal calculoPorComplexidade(PeriodoMedicao periodo,List<MedicaoEquipe>medicoes){
		Long qtdProfissionaisBC = 0l;
		Long qtdProfissionaisMC = 0l;
		Long qtdProfissionaisAC = 0l;
		BigDecimal totalMedidoBC = BigDecimal.ZERO;
		BigDecimal totalMedidoMC = BigDecimal.ZERO;
		BigDecimal totalMedidoAC = BigDecimal.ZERO;
		totalMedido = BigDecimal.ZERO;
		BigDecimal somaDiasTrabalhadosEquipePeriodoMC = BigDecimal.ZERO;
		BigDecimal somaDiasTrabalhadosEquipePeriodoBC = BigDecimal.ZERO;
		BigDecimal somaDiasTrabalhadosEquipePeriodoAC = BigDecimal.ZERO;
		BigDecimal somaValordeVendaEquipeBC = BigDecimal.ZERO;
		BigDecimal somaValordeVendaEquipeMC = BigDecimal.ZERO;
		BigDecimal somaValordeVendaEquipeAC = BigDecimal.ZERO;
		for(MedicaoEquipe medicaoEquipe: medicoes){
			
			if(medicaoEquipe.getMobilizacao().getCargo().getFuncaoMD().equals("Baixa Complexidade")){
				somaDiasTrabalhadosEquipePeriodoBC = somaDiasTrabalhadosEquipePeriodoBC.add(medicaoEquipe.getQuantidadeMedido());
				qtdProfissionaisBC = qtdProfissionaisBC+1l;
				somaValordeVendaEquipeBC = somaValordeVendaEquipeBC.add(medicaoEquipe.getMobilizacao().getCargo().getValorVenda());
			}
			if(medicaoEquipe.getMobilizacao().getCargo().getFuncaoMD().equals("Média Complexidade")){
				somaDiasTrabalhadosEquipePeriodoMC = somaDiasTrabalhadosEquipePeriodoMC.add(medicaoEquipe.getQuantidadeMedido());
				qtdProfissionaisMC = qtdProfissionaisMC+1l;
				somaValordeVendaEquipeMC = somaValordeVendaEquipeMC.add(medicaoEquipe.getMobilizacao().getCargo().getValorVenda());
			}
			if(medicaoEquipe.getMobilizacao().getCargo().getFuncaoMD().equals("Alta Complexidade")){
				somaDiasTrabalhadosEquipePeriodoAC = somaDiasTrabalhadosEquipePeriodoAC.add(medicaoEquipe.getQuantidadeMedido());
				qtdProfissionaisAC = qtdProfissionaisAC+1l;
				somaValordeVendaEquipeAC = somaValordeVendaEquipeAC.add(medicaoEquipe.getMobilizacao().getCargo().getValorVenda());
			}
		}
		totalMedidoBC = formulaCalculoPorEquipe(somaDiasTrabalhadosEquipePeriodoBC, qtdProfissionaisBC, somaValordeVendaEquipeBC, periodo.getBaseCalculo());
		totalMedidoMC = formulaCalculoPorEquipe(somaDiasTrabalhadosEquipePeriodoMC, qtdProfissionaisMC, somaValordeVendaEquipeMC, periodo.getBaseCalculo());
		totalMedidoAC = formulaCalculoPorEquipe(somaDiasTrabalhadosEquipePeriodoAC, qtdProfissionaisAC, somaValordeVendaEquipeAC, periodo.getBaseCalculo());
		
		totalMedido = totalMedidoBC.add(totalMedidoMC);
		totalMedido = totalMedido.add(totalMedidoAC);
		return totalMedido;
	}
	@Override
	public BigDecimal formulaCalculoPorEquipe(BigDecimal diasTrabalhados,
			Long qtdProfissionais, BigDecimal somaValorVendaEquipePeriodo, BigDecimal baseCalculo){
		
		if (diasTrabalhados.equals(BigDecimal.ZERO) || qtdProfissionais == 0) {
			return BigDecimal.ZERO;
		}

		// FORMULA :
		// diasTrabalhados / (baseCalculo * qtdProfissionais) *
		// somaValorVendaEquipePeriodo

		// (baseCalculo * qtdProfissionais)
		BigDecimal totalPeriodo = baseCalculo.multiply(BigDecimal.valueOf(qtdProfissionais),
				MathContext.DECIMAL32);

		// diasTrabalhados / (baseCalculo * qtdProfissionais)
		totalPeriodo = diasTrabalhados.divide(totalPeriodo, 5, RoundingMode.CEILING);

		// diasTrabalhados / (baseCalculo * qtdProfissionais) * valorServico
		totalPeriodo = totalPeriodo.multiply(somaValorVendaEquipePeriodo, MathContext.DECIMAL32);
		return totalPeriodo;
	}
}
