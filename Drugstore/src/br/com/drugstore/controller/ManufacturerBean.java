package br.com.drugstore.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drugstore.dao.FabricanteDAO;
import br.com.drugstore.model.Fabricante;
import br.com.drugstore.util.FacesMyMessages;

@ManagedBean
@ViewScoped
public class ManufacturerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Instanciando o objeto no get
	private Fabricante fabricante;
	private FabricanteDAO dao= new FabricanteDAO();
	private List<Fabricante> list; 			//Conterá todos os dados	
	private List<Fabricante> listFiltered; //Irá armazenar dados do filtro
	//Campos que receberei param
	private String acao;
	private Long id;
	
	public void init() {
		try {
			list= dao.selectAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			System.out.println(fabricante);
			dao.save(fabricante);
			FacesMyMessages.addSuccessfully("Successfully!");
			clearFields();
		} catch (Exception e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void update() {
		System.out.println(fabricante);
		try {
			dao.update(fabricante);
			FacesMyMessages.addSuccessfully("Updating successfully!");
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void delete() {
		System.out.println(fabricante);
		try {
			dao.delete(fabricante);
			FacesMyMessages.addSuccessfully("Deleted successfully!");
		} catch (RuntimeException e) {
			FacesMyMessages.addError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadRegister() {
		try {
			// Substituindo getParam por tag viewParam e já injetando em um campo aqui
			// String param= FacesUtil.getParam("manuId"); //definido na view
			// acao= FacesUtil.getParam("acao");

			if (id != null) {
				fabricante= dao.select(id);
			}
			//Se for null, é porque quero salvar o objeto, assim instancio o objeto
			else {
				fabricante= new Fabricante();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public void clearFields() {
		fabricante= new Fabricante();
	}
	
	public Fabricante getFabricante() {
		// if (fabricante == null) {
		// clearFields();
		// }
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getList() {
		return list;
	}
	
	public void setList(List<Fabricante> list) {
		this.list = list;
	}
	
	public List<Fabricante> getListFiltered() {
		return listFiltered;
	}
	
	public void setListFiltered(List<Fabricante> listFiltered) {
		this.listFiltered = listFiltered;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
