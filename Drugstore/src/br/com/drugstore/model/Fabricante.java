package br.com.drugstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name= "tbl_fabricantes")
@NamedQueries({@NamedQuery(name="Fabricante.selectAll", query= "select fabricante from Fabricante fabricante"),
			   @NamedQuery(name="Fabricante.select", query="select fabricante from Fabricante fabricante where fabricante.id= :id")})
public class Fabricante implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="fab_id")
	private Long id;
	
	@Size(min= 3, max= 50, message="Range allowed (3 - 50)")
	@NotEmpty(message="This field is required!")
	@Column(name="fab_descricao", length= 50, nullable= false) //Tamanho do campo na tabela do banco, nullable digo que não será nulo, ou seja, campo obrigatório
	private String descricao;
	
	
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
		Fabricante other = (Fabricante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fabricante [id=" + id + ", descricao=" + descricao + "]";
	}

	
}
