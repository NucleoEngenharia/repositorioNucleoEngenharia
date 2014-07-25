package com.nucleo.projetos.medicao.MB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.FuncionarioContratoDAO;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.medicao.FuncionarioContrato;
import com.nucleo.projetos.cadastro.MB.PermissoesMenuBean;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
public class CargaFolhaBean {
	public CargaFolhaBean() {
		usuarioLogado = Commom.getUsuarioLogado();
		acessosDoUsuarioLogado = Commom.getAcessoUsuarioLogado();
		permissoesMenuBean = new PermissoesMenuBean();
	}
	@EJB
	private FuncionarioContratoDAO funcionarioContratoDAO;
	@EJB
	private AcessosUsuarioDAO acessosUsuarioDAO;
	@EJB
	private MobilizacaoDAO mobilizacaoDAO;
	private FuncionarioContrato funcionarioContrato;
	private File file;
	private FuncionarioTO usuarioLogado;
	private boolean verificouAcesso =false;
	private boolean apenasLeitura = false;
	private AcessosUsuario acessosDoUsuarioLogado;
	private PermissoesMenuBean permissoesMenuBean;
	private BufferedReader bufferedReader;
	
	public boolean isApenasLeitura() {
		if(!verificouAcesso){
		if(acessosDoUsuarioLogado.isAdministrador()){
			apenasLeitura = false;
		}else{
		AcessosUsuario acessosUsuario = acessosUsuarioDAO.buscarAcessoDoUsuarioComMenu(usuarioLogado.getPessoa_id());
		if(permissoesMenuBean.apenasLeitura(acessosUsuario, "Profissionais")){
			apenasLeitura = true;
			}
		}
		}
		verificouAcesso = true;
		return apenasLeitura;
	}
	public void setApenasLeitura(boolean apenasLeitura) {
		this.apenasLeitura = apenasLeitura;
	}
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		String caminho = "D:\\Banco de dados\\Medicao\\carga_funcionarios\\";
		byte[] conteudo = uploadedFile.getContents();
		file = new File(caminho + uploadedFile.getFileName());

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(conteudo);
			fos.close();
			atualizarBase();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void atualizarBase() {
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Calendar limiteRescisao = Calendar.getInstance();
			limiteRescisao.set(2010, 1, 1);
			while (bufferedReader.ready()) {
				String linha = bufferedReader.readLine();
				System.out.println(linha);
				String[] coluna = linha.split(";");
				funcionarioContrato = new FuncionarioContrato();
				funcionarioContrato.setCpf(coluna[0]);
				funcionarioContrato.setNomeCompleto(coluna[1]);
				String salario = coluna[2].replace(".", "").replace(",", ".");
				funcionarioContrato.setSalario(new BigDecimal(salario));
				funcionarioContrato.setDataAdmissao(Calendar.getInstance());
				funcionarioContrato.getDataAdmissao().setTime(df.parse(coluna[3]));
				if (coluna[4].equals("")) {
					funcionarioContrato.setDataRescisao(null);
					coluna[4]="00/00/0000";
					
				}else if (coluna[4]!="00/00/0000"||coluna[4]!=null) {
					funcionarioContrato.setDataRescisao(Calendar.getInstance());
					funcionarioContrato.getDataRescisao().setTime(df.parse(coluna[4]));
					if (funcionarioContrato.getDataRescisao().before(limiteRescisao))
						continue;
				}
				
				funcionarioContrato.setCn(Integer.parseInt(coluna[5].replace("CN", "").trim()));
				if (funcionarioContratoDAO.funcionarioExiste(funcionarioContrato)) {
					FuncionarioContrato funcOld = funcionarioContratoDAO
							.buscaPorCPF(funcionarioContrato, usuarioLogado.getPessoa_id());
					if(funcOld.getId()==0){
						List<FuncionarioContrato>list = funcionarioContratoDAO.listarTodosPorCPF(funcionarioContrato.getCpf());
						for(FuncionarioContrato f:list){
							if(!mobilizacaoDAO.funcionarioMobilizado(f)){
								funcionarioContratoDAO.deletarPorId(f.getId(), usuarioLogado.getPessoa_id());
							}else{
								funcOld = f;
							}
						}
					}
					

					if (funcOld.getCn() != funcionarioContrato.getCn()) {
						if ((funcOld.getDataRescisao() != null && funcionarioContrato
								.getDataRescisao() == null)) {
							funcionarioContrato.setId(funcOld.getId());
							funcionarioContratoDAO.alterar(funcionarioContrato,
									usuarioLogado.getPessoa_id());
							continue;
						} else {
							if (funcOld.getDataRescisao() != null
									&& funcionarioContrato.getDataRescisao() != null) {
								if (funcOld.getDataRescisao().compareTo(
										funcionarioContrato.getDataRescisao()) == -1) {
									funcionarioContratoDAO.alterar(funcionarioContrato,
											usuarioLogado.getPessoa_id());
									continue;
								} 
							} 
						}
					}
					if (funcionarioContrato.getDataRescisao() == null) {
						FuncionarioContrato f = funcionarioContratoDAO.buscaPorCPF(funcionarioContrato, usuarioLogado.getPessoa_id());
						if(f.getId()==0){
							funcionarioContratoDAO.salvar(funcionarioContrato, usuarioLogado.getPessoa_id());
						}else{
						funcionarioContratoDAO.alterar(f,usuarioLogado.getPessoa_id());
						}
						continue;
					}
					if (funcionarioContrato.getDataRescisao() != null
							&& funcOld.getDataRescisao() != null) {
						if (funcionarioContrato.getDataRescisao().getTimeInMillis() > funcOld
								.getDataRescisao().getTimeInMillis()) {
							funcionarioContratoDAO.alterar(funcionarioContrato,usuarioLogado.getPessoa_id());
							System.out.println(funcionarioContrato.getNomeCompleto()+" alterado com sucesso");
							continue;
						}
					}
				} else {
					System.out.println(linha+"não existe");
					funcionarioContratoDAO.salvar(funcionarioContrato,
							usuarioLogado.getPessoa_id());
				}
			}

			bufferedReader.close();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Base carregada com sucesso"));
		} catch (FileNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(e + ": Arquivo não encontrado");
			context.addMessage(null, message);
			System.out.println(e + ": Arquivo não encontrado");
		} catch (IOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(e + ": Erro ao ler o arquivo");
			context.addMessage(null, message);
			System.out.println(e + ": Erro ao ler o arquivo");
		} catch (ParseException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(e + ": Erro na coluna data de admissão");
			context.addMessage(null, message);
			System.out.println(e + ": Erro na coluna data de admissão");
		}

	}
}
