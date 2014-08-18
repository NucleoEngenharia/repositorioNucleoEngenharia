package com.nucleo.contratos.MB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.nucleo.commom.Commom;
import com.nucleo.commom.Messages;
import com.nucleo.contratos.dao.AtividadeDAO;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.Atividades;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@ViewScoped
public class AtividadesBean {
	
	@PostConstruct
	public void init(){
		usuarioLogado = Commom.getUsuarioLogado();
		funcionarioExternoLogado = funcionarioDAO.buscaFuncionarioPorCpf(usuarioLogado.getCpf());
		atividades = atividadeDAO.buscarPorFuncionario(funcionarioExternoLogado);
		atividade = new Atividades();
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
	private String dataEscolhida;

	public void buscaAtividade(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(sdf.parse(dataEscolhida));
			this.atividade = atividadeDAO.buscarPorDataEFuncionario(data, funcionarioExternoLogado);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void atualizarAtividade(){
		atividadeDAO.alterar(atividade, funcionarioExternoLogado);
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
		if(atividades.size()==0){
			atividades = atividadeDAO.buscarPorFuncionario(funcionarioExternoLogado);
		}
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
	
}
