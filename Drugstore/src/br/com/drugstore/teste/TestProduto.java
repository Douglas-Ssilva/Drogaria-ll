package br.com.drugstore.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drugstore.dao.FabricanteDAO;
import br.com.drugstore.dao.ProdutoDAO;
import br.com.drugstore.model.Fabricante;
import br.com.drugstore.model.Produto;

public class TestProduto {
	private ProdutoDAO dao= new ProdutoDAO();
	private Produto produto= new Produto();
	private FabricanteDAO daoF= new FabricanteDAO();
	
	
	@Test
	@Ignore
	public void insert() {
		Fabricante fabricante = daoF.select(4L);
		produto.setDescricao("Eno");
		produto.setFabricante(fabricante);
		produto.setQuantidade(3);
		produto.setPreco(new BigDecimal(33.55D));
		dao.save(produto);
	}
	
	@Test
	@Ignore
	public void select() {
		Produto produto2 = dao.select(3L);
		System.out.println(produto2);
	}
	
	@Test
	@Ignore
	public void selectAll() {
		List<Produto> list = dao.selectAll();
		for (Produto p : list) {
			System.out.println(p);
		}
	}
	
	@Test
	@Ignore
	public void update() {
		Produto produto2 = dao.select(1L);
		produto2.setDescricao("Update");
		produto2.setPreco(new BigDecimal(50.02D));
		produto2.setQuantidade(2);
		dao.update(produto2);
	}

	@Test
	public void delete() {
		Produto produto2 = dao.select(1L);
		dao.delete(produto2);
		
	}




}
