package br.com.drugstore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drugstore.model.Fabricante;
import br.com.drugstore.util.HibernateUtil;

public class FabricanteDAO {

	public void save(Fabricante fabricante) {
		// Pegando uma sessão da fabrica, pego uma sessão aberta e a armazeno
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction(); // começe a transação
			session.save(fabricante);

			transaction.commit(); // confirmando a transação
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback(); // desfaça o que tentou
			}
			throw e; // lançando a exception
		} finally {
			// Independete do que aconteça, feche a sesssão
			session.close();
		}

		// preciso saber se a transação foi aberta
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Fabricante> list = null;

		try {
			Query query = session.getNamedQuery("Fabricante.selectAll");
			list = query.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return list;
	}

	public Fabricante select(Long cod) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabricante = null;
		try {
			Query query = session.getNamedQuery("Fabricante.select");
			query.setLong("id", cod); // Nome do param da namedQuery e variavel local
			fabricante = (Fabricante) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return fabricante;
	}

	// É útil quando tenho todos os atributos preenchidos
	public void delete(Fabricante fabricante) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(fabricante);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	// É útil quando não tenho todos os atributos not null preenchidos
	// public void delete(Long id) {
	// Session session = HibernateUtil.getSessionFactory().openSession();
	// Transaction transaction = null;
	//
	// try {
	// transaction = session.beginTransaction();
	// Fabricante f = select(id);
	// session.delete(f);
	// transaction.commit();
	// } catch (RuntimeException e) {
	// if (transaction != null) {
	// transaction.rollback();
	// }
	// throw e;
	// } finally {
	// session.close();
	// }
	// }

	//É útil quando tenho o objeto preenchido, posso validar a tela p nn deixar campos nulos
	public void update(Fabricante fabricante) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction transaction= null;
		
		try {
			transaction= session.beginTransaction();
			session.update(fabricante);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null) {
					transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	//Quando não tenh todos os campos preenchidos
	// public void update2(Fabricante fabricante) {
	// Session session= HibernateUtil.getSessionFactory().openSession();
	// Transaction transaction= null;
	//
	// try {
	// transaction= session.beginTransaction();
	// //Objeto persistente / Transiente
	// Fabricante fabricante2 = select(fabricante.getId());
	// fabricante2.setDescricao(fabricante.getDescricao());
	// session.update(fabricante2);
	// transaction.commit();
	// } catch (RuntimeException e) {
	// if(transaction != null) {
	// transaction.rollback();
	// }
	// throw e;
	// }finally {
	// session.close();
	// }
	// }

}
