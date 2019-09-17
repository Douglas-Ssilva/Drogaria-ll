package br.com.drugstore.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tbl_itens")
@NamedQueries({@NamedQuery(name="Itens.select", query="select i from Itens i where i.id= :id"), 
			   @NamedQuery(name="Itens.selectAll", query="select i from Itens i")})
public class Itens {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="iten_id")
	private Long id;
	
	@Column(name="iten_quantidade", nullable= false)
	private Integer quantidade;
	
	@Column(name="iten_valorParcial", precision= 7, scale= 2, nullable= false)
	private BigDecimal valorParcial;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="fk_ven_iten_id", referencedColumnName="ven_id", nullable= false)
	private Vendas vendas;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="fk_pro_iten_id", referencedColumnName="pro_id", nullable= false)
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public Vendas getVendas() {
		return vendas;
	}

	public void setVendas(Vendas vendas) {
		this.vendas = vendas;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itens other = (Itens) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Itens [id=" + id + ", quantidade=" + quantidade + ", valorParcial=" + valorParcial + ", vendas="
				+ vendas + ", produto=" + produto + "]";
	}

}
