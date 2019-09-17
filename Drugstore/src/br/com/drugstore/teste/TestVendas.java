package br.com.drugstore.teste;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drugstore.dao.FuncionarioDAO;
import br.com.drugstore.dao.VendasDAO;
import br.com.drugstore.model.Funcionario;
import br.com.drugstore.model.Vendas;
import br.com.drugstore.vendaFilter.SalesFilter;

public class TestVendas {
	private VendasDAO dao= new VendasDAO();
	private Vendas vendas= new Vendas();
	private FuncionarioDAO daoF= new FuncionarioDAO();
	
	@Test
	@Ignore
	public void insert() {
		Funcionario funcionario = daoF.select(1L);
		vendas.setFuncionario(funcionario);
//		vendas.setHorario(new Date());
		vendas.setHorario(Calendar.getInstance().getTime());
		vendas.setValorTotal(new BigDecimal(1.65D));
		dao.save(vendas);
	}

	@Test
	@Ignore
	public void select() {
		Vendas vendas2 = dao.select(3L);
		System.out.println(vendas2);
	}
	
	@Test
	@Ignore
	public void selectAll() {
		List<Vendas> all = dao.selectAll();
		for (Vendas vendas : all) {
			System.out.println(vendas);
		}
	}
	
	@Test
	@Ignore
	public void update() {
		Vendas v = dao.select(1L);
		System.out.println("Before updating: "+ v);
		FuncionarioDAO daoF= new FuncionarioDAO();
		Funcionario funcionario = daoF.select(5L);
		v.setHorario(Calendar.getInstance().getTime());
		v.setValorTotal(new BigDecimal(100.12D));
		v.setFuncionario(funcionario);
		dao.update(v);
		Vendas v2 = dao.select(1L);
		System.out.println("After updating: "+ v2);
		
	}
	@Test
	@Ignore
	public void delete() {
		Vendas v = dao.select(1L);
		dao.delete(v);
	}
	
	@Test
	@Ignore
	public void testeDate() throws ParseException {
		DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
		SalesFilter filter= new SalesFilter();
		filter.setDataFinal(format.parse("31/01/2019"));
		filter.setDataInicial(format.parse("04/02/2019"));
		VendasDAO dao= new VendasDAO();
		List<Vendas> list = dao.filterData(filter);
		for (Vendas vendas : list) {
			System.out.println(vendas);
		}
		
	}
	
}
