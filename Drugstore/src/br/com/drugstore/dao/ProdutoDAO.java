package br.com.drugstore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drugstore.model.Produto;
import br.com.drugstore.util.HibernateUtil;

public class ProdutoDAO {

	public void save(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(produto);
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

	public Produto select(Long cod) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Produto produto = null;

		try {
			Query query = session.getNamedQuery("Produto.select");
			query.setLong("id", cod);
			produto = (Produto) query.uniqueResult();
		} catch (RuntimeException e) {

			throw e;
		} finally {
			session.close();
		}
		return produto;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Produto> list = null;
		try {
			Query query = session.getNamedQuery("Produto.selectAll");
			list = query.list();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return list;
	}

	public void update(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(produto);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	public void delete(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(produto);
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
