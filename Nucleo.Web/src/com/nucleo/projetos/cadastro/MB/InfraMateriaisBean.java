package com.nucleo.projetos.cadastro.MB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.nucleo.commom.Commom;
import com.nucleo.dao.InfraMateriaisDAO;
import com.nucleo.dao.ItemInfraMateriaisDAO;
import com.nucleo.entity.cadastro.InfraMateriais;
import com.nucleo.entity.cadastro.ItemInfraMateriais;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@ViewScoped
public class InfraMateriaisBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private InfraMateriaisDAO infraDAO;
	@EJB
	private ItemInfraMateriaisDAO itemInfraDAO;
	
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();
	
	private InfraMateriais infraSelecionado;
	private ItemInfraMateriais itemInfra;
	private List<ItemInfraMateriais> itens;
	
	private BigDecimal valorDisponivel;
	
	@PostConstruct
	public void init(){
    	FacesContext instance = FacesContext.getCurrentInstance();
    	ExternalContext externalContext = instance.getExternalContext();
    	infraSelecionado = (InfraMateriais) externalContext.getFlash().get("InfraSelecionado");
	}
	
	// métodos do cargo
	public void salvarItem(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
	    	if(itemInfra.getId() == 0){
	    		if (getValorDisponivel().subtract(itemInfra.getValor()).compareTo(new BigDecimal(0)) < 0) {
	    			context.addMessage(null, new FacesMessage("Atenção!",
	    					"O valor do item é maior que o valor disponível na Infra - Materiais."));
	    			return;
	    		}
	    		itemInfraDAO.inserir(itemInfra, usuarioLogado.getPessoa_id());
	    	}else{
	    		BigDecimal valorOld = itemInfraDAO.buscarPorID(itemInfra.getId()).getValor();
	    		if (getValorDisponivel().add(valorOld).subtract(itemInfra.getValor()).compareTo(new BigDecimal(0)) < 0) {
	    			context.addMessage(null, new FacesMessage("Atenção!",
	    					"O valor do item é maior que o valor disponível na Infra - Materiais."));
	    			return;
	    		}
	    		itemInfraDAO.alterar(itemInfra, usuarioLogado.getPessoa_id());
	    	}
	    	itemInfra = null;			
    		context.addMessage(null, new FacesMessage("Sucesso!", "Item atualizada com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!", "Por favor, contate o administrador do sistema."));
		}
	}
    public void selecionarItem(ItemInfraMateriais itemSelecionado){
    	itemInfra = itemSelecionado;
    }
    public void removerItem(ItemInfraMateriais item){
    	FacesContext context = FacesContext.getCurrentInstance();
    	try {
        	if(itemInfraDAO.temMedicoes(item)){
    			context.addMessage(null, new FacesMessage("Erro!",
    					"O item não pode ser excluído porque ja possui medições cadastradas."));
    			return;
    		}
    		itemInfraDAO.deletarPorId(item.getId(), usuarioLogado.getPessoa_id());
    		context.addMessage(null, new FacesMessage("Sucesso!",
					"Item excluído com sucesso."));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
    }
	
	public void calcularPorcent(AjaxBehaviorEvent e){
		if(itemInfra.getValor().equals(new BigDecimal(0))) return;
		BigDecimal porcent = itemInfra.getValor().divide(infraSelecionado.getValor(), MathContext.UNLIMITED);
		itemInfra.setPorcentagem(porcent.multiply(new BigDecimal(100)));
	}	
	public void calcularValor(AjaxBehaviorEvent e){
		if(itemInfra.getPorcentagem().equals(new BigDecimal(0))) return;
		BigDecimal percent = itemInfra.getPorcentagem().divide(new BigDecimal(100));
		itemInfra.setValor(infraSelecionado.getValor().multiply(percent, MathContext.UNLIMITED));
	}
	
    
	public ItemInfraMateriais getItemInfra() {
		if(itemInfra == null){
			itemInfra = new ItemInfraMateriais();
			itemInfra.setInfraMateriais(infraSelecionado);
		}
		return itemInfra;
	}
	public List<ItemInfraMateriais> getItens() {
		itens = new ArrayList<ItemInfraMateriais>();
		itens.addAll(itemInfraDAO.buscarTodosPorInfraMateriais(infraSelecionado));
		return itens;
	}

	public InfraMateriais getInfraSelecionado() {
		return infraSelecionado;
	}

	public void setInfraSelecionado(InfraMateriais infraSelecionado) {
		this.infraSelecionado = infraSelecionado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setItemInfra(ItemInfraMateriais itemInfra) {
		this.itemInfra = itemInfra;
	}

	public void setItens(List<ItemInfraMateriais> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorDisponivel() {
		valorDisponivel = infraDAO.getValorDisponivel(infraSelecionado);
		return valorDisponivel;
	}

	public void setValorDisponivel(BigDecimal valorDisponivel) {
		this.valorDisponivel = valorDisponivel;
	}
	
	
}
