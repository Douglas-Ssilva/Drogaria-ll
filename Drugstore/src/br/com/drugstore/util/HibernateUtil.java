package br.com.drugstore.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	// Cria-se a fábrica de sessões
	private static final SessionFactory session = buildSessionFactory();

	// Constroi a fábrica de sessões
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory a partir hibernate.cfg.xml
			Configuration configuration= new Configuration();
			configuration.configure(); //busca o hibernate.cfg
			
			ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			SessionFactory factory= configuration.buildSessionFactory(serviceRegistry);
			
			return factory;
			
//Da documentação estava incorreto:		return new Configuration().configure().buildSessionFactory(new StandardServiceRegistryBuilder().build());
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.out.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return session;
	}
}
