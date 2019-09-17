package br.com.drugstore.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drugstore.dao.ItemDAO;
import br.com.drugstore.dao.ProdutoDAO;
import br.com.drugstore.dao.VendasDAO;
import br.com.drugstore.model.Itens;
import br.com.drugstore.model.Produto;
import br.com.drugstore.model.Vendas;

public class TestItens {
	private ItemDAO dao= new ItemDAO();
	private Itens itens= new Itens();
	private VendasDAO daoV= new VendasDAO();
	private ProdutoDAO daoP= new ProdutoDAO();
	
	@Test
	@Ignore
	public void insert() {
		Vendas venda = daoV.select(3L);
		Produto produto = daoP.select(3L);
		itens.setProduto(produto);
		itens.setVendas(venda);
		itens.setQuantidade(10);
		itens.setValorParcial(new BigDecimal(900.89D));
		dao.save(itens);
	}
	
	@Test
	@Ignore
	public void select() {
		Itens itens2 = dao.select(3L);
		System.out.println(itens2);
	}
	
	@Test
	@Ignore
	public void selectAll() {
		List<Itens> all = dao.selectAll();
		for (Itens itens : all) {
			System.out.println(itens);
		}
	}
	
	@Test
	@Ignore
	public void update() {
		Itens i = dao.select(3L);
		System.out.println("Before updating: " + i);
		Produto produto = daoP.select(2L);
		Vendas vendas = daoV.select(4L);
		i.setQuantidade(100);
		i.setValorParcial(new BigDecimal(150.25D));
		i.setProduto(produto);
		i.setVendas(vendas);
		dao.update(i);
		Itens i2 = dao.select(3L);
		System.out.println("After updating: " + i2);
	}
	
	@Test
	@Ignore
	public void delete() {
		Itens i = dao.select(1L);
		dao.delete(i);
		
	}
}
