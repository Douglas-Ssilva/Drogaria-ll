package br.com.drugstore.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{
	//Ao encerrar o tomcat encerre a sessão
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		HibernateUtil.getSessionFactory().close();
	}
	
	//Ao iniciar o tomcat, inicie a sessão, pede uma sessão p fabrica
	@Override
	public void contextInitialized(ServletContextEvent e) {
		HibernateUtil.getSessionFactory().openSession();
	}

}
