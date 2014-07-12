package com.nucleo.projetos.medicao.MB;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import com.nucleo.dao.JustificativaDAO;
import com.nucleo.entity.medicao.Justificativa;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.seguranca.to.FuncionarioTO;
public class JustificativaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	JustificativaDAO justificativaDAO;
	private double diasAtestado;
	private double diasInjustificado;
	private double diasFerias;
	private double diasOutros;
	@PostConstruct
	public void init() {
	}
	public double getDiasAtestado() {
		return diasAtestado;
	}

	public double getDiasInjustificado() {
		return diasInjustificado;
	}

	public double getDiasFerias() {
		return diasFerias;
	}

	public double getDiasOutros() {
		return diasOutros;
	}
	public void setDiasAtestado(double diasAtestado) {
		this.diasAtestado = diasAtestado;
	}
	public void setDiasInjustificado(double diasInjustificado) {
		this.diasInjustificado = diasInjustificado;
	}

	public void setDiasFerias(double diasFerias) {
		this.diasFerias = diasFerias;
	}

	public void setDiasOutros(double diasOutros) {
		this.diasOutros = diasOutros;
	}
	private Justificativa justificativa = new Justificativa();

	public Justificativa getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(Justificativa justificativa) {
		this.justificativa = justificativa;
	}

	public void salvaJustificativas(MedicaoEquipe equipeSelect, PeriodoMedicao periodoMedicao, BigDecimal diasDevidos,
			FuncionarioTO usuarioLogado) {
		System.out.println("teste" + diasAtestado + "/" + equipeSelect.getMobilizacao().getId());
		justificativa.setIdPeriodoMedicao(periodoMedicao.getId());
		justificativa.setDiasAtestado(BigDecimal.valueOf(diasAtestado));
		justificativa.setDiasFerias(BigDecimal.valueOf(diasFerias));
		justificativa.setDiasInjustificado(BigDecimal.valueOf(diasInjustificado));
		justificativa.setDiasOutros(BigDecimal.valueOf(diasOutros));
		justificativa.setFaltas(diasDevidos);
		justificativa.setMobilizacao(equipeSelect.getMobilizacao());
		justificativaDAO.inserir(justificativa, usuarioLogado.getPessoa_id());

	}
}
