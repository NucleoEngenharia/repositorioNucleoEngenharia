package com.nucleo.projetos.relatorios;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import com.nucleo.commom.Commom;
import com.nucleo.dao.CargoDAO;
import com.nucleo.dao.MedicaoDespesaDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.medicao.MedicaoDespesa;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.Mobilizacao;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.projetos.relatorios.model.CargoModel;
import com.nucleo.projetos.relatorios.model.DespesaModel;
import com.nucleo.projetos.relatorios.model.EquipeModel;
import com.nucleo.projetos.relatorios.model.MobilizacaoModel;
import com.nucleo.projetos.relatorios.model.RelatorioMensal;

public class ProcessosRelatorioMensal  {

	private ProjetoDAO projetoDAO;
	private ServicoDAO servicoDAO;
	private CargoDAO cargoDAO;
	private PeriodoMedicaoDAO periodoDAO;
	private MobilizacaoDAO mobilizacaoDAO;
	private MedicaoDespesaDAO medicaoDespesaDAO;

	public ProcessosRelatorioMensal() {
		projetoDAO = (ProjetoDAO) Commom.lookup("ProjetoDAOImpl");
		cargoDAO = (CargoDAO) Commom.lookup("CargoDAOImpl");
		servicoDAO = (ServicoDAO) Commom.lookup("ServicoDAOImpl");
		periodoDAO = (PeriodoMedicaoDAO) Commom.lookup("PeriodoMedicaoDAOImpl");
		mobilizacaoDAO = (MobilizacaoDAO) Commom.lookup("MobilizacaoDAOImpl");		
		medicaoDespesaDAO = (MedicaoDespesaDAO) Commom.lookup("MedicaoDespesaDAOImpl");		
	}
	public  String geraUrlRelatorio(PeriodoMedicao periodo, String pastaDestino) {
		File pastaProjeto = new File(pastaDestino + "CN" + periodo.getProjeto().getCN());
		if (!pastaProjeto.exists()) {
			pastaProjeto.mkdirs();
		}

		StringBuilder strRetorno = new StringBuilder();
		strRetorno.append(pastaProjeto.toString());
		strRetorno.append(File.separator);
		strRetorno.append("periodo_" + periodo.getId() + ".xlsx");

		return strRetorno.toString();
	}

	public String gerarRelatorioPeriodo(PeriodoMedicao periodo, RelatorioMensal relatorio,
			String urlModelo, String pastaDestino) {

		XLSTransformer transformer = new XLSTransformer();

		File modelo = new File(urlModelo);
		if (!modelo.exists())
			return null;
		if (!modelo.isFile())
			return null;

		String urlDestino = geraUrlRelatorio(periodo, pastaDestino);

		Map<String, Object> beans = new HashMap<String, Object>();
		
		beans.put("relatorio", relatorio);

		try {
			transformer.transformXLS(urlModelo, beans, urlDestino);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlDestino;
	}

	public RelatorioMensal carregaDadosRelatorio(PeriodoMedicao periodo,
			Map<Integer, List<MedicaoEquipe>> medicoesEquipe) {

		RelatorioMensal modelo = new RelatorioMensal();
		modelo.setInicioPeriodo(periodo.getDataDe());
		modelo.setFimPeriodo(periodo.getDataAte());
		modelo.setNumContrato(periodo.getProjeto().getCNCliente());
		modelo.setValorContrato(projetoDAO.getValorAtual(periodo.getProjeto()));
		modelo.setValorAcumuladoAtePeriodo(projetoDAO.getTotalMedido(periodo.getProjeto()));
		modelo.setInicioContrato(periodo.getProjeto().getDataAberturaOIS());
		modelo.setFimContrato(projetoDAO.getDataFimAtual(periodo.getProjeto()));
		modelo.setSaldoProjeto(projetoDAO.getSaldo(periodo.getProjeto()));
		modelo.setIndiceReajuste(projetoDAO.buscarUltimoReajusteValido(periodo.getProjeto()).getIndice());
		modelo.setCn(periodo.getProjeto().getCN());
		modelo.setObjeto(periodo.getProjeto().getObjeto());
		modelo.setNomeResponsavel(periodo.getProjeto().getResponsavelAdm().getNomeProposto());
		modelo.setNomeGerenteNucleo(periodo.getProjeto().getResponsavelAdm().getNomeGerenteNucleo());
		modelo.setNomeGerenteRelacionamento(periodo.getProjeto().getResponsavelAdm().getNomeGerenteRelacionamento());
		modelo.setNomeFiscal(periodo.getProjeto().getResponsavelAdm().getFiscal());
		modelo.setDescricaoProjeto(periodo.getProjeto().getDescricaoCurta());
		
		for (Servico servico : servicoDAO.buscarEquipesPorProjeto(periodo.getProjeto())) {
			EquipeModel equipe = new EquipeModel();
			equipe.setValorUnitario(servico.getValorUnitario());
			equipe.setValorParcial(servico.getValorTotal());
			equipe.setDescricao(servico.getDescricao());
			BigDecimal valorMedidoEquipe = servicoDAO.calcularTotalMedidoEquipe(servico, periodo,
					medicoesEquipe.get(servico.getId()));

			equipe.setTotalMedicoesPendentes(valorMedidoEquipe);
			equipe.setTotalMedicoesAprovadas(servicoDAO.getTotalMedido(servico));

			for (Cargo cargo : cargoDAO.buscarTodosPorServico(servico)) {
				CargoModel cargoModel = new CargoModel();
				cargoModel.setDescricao(cargo.getFuncaoMD());

				cargoModel.setQuant(periodoDAO.buscarTodosPorProjeto(periodo.getProjeto()).size());
				cargoModel.setUn(cargo.getQuantidadeUso()
						.divide(BigDecimal.valueOf(cargoModel.getQuant()), 2, RoundingMode.CEILING)
						.doubleValue());
				cargoModel.setValorUnitario(cargo.getValorVenda());
				cargoModel.setValorParcial(cargo.getValorTotal());

				cargoModel.setValorMedicoesAprovadas(cargoDAO.getTotalMedido(cargo));
				cargoModel.setValorMedicaoPendente(cargoDAO.getTotalMedido(cargo, periodo));
				
				equipe.getCargos().add(cargoModel);
			}

			for (Mobilizacao mobilEntity : mobilizacaoDAO.buscarTodosPorPeriodoServico(periodo,
					servico)) {
				MedicaoEquipe medicao = getMedicaoPorMobilizacao(
						medicoesEquipe.get(servico.getId()), mobilEntity);

				MobilizacaoModel mobil = new MobilizacaoModel();
				mobil.setDataMobilizacao(mobilEntity.getDataMobilizacao());
				mobil.setDataDesmobilizacao(mobilEntity.getDataDesmobilizacao());
				mobil.setDiasTrabalhados(medicao.getQuantidadeMedido());
				mobil.setFuncao(mobilEntity.getCargo().getFuncaoMD());
				mobil.setNome(mobilEntity.getFuncionario().getNomeCompleto());
				mobil.setSalarioBase(mobilEntity.getFuncionario().getSalario());
				mobil.setValorMedido(medicao.getValorMedido());
				mobil.setValorVenda(mobilEntity.getCargo().getValorVenda());
				mobil.setBaseCalculo(periodo.getBaseCalculo());

				equipe.getMobilizacoes().add(mobil);
			}

			for(MedicaoDespesa despesa : medicaoDespesaDAO.buscarTodosPorPeriodoEquipe(periodo, servico)){
				DespesaModel despesaModel = new DespesaModel();
				despesaModel.setNomeFuncionario(despesa.getMobilizacao().getFuncionario().getNomeCompleto());
				despesaModel.setFuncao(despesa.getMobilizacao().getCargo().getFuncaoMD());
				despesaModel.setTemImpostos(periodo.getProjeto().isDespesaTemImposto());
				despesaModel.setValor(despesa.getValor());
				
				StringBuilder periodoDespesa = new StringBuilder();
				periodoDespesa.append(Commom.converterDataParaString(despesa.getPeriodoDe().getTime(), "dd/MM/YYYY"));
				periodoDespesa.append(" a ");
				periodoDespesa.append(Commom.converterDataParaString(despesa.getPeriodoAte().getTime(), "dd/MM/YYYY"));
				despesaModel.setPeriodoDespesa(periodoDespesa.toString());
				
				equipe.getDespesas().add(despesaModel);
			}
			
			modelo.getEquipes().add(equipe);
		}
		return modelo;
	}

	private MedicaoEquipe getMedicaoPorMobilizacao(List<MedicaoEquipe> medicoes,
			Mobilizacao mobilizacao) {
		for (MedicaoEquipe medicao : medicoes) {
			if (medicao.getMobilizacao().getId() == mobilizacao.getId()) {
				return medicao;
			}
		}
		return null;
	}

}
