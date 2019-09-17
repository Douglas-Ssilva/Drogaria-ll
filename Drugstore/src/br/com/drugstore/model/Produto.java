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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "tbl_produtos")
@NamedQueries({@NamedQuery(name="Produto.select", query= "select p from Produto p where p.id= :id"),
	           @NamedQuery(name="Produto.selectAll", query="select p from Produto p")})
public class Produto {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="pro_id")
	private Long id;
	
	@NotEmpty(message="Description is empty!")
	@Size(min= 3, max= 50, message="Description must contain between 3 and 50 characteres!")
	@Column(name="pro_descricao", length= 50, nullable= false)
	private String descricao;
	
	//Consigo controlar com mais facilidade quantas casas decimais pós vírgula
	@NotNull(message="Price is null!")
	@DecimalMax(value="99999.99", message="Value max is 99999.99!")
	@DecimalMin(value="0.01", message="Value min is 0.0.1")
	@Digits(integer= 5, fraction= 2, message="Pattern 555.55")
	@Column(name="pro_preco", precision=7 , scale= 2, nullable= false ) //precision, quantos dígitos total, scale quantos após ,
	private BigDecimal preco;
	
	@NotNull(message="Amount is null!")
	@Min(value= 1, message="The quantity must be greater than 1!")
	@Max(value= 1000, message="The quantity not must be greater than 1000!")
	@Column(name="pro_quantidade", nullable= false)//pegará o tamanho padrão do banco
	private Integer quantidade;
	
	//Muitos produtos possuem um fab
	@NotNull(message="Manufacturer is empty!")
	@ManyToOne(fetch= FetchType.EAGER) //Eager --> quando carregar o produto, irá carregar os fab juntos. Lazer --> só carrega os produtos, fab depois
	@JoinColumn(name="fk_fab_pro_id", referencedColumnName="fab_id", nullable= false) //Nome na tabela filha, e nome da PK na pai
	private Fabricante fabricante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fabricante=" + fabricante + "]";
	}
	
	

}
