package com.nucleo.projetos.medicao.MB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.nucleo.commom.Commom;
import com.nucleo.dao.RelatoriosRMSGeradosDAO;
import com.nucleo.entity.medicao.MedicaoEquipe;
import com.nucleo.entity.medicao.PeriodoMedicao;
import com.nucleo.entity.medicao.RelatoriosRMSGerados;
import com.nucleo.projetos.relatorios.ProcessosRelatorioMensal;
import com.nucleo.projetos.relatorios.ProcessosResumoContratos;
import com.nucleo.projetos.relatorios.model.RelatorioMensal;
import com.nucleo.projetos.relatorios.model.ResumoModeloSistema;
import com.nucleo.seguranca.to.FuncionarioTO;
public class RelatorioBean {
	private final String CAMINHO_RELATORIOS = "D:/Relatorios/";
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	private RelatoriosRMSGeradosDAO relatorioGeradoDAO = (RelatoriosRMSGeradosDAO) Commom.lookup("RelatoriosRMSGeradosDAOImpl");
	public StreamedContent buscarRelatorio(PeriodoMedicao periodo){
		StringBuilder strFile = new StringBuilder();
		strFile.append(CAMINHO_RELATORIOS);
		strFile.append("CN" + periodo.getProjeto().getCN());
		strFile.append(File.separator);
		strFile.append("periodo_" + periodo.getId() + ".xlsx");

		InputStream stream = null;
		try {
			stream = new FileInputStream(new File(strFile.toString()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");

		StringBuilder nomeArquivo = new StringBuilder();
		nomeArquivo.append("CN " + periodo.getProjeto().getCN());
		nomeArquivo.append(" Medicao " + periodo.getId() + " ");
		nomeArquivo.append(df.format(periodo.getDataDe().getTime()));
		nomeArquivo.append(" a ");
		nomeArquivo.append(df.format(periodo.getDataAte().getTime()));
		nomeArquivo.append(".xlsx");

		StreamedContent relatorio = new DefaultStreamedContent(stream, null, nomeArquivo.toString());
		return relatorio;
	}
	//Relatório modelo1
	public boolean gerarRelatorio(PeriodoMedicao periodoSelecionado,Map<Integer,List<MedicaoEquipe>> medicoesEquipe) {
		boolean retorno = false;
		ProcessosRelatorioMensal processos = new ProcessosRelatorioMensal();
		RelatorioMensal relatorioMensal = processos.carregaDadosRelatorio(periodoSelecionado,
				medicoesEquipe);
		StringBuilder urlModelo = new StringBuilder();
		urlModelo.append(CAMINHO_RELATORIOS);
		urlModelo.append("modelo/");
		urlModelo.append("modelo1.xlsx");

		String urlRelatorio = processos.gerarRelatorioPeriodo(periodoSelecionado, relatorioMensal,
				urlModelo.toString(), CAMINHO_RELATORIOS);

		if (urlRelatorio != null) {
			retorno = true;
		}
		return retorno;
	}
	//Relatorios RMS
	public void gerarRelatorioRMS(Calendar apartirDe){
		boolean gerado = false;
		RelatoriosRMSGerados relatorioGerado = new RelatoriosRMSGerados();
		String pastaDestino="";
		ProcessosResumoContratos resumoModelSistema = new ProcessosResumoContratos();
		try{
		ResumoModeloSistema modeloSistema = resumoModelSistema.geraResumoModeloSistema(apartirDe);
		pastaDestino = resumoModelSistema.geraUrlRelatorio(CAMINHO_RELATORIOS, apartirDe);
		resumoModelSistema.gerarRelatorioRMS(modeloSistema, CAMINHO_RELATORIOS+"modelo/modeloRMS.xlsx", pastaDestino);
		gerado = true;
		}catch(Exception e){
			System.out.println(e);
		}
		if(gerado){
			relatorioGerado.setCaminhoDoArquivo(pastaDestino);
			relatorioGerado.setNomeArquivo(resumoModelSistema.getNomeDoArquivo());
			RelatoriosRMSGerados rms = relatorioGeradoDAO.buscarPorNome(relatorioGerado);
			if(rms.getId()==0){
			relatorioGeradoDAO.inserir(relatorioGerado, usuarioLogado.getPessoa_id());
			}
		}
		
	}
	public StreamedContent baixarRelatorioRMS(RelatoriosRMSGerados relatorioEscolhido) {
		StringBuilder strFile = new StringBuilder();
		strFile.append(relatorioEscolhido.getCaminhoDoArquivo());

		InputStream stream = null;
		try {
			stream = new FileInputStream(new File(strFile.toString()));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		}

		StringBuilder nomeArquivo = new StringBuilder();
		nomeArquivo.append(relatorioEscolhido.getNomeArquivo());

		StreamedContent relatorio = new DefaultStreamedContent(stream, null, nomeArquivo.toString());
		return relatorio;
	}
}
