package br.com.drugstore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drugstore.model.Vendas;
import br.com.drugstore.util.HibernateUtil;
import br.com.drugstore.vendaFilter.SalesFilter;

public class VendasDAO {

	//Precisa retornar o ID da última venda salva p eu salvar os itens
	public Long save(Vendas venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id= null;
		try {
			transaction = session.beginTransaction();
			//Pra eu saber qual foi a última venda salva
			id = (Long) session.save(venda);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return id;
	}

	public Vendas select(Long cod) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Vendas vendas = null;
		try {
			Query query = session.getNamedQuery("Vendas.select");
			query.setLong("id", cod);
			vendas = (Vendas) query.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return vendas;
	}

	@SuppressWarnings("unchecked")
	public List<Vendas> selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Vendas> list = null;
		try {
			Query query = session.getNamedQuery("Vendas.selectAll");
			list = query.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return list;
	}

	public void update(Vendas venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(venda);
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

	public void delete(Vendas vendas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(vendas);
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
	
	@SuppressWarnings("unchecked")
	public List<Vendas> filterData(SalesFilter filter){
		List<Vendas> list= null;
		StringBuilder builder= new StringBuilder();
		builder.append("select v from Vendas v ");
		
		if (filter.getDataInicial() != null && filter.getDataFinal() != null) {
			builder.append("where v.horario between :dataInicial and :dataFinal ");
		}
		builder.append("order by v.horario ");
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery(builder.toString());
			if (filter.getDataInicial() != null && filter.getDataFinal() != null) {
				query.setDate("dataInicial", filter.getDataInicial());
				query.setDate("dataFinal", filter.getDataFinal());
			}
			list= query.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
		return list;
	}

}
