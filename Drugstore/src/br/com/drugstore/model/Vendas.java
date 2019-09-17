
package br.com.drugstore.model;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="tbl_vendas")
@NamedQueries({@NamedQuery(name="Vendas.select", query="select v from Vendas v where v.id= :id"), 
			@NamedQuery(name="Vendas.selectAll", query="select v from Vendas v")})
public class Vendas {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="ven_id")
	private Long id;
	
	@Column(name="ven_horario", nullable= false)
	@Temporal(value= TemporalType.TIMESTAMP)//Informo p banco que é um campo data, timestamp guardará hora e data
	private Date horario;
	
	@Column(name="ven_valorTotal", precision= 7, scale= 2, nullable= false)
	private BigDecimal valorTotal;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="fk_fun_ven_id" ,referencedColumnName="fun_id", nullable= false)
	private Funcionario funcionario;

	//Outra forma de controlar a quantidade total de itens, já que esqueci de colocar esse atributo
	@Transient
	private Integer quantidadeTotal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}
	
	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
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
		Vendas other = (Vendas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendas [id=" + id + ", horario=" + horario + ", valorTotal=" + valorTotal + ", funcionario="
				+ funcionario + "]";
	}
	
	

}
