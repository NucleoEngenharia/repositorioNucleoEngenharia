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

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.nucleo.commom.Commom;
import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.FuncionarioContratoDAO;
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
	private FuncionarioContrato funcionarioContrato;
	private File file;
	private FuncionarioTO usuarioLogado;
	private int verificouAcesso =0;
	private boolean apenasLeitura = false;
	private AcessosUsuario acessosDoUsuarioLogado;
	private PermissoesMenuBean permissoesMenuBean;
	
	public boolean isApenasLeitura() {
		if(verificouAcesso==0){
		if(acessosDoUsuarioLogado.isAdministrador()){
			apenasLeitura = false;
		}else{
		AcessosUsuario acessosUsuario = acessosUsuarioDAO.buscarAcessoDoUsuarioComMenu(usuarioLogado.getPessoa_id());
		if(permissoesMenuBean.apenasLeitura(acessosUsuario, "Profissionais")){
			apenasLeitura = true;
			}
		}
		}
		verificouAcesso = 1;
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
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Calendar limiteRescisao = Calendar.getInstance();
			limiteRescisao.set(2010, 1, 1);
			while (bufferedReader.ready()) {
				String linha = bufferedReader.readLine();
				String[] coluna = linha.split(";");
				funcionarioContrato = new FuncionarioContrato();
				funcionarioContrato.setCpf(coluna[0]);
				funcionarioContrato.setNomeCompleto(coluna[1]);
				String salario = coluna[2].replace(".", "").replace(",", ".");
				funcionarioContrato.setSalario(new BigDecimal(salario));
				funcionarioContrato.setDataAdmissao(Calendar.getInstance());
				funcionarioContrato.getDataAdmissao().setTime(df.parse(coluna[3]));
				
				funcionarioContrato.setCn(Integer.parseInt(coluna[5].replace("CN", "").trim()));
				if (coluna[4].equals("")) {
					funcionarioContrato.setDataRescisao(Calendar.getInstance());
					coluna[4]="00/00/0000";
					
				}
				if (coluna[4]!="00/00/0000"||coluna[4]!=null) {
					funcionarioContrato.setDataRescisao(Calendar.getInstance());
					funcionarioContrato.getDataRescisao().setTime(df.parse(coluna[4]));
					if (funcionarioContrato.getDataRescisao().before(limiteRescisao))
						continue;
				}
				if (funcionarioContratoDAO.funcionarioExiste(funcionarioContrato)) {
					FuncionarioContrato funcOld = funcionarioContratoDAO
							.buscarPorCPF(funcionarioContrato.getCpf());

					if (funcOld.getCn() != funcionarioContrato.getCn()) {
						if ((funcOld.getDataRescisao() != null && funcionarioContrato
								.getDataRescisao() == null)) {
							funcionarioContratoDAO.inserir(funcionarioContrato,
									usuarioLogado.getPessoa_id());
							continue;
						} else {
							if (funcOld.getDataRescisao() != null
									&& funcionarioContrato.getDataRescisao() != null) {
								if (funcOld.getDataRescisao().compareTo(
										funcionarioContrato.getDataRescisao()) == 0) {
									funcionarioContratoDAO.alterar(funcionarioContrato,
											usuarioLogado.getPessoa_id());
									continue;
								} else {
									funcionarioContratoDAO.inserir(funcionarioContrato,
											usuarioLogado.getPessoa_id());
									continue;
								}
							} else {
								funcionarioContratoDAO.alterar(funcionarioContrato,usuarioLogado.getPessoa_id());
								continue;
							}
						}
					}
					if (funcionarioContrato.getDataRescisao() == null) {
						funcionarioContratoDAO.alterar(funcionarioContrato,
								usuarioLogado.getPessoa_id());
						continue;
					}
					if (funcionarioContrato.getDataRescisao() != null
							&& funcOld.getDataRescisao() != null) {
						if (funcionarioContrato.getDataRescisao().getTimeInMillis() > funcOld
								.getDataRescisao().getTimeInMillis()) {
							funcionarioContratoDAO.alterar(funcionarioContrato,
									usuarioLogado.getPessoa_id());
							System.out.println(funcionarioContrato.getNomeCompleto()+" alterado com sucesso");
							continue;
						}
					}
				} else {
					funcionarioContratoDAO.inserir(funcionarioContrato,
							usuarioLogado.getPessoa_id());
				}
			}

			bufferedReader.close();
			FacesMessage message = new FacesMessage("Base de dados foi carregada com sucesso!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, message);
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
