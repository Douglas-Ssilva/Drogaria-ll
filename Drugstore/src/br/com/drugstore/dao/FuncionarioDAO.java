package br.com.drugstore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drugstore.model.Funcionario;
import br.com.drugstore.util.HibernateUtil;

public class FuncionarioDAO {

	public void save(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(funcionario);
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

	public Funcionario select(Long cod) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;

		try {
			Query query = session.getNamedQuery("Funcionario.select");
			query.setLong("id", cod);
			funcionario = (Funcionario) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return funcionario;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> list = new ArrayList<>();
		try {
			Query query = session.getNamedQuery("Funcionario.selectAll");
			list = query.list();
		} catch (RuntimeException e) {

			throw e;
		} finally {
			session.close();
		}
		return list;
	}

	public void update(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(funcionario);
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

	public void delete(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction= session.beginTransaction();
			session.delete(funcionario);
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
	
	public Funcionario authenticate(String cpf, String senha) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario= null;
		try {
			Query query = session.getNamedQuery("Funcionario.authenticate");
			query.setString("cpf", cpf);
			query.setString("senha", senha);
			funcionario= (Funcionario) query.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return funcionario;
	}

}
