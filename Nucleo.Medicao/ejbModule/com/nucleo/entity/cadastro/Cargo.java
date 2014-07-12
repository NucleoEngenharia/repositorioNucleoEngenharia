package com.nucleo.entity.cadastro;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.medicao.Mobilizacao;

@Entity
@SequenceGenerator(allocationSize = 1, name = "seqCargo", sequenceName = "SEQ_CARGO")
public class Cargo extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCargo")
	private int id;

	@ManyToOne(optional = false)
	private Servico servico;

	@OneToMany(mappedBy = "cargo", targetEntity = PrevisaoUso.class)
	private List<PrevisaoUso> histograma;

	@OneToMany(mappedBy = "cargo", targetEntity = Mobilizacao.class)
	private List<Mobilizacao> mobilizacoes;

	@Column(nullable = false, length = 1500)
	private String funcaoMD;

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal valorVenda;

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal valorContratacaoDe;

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal valorContratacaoAte;

	@Column(nullable = false, precision = 18, scale = 4)
	private BigDecimal quantidadeUso;

	@Transient
	private BigDecimal valorTotal;

	@Transient
	private BigDecimal mediaUsoPeriodo;

	@Transient
	private BigDecimal totalJaMedido;
	
	public Cargo() {
		histograma = new ArrayList<PrevisaoUso>();
		mobilizacoes = new ArrayList<Mobilizacao>();
	}

	public BigDecimal getTotalJaMedido(){
		try {
			totalJaMedido = BigDecimal.ZERO;
			for (Mobilizacao mobilizacao : mobilizacoes) {
				totalJaMedido = totalJaMedido.add(mobilizacao.getTotalJaMedido());
			}
		} catch (Exception e) {
			System.out.println("Falta as mobilizações na classe Cargo");
		}
		return totalJaMedido;
	}
	public BigDecimal getMediaUsoPeriodo() {
		if (quantidadeUso == null || quantidadeUso.equals(BigDecimal.ZERO))
			return BigDecimal.ZERO;

		int qtdPeriodos = servico.getProjeto().getPeriodosMedicao().size();
		mediaUsoPeriodo = quantidadeUso.divide(
				BigDecimal.valueOf(qtdPeriodos), BigDecimal.ROUND_UP); 
		
		return mediaUsoPeriodo;
	}
	public BigDecimal getValorTotal() {
		if (valorVenda == null || quantidadeUso == null)
			return BigDecimal.ZERO;
		if (valorVenda.equals(BigDecimal.ZERO)
				|| quantidadeUso.equals(BigDecimal.ZERO))
			return BigDecimal.ZERO;

		valorTotal = valorVenda.multiply(quantidadeUso, MathContext.UNLIMITED);
		return valorTotal;
	}
	
	/*public BigDecimal getSaldoDisponivel() {
		saldoDisponivel = getValorTotal();
		if (mobilizacoes.size() == 0)
			return saldoDisponivel;

		for (Mobilizacao mobilizazao : mobilizacoes) {
			if (mobilizazao.getMedicoes().size() == 0)
				continue;
			for (MedicaoEquipe medicao : mobilizazao.getMedicoes()) {
				saldoDisponivel = saldoDisponivel.subtract(
						medicao.getValorMedido(), MathContext.UNLIMITED);
			}
		}

		return saldoDisponivel;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFuncaoMD() {
		return funcaoMD;
	}
	public void setFuncaoMD(String funcaoMD) {
		this.funcaoMD = funcaoMD;
	}
	public BigDecimal getQuantidadeUso() {
		return quantidadeUso;
	}
	public void setQuantidadeUso(BigDecimal quantidadeUso) {
		this.quantidadeUso = quantidadeUso;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public BigDecimal getValorContratacaoDe() {
		return valorContratacaoDe;
	}
	public void setValorContratacaoDe(BigDecimal valorContratacaoDe) {
		this.valorContratacaoDe = valorContratacaoDe;
	}
	public BigDecimal getValorContratacaoAte() {
		return valorContratacaoAte;
	}
	public void setValorContratacaoAte(BigDecimal valorContratacaoAte) {
		this.valorContratacaoAte = valorContratacaoAte;
	}
	public List<PrevisaoUso> getHistograma() {
		return histograma;
	}
	public void setHistograma(List<PrevisaoUso> histograma) {
		this.histograma = histograma;
	}
	public List<Mobilizacao> getMobilizacoes() {
		return mobilizacoes;
	}
	public void setMobilizacoes(List<Mobilizacao> mobilizacoes) {
		this.mobilizacoes = mobilizacoes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null)return false;
		if(!(o instanceof Cargo)) return false;
		
		Cargo other = (Cargo) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}	

	
	
}
