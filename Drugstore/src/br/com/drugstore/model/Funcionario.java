package br.com.drugstore.model;

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

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="tbl_funcionario")
@NamedQueries({@NamedQuery(name="Funcionario.selectAll", query="select funcionario from Funcionario funcionario"),
				@NamedQuery(name="Funcionario.select", query="select funcionario from Funcionario funcionario where funcionario.id= :id"),
				@NamedQuery(name="Funcionario.authenticate", query="select f from Funcionario f where cpf= :cpf and senha= :senha")})
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="fun_id")
	private Long id;
	
	@NotEmpty(message="Name is required!")
	@Column(name="fun_nome", length= 50, nullable= false)
	private String nome;
	
	@CPF
	@Column(name="fun_cpf", length = 14, nullable= false, unique= true) //Unique falo que não pode-se repetir
	private String cpf;
	
	@NotEmpty(message="Password is empty!")
	@Size(max= 32, min= 3, message="Range the password (3-32)")
	@Column(name="fun_senha", length= 32, nullable= false)
	private String senha;
	
	@NotEmpty(message="Function is empty!")
	@Column(name="fun_funcao", length= 50, nullable= false)
	private String funcao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", funcao=" + funcao
				+ "]";
	}
	
	

}
