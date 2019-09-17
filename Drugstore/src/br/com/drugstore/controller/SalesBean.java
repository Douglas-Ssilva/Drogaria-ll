package br.com.drugstore.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.drugstore.dao.FuncionarioDAO;
import br.com.drugstore.dao.ItemDAO;
import br.com.drugstore.dao.ProdutoDAO;
import br.com.drugstore.dao.VendasDAO;
import br.com.drugstore.model.Funcionario;
import br.com.drugstore.model.Itens;
import br.com.drugstore.model.Produto;
import br.com.drugstore.model.Vendas;
import br.com.drugstore.util.FacesMyMessages;
import br.com.drugstore.vendaFilter.SalesFilter;

@ManagedBean
@ViewScoped
public class SalesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProdutoDAO dao = new ProdutoDAO();
	private List<Produto> list;
	private List<Produto> listFiltered;
	private List<Itens> itens;
	private Vendas vendas;
	
	private SalesFilter filter;
	private List<Vendas> listFilterData;
	

	//Injetando o employee
	@ManagedProperty(value="#{loginBean}") //nome do MB como está instanciado no tomcat
	private LoginBean bean;

	public void loadProducts() {
		try {
			list = dao.selectAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void addProduct(Produto p) {
		int index = -1;

		Itens item = new Itens();
		for (int i = 0; i < itens.size() && index < 0; i++) {

			if (itens.get(i).getProduto().equals(p)) { // Se já existir o produto
				index = i;
				item.setProduto(p);
				item.setQuantidade(itens.get(i).getQuantidade() + 1);
				item.setValorParcial(p.getPreco().multiply(new BigDecimal(item.getQuantidade())));
				itens.set(i, item);
			}
		}
		if (index < 0) {
			item.setProduto(p);
			item.setValorParcial(p.getPreco());
			item.setQuantidade(1);
			itens.add(item);
		}
		//Somando ao valor total cada produto adicionado, add cada produto por vez
		vendas.setValorTotal(vendas.getValorTotal().add(p.getPreco()));
		vendas.setQuantidadeTotal(vendas.getQuantidadeTotal() + 1);//como a cada clique, ele passa aqui, posso add + 1
	}
	
	public void delete(Itens item) {
		int index = -1;
		for (int i = 0; i < itens.size() && index < 0; i++) {
			Itens itemTemp= itens.get(i);
			
			//Compara os produtos, acho o índice
			if (itemTemp.getProduto().equals(item.getProduto())) {
				index= i;
			}
		}
		if (index > -1) {
			itens.remove(index);
			//Pego o item p remover aqui pois remove todos os produtos
			vendas.setValorTotal(vendas.getValorTotal().subtract(item.getValorParcial()));
			vendas.setQuantidadeTotal(vendas.getQuantidadeTotal() - item.getQuantidade());
		}
	}
	
	public void salesData() {
		vendas.setHorario(Calendar.getInstance().getTime());
		FuncionarioDAO dao= new FuncionarioDAO();
		Funcionario funcionario = dao.select(bean.getFuncionario().getId());
		vendas.setFuncionario(funcionario);
	}
	
	public void saveSalesBanco() {
		try {
			VendasDAO dao= new VendasDAO();
			Long idSalesSave = dao.save(vendas);
			Vendas vendas2 = dao.select(idSalesSave);
			
			//Os dados dos itens foram setados acima
			for (Itens item : itens) {
				item.setVendas(vendas2);
				ItemDAO dao2= new ItemDAO();
				dao2.save(item);
			}
			
			//Preciso zerar a venda p não aparecer na tela os dados da venda salva
			vendas.setValorTotal(new BigDecimal("0.00"));
			vendas.setQuantidadeTotal(0);
			itens= new ArrayList<>();
			FacesMyMessages.addSuccessfully("Sales successfully!");
			
		} catch (RuntimeException  e) {
			e.printStackTrace();
			FacesMyMessages.addError(e.getMessage());
		}
	}

	public void loadDates() {
		try {
			VendasDAO daoV= new VendasDAO();
			listFilterData= daoV.filterData(filter);
			for (Vendas v: listFilterData) {
				System.out.println(v);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public BarChartModel getModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		model.setLegendPosition("ne");
		model.setTitle("Vendas");
		
		ChartSeries series= new ChartSeries();
		series.setLabel("Vendas");
		List<Itens> list2 = getVendasList();
		
		for (int i = 0; i < list2.size(); i++) {
			series.set(list2.get(i).getVendas().getFuncionario().getNome(), list2.get(i).getQuantidade());
		}
		model.addSeries(series);
		return model;
	}
	
	public List<Itens> getVendasList() {
		ItemDAO dao = new ItemDAO();
		List<Itens> list = dao.selectAll();
		List<Itens> listTemp = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			boolean flag= false;
			Itens itemTemp = list.get(i);
			System.out.println(itemTemp.getVendas().getFuncionario().getNome());

			if (listTemp.size() == 0) {
				listTemp.add(itemTemp);
			} else {
				for (int j = 0; j < listTemp.size() && !flag; j++) {
					System.out.println(listTemp.get(j).getVendas().getFuncionario().getNome());
					if (itemTemp.getVendas().getFuncionario().equals(listTemp.get(j).getVendas().getFuncionario())) {
						itemTemp.setQuantidade(itemTemp.getQuantidade() + listTemp.get(j).getQuantidade());
						listTemp.set(j, itemTemp);
						flag= true;
					}
				}
				if (!flag) {
					listTemp.add(itemTemp);
				}
			}
		}
		return listTemp;
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

	public List<Itens> getItens() {
		if (itens == null) {
			itens = new ArrayList<>();
		}
		return itens;
	}

	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}

	public Vendas getVendas() {
		if (vendas == null) {
			vendas= new Vendas();
			//Pra não dar nullPointer no objeto BigDecimal
			vendas.setValorTotal(new BigDecimal("0.00"));
			vendas.setQuantidadeTotal(0);
		}
		return vendas;
	}

	public void setVendas(Vendas vendas) {
		this.vendas = vendas;
	}

	public LoginBean getBean() {
		return bean;
	}

	public void setBean(LoginBean bean) {
		this.bean = bean;
	}

	public SalesFilter getFilter() {
		if (filter == null) {
			filter= new SalesFilter();
		}
		return filter;
	}
	
	public void setFilter(SalesFilter filter) {
		this.filter = filter;
	}
	
	public List<Vendas> getListFilterData() {
		if (listFilterData == null)	 {
			listFilterData= new ArrayList<>();
		}
		return listFilterData;
	}
	
	public void setListFilterData(List<Vendas> listFilterData) {
		this.listFilterData = listFilterData;
	}
}
