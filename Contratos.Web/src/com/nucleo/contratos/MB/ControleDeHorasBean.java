package com.nucleo.contratos.MB;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.nucleo.commom.Commom;
import com.nucleo.commom.Messages;
import com.nucleo.contratos.dao.ControleHorasDAO;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.entity.ControleDeHorarios;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
public class ControleDeHorasBean {
	//Não se preocupe com essa classe, ela não esta sendo utilizada,
	//foi criada para o controle de acesso, mas o processo foi alterado
	//o não apaguei o esse bean para o caso de um dia ser necessário utilizar algo parecido.
	@PostConstruct
	public void init() {
		usuarioLogado = Commom.getUsuarioLogado();
	}
	
	@EJB
	private ControleHorasDAO controleHorasDAO;
	@EJB
	private FuncionarioDAO funcionarioDAO;
	
	private FuncionarioTO usuarioLogado;
	
	private String getRangeDeIp(){
		String range;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request= (HttpServletRequest) context.getExternalContext().getRequest();
		range=request.getRemoteAddr();
		return range;
	}
	public void cadastraInicioAtividades(){
		ControleDeHorarios ponto = new ControleDeHorarios();
		Funcionario func = funcionarioDAO.buscaFuncionarioPorCpf(usuarioLogado.getCpf());
		if(func.getId()>0){
			ponto.setData(Calendar.getInstance());
			ponto.setRangeChegada(getRangeDeIp());
			ponto.setFuncionario(func);
			ponto.setHrChegada(Calendar.getInstance());
			controleHorasDAO.registarInicioAtividades(ponto);
			Messages.geraMensagemAviso("Início das atividades resgistrada com sucesso, bom trabalho!");
		}else{
			Messages.geraMensagemAviso("Esta opção está ativa apenas para usuários externos");
		}
	}
	public void cadastraSaidaAlmoco(){
		Funcionario func = funcionarioDAO.buscaFuncionarioPorCpf(usuarioLogado.getCpf());
		if(func.getId()>0){
			ControleDeHorarios ponto = controleHorasDAO.buscarHorariosPorData(Calendar.getInstance(), func);
			ponto.setData(Calendar.getInstance());
			ponto.setFuncionario(func);
			ponto.setHrSaidaAlmoco(Calendar.getInstance());
			controleHorasDAO.registarPonto(ponto);
			Messages.geraMensagemAviso("Pausa para almoço resgistrado com sucesso, bom almoço!");
		}else{
			Messages.geraMensagemAviso("Esta opção está ativa apenas para usuários externos");
		}
	}
	public void cadastraChegadaAlmoco(){
		Funcionario func = funcionarioDAO.buscaFuncionarioPorCpf(usuarioLogado.getCpf());
		if(func.getId()>0){
			ControleDeHorarios ponto = controleHorasDAO.buscarHorariosPorData(Calendar.getInstance(), func);
			ponto.setData(Calendar.getInstance());
			ponto.setRangeVoltaAlmoco(getRangeDeIp());
			ponto.setFuncionario(func);
			ponto.setHrChegadaAlmoco(Calendar.getInstance());
			controleHorasDAO.registarPonto(ponto);
			Messages.geraMensagemAviso("Retorno as atividades resgistrada com sucesso, bom trabalho!");
		}else{
			Messages.geraMensagemAviso("Esta opção está ativa apenas para usuários externos");
		}
	}
	public void cadastraFimExpediente(){
		Funcionario func = funcionarioDAO.buscaFuncionarioPorCpf(usuarioLogado.getCpf());
		if(func.getId()>0){
			ControleDeHorarios ponto = controleHorasDAO.buscarHorariosPorData(Calendar.getInstance(), func);
			ponto.setData(Calendar.getInstance());
			ponto.setFuncionario(func);
			ponto.setHrSaida(Calendar.getInstance());
			controleHorasDAO.registarPonto(ponto);
			Messages.geraMensagemAviso("Fim das atividades resgistrado com sucesso, bom descanso!");
		}else{
			Messages.geraMensagemAviso("Esta opção está ativa apenas para usuários externos");
		}
	}
	
}
