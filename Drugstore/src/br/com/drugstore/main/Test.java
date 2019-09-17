package br.com.drugstore.main;

import br.com.drugstore.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		//Carrega o contexto do hibernate
		HibernateUtil.getSessionFactory();
		//Limpou o pool de conexões, sempre dar close na fábrica de sessões
		HibernateUtil.getSessionFactory().close();

	}

}
