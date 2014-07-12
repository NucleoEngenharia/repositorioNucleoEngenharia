package com.nucleo.entity.cadastro;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.medicao.MedicaoProduto;

/**
 * @author alysson.santos
 *
 */
@Entity
@SequenceGenerator(allocationSize = 1, name = "seqProduto", sequenceName = "SEQ_PRODUTO")
public class Produto extends CommomEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProduto")
	private int id;

	@ManyToOne
	private Servico servico;

	@ManyToOne
	private Produto produtoPai;

	@OneToMany(mappedBy = "produtoPai", fetch=FetchType.EAGER)
	private Set<Produto> produtosfilho;

	@OneToMany(mappedBy = "produto", targetEntity = PrevisaoUso.class, cascade = CascadeType.ALL)
	private List<PrevisaoUso> cronograma;

	@OneToMany(mappedBy = "produto", targetEntity = MedicaoProduto.class)
	private List<MedicaoProduto> medicoesProduto;

	private String unidade;
	private String codigo;
	
	@Column(length=1500)
	private String descricao;
	
	@Column(precision=18, scale=4)
	private BigDecimal quantidade;
	private boolean isGrupo;

	@Column(precision = 18, scale = 2)
	private BigDecimal valorVenda;
	@Column(precision = 18, scale = 2)
	private BigDecimal valorCompraDe;
	@Column(precision = 18, scale = 2)
	private BigDecimal valorCompraAte;

	@Transient
	private BigDecimal valorTotal;
	@Transient
	private BigDecimal totalJaMedido;
	
	public Produto() {
		cronograma = new ArrayList<PrevisaoUso>();
		medicoesProduto = new ArrayList<MedicaoProduto>();
		produtosfilho = new HashSet<Produto>();
	}

	public BigDecimal getTotalJaMedido() {
		try {
			totalJaMedido = BigDecimal.ZERO;
			for (MedicaoProduto medicao : medicoesProduto) {
				totalJaMedido = totalJaMedido.add(medicao.getValorMedido());
			}
		} catch (Exception e) {
			System.out.println("Falta as medições na classe Produto");
		}
		return totalJaMedido;
	}
	
	public BigDecimal getValorTotal() {
		valorTotal = new BigDecimal(0);
		if (isGrupo) {
			if (produtosfilho != null) {
				for (Produto prodFilho : produtosfilho) {
					if(prodFilho.isExcluido()) continue;
					valorTotal = valorTotal.add(prodFilho.getValorTotal(),
							MathContext.UNLIMITED);
				}
			}
		} else {
			if(valorVenda == null || quantidade == null) return BigDecimal.ZERO;
			if(valorVenda.equals(BigDecimal.ZERO) || quantidade.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
			
			valorTotal = valorVenda.multiply(quantidade, MathContext.UNLIMITED);
		}
		return valorTotal;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProdutoPai() {
		return produtoPai;
	}

	public void setProdutoPai(Produto produtoPai) {
		this.produtoPai = produtoPai;
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
	public Set<Produto> getProdutosfilho() {
		return produtosfilho;
	}

	public void setProdutosfilho(Set<Produto> produtosfilho) {
		this.produtosfilho = produtosfilho;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public List<PrevisaoUso> getCronograma() {
		return cronograma;
	}

	public void setCronograma(List<PrevisaoUso> cronograma) {
		this.cronograma = cronograma;
	}

	public List<MedicaoProduto> getMedicoesProduto() {
		return medicoesProduto;
	}

	public void setMedicoesProduto(List<MedicaoProduto> medicoesProduto) {
		this.medicoesProduto = medicoesProduto;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public BigDecimal getValorCompraDe() {
		return valorCompraDe;
	}
	public void setValorCompraDe(BigDecimal valorCompraDe) {
		this.valorCompraDe = valorCompraDe;
	}
	public BigDecimal getValorCompraAte() {
		return valorCompraAte;
	}
	public void setValorCompraAte(BigDecimal valorCompraAte) {
		this.valorCompraAte = valorCompraAte;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public boolean isGrupo() {
		return isGrupo;
	}
	public void setGrupo(boolean isGrupo) {
		this.isGrupo = isGrupo;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof Produto)) return false;
		
		Produto other = (Produto) o;
		if(other.getId() == id) return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return id * 32;
	}	
}
