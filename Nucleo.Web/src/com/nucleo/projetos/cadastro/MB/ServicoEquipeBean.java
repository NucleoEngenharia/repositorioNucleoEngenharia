package com.nucleo.projetos.cadastro.MB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.nucleo.commom.Commom;
import com.nucleo.dao.CargoDAO;
import com.nucleo.dao.PrevisaoUsoDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.entity.cadastro.Cargo;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.entity.cadastro.Enum.TipoCalculoEnum;
import com.nucleo.entity.cadastro.Enum.TipoServicoEnum;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@ViewScoped
public class ServicoEquipeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicoDAO servicoDAO;
	@EJB
	private CargoDAO cargoDAO;
	@EJB
	private PrevisaoUsoDAO previsaoUsoDAO;
	
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();

	private Servico servicoSelecionado;
	private Cargo cargo;
	private List<PrevisaoUso> previsoesUso;
	private List<Cargo> cargos;
	
	private boolean calcProjPorEquipe = true;
	private boolean calcProjPorComp = false;

	public boolean isCalcProjPorEquipe() {
		return calcProjPorEquipe;
	}

	public boolean isCalcProjPorComp() {
		return calcProjPorComp;
	}

	public void setCalcProjPorEquipe(boolean calcProjPorEquipe) {
		this.calcProjPorEquipe = calcProjPorEquipe;
	}

	public void setCalcProjPorComp(boolean calcProjPorComp) {
		this.calcProjPorComp = calcProjPorComp;
	}
	private BigDecimal valorDisponivel;

	@PostConstruct
	public void init(){
		FacesContext instance = FacesContext.getCurrentInstance();
		ExternalContext externalContext = instance.getExternalContext();
		servicoSelecionado = (Servico) externalContext.getFlash().get("ServicoSelecionado");
		if(servicoSelecionado.getTipo().equals(TipoServicoEnum.EQUIPE)&&servicoSelecionado.getProjeto().getCalculo().equals(TipoCalculoEnum.SIMPLES)||servicoSelecionado.getProjeto().getCalculo().equals(TipoCalculoEnum.POREQUIPE)){
			calcProjPorEquipe = true;
			calcProjPorComp = false;
		}else if(servicoSelecionado.getTipo().equals(TipoServicoEnum.EQUIPE)&&servicoSelecionado.getProjeto().getCalculo().equals(TipoCalculoEnum.PORCOMPLEXIDADE)){
			calcProjPorEquipe = false;
			calcProjPorComp = true;
		}
	}

	// métodos do cargo
	public void salvarCargo(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (cargo.getId() == 0) {
				
				if (getValorDisponivel().subtract(cargo.getValorTotal()).compareTo(new BigDecimal(0)) < 0) {
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor do cargo é maior que o valor disponível do serviço."));
					return;
				}
				cargo.getHistograma().addAll(previsoesUso);
				cargoDAO.inserir(cargo, usuarioLogado.getPessoa_id());
			} else {
				BigDecimal valorOld = cargoDAO.buscarPorID(cargo.getId()).getValorTotal();
				if(getValorDisponivel().add(valorOld).subtract(cargo.getValorTotal()).compareTo(new BigDecimal(0)) < 0){
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor do cargo é maior que o valor disponível do serviço."));
					return;
				}
				cargoDAO.alterar(cargo, usuarioLogado.getPessoa_id());
			}
			cargo = null;
			previsoesUso = null;
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Cargo/Função (MD) salvo com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void selecionarCargo(Cargo cargoSelecionado){
		cargo = cargoSelecionado;
		previsoesUso = cargoDAO.obterPrevisoesUso(cargo);
		cargo.setHistograma(previsoesUso);
	}
	public void removerCargo(Cargo cargo){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(cargoDAO.temMobilizacao(cargo)){
				context.addMessage(null, new FacesMessage("Erro!",
					"O serviço não pode ser excluído pois ja possui medições cadastradas."));
				return;
			}
			
			cargoDAO.removerHistograma(cargo);
			cargoDAO.deletarPorId(cargo.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Cargo/Função (MD) excluído com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void distribuirUsoFuncao(){
		BigDecimal qtdUso = (cargo.getQuantidadeUso().divide(BigDecimal.valueOf(previsoesUso.size()), BigDecimal.ROUND_UP));

		for (Iterator<PrevisaoUso> iterator = previsoesUso.iterator(); iterator.hasNext();) {
			PrevisaoUso previsao = (PrevisaoUso) iterator.next();
			previsao.setQuantidade(qtdUso);
			
		}
	}

	public Servico getServicoSelecionado(){
		return servicoSelecionado;
	}
	public void setServicoSelecionado(Servico servicoSelecionado){
		this.servicoSelecionado = servicoSelecionado;
	}
	public static long getSerialversionuid(){
		return serialVersionUID;
	}
	public Cargo getCargo(){
		if (cargo == null) {
			cargo = new Cargo();
			cargo.setServico(servicoSelecionado);
		}
		return cargo;
	}
	public void setCargo(Cargo cargo){
		this.cargo = cargo;
	}
	public List<PrevisaoUso> getPrevisoesUso(){
		if (previsoesUso == null) {
			previsoesUso = cargoDAO.gerarPrevisoesUso(cargo);
		}

		Collections.sort(previsoesUso, new Comparator<PrevisaoUso>() {
			public int compare(PrevisaoUso p1, PrevisaoUso p2){
				Calendar dataP1 = p1.getPeriodoMedicao().getDataDe();
				Calendar dataP2 = p2.getPeriodoMedicao().getDataDe();
				return dataP1.before(dataP2) ? -1 : (dataP1.after(dataP2) ? +1 : 0);
			}
		});

		return previsoesUso;
	}
	public void setPrevisoesUso(List<PrevisaoUso> previsoesUso){
		this.previsoesUso = previsoesUso;
	}
	public List<Cargo> getCargos(){
		cargos = new ArrayList<Cargo>();
		cargos.addAll(cargoDAO.buscarTodosPorServico(servicoSelecionado));
		return cargos;
	}
	public void setCargos(List<Cargo> cargos){
		this.cargos = cargos;
	}
	public BigDecimal getValorDisponivel(){
		valorDisponivel = servicoDAO.getValorDisponivel(servicoSelecionado);
		return valorDisponivel;
	}
	public void setValorDisponivel(BigDecimal valorDisponivel){
		this.valorDisponivel = valorDisponivel;
	}

}
