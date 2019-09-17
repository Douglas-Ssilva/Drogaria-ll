package br.com.drugstore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drugstore.model.Itens;
import br.com.drugstore.util.HibernateUtil;

public class ItemDAO {

	public void save(Itens itens) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(itens);
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

	public Itens select(Long cod) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Itens itens = null;
		try {
			Query query = session.getNamedQuery("Itens.select");
			query.setLong("id", cod);
			itens = (Itens) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return itens;
	}

	@SuppressWarnings("unchecked")
	public List<Itens> selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Itens> list = null;
		try {
			Query query = session.getNamedQuery("Itens.selectAll");
			list = query.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return list;
	}

	public void update(Itens item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(item);
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

	public void delete(Itens item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(item);
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

}
