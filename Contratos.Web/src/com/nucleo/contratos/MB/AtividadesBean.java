package com.nucleo.contratos.MB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.nucleo.commom.Commom;
import com.nucleo.commom.Messages;
import com.nucleo.contratos.dao.AtividadeDAO;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Atividades;
import com.nucleo.contratos.entity.DetalhamentoAtividade;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@ViewScoped
public class AtividadesBean {
	
	@PostConstruct
	public void init(){
		usuarioLogado = Commom.getUsuarioLogado();
		funcionarioExternoLogado = funcionarioDAO.buscaFuncionarioPorCpf(usuarioLogado.getCpf());
		atividade = new Atividades();
		detalhamentoAtividade = new DetalhamentoAtividade();
		if(funcionarioExternoLogado.getId()!=0){
			atividades = atividadeDAO.buscarPorFuncionario(funcionarioExternoLogado);
			}
	}
	@EJB
	private AtividadeDAO atividadeDAO;
	@EJB
	private FuncionarioDAO funcionarioDAO;
	
	private Atividades atividade;
	private FuncionarioTO usuarioLogado;
	private Funcionario funcionarioExternoLogado;
	private List<Atividades>atividades=null;
	private List<String> datas=null;
	private List<Funcionario>funcionarios = null;
	private List<Atividades>atividadesUserSelecionado;
	private String dataEscolhida;
	private boolean usuarioExterno=false;
	private int idFuncSelecionado;
	private boolean hoje=false;
	private DetalhamentoAtividade detalhamentoAtividade;
	private File arquivo;
	private StreamedContent file;
	private static String path="D:\\atestados\\contratos\\";
	
	public StreamedContent getFile() {
		return file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	public void download(String nome){
		String caminhoCompleto = path+nome;
		InputStream stream;
		try {
			stream = new FileInputStream(new File(caminhoCompleto));
			this.file = new DefaultStreamedContent(stream, null,nome);
		} catch (FileNotFoundException e) {
			Messages.geraMensagemFatal(""+e);
		}
	}
	public void upload(FileUploadEvent event){
		UploadedFile uploadedFile = event.getFile();
		File caminho = new File(path);
		byte[] conteudo = uploadedFile.getContents();
		arquivo = new File(path+uploadedFile.getFileName());
		try {
		if(!caminho.exists()){
				caminho.mkdirs();
		}
		if(arquivo.exists()){
			Messages.geraMensagemDeErro("Já exixte um arquivo com esse nome, por favor altere e tente novamente");
			return;
		}
		detalhamentoAtividade.setAnexo(arquivo.getName());
		FileOutputStream fos = new FileOutputStream(arquivo);
		fos.write(conteudo);
		fos.close();
		Messages.geraMensagemAviso("");
		}catch(IOException io){
			System.out.println(io);
		}
		
	}
	
	public void buscaAtividade(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataEscolhida = Calendar.getInstance();
			String hoje ; 
			hoje = sdf.format(dataEscolhida.getTime());
			if(hoje.equals(this.dataEscolhida)){
				this.hoje=true;
			}else{
				this.hoje=false;
			}
			dataEscolhida.setTime(sdf.parse(this.dataEscolhida));
			this.atividade = atividadeDAO.buscarPorDataEFuncionario(dataEscolhida, funcionarioExternoLogado);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarAtividade(){
		detalhamentoAtividade.setHora(Calendar.getInstance());
		detalhamentoAtividade.setAtividades(atividade);
		atividade.getDetalhamentoAtividade().add(detalhamentoAtividade);
		atividadeDAO.alterar(atividade, funcionarioExternoLogado);
		atividade.getDetalhamentoAtividade().clear();
		buscaAtividade();
		detalhamentoAtividade = new DetalhamentoAtividade();
		Messages.geraMensagemAviso("Atividade atualizada com sucesso!");
	}
	public String getDataEscolhida() {
		return dataEscolhida;
	}
	public void setDataEscolhida(String dataEscolhida) {
		this.dataEscolhida = dataEscolhida;
	}
	public List<String> getDatas() {
		if(datas==null){
			datas = new ArrayList<String>();
			for(Atividades a: atividades){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				datas.add(sdf.format(a.getData().getTime()));
			}
		}
		return datas;
	}
	public void setDatas(List<String> datas) {
		this.datas = datas;
	}
	public List<Atividades> getAtividadedes() {
		return atividades;
	}
	public void setAtividadedes(List<Atividades> atividades) {
		this.atividades = atividades;
	}
	public Atividades getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividades atividade) {
		this.atividade = atividade;
	}

	public boolean isUsuarioExterno() {
		if(funcionarioExternoLogado.getId()>0){
			usuarioExterno = true;
		}
		return usuarioExterno;
	}

	public void setUsuarioExterno(boolean usuarioExterno) {
		this.usuarioExterno = usuarioExterno;
	}

	public List<Funcionario> getFuncionarios() {
		if(funcionarios==null){
			 funcionarios = funcionarioDAO.listarTodos();
		}
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Atividades> getAtividadesUserSelecionado() {
		return atividadesUserSelecionado;
	}

	public void setAtividadesUserSelecionado(List<Atividades> atividadesUserSelecionado) {
		this.atividadesUserSelecionado = atividadesUserSelecionado;
	}
	public void buscarAtividadesDoUserEscolhido(){
		atividadesUserSelecionado = atividadeDAO.listPorFuncId(idFuncSelecionado);
	}

	public int getIdFuncSelecionado() {
		return idFuncSelecionado;
	}

	public void setIdFuncSelecionado(int idFuncSelecionado) {
		this.idFuncSelecionado = idFuncSelecionado;
	}

	public boolean isHoje() {
		return hoje;
	}

	public void setHoje(boolean hoje) {
		this.hoje = hoje;
	}

	public DetalhamentoAtividade getDetalhamentoAtividade() {
		return detalhamentoAtividade;
	}

	public void setDetalhamentoAtividade(DetalhamentoAtividade detalhamentoAtividade) {
		this.detalhamentoAtividade = detalhamentoAtividade;
	}

}
