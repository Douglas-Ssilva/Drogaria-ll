package br.com.drugstore.teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drugstore.dao.FabricanteDAO;
import br.com.drugstore.dao.ItemDAO;
import br.com.drugstore.model.Fabricante;
import br.com.drugstore.model.Itens;
import br.com.drugstore.model.Vendas;

public class TestesFabricante {
	FabricanteDAO dao = new FabricanteDAO();

	@Test
	@Ignore
	public void save() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Biolab");
		Fabricante fabricante2 = new Fabricante();
		fabricante2.setDescricao("Tuts");

		dao.save(fabricante2);
		dao.save(fabricante);

	}

	@Test
	@Ignore
	public void selectAll() {
		List<Fabricante> list = dao.selectAll();
		for (Fabricante fabricante : list) {
			System.out.println(fabricante);
		}
	}

	@Test
	@Ignore
	public void select() {
		Fabricante select = dao.select(1L);
		System.out.println(select);
	}

	@Test
	@Ignore
	public void delete() {
		Fabricante f = dao.select(1L);
		if (f != null) {
			dao.delete(f);
		}
	}

	@Test
	@Ignore
	public void update() {
		// Fabricante f = dao.select(3L);
		// f.setDescricao("Achê");
		// dao.update(f);

		Fabricante fabricante = new Fabricante();
		fabricante.setId(4L);
		fabricante.setDescricao("Pharmaton");
		dao.update(fabricante);

	}

	@Test
//	@Ignore
	public void getVendasList() {
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
	}

	@Test
	@Ignore
	public void teste2() {
		List<String> list= Arrays.asList("Abel","Lucas","Satiago","Lucas","Satiago");
		List<String> list2= new ArrayList<>();
		
			for (int i = 0; i < list.size(); i++) {
				boolean flag= false;
				String string = list.get(i);
				if (list2.size() == 0) {
					list2.add(string);
				}else {
					for (int j = 0; j < list2.size(); j++) {
						if (string.equals(list2.get(j))) {
							list2.set(j, string+"2");
							flag= true;
						}
					}
					if (!flag) {
						list2.add(string);
					}
				}
				System.out.println(list2);
			}
		
	}

}
