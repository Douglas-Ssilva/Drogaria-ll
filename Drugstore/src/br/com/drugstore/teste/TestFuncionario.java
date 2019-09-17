package br.com.drugstore.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drugstore.dao.FuncionarioDAO;
import br.com.drugstore.model.Funcionario;
import junit.framework.Assert;

public class TestFuncionario {
	private FuncionarioDAO dao= new FuncionarioDAO();
	private Funcionario funcionario= new Funcionario();
	
	@Test
	@Ignore
	public void save() {
		for (int i = 0; i < 3; i++) {
			funcionario.setCpf(i+"22."+ i +"22.222-1"+ i);
			funcionario.setFuncao("Recepção"+ i);
			funcionario.setNome("Teste" + i);
			funcionario.setSenha("45"+ i);
			dao.save(funcionario);
		}
	}
	
	@Test
	@Ignore
	public void selectAll() {
		List<Funcionario> list = dao.selectAll();
		for (Funcionario funcionario : list) {
			System.out.println(funcionario);
		}
	}
	
	@Test
	@Ignore
	public void select() {
		Funcionario funcionario2 = dao.select(2L);
		System.out.println(funcionario2);
	}

	@Test
	@Ignore
	public void update() {
		Funcionario funcionario2 = dao.select(3L);
		funcionario2.setNome("Igor");
		funcionario2.setFuncao("Player");
		funcionario2.setCpf("999.999.999-99");
		funcionario2.setSenha("*/*/*/*/*/");
		dao.update(funcionario2);
	}
	
	@Test
	@Ignore
	public void delete() {
		Funcionario funcionario2 = dao.select(4L);
		dao.delete(funcionario2);
		
	}
	@Test
	public void authenticate() {
		Funcionario funcionario2 = dao.authenticate("474.659.420-12", "87847989");
//		System.out.println(funcionario2);
		Assert.assertNotNull(funcionario2); //Se for nulo, JUnit fica vermelho
		
	}
}
