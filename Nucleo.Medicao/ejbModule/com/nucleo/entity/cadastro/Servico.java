package com.nucleo.entity.cadastro;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.Enum.TipoServicoEnum;

@Entity
@SequenceGenerator(allocationSize=1, name="seqServico", sequenceName="SEQ_SERVICO")
public class Servico extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqServico")
	private int id;
    
	@ManyToOne(optional=false)
	private Projeto projeto;
	
	@OneToMany(mappedBy="servico", targetEntity=Cargo.class)
	private List<Cargo> cargos;
	
	@OneToMany(mappedBy="servico", targetEntity=Produto.class)
	private List<Produto> produtos;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataInicial;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataFim;
	
	private TipoServicoEnum tipo;
	
	@Column(length=1500)
	private String descricao;
	@Column(precision=18, scale=4)
	private BigDecimal quantidade;
	
	@Column(precision=18, scale=2)
	private BigDecimal valorUnitario;
	
	@Column(length=1500)
	private String observacao;
	
	@Transient
	private BigDecimal valorTotal;
	
	@Transient
	private BigDecimal totalJaMedido;
	
	public Servico(){
		this.quantidade = BigDecimal.ONE;
		cargos = new ArrayList<Cargo>();
		produtos = new ArrayList<Produto>();
	}
	
	public BigDecimal getTotalJaMedido(){
		totalJaMedido = BigDecimal.ZERO;
		try {
			if(tipo.equals(TipoServicoEnum.EQUIPE)){
				for (Cargo cargo : cargos) {
					totalJaMedido = totalJaMedido.add(cargo.getTotalJaMedido());
				}
			} else {
				for (Produto produto : produtos) {
					totalJaMedido = totalJaMedido.add(produto.getTotalJaMedido());
				}
			}
		} catch (Exception e) { }
		return totalJaMedido;
	}
	
	public BigDecimal getValorTotal() {
		if(valorUnitario != null && quantidade.compareTo(BigDecimal.ZERO) == 1)
			valorTotal = valorUnitario.multiply(quantidade, MathContext.UNLIMITED);
		return valorTotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public List<Cargo> getCargos() {
		return cargos;
	}
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Calendar getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Calendar getDataFim() {
		return dataFim;
	}
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	public TipoServicoEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoServicoEnum tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof Servico)) return false;
		
		Servico other = (Servico) o;
		if(id == other.getId()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}
	
}
