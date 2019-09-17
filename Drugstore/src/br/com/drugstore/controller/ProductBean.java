package br.com.drugstore.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drugstore.dao.FabricanteDAO;
import br.com.drugstore.dao.ProdutoDAO;
import br.com.drugstore.model.Fabricante;
import br.com.drugstore.model.Produto;
import br.com.drugstore.util.FacesMyMessages;

@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto;
	private ProdutoDAO dao= new ProdutoDAO();
	private FabricanteDAO daoF= new FabricanteDAO();
	private List<Produto> list;
	private List<Fabricante> listFabricantes;
	private List<Produto> listFiltered;
	private Long id;
	private String acao;

	public void init() {
		try {
			list= dao.selectAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			dao.save(produto);
			FacesMyMessages.addSuccessfully("Saved successfully!");
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void preparedActions() {
		try {
				if (id != null) {
					produto= dao.select(id);
				}else {
					clearFields();
				}
				listFabricantes= daoF.selectAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public void edit() {
		try {
			dao.update(produto);
			FacesMyMessages.addSuccessfully("Updated successfully!");
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			dao.delete(produto);
			FacesMyMessages.addSuccessfully("Deleted successfully!");
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void clearFields() {
		produto= new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getList() {
		return list;
	}
	public void setList(List<Produto> list) {
		this.list = list;
	}
	public List<Produto> getListFiltered() {
		return listFiltered;
	}
	public void setListFiltered(List<Produto> listFiltered) {
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

	public List<Fabricante> getListFabricantes() {
		return listFabricantes;
	}

	public void setListFabricantes(List<Fabricante> listFabricantes) {
		this.listFabricantes = listFabricantes;
	}
}
