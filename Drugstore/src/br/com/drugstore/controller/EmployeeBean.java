package br.com.drugstore.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drugstore.dao.FuncionarioDAO;
import br.com.drugstore.model.Funcionario;
import br.com.drugstore.util.FacesMyMessages;

@ManagedBean
@ViewScoped
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private FuncionarioDAO dao= new FuncionarioDAO();
	private List<Funcionario> list;
	private List<Funcionario> listFiltered;
	private Long id;
	private String acao;

	public void init() {
		try {
			list= dao.selectAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	//Se for add, id vira nulo, assim instacio o objeto. Se for editar ou excluir, ID servirá p pegar o objeto do banco e preencher
	//os campos
	public void preparedActions() {
		try {
			if (id != null) {
				funcionario= dao.select(id);
			}else {
				clearEmployee();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			//Passando a senha p MD5
			funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
			dao.save(funcionario);
			FacesMyMessages.addSuccessfully("Add Successfully!");
//			clearEmployee();
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void edit() {
		try {
			funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
			dao.update(funcionario);
			FacesMyMessages.addSuccessfully("Updating successfully!");
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			dao.delete(funcionario);
			FacesMyMessages.addSuccessfully("Deleted successfully!");
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void clearEmployee() {
		funcionario= new Funcionario();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getList() {
		return list;
	}

	public void setList(List<Funcionario> list) {
		this.list = list;
	}

	public List<Funcionario> getListFiltered() {
		return listFiltered;
	}

	public void setListFiltered(List<Funcionario> listFiltered) {
		this.listFiltered = listFiltered;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAcao() {
		return acao;
	}
	
	public void setAcao(String acao) {
		this.acao = acao;
	}
}

