package com.nucleo.projetos.relatorios;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.values.XmlValueDisconnectedException;

import net.sf.jxls.transformer.XLSTransformer;

import com.nucleo.commom.Commom;
import com.nucleo.dao.MedicaoEquipeDAO;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.dao.ProjetoDAO;
import com.nucleo.dao.ReajusteDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.entity.cadastro.Projeto;
import com.nucleo.entity.cadastro.Reajuste;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.cadastro.Enum.SetorEnum;
import com.nucleo.entity.medicao.DetalhamentoPeriodoMedicao;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.Enum.StatusPeriodoEnum;
import com.nucleo.projetos.relatorios.model.ContratosModel;
import com.nucleo.projetos.relatorios.model.ResumoModeloSistema;
public class ProcessosResumoContratos{
	
	private List<Projeto>projetos;
	private List<PeriodoMedicao>periodos;
	private ProjetoDAO projetoDAO;
	private ServicoDAO servicoDAO;
	private ReajusteDAO reajusteDAO;
	private MedicaoEquipeDAO medicaoEquipeDAO;
	private PeriodoMedicaoDAO periodoMedicaoDAO;
	private ResumoModeloSistema modeloSistema;
	private String nomeDoArquivo;
	private static final int ESCOLHIDA = 1;
	private static final int ANTERIOR = 2;
	private static final int ANTERIORM1 = 3;
	
	private Calendar dataEscolhida = Calendar.getInstance(), 
			 mesAnterior = Calendar.getInstance(),
			 mesAnteriorM1 = Calendar.getInstance();
	
	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}
	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}
	public ProcessosResumoContratos() {
		projetoDAO = (ProjetoDAO) Commom.lookup("ProjetoDAOImpl");
		servicoDAO = (ServicoDAO) Commom.lookup("ServicoDAOImpl");
		reajusteDAO = (ReajusteDAO) Commom.lookup("ReajusteDAOImpl");
		medicaoEquipeDAO = (MedicaoEquipeDAO) Commom.lookup("MedicaoEquipeDAOImpl");
		periodoMedicaoDAO = (PeriodoMedicaoDAO) Commom.lookup("PeriodoMedicaoDAOImpl");
		projetos = projetoDAO.listarTodosComReajuste();
	}
	public List<PeriodoMedicao> getPeriodos() {
		periodos = periodoMedicaoDAO.buscarTodos();
		return periodos;
	}
	public void setPeriodos(List<PeriodoMedicao> periodos) {
		this.periodos = periodos;
	}
	
	public String geraUrlRelatorio(String pastaDestino,Calendar apartirDe) {
		File pastaProjeto = new File(pastaDestino +"resumo_modelo_sistema");
		if (!pastaProjeto.exists()) {
			pastaProjeto.mkdirs();
		}

		StringBuilder urlArquivo = new StringBuilder();
		urlArquivo.append(pastaProjeto.toString());
		urlArquivo.append(File.separator);
		Calendar dataGeracao = Calendar.getInstance();
		StringBuilder data = new StringBuilder();
		data.append(dataGeracao.get(Calendar.DAY_OF_MONTH)+"-");
		data.append(dataGeracao.get(Calendar.MONTH)+1+"-");
		data.append(dataGeracao.get(Calendar.YEAR)+"-");
		data.append(apartirDe.get(Calendar.MONTH)+1+"-");
		data.append(apartirDe.get(Calendar.YEAR)+"-");
		this.nomeDoArquivo = "resumoContratos_"+data+".xlsx";
		urlArquivo.append(nomeDoArquivo);

		return urlArquivo.toString();
	}
	public void gerarRelatorioRMS(ResumoModeloSistema relatorio,
			String urlModelo, String pastaDestino) {

		XLSTransformer transformer = new XLSTransformer();

		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("projeto", relatorio);
		try {
			transformer.transformXLS(urlModelo, beans, pastaDestino);
			
		}catch(XmlValueDisconnectedException e){
			System.out.println(e);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	public ResumoModeloSistema geraResumoModeloSistema(Calendar apartirDe){
		ContratosModel contratoModel;
		modeloSistema = new ResumoModeloSistema();
		Reajuste ultimoReajuste = null;
		geraLabelUltimosPeriodos(modeloSistema, apartirDe);
		if(projetos == null){
		projetos = projetoDAO.listarTodos();
		}
		for(Projeto projeto:projetos){
			ultimoReajuste = reajusteDAO.buscarUltimoValido(projeto);
			validaProjeto(projeto);
			contratoModel = new ContratosModel();
			contratoModel.setProjeto(projeto);
			contratoModel.getProjeto().setStatus(projeto.getStatus());
			contratoModel.setReajuste(ultimoReajuste);
			contratoModel.setMedidoI0(somaPeriodos(projeto));
			contratoModel.setSaldoI0(contratoModel.getProjeto().getValorOriginal().subtract(contratoModel.getMedidoI0()));
			contratoModel.setSaldoReajustado(contratoModel.getSaldoI0().multiply(BigDecimal.ONE.add(contratoModel.getReajuste().getIndice().movePointLeft(5))));
			inserePeriodoCalculadoNoDTO(ultimoReajuste,dataEscolhida, contratoModel, projeto, ESCOLHIDA);
			inserePeriodoCalculadoNoDTO(ultimoReajuste,mesAnterior, contratoModel, projeto, ANTERIOR);
			inserePeriodoCalculadoNoDTO(ultimoReajuste,mesAnteriorM1, contratoModel, projeto, ANTERIORM1);
			if (projeto.getSetor().equals(SetorEnum.OLEOEGAS)) {
					modeloSistema.getContratosModelOleoEGas().add(contratoModel);
			}
			else if (projeto.getSetor().equals(SetorEnum.ENERGIA)) {
					modeloSistema.getContratosModelEnergia().add(contratoModel);
			}
			else if (projeto.getSetor().equals(SetorEnum.INFRAESTRUTURA)) {
					modeloSistema.getContratosModelInfraEstrutura().add(contratoModel);
			}
			else if (projeto.getSetor().equals(SetorEnum.URBANISMOEEDIFICACOES)) {
					modeloSistema.getContratosModelEnergiaUrbanismoeEdificacoes().add(contratoModel);
			}
			else if (projeto.getSetor().equals(SetorEnum.MINERACAO)) {
					modeloSistema.getContratosModelMineracao().add(contratoModel);
			}
		}
		return modeloSistema;
	}
	private void inserePeriodoCalculadoNoDTO(Reajuste ultimoReajuste,Calendar comp, ContratosModel contratoModel, Projeto projeto, int escolhida){
		PeriodoMedicao periodo = periodoMedicaoDAO.buscarPeriodoPorCompetenciaEProjeto(comp, projeto);
		if(periodo.getId()>0){
		DetalhamentoPeriodoMedicao detalheDoPeriodo = periodo.getDetalhamentoPeriodoMedicao();
		if(escolhida==ESCOLHIDA){
			contratoModel.getPeriodoComDataEscolhida().setPeriodoMedicao(periodo);
			contratoModel.getPeriodoComDataEscolhida().setMedicaoI0(detalheDoPeriodo.getTotalMedicaoI0());
			contratoModel.getPeriodoComDataEscolhida().setMedicaoComReajuste(detalheDoPeriodo.getTotalReajuste());
			contratoModel.getPeriodoComDataEscolhida().setMedicao(detalheDoPeriodo.getMedicaoComReajuste());
			contratoModel.getPeriodoComDataEscolhida().setTotalSalarios(detalheDoPeriodo.getTotalValorVenda());
				if(projeto.getSetor().equals(SetorEnum.OLEOEGAS)){
					modeloSistema.setTotalMDtEscolhidaOleoGas(modeloSistema.getTotalMDtEscolhidaOleoGas().add(contratoModel.getPeriodoComDataEscolhida().getMedicao()));
					modeloSistema.setTotalMI0DtEscolhidaOleoGas(modeloSistema.getTotalMI0DtEscolhidaOleoGas().add(contratoModel.getPeriodoComDataEscolhida().getMedicaoI0()));
					modeloSistema.setTotalMRDtEscolhidaOleoGas(modeloSistema.getTotalMRDtEscolhidaOleoGas().add(contratoModel.getPeriodoComDataEscolhida().getMedicaoComReajuste()));
					modeloSistema.setTotalSalDtEscolhidaOleoGas(modeloSistema.getTotalSalDtEscolhidaOleoGas().add(contratoModel.getPeriodoComDataEscolhida().getTotalSalarios()));
			
				}else if(projeto.getSetor().equals(SetorEnum.ENERGIA)){
					modeloSistema.setTotalMDtEscolhidaEnergia(modeloSistema.getTotalMDtEscolhidaEnergia().add(contratoModel.getPeriodoComDataEscolhida().getMedicao()));
					modeloSistema.setTotalMI0DtEscolhidaEnergia(modeloSistema.getTotalMI0DtEscolhidaEnergia().add(contratoModel.getPeriodoComDataEscolhida().getMedicaoI0()));
					modeloSistema.setTotalMRDtEscolhidaEnergia(modeloSistema.getTotalMRDtEscolhidaEnergia().add(contratoModel.getPeriodoComDataEscolhida().getMedicaoComReajuste()));
					modeloSistema.setTotalSalDtEscolhidaEnergia(modeloSistema.getTotalSalDtEscolhidaEnergia().add(contratoModel.getPeriodoComDataEscolhida().getTotalSalarios()));
				}else if(projeto.getSetor().equals(SetorEnum.INFRAESTRUTURA)){
					
				}else if(projeto.getSetor().equals(SetorEnum.MINERACAO)){
					
				}
		}else if(escolhida==ANTERIOR){
			contratoModel.getPeriodoComMesAnterior().setPeriodoMedicao(periodo);
			contratoModel.getPeriodoComMesAnterior().setMedicaoI0(detalheDoPeriodo.getTotalMedicaoI0());
			contratoModel.getPeriodoComMesAnterior().setMedicaoComReajuste(detalheDoPeriodo.getTotalReajuste());
			contratoModel.getPeriodoComMesAnterior().setMedicao(detalheDoPeriodo.getMedicaoComReajuste());
			contratoModel.getPeriodoComMesAnterior().setTotalSalarios(detalheDoPeriodo.getTotalValorVenda());
				if(projeto.getSetor().equals(SetorEnum.OLEOEGAS)){
					modeloSistema.setTotalMMesAntOleoGas(modeloSistema.getTotalMMesAntOleoGas().add(contratoModel.getPeriodoComMesAnterior().getMedicao()));
					modeloSistema.setTotalMI0MesAntOleoGas(modeloSistema.getTotalMI0MesAntOleoGas().add(contratoModel.getPeriodoComMesAnterior().getMedicaoI0()));
					modeloSistema.setTotalMRMesAnteriorOleoGas(modeloSistema.getTotalMRMesAnteriorOleoGas().add(contratoModel.getPeriodoComMesAnterior().getMedicaoComReajuste()));
					modeloSistema.setTotalSalMesAnteriorOleoGas(modeloSistema.getTotalSalMesAnteriorOleoGas().add(contratoModel.getPeriodoComMesAnterior().getTotalSalarios()));
				}else if(projeto.getSetor().equals(SetorEnum.ENERGIA)){
					modeloSistema.setTotalMMesAntEnergia(modeloSistema.getTotalMMesAntEnergia().add(contratoModel.getPeriodoComMesAnterior().getMedicao()));
					modeloSistema.setTotalMI0MesAntEnergia(modeloSistema.getTotalMI0MesAntEnergia().add(contratoModel.getPeriodoComMesAnterior().getMedicaoI0()));
					modeloSistema.setTotalMRMesAnteriorEnergia(modeloSistema.getTotalMRMesAnteriorEnergia().add(contratoModel.getPeriodoComMesAnterior().getMedicaoComReajuste()));
					modeloSistema.setTotalSalMesAnteriorEnergia(modeloSistema.getTotalSalMesAnteriorEnergia().add(contratoModel.getPeriodoComMesAnterior().getTotalSalarios()));
					}else if(projeto.getSetor().equals(SetorEnum.INFRAESTRUTURA)){
				
					}else if(projeto.getSetor().equals(SetorEnum.MINERACAO)){
				
			}
		}else if(escolhida==ANTERIORM1){		
			contratoModel.getPeriodoComM1().setPeriodoMedicao(periodo);
			contratoModel.getPeriodoComM1().setMedicaoI0(detalheDoPeriodo.getTotalMedicaoI0());
			contratoModel.getPeriodoComM1().setMedicaoComReajuste(detalheDoPeriodo.getTotalReajuste());
			contratoModel.getPeriodoComM1().setMedicao(detalheDoPeriodo.getMedicaoComReajuste());
			contratoModel.getPeriodoComM1().setTotalSalarios(detalheDoPeriodo.getTotalValorVenda());
				if(projeto.getSetor().equals(SetorEnum.OLEOEGAS)){
				modeloSistema.setTotalMComM1OleoGas(modeloSistema.getTotalMComM1OleoGas().add(contratoModel.getPeriodoComM1().getMedicao()));
				modeloSistema.setTotalMi0ComM1OleoGas(modeloSistema.getTotalMi0ComM1OleoGas().add(contratoModel.getPeriodoComM1().getMedicaoI0()));
				modeloSistema.setTotalMRComM1OleoGas(modeloSistema.getTotalMRComM1OleoGas().add(contratoModel.getPeriodoComM1().getMedicaoComReajuste()));
				modeloSistema.setTotalSalComM1OleoGas(modeloSistema.getTotalSalComM1OleoGas().add(contratoModel.getPeriodoComM1().getMedicaoComReajuste()));
				}else if(projeto.getSetor().equals(SetorEnum.ENERGIA)){
				
				}else if(projeto.getSetor().equals(SetorEnum.INFRAESTRUTURA)){
				
				}else if(projeto.getSetor().equals(SetorEnum.MINERACAO)){
				
			}
		}
		}
		
	}
	private void validaProjeto(Projeto projeto){
			try{
				if(projeto.getSetor().equals(null)){}
			}catch (NullPointerException e) {
					projeto.setSetor(SetorEnum.NAOINFORMADO);
				}
			
			try{
			if(projeto.getDataInicio().equals(null)){}
			}catch (NullPointerException e) {
				Calendar dataNull = Calendar.getInstance();
				dataNull.set(Calendar.DAY_OF_MONTH, 0);
				dataNull.set(Calendar.MONDAY, 0);
				dataNull.set(Calendar.YEAR, 1900);
				projeto.setDataInicio(dataNull);
			}
			
			try{
				if(projeto.getObjeto().equals(null)||projeto.getObjeto().equals("")){
					projeto.setObjeto("Objeto não informado");
				}
			}catch(NullPointerException e){
				projeto.setObjeto("Objeto não informado");
			}
	}
	
	private BigDecimal somaPeriodos(Projeto projeto){
		BigDecimal soma = BigDecimal.ZERO;
		List<Servico>equipes = servicoDAO.buscarEquipesPorProjeto(projeto);
		List<PeriodoMedicao>periodosMedicao =  periodoMedicaoDAO.buscarTodosPorProjetoComBaseDeCalculoValida(projeto);
		List<MedicaoEquipe>medicoes = new ArrayList<MedicaoEquipe>();
				for(PeriodoMedicao periodo: periodosMedicao){
					if(periodo.getStatus().equals(StatusPeriodoEnum.APROVADO)){
					List<MedicaoEquipe> medicoesPorPeriodo = medicaoEquipeDAO.listarPorPeriodo(periodo);
					try{
						for(Servico equipe:equipes){
						for(MedicaoEquipe medicaoEquipe: medicoesPorPeriodo){
							if(medicaoEquipe.getMobilizacao().getCargo().getServico().getId() == equipe.getId() ){
								medicoes.add(medicaoEquipe);
								}
							}
						soma = servicoDAO.calcularTotalMedidoEquipe(equipe, periodo, medicoes);
						}
						}
					catch (NullPointerException e) {
						System.out.println("Periodo" + periodo.getNumSequencial() + " do projeto cn "+ periodo.getProjeto().getCN()+" não tem base de calculo");
					}
				}
				}
			
		return soma;
	}
	private void geraLabelUltimosPeriodos(ResumoModeloSistema rms, Calendar dataDe){
		String meses[] = {"Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"};
		Calendar dataAtual = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		rms.setLabelDataAvaliacao(df.format(dataAtual.getTime()));
		rms.setMesCompetencia(meses[dataAtual.get(Calendar.MONTH)]);
		rms.setAnoCompetencia(dataAtual.get(Calendar.YEAR));
		
		dataEscolhida.set(Calendar.MONTH, dataDe.get(Calendar.MONTH));
		dataEscolhida.set(Calendar.YEAR, dataDe.get(Calendar.YEAR)-1);
		
		mesAnterior.set(Calendar.MONTH, dataDe.get(Calendar.MONTH)-1);
		mesAnterior.set(Calendar.YEAR, dataDe.get(Calendar.YEAR)-1);
		
		mesAnteriorM1.set(Calendar.MONTH, dataDe.get(Calendar.MONTH)-2);
		mesAnteriorM1.set(Calendar.YEAR, dataDe.get(Calendar.YEAR)-1);
		rms.setDataEscolhida(dataEscolhida);
		rms.setMesAnterior(mesAnterior);
		rms.setMesAnteriorM1(mesAnteriorM1);
	}
}
