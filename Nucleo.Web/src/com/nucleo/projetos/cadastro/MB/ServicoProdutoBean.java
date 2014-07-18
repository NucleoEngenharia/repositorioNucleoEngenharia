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
import com.nucleo.dao.PrevisaoUsoDAO;
import com.nucleo.dao.ProdutoDAO;
import com.nucleo.dao.ServicoDAO;
import com.nucleo.entity.cadastro.PrevisaoUso;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.cadastro.Servico;
import com.nucleo.seguranca.to.FuncionarioTO;


@ManagedBean
@ViewScoped
public class ServicoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicoDAO servicoDAO;
	@EJB
	private ProdutoDAO produtoDAO;
	@EJB
	private PrevisaoUsoDAO previsaoUsoDAO;

	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();

	private Servico servicoSelecionado;
	private Produto produto;
	private Produto grupo;
	private List<PrevisaoUso> previsoesUso;
	private List<Produto> produtos;
	private List<Produto> grupos;
	private int idProdutoPai;
	private int idGrupoPai;
	private int tabSelect = 0;
	private BigDecimal valorDisponivel;
	
	@PostConstruct
	public void init() {
		FacesContext instance = FacesContext.getCurrentInstance();
		ExternalContext externalContext = instance.getExternalContext();
		servicoSelecionado = (Servico) externalContext.getFlash().get(
				"ServicoSelecionado");
		valorDisponivel = BigDecimal.ZERO;
	}
	
	// métodos do produto
	public void salvarProduto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			produto.setProdutoPai(produtoDAO.buscarPorID(idProdutoPai));
			if (produto.getId() == 0) {
				if (getValorDisponivel().subtract(produto.getValorTotal()).compareTo(new BigDecimal(0)) < 0) {
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor do produto é maior que o valor disponível no serviço."));
					tabSelect = 0;
					return;
				}
				produto.setGrupo(false);
				produto.getCronograma().addAll(previsoesUso);
				produtoDAO.inserir(produto, usuarioLogado.getPessoa_id());
			} else {
				BigDecimal valorOld = produtoDAO.buscarPorID(produto.getId()).getValorTotal();
				if (getValorDisponivel().add(valorOld).subtract(produto.getValorTotal()).compareTo(new BigDecimal(0)) < 0) {
					context.addMessage(null, new FacesMessage("Atenção!",
							"O valor do produto é maior que o valor disponível no serviço."));
					tabSelect = 0;
					return;
				}
				produtoDAO.alterar(produto, usuarioLogado.getPessoa_id());
			}
			produto = null;
	    	previsoesUso = null;
	    	
	    	context.addMessage(null, new FacesMessage("Sucesso!",
					"Produto cadastrado com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Atenção!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void salvarGrupo() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			grupo.setProdutoPai(produtoDAO.buscarPorID(idGrupoPai));
			if (grupo.getId() == 0) {
				grupo.setGrupo(true);
				grupo.setCronograma(null);
				produtoDAO.inserir(grupo, usuarioLogado.getPessoa_id());
			} else {
				produtoDAO.alterar(grupo, usuarioLogado.getPessoa_id());
			}
			grupo = null;
			previsoesUso = null;			
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Grupo atualizado com sucesso."));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void selecionarProduto(Produto produtoSelec) {
		if(produtoSelec.isGrupo()){
			grupo = produtoSelec;
			tabSelect = 1;
		}else{
			produto = produtoSelec;
			previsoesUso = produtoDAO.obterPrevisoesUso(produto);
			produto.setCronograma(previsoesUso);
			tabSelect = 0;
		}
	}
	public void removerProduto(Produto produto) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(produto.isGrupo()){
				if(produto.getProdutosfilho().size() > 0){
					context.addMessage(null, new FacesMessage("Erro!",
							"O Grupo não pode ser excluído pois possui produtos filhos cadastrados."));
					return;
				}
			}else{
				if(produtoDAO.temMedicoes(produto)){
					context.addMessage(null, new FacesMessage("Erro!",
							"O produto não pode ser excluído pois ja possui medições cadastradas."));
					return;
				}
				produtoDAO.removerCronograma(produto);
			}
			produtoDAO.deletarPorId(produto.getId(), usuarioLogado.getPessoa_id());
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Produto excluído com sucesso."));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Erro!",
					"Por favor, contate o administrador do sistema."));
		}
	}
	public void distribuirUsoFuncao() {
		BigDecimal qtdUso = (produto.getQuantidade().divide(new BigDecimal(previsoesUso.size()), BigDecimal.ROUND_UP));
		for (Iterator<PrevisaoUso> iterator = previsoesUso.iterator(); iterator
				.hasNext();) {
			PrevisaoUso previsao = (PrevisaoUso) iterator.next();
			previsao.setQuantidade(qtdUso);
		}
	}

	
	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}
	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
			produto.setServico(servicoSelecionado);
		}
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<PrevisaoUso> getPrevisoesUso() {
		if (previsoesUso == null) {
			previsoesUso = produtoDAO.gerarPrevisoesUso(produto);
		}

		Collections.sort(previsoesUso, new Comparator<PrevisaoUso>() {
			public int compare(PrevisaoUso p1, PrevisaoUso p2) {
				Calendar dataP1 = p1.getPeriodoMedicao().getDataDe();
				Calendar dataP2 = p2.getPeriodoMedicao().getDataDe();
				return dataP1.before(dataP2) ? -1 : (dataP1.after(dataP2) ? +1
						: 0);
			}
		});

		return previsoesUso;
	}
	public void setPrevisoesUso(List<PrevisaoUso> previsoesUso) {
		this.previsoesUso = previsoesUso;
	}
	public List<Produto> getProdutos() {
		produtos = new ArrayList<Produto>();
		produtos.addAll(produtoDAO.buscarTodosPorServico(servicoSelecionado));
		
		Collections.sort(produtos, new Comparator<Produto>() {
			public int compare(Produto p1, Produto p2) {
				String codigoP1 = p1.getCodigo();
				String codigoP2 = p2.getCodigo();
				return codigoP1.compareToIgnoreCase(codigoP2);
			}
		});
		
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public int getIdProdutoPai() {
		return idProdutoPai;
	}
	public void setIdProdutoPai(int idProdutoPai) {
		this.idProdutoPai = idProdutoPai;
	}
	public int getTabSelect() {
		return tabSelect;
	}
	public void setTabSelect(int tabSelect) {
		this.tabSelect = tabSelect;
	}
	public BigDecimal getValorDisponivel(){
		valorDisponivel = valorDisponivel.add(servicoDAO.getValorDisponivel(servicoSelecionado));
		return valorDisponivel;
	}
	public void setValorDisponivel(BigDecimal valorDisponivel){
		this.valorDisponivel = valorDisponivel;
	}
	public List<Produto> getGrupos() {
		grupos = produtoDAO.buscarTodosGruposPorServico(servicoSelecionado);
		return grupos;
	}
	public void setGrupos(List<Produto> grupos) {
		this.grupos = grupos;
	}

	public Produto getGrupo(){
		if (grupo == null) {
			grupo = new Produto();
			grupo.setServico(servicoSelecionado);
		}
		return grupo;
	}

	public void setGrupo(Produto grupo){
		this.grupo = grupo;
	}
	public int getIdGrupoPai(){
		return idGrupoPai;
	}
	public void setIdGrupoPai(int idGrupoPai){
		this.idGrupoPai = idGrupoPai;
	}

}
